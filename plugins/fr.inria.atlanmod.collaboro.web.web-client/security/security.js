angular.module('security.service',[
	
	]).factory('security',['$http', '$q', '$location', '$modal',
	function($http, $q, $location, $modal)
	{
		
		function redirect(url)
		{
			url = url || '/';
			$location.path(url);
		}

		var loginDialog = null;

		function openLoginDialog()
		{

			if(!loginDialog)
			{
				loginDialog=$modal.open
				(
      				{
        				templateUrl:'security/login/form.tpl.html',
        				controller :  'LoginFormController'
      				}
      			);
			}
		}

		function closeLoginDialog(success)
		{
			if(loginDialog)
			{
				loginDialog.close(success);
				loginDialog=null;
				
				redirect('/project');
			}
		}

	

		
		var service=
		{


			showLogin: function()
			{
				openLoginDialog();
			},

			login: function(email, password)
			{

				
				var request=$http.post('http://localhost:8080/fr.inria.atlanmod.collaboro.web.servlets/login',{email: email, password: password});

				return request.then(
					function(response)
					{
						service.currentUser=response.data.user;
						if(service.isAuthenticated())
						{	
							closeLoginDialog(true);
						}
					}
				);
				
			},

			// Give up trying to login and clear the retry queue
      		cancelLogin: function()
      		{
      			closeLoginDialog(false);
      			//redirect();
      		},

      		logout:function()
      		{
      			service.currentUser=null;
      			redirect('/');
      		},

      		//Ask the backend to see if a user is already authenticated - this may be from a previous section
      		requestCurrentUser: function()
      		{
      			if(service.isAuthenticated())
      			{
      				return $q.when(service.currentUser);
      			}
      			else
      			{
      				
      				return $http.get('/current-user').then(function(response){
      					service.currentUser = response.data.user;
      					return service.currentUser;
      				})
      			}
      		},

			// Information about the current user
			currentUser:null,

			// Is the current user authenticated?
      		isAuthenticated: function()
      		{
        		return !!service.currentUser;
      		},
    
      		// Is the current user an adminstrator?
      		isAdmin: function()
      		{
      			return !!(service.currentUser && service.currentUser.admin);
      		}


		};

		return service;
	}

	]);
