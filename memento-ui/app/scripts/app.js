'use strict';

/**
 * @ngdoc overview
 * @name mementoApp
 * @description
 * # mementoApp
 *
 * Main module of the application.
 */
angular
    .module('mementoApp', [
        'ngAnimate',
        'ngCookies',
        'ngResource',
        'ngRoute',
        'ngSanitize',
        'ngTouch',
        'ui.bootstrap'
    ])
    .config(function ($routeProvider, $httpProvider, $locationProvider) {
        $routeProvider
            .when('/about', { templateUrl: 'views/about.html', controller: 'AboutCtrl' })
            .when('/login', { templateUrl: 'views/login.html', controller: 'LoginCtrl' })
            .when('/main', { templateUrl: 'views/main.html', controller: 'MainCtrl' })
            .when('/bookmarks', { templateUrl: 'views/bookmarks.html', controller: 'BookmarksCtrl' })
            .when('/error', { templateUrl: 'views/error.html', controller: 'ErrorCtrl' })
            .when('/dashboard', { templateUrl: 'views/dashboard.html', controller: 'DashboardCtrl' })
            .when('/editor', {
              templateUrl: 'views/editor.html',
              controller: 'EditorCtrl'
            })
            .otherwise({ redirectTo: '/bookmarks' });

        /* CORS... */
        /* http://stackoverflow.com/questions/17289195/angularjs-post-data-to-external-rest-api */
        $httpProvider.defaults.useXDomain = true;
        delete $httpProvider.defaults.headers.common['X-Requested-With'];

        /* Remove '#' character in URLs */
        //$locationProvider.html5Mode(true);

    })
    .run(function($rootScope) {
        $rootScope.currentView = 'DASHBOARD';

        $rootScope.selectView = function (view) {
            $rootScope.currentView = view;
        };

        $rootScope.isCurrentView = function(view) {
            return $rootScope.currentView === view;
        };

        $rootScope.openEditor = function() {
            $rootScope.currentView = 'EDITOR';
        };

        $rootScope.closeEditor = function() {
            $rootScope.currentView = 'DASHBOARD';
        };

    });
