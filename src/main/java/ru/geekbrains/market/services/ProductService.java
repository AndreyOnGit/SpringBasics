package ru.geekbrains.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.market.carts.Cart;
import ru.geekbrains.market.dto.ProductDto;
import ru.geekbrains.market.models.Product;
import ru.geekbrains.market.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final Cart cart;

    public Optional<ProductDto> findProductById(Long id) {
        return Optional.of(new ProductDto(productRepository.findById(id).get()));
    }

    public ProductDto saveOrUpdate(Product product) {
        return new ProductDto(productRepository.save(product));
    }

//    public void deleteProductById(Long id) {
//        productRepository.deleteById(id);
//    }

//    public Page<ProductDto> getProductByPrice(int min, int max, int page, int size) {
//        Page<Product> productPage = productRepository.findAllByPriceBetween(min, max, PageRequest.of(page, size));
//        return productPage.map(ProductDto::new);
//    }

    public Page<ProductDto> findAll(Specification<Product> spec, int page, int pageSize) {
        return productRepository.findAll(spec, PageRequest.of(page - 1, pageSize)).map(ProductDto::new);
    }

    public List<ProductDto> getCart (){
//        cart.setProductDtoList(productRepository.findAll().stream().map(ProductDto::new).collect(Collectors.toList()));
//        System.out.println(cart.getProductDtoList());
        return cart.getProductDtoList();
    }

    public boolean putProductIntoCart(Long id){
        return cart.addProduct(findProductById(id).get());
    }

    public void removeProductFromCartById (Long id){
        cart.removeProductById(id);
    }


}
