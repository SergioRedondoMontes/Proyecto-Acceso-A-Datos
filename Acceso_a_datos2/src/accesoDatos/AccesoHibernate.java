package accesoDatos;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import auxiliares.HibernateUtil;
import entidades.Alumno;
import entidades.Titulacion;

public class AccesoHibernate implements I_Acceso_Datos{
	
	Session session;
	HashMap<String, Alumno> recogerAlumnos;
	HashMap<Integer, Titulacion> recogerTitulaciones;
	
public AccesoHibernate() {
		
		HibernateUtil util = new HibernateUtil(); 
		
		session = util.getSessionFactory().openSession();
		
	}

	@Override
	public HashMap<String, Alumno> obtenerAlumno() {
		recogerAlumnos = new HashMap<String, Alumno>();
		Query q= session.createQuery("select alum from  alum");
        List results = q.list();
        
        Iterator alumnosIterator= results.iterator();

        while (alumnosIterator.hasNext()){
        	Alumno alumno = (Alumno)alumnosIterator.next();
        	recogerAlumnos.put(alumno.getDni(), alumno);
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
		boolean todoOK = true;
		return todoOK;
	}

	@Override
	public HashMap<Integer, Titulacion> obtenerTitulacion() {
		recogerTitulaciones = new HashMap<Integer, Titulacion>();
		Query q= session.createQuery("select dep from Deposito dep");
        List results = q.list();
        
        Iterator titulacionesIterator= results.iterator();

        while (titulacionesIterator.hasNext()){
        	Titulacion tituacion = (Titulacion)titulacionesIterator.next();
        	recogerTitulaciones.put(tituacion.getCod(), tituacion);
        }

    	
		return recogerTitulaciones;
	}

	@Override
	public boolean insertarTitulacion(Titulacion titulacion) {
		// TODO Auto-generated method stub
		return false;
	}

}
