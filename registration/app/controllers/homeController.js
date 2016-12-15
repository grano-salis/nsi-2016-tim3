myApp.controller('RegisterController', procesirajRegistraciju);


function procesirajRegistraciju($scope, $http){
    $scope.altModel = {}
    $scope.register = {
                        "registerModel": {
                            "Username": "",
                            "Email": "",
                            "Password": "",
                            "FirstName": "",
                            "LastName": "",
                        }}

    $scope.processForm = function()
    {
      $http({
        method  : 'POST',
        url     : 'http://do.mac.ba:8888/BusinessLogic/Account.svc/json/register',
        data    : $scope.register,
        headers : { 'Content-Type': 'application/json' }
      }).then(function successCallback(response) {
           console.log("proslo");
      }, function errorCallback(response) {
           console.log("nije proslo");
      });
    };
}
