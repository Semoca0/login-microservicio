
package com.ejemplo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Usuario {
	
	private Integer codigo;
	private String usuario;
	private String clave;
	private int estado;
	private Persona persona;

	
}
