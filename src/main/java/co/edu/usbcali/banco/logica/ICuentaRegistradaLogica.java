package co.edu.usbcali.banco.logica;

import java.math.BigDecimal;
import java.util.List;

import co.edu.usbcali.banco.modelo.CuentaRegistrada;


public interface ICuentaRegistradaLogica {
	
	public void grabar(CuentaRegistrada cuentaRegistrada) throws Exception;
	
	public void modificar(CuentaRegistrada cuentaRegistrada) throws Exception;
	
	public void borrar(CuentaRegistrada cuentaRegistrada)  throws Exception;
	
	public List<CuentaRegistrada> consultarCuentaRegistradaPorCliente(BigDecimal clieId);
	
	public CuentaRegistrada consultarPorId(long cureId);
	public List<CuentaRegistrada> consultarTodasLasCuentasPorId(BigDecimal id);
	public List<CuentaRegistrada> consultarTodos();

}
