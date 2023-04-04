package com.merakses.sensorsmonitorbackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.merakses.sensorsmonitorbackend.dto.AuthRequestDto;
import com.merakses.sensorsmonitorbackend.dto.AuthResponseDto;

import jakarta.validation.Valid;

@CrossOrigin
public interface AuthenticationController {

    @PostMapping("/api/authenticate")
    ResponseEntity<AuthResponseDto> authenticate(@RequestBody @Valid AuthRequestDto authRequestDto);
}
