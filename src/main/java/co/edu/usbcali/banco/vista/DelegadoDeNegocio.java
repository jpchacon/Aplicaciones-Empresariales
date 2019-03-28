package co.edu.usbcali.banco.vista;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import co.edu.usbcali.banco.logica.IClienteLogica;
import co.edu.usbcali.banco.logica.ICuentaLogica;
import co.edu.usbcali.banco.logica.ICuentaRegistradaLogica;
import co.edu.usbcali.banco.logica.ITipoDocumentoLogica;
import co.edu.usbcali.banco.logica.ITipoTransaccionLogica;
import co.edu.usbcali.banco.logica.ITipoUsuarioLogica;
import co.edu.usbcali.banco.logica.ITransaccionLogica;
import co.edu.usbcali.banco.logica.ITransaccionesBancariasLogica;
import co.edu.usbcali.banco.logica.IUsuarioLogica;
import co.edu.usbcali.banco.modelo.Cliente;
import co.edu.usbcali.banco.modelo.Cuenta;
import co.edu.usbcali.banco.modelo.CuentaRegistrada;
import co.edu.usbcali.banco.modelo.TipoDocumento;
import co.edu.usbcali.banco.modelo.TipoTransaccion;
import co.edu.usbcali.banco.modelo.TipoUsuario;
import co.edu.usbcali.banco.modelo.Transaccion;
import co.edu.usbcali.banco.modelo.Usuario;

@Component
@Scope("singleton")
public class DelegadoDeNegocio implements IDelegadoDeNegocio{
	
	@Autowired
	private IClienteLogica clienteLogica;
	
	@Autowired
	private ITipoDocumentoLogica tipoDocumentoLogica;
	
	@Autowired
	private ICuentaLogica cuentaLogica;
	
	@Autowired
	private ICuentaRegistradaLogica cuentaRegistradaLogica;
	
	@Autowired
	private ITipoTransaccionLogica tipoTransaccionLogica;
	
	@Autowired
	private ITipoUsuarioLogica tipoUsuarioLogica;
	
	@Autowired
	private ITransaccionLogica transaccionLogica;
	
	@Autowired
	private IUsuarioLogica usuarioLogica;
	
	@Autowired
	private ITransaccionesBancariasLogica transaccionesBancariasLogica;

	@Override
 	public void grabarCliente(Cliente cliente) throws Exception {
		clienteLogica.grabar(cliente);
	}

	@Override
	public void modificarCliente(Cliente cliente) throws Exception {
		clienteLogica.modificar(cliente);		
	}

	@Override
	public void borrarCliente(Cliente cliente) throws Exception {
		clienteLogica.borrar(cliente);		
	}

	@Override
	public Cliente consultarClientePorId(BigDecimal clieId) {
		return clienteLogica.consultarPorId(clieId);
	}

	@Override
	public List<Cliente> consultarClienteTodos() {
		return clienteLogica.consultarTodos();
	}

	@Override
	public void grabarTipoDocumento(TipoDocumento tipoDocumento) throws Exception {
		tipoDocumentoLogica.grabar(tipoDocumento);		
	}

	@Override
	public void modificarTipoDocumento(TipoDocumento tipoDocumento) throws Exception {
		tipoDocumentoLogica.modificar(tipoDocumento);		
	}

	@Override
	public void borrarTipoDocumento(TipoDocumento tipoDocumento) throws Exception {
		tipoDocumentoLogica.borrar(tipoDocumento);		
	}

	@Override
	public TipoDocumento consultarTipoDocumentoPorId(long tdocId) {
		return tipoDocumentoLogica.consultarPorId(tdocId);
	}

	@Override
	public List<TipoDocumento> consultarTipoDocumentoTodos() {
		return tipoDocumentoLogica.consultarTodos();
	}

	@Override
	public void grabarCuenta(Cuenta cuenta) throws Exception {
		cuentaLogica.grabar(cuenta);		
	}

	@Override
	public void modificarCuenta(Cuenta cuenta) throws Exception {
		cuentaLogica.modificar(cuenta);		
	}

	@Override
	public void borrarCuenta(Cuenta cuenta) throws Exception {
		cuentaLogica.borrar(cuenta);		
	}

	@Override
	public Cuenta consultarCuentaPorId(String cuenId) {
		return cuentaLogica.consultarPorId(cuenId);
	}

	@Override
	public List<Cuenta> consultarCuentaTodos() {
		return cuentaLogica.consultarTodos();
	}

	@Override
	public void grabarCuentaRegistrada(CuentaRegistrada cuentaRegistrada) throws Exception {
		cuentaRegistradaLogica.grabar(cuentaRegistrada);		
	}

	@Override
	public void modificarCuentaRegistrada(CuentaRegistrada cuentaRegistrada) throws Exception {
		cuentaRegistradaLogica.modificar(cuentaRegistrada);		
	}

	@Override
	public void borrarCuentaRegistrada(CuentaRegistrada cuentaRegistrada) throws Exception {
		cuentaRegistradaLogica.borrar(cuentaRegistrada);		
	}

	@Override
	public CuentaRegistrada consultarCuentaRegistradaPorId(long cureId) {
		return cuentaRegistradaLogica.consultarPorId(cureId);
	}
	
	@Override
	public List<CuentaRegistrada> consultarCuentaRegistradaPorCliente(BigDecimal clieId) {
		return cuentaRegistradaLogica.consultarCuentaRegistradaPorCliente(clieId);
	}

	@Override
	public List<CuentaRegistrada> consultarTodasLasCuentasRegistradasPorId(BigDecimal id) {
		return cuentaRegistradaLogica.consultarTodasLasCuentasPorId(id);
	}
	

	@Override
	public List<CuentaRegistrada> consultarCuentaRegistradaTodos() {
		return cuentaRegistradaLogica.consultarTodos();
	}

	@Override
	public void grabarTipoTransaccion(TipoTransaccion tipoTransaccion) throws Exception {
		tipoTransaccionLogica.grabar(tipoTransaccion);		
	}

	@Override
	public void modificarTipoTransaccion(TipoTransaccion tipoTransaccion) throws Exception {
		tipoTransaccionLogica.modificar(tipoTransaccion);		
	}

	@Override
	public void borrarTipoTransaccion(TipoTransaccion tipoTransaccion) throws Exception {
		tipoTransaccionLogica.borrar(tipoTransaccion);		
	}

	@Override
	public TipoTransaccion consultarTipoTransaccionPorId(long titrId) {
		return tipoTransaccionLogica.consultarPorId(titrId);
	}

	@Override
	public List<TipoTransaccion> consultarTipoTransaccionTodos() {
		return tipoTransaccionLogica.consultarTodos();
	}

	@Override
	public void grabarTipoUsuario(TipoUsuario tipoUsuario) throws Exception {
		tipoUsuarioLogica.grabar(tipoUsuario);		
	}

	@Override
	public void modificarTipoUsuario(TipoUsuario tipoUsuario) throws Exception {
		tipoUsuarioLogica.modificar(tipoUsuario);		
	}

	@Override
	public void borrarTipoUsuario(TipoUsuario tipoUsuario) throws Exception {
		tipoUsuarioLogica.borrar(tipoUsuario);		
	}

	@Override
	public TipoUsuario consultarTipoUsuarioPorId(long tiusId) {
		return tipoUsuarioLogica.consultarPorId(tiusId);
	}

	@Override
	public List<TipoUsuario> consultarTipoUsuarioTodos() {
		return tipoUsuarioLogica.consultarTodos();
	}

	@Override
	public void grabarTransaccion(Transaccion transaccion) throws Exception {
		transaccionLogica.grabar(transaccion);		
	}

	@Override
	public void modificarTransaccion(Transaccion transaccion) throws Exception {
		transaccionLogica.modificar(transaccion);		
	}

	@Override
	public void borrarTransaccion(Transaccion transaccion) throws Exception {
		transaccionLogica.borrar(transaccion);		
	}

	@Override
	public Transaccion consultarTransaccionPorId(long tranId) {
		return transaccionLogica.consultarPorId(tranId);
	}
	
	@Override
	public List<Transaccion> consultarPorCuentaYTipoTransaccion(String cuendId, Long titrId) {
		return transaccionLogica.consultarPorCuentaYTipoTransaccion(cuendId, titrId);
	}
	
	@Override
	public List<Transaccion> consultarPorCuentaDeTransaccion(String cuendId) {
		return transaccionLogica.consultarPorCuentaDeTransaccion(cuendId);
	}

	@Override
	public List<Transaccion> consultarTransaccionTodos() {
		return transaccionLogica.consultarTodos();
	}

	@Override
	public void grabarUsuario(Usuario usuario) throws Exception {
		usuarioLogica.grabar(usuario);		
	}

	@Override
	public void modificarUsuario(Usuario usuario) throws Exception {
		usuarioLogica.modificar(usuario);		
	}

	@Override
	public void borrarUsuario(Usuario usuario) throws Exception {
		usuarioLogica.borrar(usuario);		
	}

	@Override
	public Usuario consultarUsuarioPorId(String usuUsuario) {
		return usuarioLogica.consultarPorId(usuUsuario);
	}

	@Override
	public List<Usuario> consultarUsuarioTodos() {
		return usuarioLogica.consultarTodos();
	}

	@Override
	public void retirar(String cuenId, BigDecimal valor, String usuUsuario) throws Exception {
		transaccionesBancariasLogica.retirar(cuenId, valor, usuUsuario);
	}

	@Override
	public void consignar(String cuenId, BigDecimal valor, String usuUsuario) throws Exception {
		transaccionesBancariasLogica.consignar(cuenId, valor, usuUsuario);
	}

	@Override
	public void transferencia(String cuenIdOrigen, String cuenIdDestino, BigDecimal valor, String usuUsuario)
			throws Exception {
		transaccionesBancariasLogica.transferencia(cuenIdOrigen, cuenIdDestino, valor, usuUsuario);
	}

	
}
