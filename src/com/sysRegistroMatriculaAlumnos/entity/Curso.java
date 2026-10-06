package com.sysRegistroMatriculaAlumnos.entity;

public class Curso {
	private int codCurso;
	private String asignatura;
	private int ciclo;
	private int créditos;
	private int horas;
	
	
	// CONSTRUCTOR
	public Curso(int codCurso, String asignatura, int ciclo, int créditos, int horas) {
		this.codCurso = codCurso;
		this.asignatura = asignatura;
		this.ciclo = ciclo;
		this.créditos = créditos;
		this.horas = horas;
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
	

	


	
	
	
}
