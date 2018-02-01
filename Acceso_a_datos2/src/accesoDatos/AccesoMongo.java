package accesoDatos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import org.bson.BsonDocument;
import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import entidades.Alumno;
import entidades.Titulacion;

public class AccesoMongo implements I_Acceso_Datos {

	MongoClient mongoClient;
	MongoCollection<Document> collection;
	MongoDatabase db;
	HashMap<String, Alumno> recogerAlumnos;
	HashMap<String, Titulacion> recogerTitulaciones;

	public AccesoMongo() {
		try {
			// PASO 1: Conexión al Server de MongoDB Pasandole el host y el
			// puerto
			mongoClient = new MongoClient("localhost", 27017);

			// PASO 2: Conexión a la base de datos
			db = mongoClient.getDatabase("adat_alumnos");
			System.out.println("Conectado a BD MONGO");
			recogerAlumnos = new HashMap<String, Alumno>();
			recogerTitulaciones = new HashMap<String, Titulacion>();

		} catch (Exception e) {
			System.out.println("Error leyendo la BD MONGO: " + e.getMessage());
			System.out.println("Fin de la ejecucion del programa");
			System.exit(1);
		}

	}

	@Override
	public HashMap<String, Alumno> obtenerAlumno() {
		obtenerTitulacion();

		Alumno nuevoAlumno;
		int cod;
		String dni;
		String nombre;
		String apellido;
		int telefono;
		String nacionalidad;
		Titulacion titulacionAlumno;

		try {

			// PASO 3: Obtenemos una coleccion para trabajar con ella
			collection = db.getCollection("titulaciones");

			// PASO 4.2.1: "READ" -> Leemos todos los documentos de la base de
			// datos
			int numDocumentos = (int) collection.count();
			System.out.println("Número de documentos (registros) en la colección alumnos: " + numDocumentos + "\n");

			BsonDocument documentBson;
			Document alumnoDocument = null;

			for (Entry<String, Titulacion> entry : recogerTitulaciones.entrySet()) {
				String key = entry.getKey();
				Titulacion titulacion = entry.getValue();
				ArrayList<Document> arrAlumno = titulacion.getArrayAlumnos();
				for (int i = 0; i < arrAlumno.size(); i++) {
					cod = arrAlumno.get(i).getInteger("cod");
					nombre = arrAlumno.get(i).getString("nombre");
					dni = arrAlumno.get(i).getString("dni");
					apellido = arrAlumno.get(i).getString("apellido");
					telefono = arrAlumno.get(i).getInteger("telefono");
					nacionalidad = arrAlumno.get(i).getString("nacionalidad");
					titulacionAlumno = recogerTitulaciones.get(arrAlumno.get(i).getString("titulacionAlumno"));
					nuevoAlumno = new Alumno(cod, dni, nombre, apellido, telefono, nacionalidad, titulacionAlumno);
					recogerAlumnos.put(dni, nuevoAlumno);

				}

				// do what you have to do here
				// In your case, another loop.
			}

		} catch (Exception ex) {
			System.out.println("Error leyendo la coleccion: no se ha podido acceder a los datos");
			System.out.println(ex.getMessage());
			ex.printStackTrace();
			System.out.println("Fin de la ejecucion del programa");
			System.exit(1);
		}

		System.out.println("Leidos datos de la coleccion de Depositos");
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
		Titulacion nuevaTitulacion;

		String cod;
		String nombre;
		String descripcion;

		try {

			// PASO 3: Obtenemos una coleccion para trabajar con ella
			collection = db.getCollection("titulaciones");

			// PASO 4.2.1: "READ" -> Leemos todos los documentos de la base de
			// datos
			int numDocumentos = (int) collection.count();
			System.out
					.println("Número de documentos (registros) en la colección titulaciones: " + numDocumentos + "\n");

			// Busco todos los documentos de la colección, creo el objeto
			// deposito y lo almaceno en el hashmap
			MongoCursor<Document> cursor = collection.find().iterator();

			while (cursor.hasNext()) {

				Document rs = cursor.next();
				ArrayList<Document> arrAlumnos = (ArrayList<Document>) rs.get("alumnos");
				nombre = rs.getString("titulacion");

				descripcion = rs.getString("descripcion");

				nuevaTitulacion = new Titulacion(nombre, descripcion, arrAlumnos);
				// Una vez creado el deposito con valor de la moneda y cantidad
				// lo metemos en el hashmap
				recogerTitulaciones.put(nombre, nuevaTitulacion);

			}

		} catch (Exception ex) {
			System.out.println("Error leyendo la coleccion: no se ha podido acceder a los datos");
			System.out.println(ex.getMessage());
			ex.printStackTrace();
			System.out.println("Fin de la ejecucion del programa");
			System.exit(1);
		}

		System.out.println("Leidos datos de la coleccion de Depositos");
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
