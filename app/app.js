'use strict';

//var collaboroServletURL = 'http://atlanmodexp.info.emn.fr:8800/collaboro';
var collaboroServletURL = 'http://localhost:8080/fr.inria.atlanmod.collaboro.web.servlets';

angular.module('collaboroApp', [
	'ngRoute',
	'ngAnimate',
	'ngSanitize',
	'ngCookies',
	'angularBootstrapNavTree',
	'ui.bootstrap',
	'ui.select2',
	'angular-md5',
	'collaboroServices',
	'collaboroControllers'	
]);

angular.module('collaboroApp').config( [
    '$compileProvider',
    function( $compileProvider )
    {   
        $compileProvider.aHrefSanitizationWhitelist(/^\s*(https?|ftp|mailto|chrome-extension|data):/);
        // Angular before v1.2 uses $compileProvider.urlSanitizationWhitelist(...)
    }
]);