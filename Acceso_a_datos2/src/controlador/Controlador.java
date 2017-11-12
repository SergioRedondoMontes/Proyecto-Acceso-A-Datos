package controlador;

import java.util.HashMap;
import java.util.Scanner;

import accesoDatos.I_Acceso_Datos;
import entidades.Alumno;
import entidades.Titulacion;
import interfazUsuario.VistaPrincipal;

public class Controlador {

	Scanner teclado;
	SeleccionAccesoDatos selector;
	I_Acceso_Datos accesoDatos;
	VistaPrincipal vistaP;
	
	HashMap<String, Alumno> recogerAlumnos;//deposito
	HashMap<String, Titulacion> recogerTitulaciones;//dispensadores
	

	

	String mensaje;

	public Controlador(Scanner miScanner) {
		teclado = miScanner;
		selector = new SeleccionAccesoDatos(miScanner);
		// Seleccionamos el acceso a datos
		accesoDatos = (I_Acceso_Datos) selector.elegirClase("accesoDatos");
		// Una vez tenemos el acceso a datos, obtenemos dispensadores y
		// depositos
		if (accesoDatos != null) {
			recogerAlumnos = accesoDatos.obtenerAlumno();
			recogerTitulaciones = accesoDatos.obtenerTitulacion();
			System.out.println(recogerAlumnos.size() + "  " + recogerTitulaciones.size());
			if((recogerAlumnos!=null) && (recogerTitulaciones!=null)){
				
				this.setVisibleInterfazGrafica();
				
		}
		
	}

	}

	private void setVisibleInterfazGrafica() {
		try {
			vistaP = new VistaPrincipal();
			this.crearTabla();
			vistaP.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	private void crearTabla() {
		if(!recogerAlumnos.isEmpty()){
		vistaP.crearTabla(recogerAlumnos);
		}else {
			System.out.println("vacio");
		}
		}

	public void nuevoAlumno() {
		Alumno alumno;
		Titulacion titulacion;
		titulacion = new Titulacion(recogerTitulaciones.get(vistaP.getTxtTitulacion()).getNombre(),recogerTitulaciones.get(vistaP.getTxtTitulacion()).getDescripcion());
		alumno = new Alumno(vistaP.getTxtDni(),vistaP.getTxtNombre(),vistaP.getTxtApellido(),Integer.parseInt(vistaP.getTxtTelefono()),vistaP.getTxtNacionalidad(),titulacion);
		
		accesoDatos.insertarAlumno(alumno);
	}

	public void actualizarAlumno() {
		Alumno alumno;
		Titulacion titulacion;
		titulacion = new Titulacion(recogerTitulaciones.get(vistaP.getTxtTitulacionMod()).getNombre(),recogerTitulaciones.get(vistaP.getTxtTitulacionMod()).getDescripcion());
		String dato=String.valueOf(vistaP.getTabla().getValueAt(vistaP.getTabla().getSelectedRow(),1));
		alumno = new Alumno(dato,vistaP.getTxtNombreMod(),vistaP.getTxtApellidoMod(),Integer.parseInt(vistaP.getTxtTelefonoMod()),vistaP.getTxtNacionalidadMod(),titulacion);
		accesoDatos.actualizarAlumnos(alumno);
		
	}

	public void eliminarUno() {
		String dni = (String) vistaP.getTabla().getValueAt(vistaP.getTabla().getSelectedRow(),1);
		accesoDatos.borrarAlumno(dni);
		
	}

	public void eliminarTodos() {
		accesoDatos.borrarTodoAlumnos();
		
	}

	public void nuevoCurso() {
		Titulacion titulacion;
		titulacion = new Titulacion(vistaP.getTxtNombreCurso(),vistaP.getTxtDescripcionCurso());
		
		accesoDatos.insertarTitulacion(titulacion);
		
	}
}