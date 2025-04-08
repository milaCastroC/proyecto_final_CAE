package com.gestVet.app.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class PersonaDTO {
    private Long personaId;

    @NotBlank(message = "El tipo de identificación es obligatorio")
    private String tipoIdentificacion;

    @NotBlank(message = "La identificación es obligatoria")
    private String identificacion;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;

    @Pattern(regexp = "^$|\\d{10}", message = "El teléfono debe tener 10 dígitos o estar vacío")
    private String telefono;

    @Email(message = "El correo no tiene un formato válido")
    private String email;

    private String direccion;

    public PersonaDTO(Long personaId, String identificacion, String tipoIdentificacion, String nombre, String apellido, String telefono, String email, String direccion) {
        this.personaId = personaId;
        this.identificacion = identificacion;
        this.tipoIdentificacion = tipoIdentificacion;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
    }
}
