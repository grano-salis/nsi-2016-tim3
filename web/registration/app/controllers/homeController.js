myApp.controller('RegisterController', procesirajRegistraciju);


function procesirajRegistraciju($scope, $http, $cookies){
    console.log($cookies.get('sid'));
    $scope.altModel = {}
    $scope.register = {
      "Username": "",
      "Email": "",
      "Password": "",
      "FirstName": "",
      "LastName": "",
    }

    $scope.processForm = function()
    {
      $http({
        method  : 'POST',
        url     : 'http://do.mac.ba:8888/BusinessLogic/Account.svc/json/register',
        data    : $scope.register,
        headers : { 'Content-Type': 'application/json' }
      }).then(function successCallback(response) {
        window.location = "http://charlie.mac.ba/sso/dist/";
      }, function errorCallback(response) {
        console.log(response);
        if (response.data.Message == "Password weak.") {
	        $scope.register.Password = "";
	        $scope.altModel.confirmPassword = "";
      	} else {
      		$scope.register.Username = "";
      	}
      });
    };
}