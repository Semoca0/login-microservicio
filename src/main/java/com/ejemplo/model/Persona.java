package com.ejemplo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Persona {
	
	private int codigo;
	private String nombre;
	private String apellido;
	private String identificacion;
	private int estado;
}
