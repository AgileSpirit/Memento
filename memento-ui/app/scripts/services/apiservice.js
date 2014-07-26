'use strict';

/**
 * @ngdoc service
 * @name mementoUiApp.Documentservice
 * @description
 * # Documentservice
 * Service in the mementoUiApp.
 */
angular.module('mementoApp')
  .service('ApiService', function ApiService($resource) {
        var apiBaseUrl = 'http://localhost:8080/api';

        return $resource(apiBaseUrl, {}, {
            findDocuments: { method: 'GET', url: apiBaseUrl + '/documents/search?q=:query&o=:offset&s=:size' },
            getBookmark: { method: 'GET', url: apiBaseUrl + '/bookmarks/:id', params: { id: '@id' } },
            saveBookmark: { method: 'POST', url: apiBaseUrl + '/bookmarks'},
            updateBookmark: { method: 'PUT', url: apiBaseUrl + '/bookmarks/:id', params: { id: '@id' } },
            deleteBookmark: { method: 'DELETE', url: apiBaseUrl + '/bookmarks/:id', params: { id: '@id' } },
            getNote: { method: 'GET', url: apiBaseUrl + '/notes/:id', params: { id: '@id' } },
            saveNote: { method: 'POST', url: apiBaseUrl + '/notes'},
            updateNote: { method: 'PUT', url: apiBaseUrl + '/notes/:id', params: { id: '@id' } },
            deleteNote: { method: 'DELETE', url: apiBaseUrl + '/notes/:id', params: { id: '@id' } }

        });
  });
