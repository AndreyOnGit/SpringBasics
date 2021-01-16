package ru.geekbrains.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
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

    public Optional<ProductDto> findProductById(Long id) {
        return Optional.of(new ProductDto(productRepository.findById(id).get()));
    }

    public ProductDto saveOrUpdate(Product product) {
        return new ProductDto(productRepository.save(product));
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }
    public Page<ProductDto> getProductByPrice(int min, int max, int page, int size) {
        Page<Product> productPage = productRepository.findAllByPriceBetween(min, max, PageRequest.of(page, size));
        return new PageImpl<>(productPage.stream().map(ProductDto::new).collect(Collectors.toList()), productPage.getPageable(), productPage.getTotalElements());
    }
}
