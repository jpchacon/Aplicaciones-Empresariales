package co.edu.usbcali.banco.logica;

import java.util.List;

import co.edu.usbcali.banco.modelo.Transaccion;


public interface ITransaccionLogica {
	
	public void grabar(Transaccion transaccion) throws Exception;
	
	public void modificar(Transaccion transaccion) throws Exception;
	
	public void borrar(Transaccion transaccion)  throws Exception;
	
	public Transaccion consultarPorId(long tranId);
	public List<Transaccion> consultarPorCuentaDeTransaccion(String cuendId);
	public List<Transaccion> consultarPorCuentaYTipoTransaccion(String cuendId, Long titrId);
	public List<Transaccion> consultarTodos();

}
