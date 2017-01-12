/**
 * Created by Adna on 14/12/2016.
 */
'use strict';

// Declare app level module which depends on filters, and services
var nsiCharlie = angular.module('nsi.charlie', ['nsi.charlie.services', 'nsi.charlie.controllers', 'ngRoute', 'ngCookies']);

nsiCharlie.config(function($routeProvider, $httpProvider) {
    $httpProvider.defaults.withCredentials = true;
    $routeProvider
        .when('/', {
            templateUrl: './partials/managerPanelPartial.html',
            controller: 'ManagerPanelController',
            resolve: {
                check: function ($cookies, $location) {
                    if ($cookies.get('sid') != '') {
                        $.ajax({
                            type: "GET",
                            url: 'http://do.mac.ba:8888/BusinessLogic/Account.svc/json/auth',
                            xhrFields: {
                                withCredentials: true
                            },
                            success: function (data) {
                                if (data && !data.Roles.includes('ADMIN')) {
                                    window.location.href = "http://charlie.mac.ba/";
                                }
                            },
                            error: function (data) {
                                window.location.href = "http://charlie.mac.ba/";
                            }
                        });
                    }
                }
            }
        })
        // .when('/managerPanel', {
        //     templateUrl: './partials/managerPanelPartial.html',
        //     controller: 'ManagerPanelController'
        // })
        // .when('/componentDetailsPartial', {
        //     templateUrl: './partials/componentDetailsPartial.html',
        //     controller: 'ComponentCtrl'
        // })
        .otherwise({
            redirectTo: '/'
        });
});