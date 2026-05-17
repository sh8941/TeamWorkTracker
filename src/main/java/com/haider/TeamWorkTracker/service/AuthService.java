package com.haider.TeamWorkTracker.service;


import com.haider.TeamWorkTracker.dtos.request.AuthRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;

    public String login(AuthRequest authRequest) {
        Authentication authentication =
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authRequest.getUsername(), authRequest.getPassword()
            ));

        if (authentication != null) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtService.generateToken(authRequest.getUsername());
            return token;
        }
        return null;
    }
}
