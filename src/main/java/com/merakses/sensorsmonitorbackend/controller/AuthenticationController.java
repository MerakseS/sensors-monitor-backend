package com.merakses.sensorsmonitorbackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.merakses.sensorsmonitorbackend.dto.AuthRequestDto;
import com.merakses.sensorsmonitorbackend.dto.AuthResponseDto;

import jakarta.validation.Valid;

public interface AuthenticationController {

    @PostMapping("/authenticate")
    ResponseEntity<AuthResponseDto> authenticate(@RequestBody @Valid AuthRequestDto authRequestDto);
}
