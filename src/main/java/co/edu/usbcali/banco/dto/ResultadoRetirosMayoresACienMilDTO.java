package co.edu.usbcali.banco.dto;

import java.math.BigDecimal;

public class ResultadoRetirosMayoresACienMilDTO {
	
	private String tipoTransaccion;
	private BigDecimal saldo;
	
	public ResultadoRetirosMayoresACienMilDTO() {
		super();
	}

	public ResultadoRetirosMayoresACienMilDTO(String tipoTransaccion, BigDecimal saldo) {
		super();
		this.tipoTransaccion = tipoTransaccion;
		this.saldo = saldo;
	}

	public String getTipoTransaccion() {
		return tipoTransaccion;
	}

	public void setTipoTransaccion(String tipoTransaccion) {
		this.tipoTransaccion = tipoTransaccion;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
	
	
}
