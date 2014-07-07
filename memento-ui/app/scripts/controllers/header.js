'use strict';

angular.module('mementoApp')
    .controller('HeaderCtrl', ['$scope', '$modal', function ($scope, $modal) {

        $scope.addBookmark = function() {
            $modal.open({
                templateUrl: 'views/bookmark-form.html',
                controller: 'BookmarkFormCtrl',
                resolve: {
                    bookmarkId: function(){
                        return null;
                    }
                }
            });
        };

    }]);