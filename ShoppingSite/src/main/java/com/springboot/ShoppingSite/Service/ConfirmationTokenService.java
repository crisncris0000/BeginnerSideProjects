package com.springboot.ShoppingSite.Service;


import com.springboot.ShoppingSite.Entity.ConfirmationToken;
import com.springboot.ShoppingSite.Entity.User;

public interface ConfirmationTokenService {

    public ConfirmationToken findTokenById(int id);

    public void saveToken(ConfirmationToken token);

    public ConfirmationToken findConfirmationToken(String confirmationToken);

    public boolean doesTokenExist(String token);

}
