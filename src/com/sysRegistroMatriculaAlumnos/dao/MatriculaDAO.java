/**
Descripción:clase que implementa el patrón DAO para la entidad Matricula, permitiendo la gestión de las matrículas de los alumnos en un sistema de registro académico. Esta clase proporciona métodos para agregar, eliminar, buscar y listar matrículas, así como para leer y escribir datos en un archivo de texto.
@author Edwin Huamanñahui Aedo
@version 1.0
@since 7/10/2026
*/
package com.sysRegistroMatriculaAlumnos.dao;

import java.util.ArrayList;
import java.io.*;
import com.sysRegistroMatriculaAlumnos.entity.Matricula;

public class MatriculaDAO {

	private ArrayList<Matricula> matriculas;
	private String archivo;

	// Constructor
	public MatriculaDAO(String archivo) {
		matriculas = new ArrayList<Matricula>();
		this.archivo = archivo;
		leerMatriculas();
	}

	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}

	public String getArchivo() {
		return archivo;
	}

	public void adicionar(Matricula x) {
		matriculas.add(x);
	}

	public int tamanio() {
		return matriculas.size();
	}

	public Matricula obtener(int posicion) {
		return matriculas.get(posicion);
	}

	public void eliminar(Matricula x) {
		matriculas.remove(x);
	}

	// Buscar por número de matrícula
	public Matricula buscar(int numMatricula) {
		Matricula x;
		for (int i = 0; i < tamanio(); i++) {
			x = obtener(i);
			if (x.getNumMatricula() == numMatricula)
				return x;
		}
		return null;
	}

	// Buscar si un alumno ya cuenta con una matrícula registrada
	public Matricula buscarPorAlumno(int codAlumno) {
		Matricula x;
		for (int i = 0; i < tamanio(); i++) {
			x = obtener(i);
			if (x.getCodAlumno() == codAlumno)
				return x;
		}
		return null;
	}

	// Operaciones complementarias: Correlativo numérico
	public int codigoCorrelativo() {
		if (tamanio() == 0)
			return 100001; // Código inicial estándar para matrículas
		else
			return obtener(tamanio() - 1).getNumMatricula() + 1;
	}

	// Grabar datos en el archivo de texto
	public void grabarMatriculas() {
		try {
			PrintWriter pw;
			String linea;
			Matricula x;
			pw = new PrintWriter(new FileWriter(archivo));
			for (int i = 0; i < tamanio(); i++) {
				x = obtener(i);
				linea = x.getNumMatricula() + "," +
						x.getCodAlumno() + "," +
						x.getCodCurso() + "," +
						x.getFecha() + "," +
						x.getHora();
				pw.println(linea);
			}
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Cargar datos desde el archivo de texto
	public void leerMatriculas() {
		File f = new File(archivo);

		// Si el archivo aún no existe en el disco, no genera excepción
		if (!f.exists()) {
			return;
		}

		matriculas.clear();

		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			String linea, fecha, hora;
			String s[];
			int numMatricula, codAlumno, codCurso;

			while ((linea = br.readLine()) != null) {
				s = linea.split(",");

				numMatricula = Integer.parseInt(s[0].trim());
				codAlumno = Integer.parseInt(s[1].trim());
				codCurso = Integer.parseInt(s[2].trim());
				fecha = s[3].trim();
				hora = s[4].trim();

				Matricula x = new Matricula(numMatricula, codAlumno, codCurso, fecha, hora);
				matriculas.add(x);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}