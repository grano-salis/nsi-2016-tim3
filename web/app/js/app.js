/**
 * Created by Adna on 14/12/2016.
 */
'use strict';

// Declare app level module which depends on filters, and services
var nsiCharlie = angular.module('nsi.charlie', ['nsi.charlie.services', 'nsi.charlie.controllers', 'ngRoute']);

nsiCharlie.config(function($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl: './partials/managerPanelPartial.html',
            controller: 'ManagerPanelController'
        })
        .when('/managerPanel', {
            templateUrl: './partials/managerPanelPartial.html',
            controller: 'ManagerPanelController'
        })
        .when('/componentDetailsPartial', {
            templateUrl: './partials/componentDetailsPartial.html',
            controller: 'ComponentCtrl'
        })
        .otherwise({
            redirectTo: '/componentDetailsPartial'
        });
});
