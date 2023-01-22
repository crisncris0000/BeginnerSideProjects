package com.springboot.ShoppingSite.Repository;

import com.springboot.ShoppingSite.Entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {

}
