'use strict';

/**
 * @ngdoc service
 * @name mementoUiApp.Documentservice
 * @description
 * # Documentservice
 * Service in the mementoUiApp.
 */
angular.module('mementoApp')
  .service('DocumentService', function DocumentService($resource) {
        var endPoint = 'http://localhost:8080/api/documents';

        return $resource(endPoint, {}, {
            save: { method: 'POST', url: endPoint },
            update: { method: 'PUT', url: endPoint + '/:id', params: { id: '@id' } },
            search: { method: 'GET', url: endPoint + '/search?q=:query&o=:offset&s=:size' }
        });
  });
