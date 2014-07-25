angular.module('security.service', []).factory('security', ['$http', '$q', '$location', '$modal', '$cookieStore',
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
            templateUrl : 'security/partials/form.tpl.html',
            controller  : 'LoginFormController'
          }
        );
        console.log(loginDialog);
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
        var request = $http.post('http://localhost:8080/fr.inria.atlanmod.collaboro.web.servlets/login', { email: email, password: password, dsl: dsl});
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
        $http.post('http://localhost:8080/fr.inria.atlanmod.collaboro.web.servlets/logout').then(
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
          return $http.get('http://localhost:8080/fr.inria.atlanmod.collaboro.web.servlets/currentUser').then(
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
