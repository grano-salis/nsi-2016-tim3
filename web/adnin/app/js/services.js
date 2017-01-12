/**
 * Created by Adna on 14/12/2016.
 */
'use strict';

/* Services */

var services = angular.module('nsi.charlie.services', ['ngResource']);

services.factory('GUIFactory', function ($http, $q) {
	var service = {};
	service.logout = function () {
		var d = $q.defer();
		$http({
			url: 'http://do.mac.ba:8888/BusinessLogic/Account.svc/json/logout',
			method: 'POST'
		})
		.success(function (response) {
			d.resolve(response);
			window.location.href = "http://do.mac.ba:88/sso/dist/";
		}).error(function () {
			d.reject('Crko marsal.');
		});
		return d.promise;
	}

	service.download = function (jsonString) {
        var fullObj = JSON.parse(jsonString);
        var cvObj = $.extend({}, fullObj.SkillsPassport);
        var cvData = encodeURIComponent(JSON.stringify({ SkillsPassport: cvObj }));
        $('<form target="_blank" action="https://europass.cedefop.europa.eu/api/document/to/pdf" method="POST">' + 
        "<input name='json' value='" + cvData + "' />" + '</form>').appendTo('body').submit().remove();
	}

    return service;
});

services.factory('DraftFactory', function ($http, $q) {
	var service = {};
	service.approve = function (draft) {
		var d = $q.defer();
		draft.status = 'APPROVED';
		$http({
			url: 'http://localhost:8080/charlie/rest/componentdraft/',
			method: 'POST',
			data: draft
		})
		.success(function (response) {
			d.resolve(response);
		}, function () {
			d.reject('No fcuk living force.');
		});
		return d.promise;
	}
    service.fetchAll = function () { 
    	var d = $q.defer();

		$http({
			url: 'http://do.mac.ba:8080/charlie/rest/componentdrafts/',
			method: 'GET'
		})
		.success(function (response) {
			d.resolve(response);
		}, function () {
			d.reject('No fcuk living force.');
		});
		return d.promise;
	}
	return service;
});

services.factory('UserFactory', function ($http, $q) {
    var service = {};

    service.users = [];
    service.init = function () {
    	if (service.users != []) {
    		service.users = service.fetchAll().users;
    	}
    }

    service.fetchAll = function () {
    	var d = $q.defer();

		$http({
			url: 'http://do.mac.ba:8888/BusinessLogic/AccountManagement.svc/json/XgetUsers',
			data: {"Value": ""},
			method: 'POST'
		})
		.success(function (response) {
			service.users = response.users;
			d.resolve(response.users);
		}).error(function () {
			d.reject('Crko marsal.');
		});
		return d.promise;
	}
    return service;
});

services.factory('ComponentFactory', function ($http, $q) {
    var service = {};
    service.cloneDraft = function (draft) {
    	var d = $q.defer();
    	draft.id = 0;
    	draft.title = '';
    	draft.updated = '2016-12-13T01:37:27+01';

		$http({
			url: 'http://do.mac.ba:8080/charlie/rest/component/',
			method: 'POST',
			data: draft
		})
		.success(function (response) {
			d.resolve(response);
		}, function () {
			d.reject('No fcuk living force.');
		});
		return d.promise;
    }
    service.fetchAll = function () { 
    	var d = $q.defer();

		$http({
			url: 'http://do.mac.ba:8080/charlie/rest/component/',
			method: 'GET'
		})
		.success(function (response) {
			d.resolve(response);
		}, function () {
			d.reject('No fcuk living force.');
		});
		return d.promise;
	}
	return service;
});