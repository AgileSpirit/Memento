'use strict';

/**
 * @ngdoc function
 * @name mementoUiApp.controller:EditorCtrl
 * @description
 * # EditorCtrl
 * Controller of the mementoUiApp
 */
angular.module('mementoApp')
    .controller('DocumentEditorCtrl', ['$rootScope', '$scope', '$location', 'ApiService',
        function ($rootScope, $scope, $location, apiService) {

        $scope.documentTypes = [
            { label: 'Bookmark', value: 'BOOKMARK'},
            { label: 'Note', value: 'NOTE' }
        ];

        $scope.selectedType = $scope.documentTypes[0];
        $scope.document = {};


        $scope.saveDocument = function () {
            if ($scope.isBookmark()) {
                // Prepare
                var bookmark = {};
                bookmark.title = $scope.document.title;
                bookmark.description = $scope.document.description;
                bookmark.url = $scope.document.url;

                // Perform
                apiService.saveBookmark(bookmark,
                    function(successResult) {
                        $rootScope.closeEditor();
                        $rootScope.$emit('refreshDocumentList');
                    },
                    function(errorResult) {
                        console.error('An error occurred during saving');
                    });
            } else if ($scope.isNote()) {
                var note = {};
                note.title = $scope.document.title;
                note.description = '';
                note.content = $scope.document.content;
                // Perform
                apiService.saveNote(note,
                    function(successResult) {
                        $rootScope.closeEditor();
                        $rootScope.$emit('refreshDocumentList');
                    },
                    function(errorResult) {
                        console.error('An error occurred during saving');
                    });
            } else {
                // ERROR
            }
        };

        $scope.isBookmark = function () {
            return $scope.selectedType === $scope.documentTypes[0];
        };

        $scope.isNote = function () {
            return $scope.selectedType === $scope.documentTypes[1];
        };

        $scope.cancel = function() {
            $rootScope.selectView('DASHBOARD');
        };

    }]);
