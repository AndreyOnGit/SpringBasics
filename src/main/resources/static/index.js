angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market/api/v1/products';

    $scope.fillTable = function (current_page = 1) {
        $http({
            url: contextPath,
            method: 'GET',
            params: {
                min_price: $scope.filter ? $scope.filter.min_price : null,
                max_price: $scope.filter ? $scope.filter.max_price : null,
                page: current_page
            }
        }).then(function (response) {
            $scope.ProductPage = response.data;
              $scope.PaginationArray = $scope.generatePageIndices(1,$scope.ProductPage.totalPages);
        });
    };

     $scope.generatePageIndices = function(firstPage,lastPage){
        let arr = [];
        for (let i = firstPage; i<lastPage+1; i++){
            arr.push(i);
            }
        return arr;
     }


    $scope.submitCreateNewProduct = function () {
        $http.post(contextPath, $scope.newProduct)
            .then(function (response) {
                $scope.newProduct = null;
                $scope.fillTable();
            });
    };

    $scope.deleteProductById = function (productId) {
        $http({
          url: contextPath + '/' + productId,
          method: 'DELETE'
        }).then(function (response) {
            $scope.fillTable();
        });
    };

    $scope.fillTable();
});