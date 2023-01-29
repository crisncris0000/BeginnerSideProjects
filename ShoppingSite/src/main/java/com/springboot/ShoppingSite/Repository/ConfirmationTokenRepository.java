package com.springboot.ShoppingSite.Repository;

import com.springboot.ShoppingSite.Entity.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Integer> {
}
