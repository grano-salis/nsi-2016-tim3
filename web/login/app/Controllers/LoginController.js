loginApp.controller('LoginController', procesirajLogin);


function procesirajLogin($scope, $cookies, $cookieStore, $http){
  $scope.login = {
  	               "loginModel":{
		                  "Username": "",
		                  "Password": ""
  	                }
                  }

  $scope.processForm = function()
  {
    $http({
      method  : 'POST',
      url     : 'http://do.mac.ba:8888/BusinessLogic/Account.svc/json/login',
      data    : $scope.login,
      headers : { 'Content-Type': 'application/json' }
    }).then(function successCallback(response) {
         console.log($cookies.get("token"));
    }, function errorCallback(response) {
         console.log("nije proslo");
    });
  };
}
