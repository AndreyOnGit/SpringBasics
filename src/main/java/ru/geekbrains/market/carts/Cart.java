package ru.geekbrains.market.carts;

import org.springframework.stereotype.Component;
import ru.geekbrains.market.dto.ProductDto;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Component
public class Cart {
    private List<ProductDto> productDtoList;

    public void setProductDtoList(List<ProductDto> productDtoList) {
        this.productDtoList = productDtoList;
    }

    public List<ProductDto> getProductDtoList() {
        return productDtoList;
    }

    public boolean addProduct(ProductDto productDto){
        return productDtoList.add(productDto);
    }

    @PostConstruct
    public void init() {
        productDtoList = new LinkedList<ProductDto>();
    }

    public void removeProductById(Long id){
        productDtoList.removeIf(p -> p.getId() == id);
    }
}
