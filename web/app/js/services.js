/**
 * Created by Adna on 14/12/2016.
 */
'use strict';

/* Services */

var services = angular.module('nsi.charlie.services', ['ngResource']);

services.factory('ComponentFactory', function ($resource) {
    return $resource('http://localhost:8080/charlie/rest/component/53', {}, {
        query: {
            method: 'GET',
            params: {},
            isArray: false
        }
    })
});