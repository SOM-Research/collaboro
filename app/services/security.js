angular.module('collaboroServices').factory('securityService', ['$http', '$q', '$location', '$modal', '$cookieStore',
  function($http, $q, $location, $modal, $cookieStore) {
    var loginDialog = null;

    function redirect(url) {
      url = url || '/';
      $location.path(url);
    }

    // Functions controlling the modal dialog
    function openLoginDialog() {
      if(!loginDialog) {
        loginDialog = $modal.open(
          {
            templateUrl : 'app/partials/modal/login.html',
            controller  : 'LoginController'
          }
        );
      }
    }

    function closeLoginDialog(success) {
      if(loginDialog) {
        loginDialog.close(success);
        loginDialog = null;
        redirect('/project');
      }
    }

    /*function onLoginDialogClose(success) {
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
     );*/

    // The public API of the service
    var service = {
      // Information about the current user
      currentUser : null,
      currentDSL  : null,
      showLogin: function(){
        openLoginDialog();
      },
      login: function(email, password, dsl) {
        var request = $http.post(collaboroServletURL + '/login', { email: email, password: password, dsl: dsl});
        return request.then(
          function(response) {
            service.currentUser = response.data.user;
            service.currentDSL = dsl;
            service.loginReason = "Login Successful"
            $cookieStore.put('collaboro_session', { user : service.currentUser, dsl : service.currentDSL });
            if(service.isAuthenticated()) {
              closeLoginDialog(true);
            }
          }, function(response) {
            service.loginReason = "Login error"
          }
        );
      },
      // Give up trying to login
      cancelLogin: function() {
        closeLoginDialog(false);
        redirect();
      },
      // Logout the current user and redirect
      logout:function() {
        $http.post(collaboroServletURL + '/logout').then(
          function(){
            service.currentUser = null;
            service.currentDSL = null;
            redirect('/');
          }
        );
      },
      // Ask the backend to see if a user is already authenticated - this may be from a previous section
      requestCurrentUser: function() {
        if(service.isAuthenticated()) {
          return $q.when(service.currentUser);
        } else {
          redirect('/');
          return $http.get(collaboroServletURL + '/currentUser').then(
            function(response) {
              service.currentUser = response.data.user;
              redirect('/project');
              return service.currentUser;
            }, function(response) {
              if(response.status === 401) {
                return $q.when(service.showLogin());
              } else {
                service.currentUser = null;
                service.currentDSL = null;
              }
            }
          );
        }
      },
      // Is the current user authenticated?
      isAuthenticated: function() {
        return !!service.currentUser;
      }
    };
    return service;
  }
]);
