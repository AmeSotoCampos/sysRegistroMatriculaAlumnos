/**
Descripción:clase que representa la entidad Curso en el sistema de registro y matrícula de alumnos. Contiene atributos y métodos relacionados con la información del curso, como código, asignatura, ciclo, créditos y horas.
@author America Soto Campos
@version 1.0
@since 4/10/2026
*/


package com.sysRegistroMatriculaAlumnos.entity;

public class Curso {
	private int codCurso;
	private String asignatura;
	private int ciclo;
	private int créditos;
	private int horas;
	private String fecha;
	private String hora;
	
	
	// CONSTRUCTOR
	public Curso(int codCurso, String asignatura, int ciclo, int créditos, int horas, String fecha, String hora) {
		this.codCurso = codCurso;
		this.asignatura = asignatura;
		this.ciclo = ciclo;
		this.créditos = créditos;
		this.horas = horas;
		this.fecha = fecha;
		this.hora = hora;
	}
	
	
	// SETTER
	public void setAsignatura(String asignatura) {
		this.asignatura = asignatura;
	}
	public void setCiclo(int ciclo) {
		this.ciclo = ciclo;
	}
	public void setCréditos(int créditos) {
		this.créditos = créditos;
	}
	public void setHoras(int horas) {
		this.horas = horas;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}



	// GETTER
	public int getCodCurso() {
		return codCurso;
	}
	public String getAsignatura() {
		return asignatura;
	}
	public int getCiclo() {
		return ciclo;
	}
	public int getCréditos() {
		return créditos;
	}
	public int getHoras() {
		return horas;
	}
	public String getFecha() {
		return fecha;
	}
	public String getHora() {
		return hora;
	}
	
	
}
