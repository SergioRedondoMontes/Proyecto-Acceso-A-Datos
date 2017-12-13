package accesoDatos;

import java.util.HashMap;

import entidades.Alumno;
import entidades.Titulacion;

public class AccesoPhpJson implements I_Acceso_Datos{

	@Override
	public HashMap<String, Alumno> obtenerAlumno() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertarAlumno(Alumno alumno) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean borrarAlumno(String dni) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean borrarTodoAlumnos() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean actualizarAlumnos(Alumno alumno) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertarTodosAlumnos(HashMap<String, Alumno> mapAlumno) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public HashMap<String, Titulacion> obtenerTitulacion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertarTitulacion(Titulacion titulacion) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertarTodosTitulaciones(HashMap<String, Titulacion> mapTitulacion) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
