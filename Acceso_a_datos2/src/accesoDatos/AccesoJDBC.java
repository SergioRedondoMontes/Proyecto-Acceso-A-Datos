package accesoDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import auxiliares.LeeProperties;
import entidades.Alumno;
import entidades.Titulacion;

public class AccesoJDBC implements I_Acceso_Datos {

	private String driver, urlbd, user, password; // Datos de la conexion
	private Connection conn1;
	HashMap<String, Alumno> recogerAlumnos;
	HashMap<String, Titulacion> recogerTitulaciones;

	public AccesoJDBC() {
		System.out.println("ACCESO A DATOS - Acceso JDBC");
		HashMap<String, String> datosConexion;
		try {
			

			LeeProperties properties = new LeeProperties("Ficheros/config/accesoJDBC.properties");
			datosConexion = properties.getHash();

			driver = datosConexion.get("driver");
			urlbd = datosConexion.get("urlbd");
			user = datosConexion.get("user");
			password = datosConexion.get("password");
			conn1 = null;

			Class.forName(driver);
			conn1 = DriverManager.getConnection(urlbd, user, password);
			if (conn1 != null) {
				System.out.println("Conectado a la base de datos");
			}

		} catch (ClassNotFoundException e1) {
			System.out.println("ERROR: No Conectado a la base de datos. No se ha encontrado el driver de conexion");
			// e1.printStackTrace();
			System.exit(1);
		} catch (SQLException e) {
			System.out.println("ERROR: No se ha podido conectar con la base de datos");
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}

	public int cerrarConexion() {
		try {
			conn1.close();
			System.out.println("Cerrada conexion");
			return 0;
		} catch (Exception e) {
			System.out.println("ERROR: No se ha cerrado corretamente");
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public HashMap<String, Alumno> obtenerAlumno() {
		recogerAlumnos = new HashMap<String, Alumno>();
		Alumno alumnoAux;
		String clave;
		String query = "SELECT * from depositos";
		Statement st;
		ResultSet rs;
		try {
			st = conn1.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				clave = rs.getString("DNI");
				alumnoAux = new Alumno(rs.getInt("cod"), clave, rs.getString("NOMBRE"), rs.getString("APELLIDO"),
						rs.getInt("TELEFONO"), rs.getString("NACIONALIDAD"), rs.getInt("Titulacion"));
				recogerAlumnos.put(clave, alumnoAux);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return recogerAlumnos;
	}

	@Override
	public boolean insertarAlumno(Alumno alumno) {
		boolean todoOK = true;
		PreparedStatement ps;
		String query;
		query = "INSERT INTO `alumnos` (`dni`, `nombre`, `apellido`, `telefono`, `nacionalidad`, `titulacion`) VALUES (?,?,?,?,?,?)";
		try {
			recogerAlumnos.put(alumno.getDni(), alumno);
			ps = conn1.prepareStatement(query);
			ps.setString(1, alumno.getDni().toLowerCase());
			ps.setString(2, alumno.getNombre().toLowerCase());
			ps.setString(3, alumno.getApellido().toLowerCase());
			ps.setInt(4, alumno.getTelefono());
			ps.setString(5, alumno.getNacionalidad().toLowerCase());
			ps.setInt(6, alumno.getTitulacion());
			if (ps.executeUpdate() == 1) {

				// JOptionPane.showMessageDialog(null, "Informaci�n almacenada
				// satisfactoriamente");
				// El memsaje no se tiene que mostrar aqui
			} else {
				// JOptionPane.showMessageDialog(null, "La informaci�n no pudo
				// ser almacenada");
				// El memsaje no se tiene que mostrar aqui
				todoOK = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			todoOK = false;
		}

		return todoOK;
	}

	@Override
	public boolean borrarAlumno(String dni) {
		boolean todoOK = true;
		PreparedStatement ps;
		String query;
		query = "DELETE FROM `alumnos` WHERE cod=" + dni;
		try {
			recogerAlumnos.remove(dni);
			ps = conn1.prepareStatement(query);
			if (ps.executeUpdate() == 1) {

				// JOptionPane.showMessageDialog(null, "Informaci�n borrada
				// satisfactoriamente");
				// El memsaje no se tiene que mostrar aqui
			} else {
				// JOptionPane.showMessageDialog(null, "La informaci�n no pudo ser borrada");
				// El memsaje no se tiene que mostrar aqui
				todoOK = false;
			}
		} catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
			todoOK = false;
		}

		return todoOK;
	}

	@Override
	public boolean borrarTodoAlumnos() {
		boolean todoOK = true;
		PreparedStatement ps;

		String query = "DELETE FROM `alumnos` ";
		try {
			ps = conn1.prepareStatement(query);

			if (ps.executeUpdate() == 1) {
				//JOptionPane.showMessageDialog(null, "Informaci�n borrada satisfactoriamente");
				// El memsaje no se tiene que mostrar aqui
				
			} else {
				//JOptionPane.showMessageDialog(null, "La informaci�n no pudo ser borrada");
				// El memsaje no se tiene que mostrar aqui
				todoOK = false;
			}
		} catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
			todoOK = false;
		}
		return todoOK;
	}

	@Override
	public boolean actualizarAlumnos(Alumno alumno) {
		boolean todoOK = true;
		PreparedStatement ps;
		String query;
		query = "UPDATE alumnos SET NOMBRE = ?, APELLIDO = ?,  TELEFONO = ?, NACIONALIDAD = ?, TITULACION = ? WHERE alumnos.DNI = '"+ alumno.getDni()+"'";
		try {
			recogerAlumnos.put(alumno.getDni(), alumno);
			ps = conn1.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, alumno.getNombre().toLowerCase());
			ps.setString(2, alumno.getApellido().toLowerCase());
			ps.setInt(3, alumno.getTelefono());
			ps.setString(4, alumno.getNacionalidad().toLowerCase());
			ps.setInt(5, alumno.getTitulacion());

			ps.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
			todoOK = false;
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
	public HashMap<String, Titulacion> obtenerTitulacion() {
		recogerTitulaciones = new HashMap<String, Titulacion>();
		String clave;
		String query = "SELECT * from titulaciones";
		Statement st;
		ResultSet rs;
		
		try {
			st = conn1.createStatement();
			rs = st.executeQuery(query);
			Titulacion titulacion;
			while (rs.next()) {
				clave=rs.getString("nombre");//puede que cambie y l aclave sea un string con el nombre ya que no va a poder repetirse
				titulacion = new Titulacion(rs.getInt("cod"),rs.getString("nombre"),rs.getString("descripcion"));
				recogerTitulaciones.put(clave, titulacion);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return recogerTitulaciones;
	}

	@Override
	public boolean insertarTitulacion(Titulacion titulacion) {
		boolean todoOK = true;
		PreparedStatement ps;

		String query = "INSERT INTO `titulaciones` (`cod`, `nombre`, `descripcion`) VALUES (?,?,?)";
		try {
			ps = conn1.prepareStatement(query);
			ps.setInt(1, titulacion.getCod());
			ps.setString(2, titulacion.getNombre().toLowerCase());
			ps.setString(3, titulacion.getDescripcion().toLowerCase());
			
			recogerTitulaciones.put(titulacion.getNombre(), titulacion);
			if (ps.executeUpdate() == 1) {

				//JOptionPane.showMessageDialog(null, "Informaci�n almacenada satisfactoriamente");
				// El memsaje no se tiene que mostrar aqui
			} else {
				//JOptionPane.showMessageDialog(null, "La informaci�n no pudo ser almacenada");
				// El memsaje no se tiene que mostrar aqui
				todoOK = false;
			}
		} catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
			todoOK = false;
		}
		return todoOK;
	}

}
