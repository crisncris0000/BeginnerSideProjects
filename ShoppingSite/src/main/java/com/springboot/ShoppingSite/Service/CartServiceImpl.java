package com.springboot.ShoppingSite.Service;

import com.springboot.ShoppingSite.Entity.Cart;
import com.springboot.ShoppingSite.Entity.Item;
import com.springboot.ShoppingSite.Entity.User;
import com.springboot.ShoppingSite.Repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    CartRepository cartRepository;
    @Override
    public void saveItem(Item item, User user) {
        Cart cart = new Cart();

        cart.setItem(item);
        cart.setUser(user);

        cartRepository.save(cart);
    }

    @Override
    public List<Cart> findItemsFromCart(String username) {

        List<Cart> listItems = cartRepository.showMyItems(username);

        return listItems;
    }
}
