package com.springboot.ShoppingSite.Repository;

import com.springboot.ShoppingSite.Entity.ConfirmationToken;
import com.springboot.ShoppingSite.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Integer> {

    @Query("FROM ConfirmationToken WHERE confirmationToken = :confirmationToken")
    public ConfirmationToken findByConfirmationToken(@Param("confirmationToken") String confirmationToken);

}
