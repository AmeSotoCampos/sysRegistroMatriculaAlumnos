/**
Descripción:clase que representa la clase CursoDAO en el sistema de registro y matrícula de alumnos. Contiene métodos para realizar operaciones de acceso a datos relacionadas con la entidad Curso, como adicionar, consultar, modificar y eliminar registros de cursos en la base de datos.
@author America Soto Campos
@version 1.0
@since 4/10/2026
*/


package com.sysRegistroMatriculaAlumnos.dao;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;

import com.sysRegistroMatriculaAlumnos.entity.*;

public class CursoDAO {
	
	private ArrayList<Curso> cursos;
	private String archivo;
	
	
	// constructor
	public CursoDAO(String archivo) {
		cursos = new ArrayList<Curso>();
		this.archivo = archivo;
		leerCursos();
	}


	// GET/ SET
	public ArrayList<Curso> getCursos() {
		return cursos;
	}


	public void setCursos(ArrayList<Curso> cursos) {
		this.cursos = cursos;
	}


	public String getArchivo() {
		return archivo;
	}


	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}

	// ADICIONAR - TAMAÑO - OBTENER - BUSCAR X CODIGO
	public void adicionar(Curso x) {
		cursos.add(x);
	}

	public int tamaño() {
		return cursos.size();
	}

	public Curso obtener(int posicion) {
		return cursos.get(posicion);
	}

	public void eliminar(Curso x) {
		cursos.remove(x);
	}

	// mio
	public Curso buscar(int codigo) {
		Curso x;
		for (int i = 0; i < tamaño(); i++) {
			x = obtener(i);
			if (x.getCodCurso() == codigo)
				return x;
		}
		return null;
	}
	
	public int codigoCorrelativo() {
		if (tamaño() == 50502020)
			return 0;
		else
			return obtener(tamaño() - 1).getCodCurso() + 1 ;
	}

	
	
	// Gravar cursos
	public void grabarCursos() {
		try {
			PrintWriter pw;
			String linea;
			Curso x;
			pw = new PrintWriter(new FileWriter("Cursos.txt"));
			for (int i = 0; i < tamaño(); i++) {
				x = obtener(i);
				linea = x.getCodCurso() + "," +
						x.getAsignatura() + "," +
						x.getCiclo() + "," +
						x.getCréditos()+ "," +
						x.getHoras() + "," +
						x.getFecha() + "," +
						x.getHora();

				 		pw.println(linea);
				
			}
			pw.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	// Leer o cargar Cursos
	
	public void leerCursos() {
	    File archivo = new File("Cursos.txt");
	    
	    // Si el archivo no existe, no hace nada para evitar errores
	    if (!archivo.exists()) {
	        return; 
	    }
	    
	    cursos.clear(); 

	    try {
	        BufferedReader br = new BufferedReader(new FileReader(archivo));
	        String linea, asignatura, fecha, hora;
	        String s [];
	        int codCurso,  ciclo, creditos, horas;
	        
	        while ((linea = br.readLine()) != null) {
	            s = linea.split(",");
	            
	            codCurso = Integer.parseInt(s[0].trim()); 
	            asignatura = s[1].trim();  
	            ciclo = Integer.parseInt(s[2].trim()); 
	            creditos = Integer.parseInt(s[3].trim()); 
	            horas = Integer.parseInt(s[4].trim()); 
	            fecha = s[5].trim();
	            hora = s[6].trim();
	            
	            Curso x = new Curso(codCurso, asignatura, ciclo, creditos, horas, fecha, hora);
	            cursos.add(x); 
	        }
	        br.close();
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	}
	
	
}
