'use strict';

/**
 * @ngdoc function
 * @name mementoApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the mementoApp
 */
angular.module('mementoApp')
  .controller('MainCtrl', function ($scope) {
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
  });
