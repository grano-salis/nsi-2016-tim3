/**
 * Created by Adna on 14/12/2016.
 */
'use strict';

// Declare app level module which depends on filters, and services
angular.module('nsi.charlie', ['nsi.charlie.services', 'nsi.charlie.controllers']).
config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/componentDetails', {templateUrl: './partials/componentDetailsPartial.html', controller: 'ComponentCtrl'});
    $routeProvider.otherwise({redirectTo: '/componentDetails'});
}]);