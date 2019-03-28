package co.edu.usbcali.banco.dto;

public class ResultadoClienteCuentaRegistradaDTO {
	
	private String nombre;
	private String cuenta;
	
	public ResultadoClienteCuentaRegistradaDTO() {
		super();
	}

	public ResultadoClienteCuentaRegistradaDTO(String nombre, String cuenta) {
		super();
		this.nombre = nombre;
		this.cuenta = cuenta;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}
	
	
}
