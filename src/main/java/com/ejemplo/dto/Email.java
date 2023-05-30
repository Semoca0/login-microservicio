package com.ejemplo.dto;

import java.io.InputStream;

import lombok.Data;
import lombok.NoArgsConstructor;


public class Email {
	
	private	String asunto;
	private	String descripcion;
	private	String destinatario[];
	private InputStream adjunto;
	private String url;
	private String extencion;
}
