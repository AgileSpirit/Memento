'use strict';

/**
 * @ngdoc function
 * @name mementoApp.controller:LoginCtrl
 * @description
 * # LoginCtrl
 * Controller of the mementoApp
 */
angular.module('mementoApp')
  .controller('LoginCtrl', ['$scope', '$location', function ($scope, $location) {
        $scope.connect = function() {
            if ($scope.login === 'admin' && $scope.password === '1') {
                $location.path('/bookmarks');
            } else {
                $location.path('/error');
            }
        };
  }]);
