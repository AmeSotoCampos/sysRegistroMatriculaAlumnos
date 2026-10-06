package com.sysRegistroMatriculaAlumnos.entity;

public class Matricula {
	private int numMatricula;
	private int codAlumno; 
	private int codCurso;
	private String fecha;
	private String hora;
	
	// CONSTRUCTOR
	public Matricula(int numMatricula, int codAlumno, int codCurso, String fecha, String hora) {
		this.numMatricula = numMatricula;
		this.codAlumno = codAlumno;
		this.codCurso = codCurso;
		this.fecha = fecha;
		this.hora = hora;
	}

	// SETTER
	public void setCodCurso(int codCurso) {
		this.codCurso = codCurso;
	}
	
	// GETTER
	public int getNumMatricula() {
		return numMatricula;
	}
	public int getCodAlumno() {
		return codAlumno;
	}
	public int getCodCurso() {
		return codCurso;
	}
	public String getFecha() {
		return fecha;
	}
	public String getHora() {
		return hora;
	}
	
}
