package accesoDatos;

import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import auxiliares.ApiRequests;
import entidades.Alumno;
import entidades.Titulacion;

public class AccesoPhpJson implements I_Acceso_Datos{
	
	ApiRequests encargadoPeticiones;
	private String SERVER_PATH, GET;
	HashMap<String, Alumno> recogerAlumnos;
	HashMap<String, Titulacion> recogerTitulaciones;
	
	public AccesoPhpJson(){
		
		encargadoPeticiones = new ApiRequests();
		

		SERVER_PATH = "http://localhost/adat_alumnos/";
	
		
	}

	@Override
	public HashMap<String, Alumno> obtenerAlumno() {
		
		obtenerTitulacion();
		recogerAlumnos = new HashMap<String, Alumno>();
		GET = "leerAlumnos.php";
		
		try{
			
		    System.out.println("---------- Leemos datos de JSON --------------------");	
		    
			System.out.println("Lanzamos peticion JSON para alumnos");
			
			String url = SERVER_PATH + GET; // Sacadas de configuracion
			
			System.out.println("La url a la que lanzamos la peticion es " + url);
			
			String response = encargadoPeticiones.getRequest(url);
			
			System.out.println(response); // Traza para pruebas
			JSONObject respuesta = (JSONObject) JSONValue.parse(response);
			JSONArray alumnos = (JSONArray) respuesta.get("Alumnos");
			System.out.println("--------"+alumnos);
			for (Object object : alumnos) {
				JSONObject aux = (JSONObject) object;
				
				
				String clave = aux.get("dni").toString();
				int cod = Integer.parseInt(aux.get("cod").toString());
				String nombre = aux.get("nombre").toString();
				String apellido = aux.get("apellido").toString();
				int telefono = Integer.parseInt(aux.get("telefono").toString());
				String nacionalidad = aux.get("nacionalidad").toString();
				System.err.println("antes de titu");
				Titulacion titulacion = recogerTitulaciones.get(aux.get("titulacion").toString());
				System.err.println("despues de titu");
				Alumno alumno = new Alumno(cod,clave,nombre,apellido,telefono,nacionalidad,titulacion);
				System.err.println("despues de alumno");
				recogerAlumnos.put(clave, alumno);
				System.err.println("despues de recoger alumnos");
			}
			System.out.println("-----------------"+recogerAlumnos.size());
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return recogerAlumnos;
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
		GET = "leerTitulaciones.php";
		recogerTitulaciones = new HashMap<String, Titulacion>();
		
		try{
			
		    System.out.println("---------- Leemos datos de JSON --------------------");	
		    
			System.out.println("Lanzamos peticion JSON para titulaciones");
			
			String url = SERVER_PATH + GET; // Sacadas de configuracion
			
			System.out.println("La url a la que lanzamos la peticion es " + url);
			
			String response = encargadoPeticiones.getRequest(url);
			
			//System.out.println(response); // Traza para pruebas
			JSONObject respuesta = (JSONObject) JSONValue.parse(response);
			JSONArray titulaciones = (JSONArray) respuesta.get("Titulaciones");
			for (Object object : titulaciones) {
				JSONObject aux = (JSONObject) object;
				String clave = aux.get("nombre").toString();
				Titulacion titulacion = new Titulacion(Integer.parseInt(aux.get("cod").toString()),
						clave, aux.get("descripcion").toString());
				recogerTitulaciones.put(clave, titulacion);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return recogerTitulaciones;
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
