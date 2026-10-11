package padre;

public abstract class Persona {
	
	protected String nombres,apellidos,dni;	
	protected int edad;
	
	
	public Persona(String nombres, String apellidos, String dni, int edad) {
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.dni = dni;
		this.edad = edad;
	}
	
	// SET
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	public void setDni(String dni) {
		this.dni = dni;
	}
	
	// GET
	public String getNombres() {
		return nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public String getDni() {
		return dni;
	}
	public int getEdad() {
		return edad;
	}
	
	

}
