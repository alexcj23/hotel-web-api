package ni.edu.ucem.webapi.modelo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

public class Huesped {

	private final static String VALID_EMAIL_ADDRESS_REGEX = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
	private final static String VALID_PHONE_REGEX = "^(505)\\d{9}$";

	private Integer id;
	@NotNull
	@NotEmpty(message = "El nombre es requerido.")
	private String nombre;

	@NotNull
	@NotEmpty(message = "El correo es requerido.")
	@Pattern(regexp = VALID_EMAIL_ADDRESS_REGEX)
	private String email;
	
	@NotNull
	@NotEmpty(message = "El número telefónico es requerido.")
	@Pattern(regexp = VALID_PHONE_REGEX)	
	private String telefono;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

}
