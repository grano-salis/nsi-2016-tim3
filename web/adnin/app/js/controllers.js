/**
 * Created by Adna on 14/12/2016.
 */
'use strict';

var app = angular.module('nsi.charlie.controllers', []);

app.controller('ComponentCtrl', ['$scope', 'ComponentFactory', function ($scope, ComponentFactory) {
    // ComponentFactory.get({}, function (componentFactory) {
    //     $scope.drafts = componentFactory;
    //     console.log($scope.drafts);
    // })
}]);

app.controller('ManagerPanelController', function($scope, $http, ComponentFactory, DraftFactory, UserFactory, GUIFactory) {
    $scope.users = [];
    $scope.drafts = [];

    $scope.logout = function () {
        GUIFactory.logout()
        .then(function () {

        },
        function () {

        });
    }
    $scope.download = function (event) {
        var id = $(event.target).attr('data-id');
        var draft = $scope.drafts.find(function (e) { return e.id == id });
        GUIFactory.download(draft.additionalinfo);
    }
    $scope.approve = function (event) {
        var id = $(event.target).attr('data-id');
        var draft = $scope.drafts.find(function (e) { return e.id == id });
        ComponentFactory.cloneDraft(draft)
        .then(function () {
            DraftFactory.approve(draft)
            .then(function () {
                DraftFactory.fetchAll();
            }, function () {

            });
        }, function () {

        });
    }
    $http.get('http://do.mac.ba:8888/BusinessLogic/Account.svc/json/auth')
    .then(function (response) {
        $scope.user = response.data;
        UserFactory.fetchAll()
        .then(function (response) {
            $scope.users.push(...response);
            DraftFactory.fetchAll()
            .then(function (response) {
                $scope.drafts.push(...response);
            }, function (response) {

            });
        }, function (response) {

        });
    }, function (response) {
        
    });
});