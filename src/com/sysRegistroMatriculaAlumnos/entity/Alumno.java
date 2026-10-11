/**
Descripción:clase que representa la entidad Alumno en el sistema de registro y matrícula de alumnos. Contiene atributos y métodos relacionados con la información del alumno, como nombres, apellidos, DNI, edad, celular y estado.
@author America Soto Campos
@version 1.0
@since 4/10/2026
*/

package com.sysRegistroMatriculaAlumnos.entity;

import padre.Persona;

public class Alumno extends Persona {
	private int codAlumno,celular, estado;
	private String fecha;
	private String hora;
	
	
	
	// CONSTRUCTOR
	public Alumno(int codAlumno, String nombres, String apellidos, String dni, int edad, int celular,int estado, String fecha, String hora) {
		super(nombres, apellidos, dni, edad);
		this.codAlumno = codAlumno;
		this.celular = celular;
		this.estado = estado;
		this.fecha = fecha;
		this.hora = hora;
	}
	
	
	// SETTER
	public void setCodAlumno(int codAlumno) {
		this.codAlumno = codAlumno;
	}

	public void setCelular(int celular) {
		this.celular = celular;
	}
	

	public void setEstado(int estado) {
	    this.estado = estado;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}

	

	
	// GETTER
	public int getCodAlumno() {
		return codAlumno;
	}

	public int getCelular() {
		return celular;
	}
	public int getEstado() {
		return estado;
	}
	public String getFecha() {
		return fecha;
	}
	public String getHora() {
		return hora;
	}
	
	// mi codigo para probar en consola que me imprime todos los datos del objeto existente
	@Override
	public String toString() {
	    return "Alumno : " + codAlumno + ", " + nombres + 
	           ", " + apellidos + ", " + dni + 
	           ", " + edad + ", " + celular;
	}
}



