package com.sysRegistroMatriculaAlumnos.entity;

public class Alumno {
	private int codAlumno;
	private String nombres;
	private String apellidos;
	private String dni;
	private int edad;
	private int celular;
	private int estado;
	
	
	// CONSTRUCTOR
	public Alumno(String nombres, String apellidos, String dni, int edad, int celular) {
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.dni = dni;
		this.edad = edad;
		this.celular = celular;
	}
	
	
	// SETTER
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public void setCelular(int celular) {
		this.celular = celular;
	}
	
	
	// GETTER
	public int getCodAlumno() {
		return codAlumno;
	}
	public String getNombres() {
		return nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public String getDni() {
		return dni;
	}
	public int getEdad() {
		return edad;
	}
	public int getCelular() {
		return celular;
	}
	public int getEstado() {
		return estado;
	}
	
	
}


