angular.module('security.authorization', ['security.service'])

// This service provides guard methods to support AngularJS routes.
// You can add them as resolves to routes to require authorization levels
// before allowing a route change to complete
.provider('securityAuthorization', 
	{
		requireAuthenticatedUser: 
		['securityAuthorization', function(securityAuthorization)
			{
				return securityAuthorization.requireAuthenticatedUser();
			}
	    ],
	    
	    $get: ['security', 'securityRetryQueue', function(security,queue){
	    	var service = {
	    		//Require that there is an authenticated user
	    		//(use this in a route resolve to prevent non-autehnticated users from entering that route)
	    		requireAuthenticatedUser: function() {
	    			var promise = security.requestCurrentUser().then(function(userInfo){
	    				if(!security.isAuthenticated()) //Revisar en security que hace este metodo
	    				{
	    					//Related to the queu
	    					return queue.pushRetryFn('unauthenticated-client', service.requireAuthenticatedUser);
	    				}
	    			});
	    			return promise;
	    		}
	    	};

	    	return service;
	    }
	]
});

