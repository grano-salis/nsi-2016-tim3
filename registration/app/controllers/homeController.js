myApp.controller('RegisterController', procesirajRegistraciju);


function procesirajRegistraciju($scope, $http){
    $scope.register = {};

    $scope.processForm = function()
    {
      $http({
        method  : 'POST',
        url     : 'http://do.mac.ba:8888/BusinessLogic/Account.svc/json/register',
        data    : $.param($scope.register),
        headers : { 'Content-Type': 'application/json' }
      }).then(function successCallback(response) {
           console.log("naisuu");
      }, function errorCallback(response) {
           console.log("wtf");
      });
    };
}
