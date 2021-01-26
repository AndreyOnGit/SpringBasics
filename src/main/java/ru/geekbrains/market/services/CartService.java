package ru.geekbrains.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.market.carts.Cart;
import ru.geekbrains.market.dto.CartDto;
import ru.geekbrains.market.exceptions_handling.ResourceNotFoundException;
import ru.geekbrains.market.models.OrderItem;
import ru.geekbrains.market.models.Product;
import ru.geekbrains.market.repositories.CartRepository;
import ru.geekbrains.market.repositories.ProductRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {
    private final Cart cart;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;

    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }

    public void addProductIntoCartById(Long id) {
        for (OrderItem o : cart.getItems()) {
            if (o.getProduct().getId().equals(id)) {
                o.incrementQuantity();
                cart.recalculate();
                return;
            }
        }
        Product p = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No product's been found with id " + id + " to add to cart."));
        cart.addOrderItem(new OrderItem(p));
        cart.recalculate();
    }

    public CartDto getCart() {
        return new CartDto(cart);
    }

    public void clear() {
        cart.clear();
    }

    public void removeOrderItemByProductId(Long id) {
        cart.removeOrderItemByProductId(id);
    }

    public void saveCart(){
        cartRepository.deleteAll();
        for (OrderItem oi : cart.getItems()) cartRepository.save(oi);
    }
}
