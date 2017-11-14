package controlador;

import java.util.HashMap;
import java.util.Scanner;

import javax.swing.table.DefaultTableModel;

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
			vistaP.setControlador(this);
			vistaP.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	private void crearTabla() {
		recogerAlumnos = accesoDatos.obtenerAlumno();
		if(!recogerAlumnos.isEmpty()){
		vistaP.crearTabla(recogerAlumnos);
		}else {
			System.out.println("vacio");
		}
	}
	
	private void actualizarTabla() {
		recogerAlumnos = accesoDatos.obtenerAlumno();
		vistaP.actualizarTabla(recogerAlumnos);	
	}

	public void nuevoAlumno() {
		Alumno alumno;
		alumno = new Alumno(vistaP.getTxtDni(),vistaP.getTxtNombre(),vistaP.getTxtApellido(),Integer.parseInt(vistaP.getTxtTelefono()),vistaP.getTxtNacionalidad(),recogerTitulaciones.get(vistaP.getTxtTitulacion()));
		
		accesoDatos.insertarAlumno(alumno);
		actualizarTabla();
	}

	public void actualizarAlumno() {
		Alumno alumno;
		String dato=String.valueOf(vistaP.getTabla().getValueAt(vistaP.getTabla().getSelectedRow(),1));
		alumno = new Alumno(dato,vistaP.getTxtNombreMod(),vistaP.getTxtApellidoMod(),Integer.parseInt(vistaP.getTxtTelefonoMod()),vistaP.getTxtNacionalidadMod(),recogerTitulaciones.get(vistaP.getTxtTitulacionMod()));
		accesoDatos.actualizarAlumnos(alumno);
		actualizarTabla();
	}

	public void eliminarUno() {
		String dni = (String) vistaP.getTabla().getValueAt(vistaP.getTabla().getSelectedRow(),1);
		accesoDatos.borrarAlumno(dni);
		actualizarTabla();
	}

	public void eliminarTodos() {
		accesoDatos.borrarTodoAlumnos();
		actualizarTabla();
	}

	public void nuevoCurso() {
		Titulacion titulacion;
		titulacion = new Titulacion(vistaP.getTxtNombreCurso(),vistaP.getTxtDescripcionCurso());
		
		accesoDatos.insertarTitulacion(titulacion);
		
	}
	
//	public void actualizarTabla(HashMap<String, Alumno> recogerAlumnos){
//		DefaultTableModel model = (DefaultTableModel) vistaP.getTablaInfo();
//		model.setRowCount(0);
//			for(String key:recogerAlumnos.keySet()){
//			
//			
//			Alumno alumnoAux = recogerAlumnos.get(key);
//			Titulacion titulacionAux = alumnoAux.getTitulacionAlumno();
//			String nombreAux = titulacionAux.getNombre();
//			
//			model.addRow(new String[] { String.valueOf(recogerAlumnos.get(key).getCod()),
//					recogerAlumnos.get(key).getDni(),
//					recogerAlumnos.get(key).getNombre(),
//					recogerAlumnos.get(key).getApellido(),
//					String.valueOf(recogerAlumnos.get(key).getTelefono()),
//					recogerAlumnos.get(key).getNacionalidad(),
//					recogerAlumnos.get(key).getTitulacionAlumno().getNombre()
//			});
//			
//			vistaP.getTable().setModel(model);
//		}
//	}
}