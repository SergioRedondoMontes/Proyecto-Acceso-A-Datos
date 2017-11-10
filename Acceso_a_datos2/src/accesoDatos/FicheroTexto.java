package accesoDatos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import entidades.Alumno;
import entidades.Titulacion;

public class FicheroTexto implements I_Acceso_Datos{
	
	HashMap<String, Alumno> recogerAlumnos;
	File fAlumnos;
	int nAlumnos;
	HashMap<String, Titulacion> recogerTitulaciones;
	File fTitulaciones;
	int nTitulaciones;
	
	@Override
	public HashMap<String, Alumno> obtenerAlumno() {
		recogerAlumnos = new HashMap<String, Alumno>();
		fAlumnos = new File("Ficheros/datos/alumnos.txt");
		obtenerTitulacion();
		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader(fAlumnos));
			String text = null;
			String clave;
			Alumno alumno = null;

			while ((text = reader.readLine()) != null) {

				String[] datosaux = text.split(",");
				clave = datosaux[1];
				alumno = new Alumno(Integer.parseInt(datosaux[0]), clave, datosaux[2], datosaux[3],
						Integer.parseInt(datosaux[4]), datosaux[5],comprobarTitulacion(Integer.parseInt(datosaux[6])));
				recogerAlumnos.put(clave,alumno);
			}
			nAlumnos = recogerAlumnos.size();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return recogerAlumnos;
	}

	@Override
	public boolean insertarAlumno(Alumno alumno) {
		boolean todoOK = true;
		
		FileWriter fichero = null;
		PrintWriter pw = null;
		
		try {
			fichero = new FileWriter("bbdd/alumnos.txt",true);
			pw = new PrintWriter(fichero);

				pw.println((nAlumnos+1) + "," + alumno.getDni().toLowerCase() + "," + alumno.getNombre().toLowerCase() + ","
						+ alumno.getApellido().toLowerCase() + "," + alumno.getTelefono() + ","
						+ alumno.getNacionalidad().toLowerCase() + ","
								+ alumno.getTitulacionAlumno().getCod());
				nAlumnos++;
				System.out.println(nAlumnos);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			todoOK=false;
		}

		try {
			if (fichero != null) {
				pw.close();
				fichero.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			todoOK=false;
		}
		return todoOK;
	}
	
	@Override
	public boolean insertarTodosAlumnos(HashMap<String, Alumno> mapAlumno) {
		boolean todoOK = true;
		for(String key:mapAlumno.keySet()){
			todoOK = this.insertarAlumno(mapAlumno.get(key));
		}
		return todoOK;
	}
	
	@Override
	public boolean actualizarAlumnos(Alumno alumno) {
		boolean todoOK = true;
		recogerAlumnos.put(alumno.getDni(), alumno);
		try {
			insertarTodosAlumnos(recogerAlumnos);
		} catch (Exception e) {
			todoOK = false;
		}
		
		return todoOK;
	}

	@Override
	public boolean borrarAlumno(String dni) {
		boolean todoOK = true;
		try {
			recogerAlumnos.remove(dni);
			insertarTodosAlumnos(recogerAlumnos);
		} catch (Exception e) {
			todoOK = false;
		}
		
		return todoOK;
	}

	@Override
	public boolean borrarTodoAlumnos() {
		boolean todoOK = true;
		PrintWriter writer;
		try {
			writer = new PrintWriter(fAlumnos);
			writer.print("");
			writer.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			todoOK=false;
		}
		return todoOK;
	}

	

	@Override
	public HashMap<String, Titulacion> obtenerTitulacion() {
		boolean todoOK = true;
		recogerTitulaciones = new HashMap<String, Titulacion>();
		fTitulaciones = new File("Ficheros/datos/titulaciones.txt");
		BufferedReader reader = null;
		Integer clave;

		try {
			reader = new BufferedReader(new FileReader(fTitulaciones));
			String text = null;
			Titulacion titulacion = null;

			while ((text = reader.readLine()) != null) {

				String[] datosaux = text.split(",");
				clave = Integer.parseInt(datosaux[0]);
				titulacion = new Titulacion(clave,datosaux[1],datosaux[2]);
				recogerTitulaciones.put(datosaux[1],titulacion);
				

			}
			nTitulaciones=recogerTitulaciones.size();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			todoOK = false;
		} catch (IOException e) {
			e.printStackTrace();
			todoOK = false;
		}
		try {
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
			todoOK = false;
		}

		return recogerTitulaciones;
	}

	@Override
	public boolean insertarTitulacion(Titulacion titulacion) {
		boolean todoOK = true;
		FileWriter fichero = null;
		PrintWriter pw = null;
		try {
			fichero = new FileWriter("bbdd/Titulaciones.txt",true);
			pw = new PrintWriter(fichero);
			nTitulaciones++;
			pw.println(nTitulaciones + "," + titulacion.getNombre().toLowerCase() +","+titulacion.getDescripcion().toLowerCase());

			recogerTitulaciones.put(titulacion.getNombre().toLowerCase(), titulacion);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			todoOK = false;
		}

		try {
			if (fichero != null) {
				pw.close();
				fichero.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			todoOK = false;
		}
		return todoOK;
	}
	
	private Titulacion comprobarTitulacion(int titu){
		Titulacion titulacion = new Titulacion();
		for(String key:recogerTitulaciones.keySet()){
			Titulacion value=recogerTitulaciones.get(key);
			if (titu==value.getCod()) {
				titulacion = value;
			}
		}
		return titulacion;
	}

}
