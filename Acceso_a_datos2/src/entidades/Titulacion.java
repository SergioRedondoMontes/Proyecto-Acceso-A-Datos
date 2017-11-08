package entidades;
public class Titulacion {
	private int cod;
	private String nombre;
	private String descripcion;

	public Titulacion(){}
	
	public Titulacion(int cod, String nombre,String descripcion) {
		super();
		this.cod = cod;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public Titulacion( String nombre,String descripcion) {
		super();
		
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	

}

