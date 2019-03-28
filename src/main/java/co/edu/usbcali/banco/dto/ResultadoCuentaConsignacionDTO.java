package co.edu.usbcali.banco.dto;

import java.math.BigDecimal;

public class ResultadoCuentaConsignacionDTO {
	
	private String cuenta;
	private BigDecimal valor;
	private String tipoTransaccion;
	
	public ResultadoCuentaConsignacionDTO() {
		super();
	}

	public ResultadoCuentaConsignacionDTO(String cuenta, BigDecimal valor, String tipoTransaccion) {
		super();
		this.cuenta = cuenta;
		this.valor = valor;
		this.tipoTransaccion = tipoTransaccion;
	}

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getTipoTransaccion() {
		return tipoTransaccion;
	}

	public void setTipoTransaccion(String tipoTransaccion) {
		this.tipoTransaccion = tipoTransaccion;
	}
}
