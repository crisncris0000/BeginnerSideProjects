package com.springboot.ShoppingSite.Service.Implementation;

import com.springboot.ShoppingSite.Entity.ConfirmationToken;
import com.springboot.ShoppingSite.Repository.ConfirmationTokenRepository;
import com.springboot.ShoppingSite.Service.ConfirmationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Override
    public ConfirmationToken findTokenById(int id) {

        Optional<ConfirmationToken> result = confirmationTokenRepository.findById(id);

        ConfirmationToken confirmationToken = result.orElseThrow(() ->{
            throw new NotFoundException("Token with the id: " + id + " not found");
        });

        return confirmationToken;
    }

    @Override
    public void saveToken(ConfirmationToken token) {
        confirmationTokenRepository.save(token);
    }

    @Override
    public ConfirmationToken findConfirmationToken(String confirmationToken) {
        return confirmationTokenRepository.findByConfirmationToken(confirmationToken);
    }

    @Override
    public boolean doesTokenExist(String token) {

        ConfirmationToken confirmationToken = findConfirmationToken(token);

        if(confirmationToken == null){
            return false;
        }

        return true;
    }
}
