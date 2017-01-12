var etfpass = angular.module('etfpass', ['ngRoute', 'ngCookies']);

etfpass.controller('CvController', function ($scope, $timeout, $cookies) {
    
    $scope.user = {};
    $scope.draft = {};
    $(document).ready(function () {
        if ($cookies.get('sid') != '') {
            $.ajax({
                type: "GET",
                url: 'http://do.mac.ba:8888/BusinessLogic/Account.svc/json/auth',
                xhrFields: {
                    withCredentials: true
                },
                success: function (data) {
                    $scope.user = data;
                    $.get('http://do.mac.ba:8080/charlie/rest/user/' + $scope.user['UserId'] + '/collection/components', function (data) {
                        console.log(data);
                        $scope.user.cvs = data;
                        if ($scope.user.cvs.length > 0) {
                            window.localStorage.setItem('temporary.europass.ewa.skillspassport.v3', $scope.user.cvs[0].additionalinfo);
                        }
                    });
                },
                error: function (data) {
                    $('#save-wizard-init-btn').prop('disabled', false);
                }
            });
        } else {
            window.location.href = "http://do.mac.ba:88/sso/dist/";
        }
    });

    $timeout(function () {
        $('#slow-download-btn').on('click', function () {
            var fullString = window.localStorage.getItem('temporary.europass.ewa.skillspassport.v3');
            var fullObj = JSON.parse(fullString);
            var cvObj = $.extend({}, fullObj.SkillsPassport);
            var cvData = encodeURIComponent(JSON.stringify({ SkillsPassport: cvObj }));
            $('<form target="_blank" action="https://europass.cedefop.europa.eu/api/document/to/pdf" method="POST">' + 
            "<input name='json' value='" + cvData + "' />" + '</form>').appendTo('body').submit().remove();
            // $.post('https://europass.cedefop.europa.eu/api/document/to/pdf', cvData, function (data) {

            // }, 'application/pdf');
        });

        $('#save-wizard-init-btn').on('click', function () {
            var fullString = window.localStorage.getItem('temporary.europass.ewa.skillspassport.v3');
            var fullObj = JSON.parse(fullString);
            var cvObj = $.extend({}, fullObj.SkillsPassport);
            var cvData = 'json=' + encodeURIComponent(JSON.stringify({ SkillsPassport: cvObj }));
            $.post('https://europass.cedefop.europa.eu/api/document/to/xml', cvData, function (data) {
                $scope.draft = {
                    "componentid": 0,
                    "status": "PENDING",
                    "title": "Text",
                    "updated": "2016-12-14T01:37:27+01",
                    "additionalinfo": fullString,
                    "userid": $scope.user['UserId'],
                    "componenttype": 4,
                    "data": data
                };
                $.ajax({
                    type: 'POST',
                    url: 'http://do.mac.ba:8080/charlie/rest/componentdraft/',
                    data: JSON.stringify($scope.draft),
                    success: function (data) {},
                    contentType:'application/json; charset=UTF-8'
                });
            }, 'text');
        });

    }, 2000);
});