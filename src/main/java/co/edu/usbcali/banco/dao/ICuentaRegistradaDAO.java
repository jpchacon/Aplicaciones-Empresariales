package co.edu.usbcali.banco.dao;

import java.math.BigDecimal;
import java.util.List;

import co.edu.usbcali.banco.modelo.CuentaRegistrada;


public interface ICuentaRegistradaDAO {
	
	public void grabar(CuentaRegistrada cuentaRegistrada);
	
	public void modificar(CuentaRegistrada cuentaRegistrada);
	
	public void borrar(CuentaRegistrada cuentaRegistrada);
	
	public CuentaRegistrada consultarPorId(long cureId);
	
	public List<CuentaRegistrada> consultarCuentaRegistradaPorCliente(BigDecimal clieId);
	
	public List<CuentaRegistrada> consultarTodasLasCuentasPorId(BigDecimal id);
	public List<CuentaRegistrada> consultarTodos();

}
