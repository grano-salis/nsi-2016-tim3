/**
 * Created by Adna on 14/12/2016.
 */
'use strict';

var app = angular.module('nsi.charlie.controllers', []);

// Clear browser cache (in development mode)
//
// http://stackoverflow.com/questions/14718826/angularjs-disable-partial-caching-on-dev-machine
app.run(function ($rootScope, $templateCache) {
    $rootScope.$on('$viewContentLoaded', function () {
        $templateCache.removeAll();
    });
});

app.controller('ComponentCtrl', ['$scope', 'ComponentFactory', function ($scope, ComponentFactory) {
    ComponentFactory.get({}, function (componentFactory) {
        $scope.componentTitle = componentFactory.title;
    })
}]);

