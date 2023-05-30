package com.ejemplo.dto;

import com.ejemplo.model.Persona;
import com.ejemplo.model.Usuario;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonaDto {
    Persona persona;
    Usuario usuario;
}
