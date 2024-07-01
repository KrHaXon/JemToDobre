package com.JemToDobre.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.stereotype.Component;

@Component
public class CustomRememberMeServices extends TokenBasedRememberMeServices {
    public CustomRememberMeServices(UserDetailsService userDetailsService) {
        super("uniqueAndSecretKey", userDetailsService);
        this.setAlwaysRemember(true);  // Ensure this is set to true if you want always to remember
    }
}
