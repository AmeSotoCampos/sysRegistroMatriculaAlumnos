package com.sysRegistroMatriculaAlumnos.entity;

public class Retiro {
	private int numRetiro;
	private int numMatricula ;
	private String fecha;
	private String hora;
	

	// CONSTRUCTOR
	public Retiro(int numRetiro, int numMatricula, String fecha, String hora) {
		this.numRetiro = numRetiro;
		this.numMatricula = numMatricula;
		this.fecha = fecha;
		this.hora = hora;
	}
	
	
	// GETTER
	public int getNumRetiro() {
		return numRetiro;
	}
	public int getNumMatricula() {
		return numMatricula;
	}
	public String getFecha() {
		return fecha;
	}
	public String getHora() {
		return hora;
	}
	
}
