package co.edu.usbcali.banco.logica;

import java.math.BigDecimal;
import java.util.List;

import co.edu.usbcali.banco.modelo.TipoTransaccion;


public interface ITipoTransaccionLogica {
	
	public void grabar(TipoTransaccion tipoTransaccion) throws Exception;
	
	public void modificar(TipoTransaccion tipoTransaccion) throws Exception;
	
	public void borrar(TipoTransaccion tipoTransaccion)  throws Exception;
	
	public TipoTransaccion consultarPorId(long titrId);
	public List<TipoTransaccion> consultarTodos();

}
