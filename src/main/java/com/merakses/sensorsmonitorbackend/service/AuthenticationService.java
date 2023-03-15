package com.merakses.sensorsmonitorbackend.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthenticationService extends UserDetailsService {

    String generateToken(UserDetails userDetails);

    String extractUsername(String token);

    String extractAuthorities(String token);
}
