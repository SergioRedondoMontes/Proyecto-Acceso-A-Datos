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
	HashMap<String, Titulacion> recogerTitulaciones;
	
public AccesoHibernate() {
		
		HibernateUtil util = new HibernateUtil(); 
		
		session = util.getSessionFactory().openSession();
		
	}

	@Override
	public HashMap<String, Alumno> obtenerAlumno() {
		recogerAlumnos = new HashMap<String, Alumno>();
		Query q= session.createQuery("select alum from Alumno alum");
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
		boolean todoOK = true;
		try {
			session.beginTransaction();
			session.save(alumno);
			recogerAlumnos.put(alumno.getDni(), alumno);
		} catch (Exception e) {
			todoOK = false;
		}
		
		return todoOK;
	}

	@Override
	public boolean borrarAlumno(String dni) {
		boolean todoOK = true;
		try {
			session.beginTransaction();
			session.delete(dni);
			recogerAlumnos.remove(dni);
		} catch (Exception e) {
			todoOK = false;
		}
		
		return todoOK;
	}

	@Override
	public boolean borrarTodoAlumnos() {
		boolean todoOK = true;
		try {
			String hql="delete from Alumno";
			session.beginTransaction();
			Query query=session.createQuery(hql);
			recogerAlumnos.clear();
		} catch (Exception e) {
			todoOK = false;
		}
		
		return todoOK;
	}

	@Override
	public boolean actualizarAlumnos(Alumno alumno) {
		boolean todoOK = true;
		try {
			session.beginTransaction();
			session.merge(alumno);
			recogerAlumnos.put(alumno.getDni(), alumno);
		} catch (Exception e) {
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
		Query q= session.createQuery("select dep from Titulacion dep");
        List results = q.list();
        
        Iterator titulacionesIterator= results.iterator();

        while (titulacionesIterator.hasNext()){
        	Titulacion titulacion = (Titulacion)titulacionesIterator.next();
        	recogerTitulaciones.put(titulacion.getNombre(), titulacion);
        }

    	
		return recogerTitulaciones;
	}

	@Override
	public boolean insertarTitulacion(Titulacion titulacion) {
		boolean todoOK = true;
		try {
			session.beginTransaction();
			session.save(titulacion);
		} catch (Exception e) {
			todoOK = false;
		}
		
		return todoOK;
	}

}
