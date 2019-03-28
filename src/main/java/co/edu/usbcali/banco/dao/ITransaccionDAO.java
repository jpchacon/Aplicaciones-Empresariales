package co.edu.usbcali.banco.dao;

import java.util.List;

import co.edu.usbcali.banco.modelo.Transaccion;


public interface ITransaccionDAO {
	
	public void grabar(Transaccion transaccion);
	
	public void modificar(Transaccion transaccion);
	
	public void borrar(Transaccion transaccion);
	
	public Transaccion consultarPorId(long tranId);
	public List<Transaccion> consultarPorCuentaDeTransaccion(String cuendId);
	public List<Transaccion> consultarPorCuentaYTipoTransaccion(String cuendId, Long titrId);
	public List<Transaccion> consultarTodos();
}
