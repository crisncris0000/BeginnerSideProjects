package com.springboot.ShoppingSite.Repository;

import com.springboot.ShoppingSite.Entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    @Query("FROM Cart WHERE user_id = :username")
    public List<Cart> showMyItems(@Param("username") String username);
}
