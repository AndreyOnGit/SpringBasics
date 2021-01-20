package ru.geekbrains.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.market.dto.ProductDto;
import ru.geekbrains.market.repositories.specifications.ProductSpecifications;
import ru.geekbrains.market.services.ProductService;
import ru.geekbrains.market.models.Product;

import java.util.List;


@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public Page<ProductDto> findAllProducts(
            @RequestParam MultiValueMap<String, String> params,
            @RequestParam(name = "page", defaultValue = "1") Integer page
    ) {
        if (page < 1) {
            page = 1;
        }
        return productService.findAll(ProductSpecifications.build(params), page, 5);
    }

    @GetMapping("/{id}")
    public ProductDto findProductById(@PathVariable Long id) {
        return productService.findProductById(id).get();
    }

//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED) //ответ 201
//    public ProductDto saveNewProduct(@RequestBody Product product) {
//        return productService.saveOrUpdate(product);
//    }

//    @DeleteMapping("/{id}")
//    public void deleteProductById(@PathVariable Long id) {
//        productService.deleteProductById(id);
//    }

    @GetMapping("/cart")
    public List<ProductDto> showCart (){
        return productService.getCart();
    }

    @GetMapping("/cart_put/{id}")
    public boolean putProductIntoCart(@PathVariable Long id){
        return productService.putProductIntoCart(id);
    }

    @DeleteMapping("/cart_put/{id}")
    public void removeProductFromCart(@PathVariable Long id) {
        productService.removeProductFromCartById(id);
    }

}
