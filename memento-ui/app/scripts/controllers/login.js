'use strict';

/**
 * @ngdoc function
 * @name mementoApp.controller:LoginCtrl
 * @description
 * # LoginCtrl
 * Controller of the mementoApp
 */
angular.module('mementoApp')
  .controller('LoginCtrl', ['$scope', '$location', 'ApiServices', function ($scope, $location, apiServices) {

        // signIn
        $scope.userProfile = undefined;
        $scope.hasUserProfile = false;
        $scope.isSignedIn = false;
        $scope.immediateFailed = false;

        $scope.signedIn = function(profile) {
            $scope.isSignedIn = true;
            $scope.userProfile = profile;
            $scope.hasUserProfile = true;
            $location.path('/dashboard');
        };

        $scope.signIn = function(authResult) {
            $scope.$apply(function() {
                $scope.processAuth(authResult);
            });
        };

        $scope.processAuth = function(authResult) {
            $scope.immediateFailed = true;
            if ($scope.isSignedIn) {
                return 0;
            }
            if (authResult['access_token']) {
                var request = {
                    'access_token' : authResult['access_token'],
                    'client_id' : authResult['client_id'],
                    'id_token' : authResult['id_token']
                };

                $scope.immediateFailed = false;
                // Successfully authorized, create session
                apiServices.login(request, (function(response) {
                    $scope.signedIn(response.data);
                }));
            } else if (authResult['error']) {
                if (authResult['error'] == 'immediate_failed') {
                    $scope.immediateFailed = true;
                } else {
                    console.log('Error:' + authResult['error']);
                }
            }
        };

        $scope.renderSignIn = function() {
            gapi.signin.render('myGsignin', {
                'callback': $scope.signIn,
                'clientid': '276048965691-5mbhe3lasuea0gu49o8h14jboeo8tua6.apps.googleusercontent.com',
                'requestvisibleactions': 'http://schema.org/AddAction',
                'scope': 'https://www.googleapis.com/auth/plus.login',
                'theme': 'dark',
                'cookiepolicy': 'single_host_origin'
            });
        };

        $scope.start = function() {
            $scope.renderSignIn();
        };

        $scope.start();

    }]);
