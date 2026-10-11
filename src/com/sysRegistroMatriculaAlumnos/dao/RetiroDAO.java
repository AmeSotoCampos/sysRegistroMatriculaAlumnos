/**
Descripción:clase que implementa el patrón DAO para la entidad Retiro, permitiendo la gestión de los retiros de matrícula de los alumnos en un sistema de registro académico. Esta clase proporciona métodos para agregar, eliminar, buscar y listar retiros, así como para leer y escribir datos en un archivo de texto.
@author Edwin Huamanñahui Aedo
@version 1.0
@since 7/10/2026
*/
package com.sysRegistroMatriculaAlumnos.dao;

import java.io.*;
import java.util.ArrayList;
import com.sysRegistroMatriculaAlumnos.entity.Retiro;

public class RetiroDAO {

	private ArrayList<Retiro> retiros;
	private String archivo;

	public RetiroDAO(String archivo) {
		this.retiros = new ArrayList<Retiro>();
		this.archivo = archivo;
		leerRetiros();
	}

	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}

	public String getArchivo() {
		return archivo;
	}

	public void adicionar(Retiro x) {
		retiros.add(x);
	}

	// Métodos de tamaño (ambos para compatibilidad con código existente)
	public int tamaño() {
		return retiros.size();
	}

	public int tamanio() {
		return retiros.size();
	}

	public Retiro obtener(int posicion) {
		return retiros.get(posicion);
	}

	public void eliminar(Retiro x) {
		retiros.remove(x);
	}

	public Retiro buscar(int numRetiro) {
		for (int i = 0; i < retiros.size(); i++) {
			Retiro x = retiros.get(i);
			if (x.getNumRetiro() == numRetiro) {
				return x;
			}
		}
		return null;
	}

	public Retiro buscarPorMatricula(int numMatricula) {
		for (int i = 0; i < retiros.size(); i++) {
			Retiro x = retiros.get(i);
			if (x.getNumMatricula() == numMatricula) {
				return x;
			}
		}
		return null;
	}

	public int codigoCorrelativo() {
		if (retiros.isEmpty()) {
			return 200001; // Correlativo inicial para retiros
		} else {
			return retiros.get(retiros.size() - 1).getNumRetiro() + 1;
		}
	}

	public void grabarRetiros() {
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(archivo));
			for (int i = 0; i < retiros.size(); i++) {
				Retiro x = retiros.get(i);
				String linea = x.getNumRetiro() + "," +
						x.getNumMatricula() + "," +
						x.getFecha() + "," +
						x.getHora();
				pw.println(linea);
			}
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void leerRetiros() {
		File f = new File(archivo);
		if (!f.exists()) {
			return;
		}

		retiros.clear();

		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			String linea;
			while ((linea = br.readLine()) != null) {
				String[] s = linea.split(",");
				int numRetiro = Integer.parseInt(s[0].trim());
				int numMatricula = Integer.parseInt(s[1].trim());
				String fecha = s[2].trim();
				String hora = s[3].trim();

				retiros.add(new Retiro(numRetiro, numMatricula, fecha, hora));
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}











