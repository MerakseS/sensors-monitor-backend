package com.merakses.sensorsmonitorbackend.controller.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;

import com.merakses.sensorsmonitorbackend.controller.AuthenticationController;
import com.merakses.sensorsmonitorbackend.dto.AuthRequestDto;
import com.merakses.sensorsmonitorbackend.dto.AuthResponseDto;
import com.merakses.sensorsmonitorbackend.service.AuthenticationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class DefaultAuthenticationController implements AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final AuthenticationService authenticationService;

    @Override
    public ResponseEntity<AuthResponseDto> authenticate(AuthRequestDto authRequestDto) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
            authRequestDto.getLogin(), authRequestDto.getPassword());

        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        String jwt = authenticationService.generateToken((UserDetails) authentication.getPrincipal());

        return ResponseEntity.ok(new AuthResponseDto(jwt));
    }
}
