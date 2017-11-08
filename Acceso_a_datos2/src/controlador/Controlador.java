package controlador;

import java.util.HashMap;
import java.util.Scanner;

import accesoDatos.I_Acceso_Datos;
import entidades.Alumno;
import entidades.Titulacion;

public class Controlador {

	Scanner teclado;
	SeleccionAccesoDatos selector;
	I_Acceso_Datos accesoDatos;
	
	HashMap<String, Alumno> recogerAlumnos;//deposito
	HashMap<Integer, Titulacion> recogerTitulaciones;//dispensadores
	


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

			if((recogerAlumnos!=null) && (recogerTitulaciones!=null)){
//				// Y creamos la maquina
//				maquinaRefrescos = new Maquina(recogerAlumnos, recogerTitulaciones);
//				
//				if (maquinaRefrescos != null) {
//					System.out.println("Acceso a datos funcionando");
//					
//					// Una vez tenemos la maquina creada, iniciamos interfaz
//					
//					interfaz = (Generico) selector.elegirClase("interfazUsuario");
//					if (interfaz != null) {
//						interfaz.inicializar(this, teclado);
//						interfaz.ejecucionMaquinaRefrescos();
//						this.mostrarMensaje(true);
//					}	else{
//						System.out.println("Finaliza la ejecucion");
//						System.exit(1);
//					}				
//					
//				}  else{
//					System.out.println("No se ha podido inicializar la maquina\nFinaliza la ejecucion");
//					System.exit(1);
//				}
//			} else{
//				System.out.println("No se ha podido inicializar la maquina\nFinaliza la ejecucion");
//				System.exit(1);
//			}
//		} else{
//			System.out.println("No se ha podido inicializar la maquina\nFinaliza la ejecucion");
//			System.exit(1);
		}
		
	}

	}
}