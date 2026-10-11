/**
Descripción:clase que representa la entidad Retiro, utilizada para gestionar los retiros de matrículas de los alumnos en un sistema de registro académico. Esta clase contiene atributos y métodos para almacenar y acceder a la información relacionada con los retiros, como el número de retiro, el número de matrícula, la fecha y la hora del retiro.
@author Edwin Huamanñahui Aedo
@version 1.0
@since 7/10/2026
*/
package com.sysRegistroMatriculaAlumnos.entity;

public class Retiro {

	private int numRetiro;
	private int numMatricula;
	private String fecha;
	private String hora;


	// Constructor con parámetros
	public Retiro(int numRetiro, int numMatricula, String fecha, String hora) {
		this.numRetiro = numRetiro;
		this.numMatricula = numMatricula;
		this.fecha = fecha;
		this.hora = hora;
	}

	// GETTERS
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

	// SETTERS
	public void setNumRetiro(int numRetiro) {
		this.numRetiro = numRetiro;
	}

	public void setNumMatricula(int numMatricula) {
		this.numMatricula = numMatricula;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}
	
}