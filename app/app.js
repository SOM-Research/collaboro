'use strict';

//var collaboroServletURL = 'http://atlanmodexp.info.emn.fr:8800/collaboro';
var collaboroServletURL = 'http://localhost:8080/fr.inria.atlanmod.collaboro.web.servlets';

angular.module('collaboroApp', [
	'ui.bootstrap',
	'angular-md5',
	'ngRoute',
	'ngAnimate',
	'ngSanitize',
	'ngCookies',
	'security',
	'collaboroControllers',
	'collaboroServices',
	'angularBootstrapNavTree',
	'ui.select2']);
