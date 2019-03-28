package co.edu.usbcali.banco.logica;

import java.math.BigDecimal;

import co.edu.usbcali.banco.modelo.Cuenta;

public interface ITransaccionesBancariasLogica {
	
	public void retirar(String cuenId, BigDecimal valor, String usuUsuario)throws Exception;
	
	public void consignar(String cuenId, BigDecimal valor, String usuUsuario)throws Exception;
	
	public void transferencia(String cuenIdOrigen, String cuenIdDestino, BigDecimal valor, String usuUsuario)throws Exception;

}
