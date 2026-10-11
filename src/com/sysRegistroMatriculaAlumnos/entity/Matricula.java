/**
Descripción:clase que representa la entidad Matricula en el sistema de registro y matricula de alumnos. Esta clase encapsula los atributos y métodos relacionados con una matrícula, incluyendo el número de matrícula, el código del alumno, el código del curso, la fecha y la hora de la matrícula.
@author Edwin Huamanñahui Aedo
@version 1.0
@since 7/10/2026
*/
package com.sysRegistroMatriculaAlumnos.entity;

public class Matricula {
	
	private int numMatricula;
	private int codAlumno; 
	private int codCurso;
	private String fecha;
	private String hora;
	
	// Constructor por defecto
	public Matricula() {
	}

	// Constructor con parámetros
	public Matricula(int numMatricula, int codAlumno, int codCurso, String fecha, String hora) {
		this.numMatricula = numMatricula;
		this.codAlumno = codAlumno;
		this.codCurso = codCurso;
		this.fecha = fecha;
		this.hora = hora;
	}

	// GETTERS
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

	// SETTERS
	public void setNumMatricula(int numMatricula) {
		this.numMatricula = numMatricula;
	}

	public void setCodAlumno(int codAlumno) {
		this.codAlumno = codAlumno;
	}

	public void setCodCurso(int codCurso) {
		this.codCurso = codCurso;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}
}