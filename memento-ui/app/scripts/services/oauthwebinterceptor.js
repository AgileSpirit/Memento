'use strict';

/**
 * @ngdoc service
 * @name mementoUiApp.OAuthWebInterceptor
 * @description
 * # OAuthWebInterceptor
 * Service in the mementoUiApp.
 */
angular.module('mementoApp')
  .service('OAuthWebInterceptor', ['$rootScope',  function OAuthWebInterceptor($rootScope) {
        return {
            request : function(config) {
                if ($rootScope.isSignedIn) {
                    console.log('accessToken = ' + $rootScope.accessToken);
                    config.headers['X-Access-Token'] = $rootScope.accessToken;
                    config.headers['Authorization'] = 'Bearer ' + $rootScope.accessToken;
                }
                return config;
            }
        };
  }]);
