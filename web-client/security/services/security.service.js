angular.module('security.service', ['security.retryQueue']).factory('security', ['$http', '$q', '$location', 'securityRetryQueue', '$modal',
	function($http, $q, $location, queue, $modal) {
		var loginDialog = null;

		function redirect(url) {
			url = url || '/';
			$location.path(url);
		}

		// Functions controlling the modal dialog
		function openLoginDialog() {
			if(!loginDialog) {
				loginDialog= $modal.open(
					{
        				templateUrl : 'security/partials/form.tpl.html',
        				controller  : 'LoginFormController'
      				}
      			)
			}
		}

		function closeLoginDialog(success) {
			if(loginDialog) {
				loginDialog.close(success);
				loginDialog = null;
				redirect('/project');
			}
		}

		function onLoginDialogClose(success) {
			if(success) {
				queue.retryAll();
			} else {
				queue.cancelAll();
				redirect();
			}
		}

		queue.onItemAddedCallbacks.push(
			function(retryItem) {
				if(queue.hasMore())	{
					service.showLogin();
				}
			}
		);

		// The public API of the service
		var service = {
			// Information about the current user
			currentUser : null,
			getLoginReason: function() {
				return queue.retryReason();
			},
			showLogin: function(){
				openLoginDialog();
			},
			login: function(email, password, dsl) {
				var request = $http.post('http://localhost:8080/fr.inria.atlanmod.collaboro.web.servlets/login', { email: email, password: password, dsl: dsl});
				return request.then(
					function(response) {
						console.log('headers from the response from login are ' + response.headers());
						//console.log('response from login is ' + response.headers('Set-Cookie'));
						service.currentUser = response.data.user;
						if(service.isAuthenticated()) {	
							closeLoginDialog(true);
						}
					}
				);
			},
			// Give up trying to login and clear the retry queue
      		cancelLogin: function() {
      			closeLoginDialog(false);
      			redirect();
      		},
      		// Logout the current user and redirect
      		logout:function() {
      			$http.post('http://localhost:8080/fr.inria.atlanmod.collaboro.web.servlets/logout').then(function(){
      				service.currentUser=null;
      				redirect('/');
      			});
      			
      		},
      		// Ask the backend to see if a user is already authenticated - this may be from a previous section
      		requestCurrentUser: function() {
      			if(service.isAuthenticated()) {
      				return $q.when(service.currentUser);
      			} else {
      				return $http.get('http://localhost:8080/fr.inria.atlanmod.collaboro.web.servlets/currentUser').then(function(response) {
      					service.currentUser = response.data.user;
      					return service.currentUser;
      				});
      			}
      		},
			// Is the current user authenticated?
      		isAuthenticated: function() {
        		return !!service.currentUser;
      		},
      		// Is the current user an adminstrator?
      		isAdmin: function() {
      			return !!(service.currentUser && service.currentUser.admin);
      		}
		};
		return service;
	}
]);
