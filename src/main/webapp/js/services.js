var cascadiaServices = angular.module('cascadiaServices', []);

// Ensures we have a collection of users at our disposal at all times
cascadiaServices.factory('InitUserMap', ['Restangular',
 function(Restangular) {
    return function(scope){
        if(scope.isAuthenticated && (!scope.userMap || scope.userMap.length == 0)) {
            scope.userMap = {}

            Restangular.all('users').getList().then(function(response){
                scope.userList = response;
                for(var i = 0; i < scope.userList.length; ++i) {
                  scope.userMap[scope.userList[i].id] = scope.userList[i];
                }
            });
        }
    }
}]);

cascadiaServices.factory('AuthenticateUser', ['$location', 'Restangular',
 function($location, Restangular) {
  return function(scope) {
    scope.isAuthenticated = (localStorage.getItem('token')) ? true : false;
    if(!scope.isAuthenticated) {
        $location.path('/login')
    } else {
      Restangular.setDefaultHeaders({
        'Authorization': 'Basic ' + localStorage.getItem('token')
      });
    }
  }
}]);

cascadiaServices.factory('permissions',
  function($rootScope) {
    var permissionList;
    return {
      setPermissions: function(permissions) {
        permissionList = permissions;
        $rootScope.$broadcast('permissionsChanged')
      },
      hasPermission: function(permission) {
        permission = permission.trim();
        return _.some(permissionList, function(item) {
          if(_.isString(item.name))
            return item.name.trim() === permission
        });
      }
    };
  });

cascadiaServices.factory('GrowlResponse', [
  function() {
    return function(response) {
      if(response.status >= 200 && response.status <=304) {
        $.growl.notice({ title: "Success!", message:"Operation Successful"});
      }
      else if(response.status == 400){
          var errorMessage = response.errors[0].error;
          for(var i = 1; i < response.errors.length; ++i) {
            errorMessage += ('\n' + response.errors[i].error);
          } 
          $.growl.error({title: "Error! Invalid Values", message: errorMessage})
      }
      else if(response.status == 401){
          $.growl.error({title: "Error!", message: "Unauthorized. Check your username and password."})
      }
      else if(response.status == 403){
        $.growl.error({title: "Error!", message: "Forbidden"})
      }
      else if(response.status == 404){
        $.growl.error({title: "Error!", message: "Not found. You may be pointing to the wrong endpoint."})
      } else {
          $.growl.error({title: "Error!", message: "Status Code "+ response.status})
      }
    }
}]);



cascadiaServices.factory('AssignUser', function() {
 return function (scope) {
    scope.user = JSON.parse(localStorage.getItem('user'));
  }
});
