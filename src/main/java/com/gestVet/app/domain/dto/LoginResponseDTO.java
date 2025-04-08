package com.gestVet.app.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponseDTO {
    private Long id;
    private String nombreCompleto;
    private String rol;
}