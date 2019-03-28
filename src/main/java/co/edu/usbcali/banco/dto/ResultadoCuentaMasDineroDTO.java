package co.edu.usbcali.banco.dto;

import java.math.BigDecimal;

public class ResultadoCuentaMasDineroDTO {
	
	private String nombre;
	private String cuenta;
	private BigDecimal saldo;
	
	
	public ResultadoCuentaMasDineroDTO() {
		super();
	}
	
	
	public ResultadoCuentaMasDineroDTO(String nombre, String cuenta, BigDecimal saldo) {
		super();
		this.nombre = nombre;
		this.cuenta = cuenta;
		this.saldo = saldo;
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
	public BigDecimal getSaldo() {
		return saldo;
	}
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
}
