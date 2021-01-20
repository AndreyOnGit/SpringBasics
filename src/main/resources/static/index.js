angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market/api/v1/products';

    $scope.fillTable = function (current_page = 1) {
        $http({
            url: contextPath,
            method: 'GET',
            params: {
                title: $scope.filter ? $scope.filter.title : null,
                min_price: $scope.filter ? $scope.filter.min_price : null,
                max_price: $scope.filter ? $scope.filter.max_price : null,
                page: current_page
            }
        }).then(function (response) {
            $scope.ProductPage = response.data;
//            console.log('ProductPage:');
//            console.log(response.data);

            let minPageIndex = current_page - 2;
            if (minPageIndex < 1) {
                minPageIndex = 1;
            }
//            console.log('minPageIndex: ' + minPageIndex);

            let maxPageIndex = current_page + 2;
            if (maxPageIndex > $scope.ProductPage.totalPages) {
                maxPageIndex = $scope.ProductPage.totalPages;
            }
//            console.log('maxPageIndex: ' + maxPageIndex);

            $scope.PaginationArray = $scope.generatePageIndices(minPageIndex, maxPageIndex);
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
//            console.log('in submitCreateNewProduct');
                $scope.newProduct = null;
                $scope.fillTable();
            });
    };

//    $scope.deleteProductById = function (productId) {
//        $http({
//          url: contextPath + '/' + productId,
//          method: 'DELETE'
//        }).then(function (response) {
//            $scope.fillTable();
//        });
//    };

    $scope.showCart = function () {
        $http({
            url: contextPath + '/cart',
            method: 'GET'
        }).then(function (response) {
            $scope.ProductCart = response.data;
//            console.log('response:');
//            console.log(response);
        });
    };

    $scope.putProductIntoCart = function (productId) {
        $http({
          url: contextPath + '/cart_put/' + productId,
          method: 'GET'
        }).then(function (response) {
//           console.log('о добавлении: ' + response.data);
           $scope.showCart();
        });
    };

     $scope.removeProductFromCart = function (productId) {
            $http({
              url: contextPath + '/cart_put/' + productId,
              method: 'DELETE'
            }).then(function (response) {
                $scope.showCart();
            });
        };

    $scope.fillTable();
    $scope.showCart();
});