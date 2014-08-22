'use strict';

/**
 * @ngdoc function
 * @name mementoUiApp.controller:DashboardCtrl
 * @description
 * # DashboardCtrl
 * Controller of the mementoUiApp
 */
angular.module('mementoApp')
  .controller('DashboardCtrl', ['$rootScope', '$scope', '$window', 'ApiServices', function ($rootScope, $scope, $window, apiService) {
        // Model data
        $scope.searchQuery = '';
        $scope.currentPage = 1;
        $scope.pageSize = 50;
        $scope.documents = {};
        $scope.lastPage = -1;
        $scope.pageLinks = {};

        // Initialize
        function loadDocuments(query, page, size) {
            var offset = pageToOffset(page, $scope.pageSize);
            apiService.findDocuments({'query' : query, 'offset': offset , 'size': size}, function(response) {
                $scope.documents = response.documents;
                $scope.searchQuery = response.query;
                $scope.totalItems = response.totalItems;
                $scope.currentPage = offsetToPage(response.offset, $scope.pageSize);
                $scope.lastPage = Math.floor(($scope.totalItems + $scope.pageSize - 1) / size );
            });
        }

        // o = ( p - 1 ) * s
        function pageToOffset(pageIndex, pageSize) {
            return (pageIndex - 1) * pageSize;
        }

        // p = ( o / s ) + 1
        function offsetToPage(offset, pageSize) {
            return (offset / pageSize) + 1;
        }

        $scope.search = function() {
            loadDocuments($scope.searchQuery, 1, $scope.pageSize);
        };

        $scope.getDocumentExcerpt = function(document) {
            switch(document.type) {
                case 'BOOKMARK':
                    return document.description;
                case 'NOTE':
                    return document.content;
                default:
                    return '';
            }
        };

        $scope.getDocumentClass = function(document) {
          switch(document) {
              case 'BOOKMARK':
                  return 'document bookmark';
              case 'NOTE':
                  return 'document note';
              default:
                  return 'document';
          }
        };

        $scope.showBookmark = function(document) {
            $scope.bookmark = document;
            $rootScope.selectView('BOOKMARK');
        };

        /**
         * Handle the event emitted by DocumentFormCtrl controller
         */
        $rootScope.$on('refreshDocumentList', function(){
            loadDocuments($scope.searchQuery, 1, $scope.pageSize);
        });

        /**
         * Method called after this controller was initialized
         */
        function initialize() {
            loadDocuments($scope.searchQuery, $scope.currentPage, $scope.pageSize);
        }
        initialize();

    }]);
