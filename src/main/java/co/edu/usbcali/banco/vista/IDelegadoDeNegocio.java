package co.edu.usbcali.banco.vista;

import java.math.BigDecimal;
import java.util.List;

import co.edu.usbcali.banco.modelo.Cliente;
import co.edu.usbcali.banco.modelo.Cuenta;
import co.edu.usbcali.banco.modelo.CuentaRegistrada;
import co.edu.usbcali.banco.modelo.TipoDocumento;
import co.edu.usbcali.banco.modelo.TipoTransaccion;
import co.edu.usbcali.banco.modelo.TipoUsuario;
import co.edu.usbcali.banco.modelo.Transaccion;
import co.edu.usbcali.banco.modelo.Usuario;

public interface IDelegadoDeNegocio {
	
	//Cliente
	public void grabarCliente(Cliente cliente) throws Exception;
	public void modificarCliente(Cliente cliente) throws Exception;
	public void borrarCliente(Cliente cliente)  throws Exception;
	public Cliente consultarClientePorId(BigDecimal clieId);
	public List<Cliente> consultarClienteTodos();
	
	//Tipo Documento
	public void grabarTipoDocumento(TipoDocumento tipoDocumento) throws Exception;
	public void modificarTipoDocumento(TipoDocumento tipoDocumento) throws Exception;
	public void borrarTipoDocumento(TipoDocumento tipoDocumento)  throws Exception;
	public TipoDocumento consultarTipoDocumentoPorId(long tdocId);
	public List<TipoDocumento> consultarTipoDocumentoTodos();
	
	//Cuenta
	public void grabarCuenta(Cuenta cuenta) throws Exception;	
	public void modificarCuenta(Cuenta cuenta) throws Exception;	
	public void borrarCuenta(Cuenta cuenta)  throws Exception;	
	public Cuenta consultarCuentaPorId(String cuenId);
	public List<Cuenta> consultarCuentaTodos();
	
	//CuentaRegistrada
	public void grabarCuentaRegistrada(CuentaRegistrada cuentaRegistrada) throws Exception;	
	public void modificarCuentaRegistrada(CuentaRegistrada cuentaRegistrada) throws Exception;	
	public void borrarCuentaRegistrada(CuentaRegistrada cuentaRegistrada)  throws Exception;
	public CuentaRegistrada consultarCuentaRegistradaPorId(long cureId);
	public List<CuentaRegistrada> consultarCuentaRegistradaPorCliente(BigDecimal clieId);
	public List<CuentaRegistrada> consultarTodasLasCuentasRegistradasPorId(BigDecimal id);
	public List<CuentaRegistrada> consultarCuentaRegistradaTodos();
	
	//Tipo Transaccion
	public void grabarTipoTransaccion(TipoTransaccion tipoTransaccion) throws Exception;	
	public void modificarTipoTransaccion(TipoTransaccion tipoTransaccion) throws Exception;	
	public void borrarTipoTransaccion(TipoTransaccion tipoTransaccion)  throws Exception;	
	public TipoTransaccion consultarTipoTransaccionPorId(long titrId);
	public List<TipoTransaccion> consultarTipoTransaccionTodos();
	
	//Tipo Usuario
	public void grabarTipoUsuario(TipoUsuario tipoUsuario) throws Exception;	
	public void modificarTipoUsuario(TipoUsuario tipoUsuario) throws Exception;	
	public void borrarTipoUsuario(TipoUsuario tipoUsuario)  throws Exception;	
	public TipoUsuario consultarTipoUsuarioPorId(long tiusId);
	public List<TipoUsuario> consultarTipoUsuarioTodos();
	
	//Transaccion
	public void grabarTransaccion(Transaccion transaccion) throws Exception;	
	public void modificarTransaccion(Transaccion transaccion) throws Exception;	
	public void borrarTransaccion(Transaccion transaccion)  throws Exception;	
	public Transaccion consultarTransaccionPorId(long tranId);
	public List<Transaccion> consultarPorCuentaYTipoTransaccion(String cuendId, Long titrId);
	public List<Transaccion> consultarPorCuentaDeTransaccion(String cuendId);
	public List<Transaccion> consultarTransaccionTodos();
	
	//Usuario
	public void grabarUsuario(Usuario usuario) throws Exception;	
	public void modificarUsuario(Usuario usuario) throws Exception;	
	public void borrarUsuario(Usuario usuario)  throws Exception;	
	public Usuario consultarUsuarioPorId(String usuUsuario);
	public List<Usuario> consultarUsuarioTodos();
	
	//Transacciones
	public void retirar(String cuenId, BigDecimal valor, String usuUsuario)throws Exception;
	public void consignar(String cuenId, BigDecimal valor, String usuUsuario)throws Exception;
	public void transferencia(String cuenIdOrigen, String cuenIdDestino, BigDecimal valor, String usuUsuario)throws Exception;

}
