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

mainApp.controller('ManagerPanelController', function($scope, $http) {
    $http({
        method: 'GET',
        url: 'http://do.mac.ba:8080/charlie/rest/users/'
    }).then(function successCallback(response) {
        // this callback will be called asynchronously
        // when the response is available
        $scope.users = response.json;
    }, function errorCallback(response) {
        // called asynchronously if an error occurs
        // or server returns response with an error status.
    });
});

