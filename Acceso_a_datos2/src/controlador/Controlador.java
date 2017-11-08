package controlador;

import java.util.HashMap;
import java.util.Scanner;

import org.hibernate.type.TypeResolver;

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
			vistaP.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}