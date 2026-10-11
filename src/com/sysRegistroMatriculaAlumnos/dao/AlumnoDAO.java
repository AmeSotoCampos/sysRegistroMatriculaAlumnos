/**
Descripción:clase que representa la clase AlumnoDAO en el sistema de registro y matrícula de alumnos. Contiene métodos para realizar operaciones de acceso a datos relacionadas con la entidad Alumno, como adicionar, consultar, modificar y eliminar registros de alumnos en la base de datos.
@author America Soto Campos
@version 1.0
@since 4/10/2026
*/


package com.sysRegistroMatriculaAlumnos.dao;

import java.util.ArrayList;
import java.io.*;
import com.sysRegistroMatriculaAlumnos.entity.Alumno;

public class AlumnoDAO {

	private ArrayList<Alumno> alumnos;
	private String archivo;
	
	
	// constructor
	public AlumnoDAO(String archivo) {
	alumnos = new ArrayList<Alumno>();
	this.archivo = archivo;
	leerAlumnos();

	
			
	}

	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}
	
	public String getArchivo() {
		return archivo;
	}

	public void adicionar(Alumno x) {
		alumnos.add(x);
	}

	public int tamaño() {
		return alumnos.size();
	}

	public Alumno obtener(int posicion) {
		return alumnos.get(posicion);
	}

	public void eliminar(Alumno x) {
		alumnos.remove(x);
	}

	// mio
	public Alumno buscar(int codigo) {
		Alumno x;
		for (int i = 0; i < tamaño(); i++) {
			x = obtener(i);
			if (x.getCodAlumno() == codigo)
				return x;
		}
		return null;
	}
	

	public Alumno buscar(String dni) {
		Alumno x;
		for (int i = 0; i < tamaño(); i++) {
			x = obtener(i);
			if (x.getDni().equals(dni))
				return x;
		}
		return null;
	}
	


//	operaciones publicas complementarias
	public int codigoCorrelativo() {
		if (tamaño() == 0)
			return 202010001;
		else
			return obtener(tamaño() - 1).getCodAlumno() + 1 ;
	}


	
	public void grabarAlumnos() {
		try {
			PrintWriter pw;
			String linea;
			Alumno x;
			pw = new PrintWriter(new FileWriter(archivo));
			for (int i = 0; i < tamaño(); i++) {
				x = obtener(i);
				linea = x.getCodAlumno() + "," +
						x.getNombres() + "," +
				 		x.getApellidos() + "," +
				 		x.getDni() + "," +
				 		x.getEdad() + "," +
				 		x.getCelular() + "," +
				 		x.getEstado()+ "," +
						x.getFecha() + "," +
						x.getHora();
				 		pw.println(linea);
				
			}
			pw.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	// 
	
	
	
	public void leerAlumnos() {
		File archivo = new File(this.archivo);
	    
	  // si archivo no existe
	    if (!archivo.exists()) {
	        return; 
	    }
	    
	    alumnos.clear(); 

	    try {
	        BufferedReader br = new BufferedReader(new FileReader(archivo));
	        String linea, nombres, apellidos, dni, fecha, hora;
	        String s [];
	        int codAlumno, edad, celular, estado;
	        
	        while ((linea = br.readLine()) != null) {
	            s = linea.split(",");
	            
	            codAlumno = Integer.parseInt(s[0].trim()); 
	            nombres = s[1].trim(); 
	            apellidos = s[2].trim(); 
	            dni = s[3].trim(); 
	            edad = Integer.parseInt(s[4].trim()); 
	            celular = Integer.parseInt(s[5].trim()); 
	            estado = Integer.parseInt(s[6].trim()); 
	            fecha = s[7].trim();
	            hora = s[8].trim();
	            
	            Alumno x = new Alumno(codAlumno, nombres, apellidos, dni, edad, celular, estado, fecha, hora);
	            alumnos.add(x); 
	        }
	        br.close();
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	}
	
	

}

