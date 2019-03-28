package co.edu.usbcali.banco.dto;

import java.math.BigDecimal;

public class ResultadoSumaSaldoTotalClienteDTO {
	
	private String nombre;
	private BigDecimal saldo;
	
	public ResultadoSumaSaldoTotalClienteDTO() {
		super();
	}

	public ResultadoSumaSaldoTotalClienteDTO(String nombre, BigDecimal saldo) {
		super();
		this.nombre = nombre;
		this.saldo = saldo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
	
	

}
