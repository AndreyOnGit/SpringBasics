package ru.geekbrains.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.market.dto.ProductDto;
import ru.geekbrains.market.services.ProductService;
import ru.geekbrains.market.models.Product;


@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping      /*http://localhost:8189/market/index.html*/
    public Page<ProductDto> findAllProducts(
            @RequestParam(name = "min_price", defaultValue = "0") Integer minPrice,
            @RequestParam(name = "max_price", required = false) Integer maxPrice,
            @RequestParam(name = "page", defaultValue = "1") Integer pg
    ) {
        if (maxPrice == null) {
            maxPrice = Integer.MAX_VALUE;
        }
        if (pg < 1) {
            pg = 1;
        }
        return productService.getProductByPrice(minPrice, maxPrice, pg - 1, 10);
    }

    @GetMapping("/{id}")
    public ProductDto findProductById(@PathVariable Long id) {
        return productService.findProductById(id).get();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) //ответ 201
    public ProductDto saveNewProduct(@RequestBody Product product) {
        return productService.saveOrUpdate(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
    }

}
