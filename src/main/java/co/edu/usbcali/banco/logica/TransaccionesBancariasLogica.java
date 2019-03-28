package co.edu.usbcali.banco.logica;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.banco.modelo.Cuenta;
import co.edu.usbcali.banco.modelo.TipoTransaccion;
import co.edu.usbcali.banco.modelo.Transaccion;
import co.edu.usbcali.banco.modelo.Usuario;

@Service
@Scope("singleton")
public class TransaccionesBancariasLogica implements ITransaccionesBancariasLogica {
	
	@Autowired
	private ICuentaLogica cuentaLogica;
	
	@Autowired
	private IUsuarioLogica usuarioLogica;
	
	@Autowired
	private ITipoTransaccionLogica tipoTransaccionLogica;
	
	@Autowired
	private ITransaccionLogica TransaccionLogica;

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void retirar(String cuenId, BigDecimal valor, String usuUsuario) throws Exception {
		
		
		if(cuenId.trim().equals("") || cuenId == null) {
			throw new Exception("el ID de la cuenta no puede ser nulo o vacio");
		}
		if(usuUsuario.trim().equals("")==true || usuUsuario == null) {
			throw new Exception("el usuario no puede ser nulo o vacio");
		}
		
		Cuenta cuenta = cuentaLogica.consultarPorId(cuenId);
		Usuario usuario = usuarioLogica.consultarPorId(usuUsuario);
		
		
		if(cuenta == null) {
			throw new Exception("La cuenta no puede ser Nulo");
		}
		if(cuenta.getActiva() == 'N'){
			throw new Exception("La cuenta debe estar activa");	
		}
		
		if(usuario == null) {
			throw new Exception("El usuario no puede ser null");	
		}
		if(usuario.getActivo() == 'N') {
			throw new Exception("El usuario debe estar activo");
		}
		if(valor.intValue()==0 || valor == null) {
			throw new Exception("el valor de la transaccion no puede ser cero ");
		}
		if(valor.intValue()<0) {
			throw new Exception("el valor de la transaccion no puede ser negativo");
		}
		if(valor.doubleValue() > cuenta.getSaldo().doubleValue()) {
			throw new Exception("el saldo en la cuenta es insuficiente");
		}
		
		
		Transaccion transaccion = new Transaccion();
		transaccion.setCuenta(cuenta);
		transaccion.setUsuario(usuario);
		transaccion.setValor(valor);
		transaccion.setFecha(new Date());
		TipoTransaccion tipoTransaccion = tipoTransaccionLogica.consultarPorId(1L);
		transaccion.setTipoTransaccion(tipoTransaccion);
		
			
		TransaccionLogica.grabar(transaccion);
			
		cuenta.setSaldo(cuenta.getSaldo().subtract(valor));
			
		cuentaLogica.modificar(cuenta);
		
		
	}
		

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void consignar(String cuenId, BigDecimal valor, String usuUsuario) throws Exception {

		
		if(cuenId.trim().equals("") || cuenId == null) {
			throw new Exception("el ID de la cuenta no puede ser nulo o vacio");
		}
		if(usuUsuario.trim().equals("")==true || usuUsuario == null) {
			throw new Exception("el usuario no puede ser nulo o vacio");
		}
		
		Cuenta cuenta = cuentaLogica.consultarPorId(cuenId);
		Usuario usuario = usuarioLogica.consultarPorId(usuUsuario);
		
		
		if(cuenta == null) {
			throw new Exception("La cuenta no puede ser Nulo");
		}
		if(usuario == null) {
			throw new Exception("El usuario no puede ser null");	
		}
		if(usuario.getActivo() == 'N') {
			throw new Exception("El usuario debe estar activo");
		}
		if(valor.intValue()==0 || valor == null) {
			throw new Exception("el valor de la transaccion no puede ser cero ");
		}
		if(valor.intValue()<0) {
			throw new Exception("el valor de la transaccion no puede ser negativo");
		}
		
		
		
		
		Transaccion transaccion = new Transaccion();
		transaccion.setCuenta(cuenta);
		transaccion.setUsuario(usuario);
		transaccion.setValor(valor);
		transaccion.setFecha(new Date());
		TipoTransaccion tipoTransaccion = tipoTransaccionLogica.consultarPorId(2L);
		transaccion.setTipoTransaccion(tipoTransaccion);
		
			
		TransaccionLogica.grabar(transaccion);
			
		cuenta.setSaldo(cuenta.getSaldo().add(valor));
			
		cuentaLogica.modificar(cuenta);
		
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void transferencia(String cuenIdOrigen, String cuenIdDestino, BigDecimal valor, String usuUsuario)
			throws Exception {


		if(cuenIdOrigen.trim().equals("")) {
			throw new Exception("Se necesita una cuenta origen");
		}
		if(cuenIdDestino.trim().equals("")) {
			throw new Exception("Se necesita una cuenta destino");
		}
		if(usuUsuario.trim().equals("")) {
			throw new Exception("Se necesita un usuario");
		}
		
		Cuenta cuenOrigen = cuentaLogica.consultarPorId(cuenIdOrigen);
		Cuenta cuenDestino = cuentaLogica.consultarPorId(cuenIdDestino);
		String cuentaBanco = "9999-9999-9999-9999";
		
		Usuario usuario = usuarioLogica.consultarPorId(usuUsuario);
		
		BigDecimal cobroTransaccion = new BigDecimal(900);
		BigDecimal valorFinal = cobroTransaccion.add(new BigDecimal(valor.doubleValue()));
		
		if(cuenOrigen == null) {
			throw new Exception("La cuenta de origen no existe");
		}
		if(cuenDestino == null) {
			throw new Exception("La cuenta de destino no existe");
		}
		if(usuario == null) {
			throw new Exception("El usuario no puede ser nulo");
		}
		if(cuenOrigen.getActiva() == 'N') {
			throw new Exception("La cuenta de origen debe estar activa");
		}
		if(cuenDestino.getActiva() == 'N') {
			throw new Exception("La cuenta de destino debe estar activa");
		}
		if(usuario.getActivo() == 'N') {
			throw new Exception("El usuario no puede estar inactivo");
		}
		if(valor.intValue()==0 || valor == null) {
			throw new Exception("el valor de la transaccion no puede ser cero ");
		}
		if(valor.intValue()<0) {
			throw new Exception("el valor de la transaccion no puede ser negativo");
		}
		if(valorFinal.doubleValue() > cuenOrigen.getSaldo().doubleValue()) {
			throw new Exception("el saldo en la cuenta es insuficiente");
		}
		
		//Retiro de la cuenta Origen
		retirar(cuenIdOrigen, valor, usuUsuario);
		retirar(cuenIdOrigen, cobroTransaccion, usuUsuario);
		
		//Consignacion a cuenta final
		consignar(cuenIdDestino, valor, usuUsuario);
		consignar(cuentaBanco, cobroTransaccion, usuUsuario);
		
		//
		TipoTransaccion tipoTransaccion = tipoTransaccionLogica.consultarPorId(3L);
		
		
		
		Transaccion transaccion1 = new Transaccion();
		transaccion1.setCuenta(cuenOrigen);
		transaccion1.setUsuario(usuario);
		transaccion1.setValor(valor);
		transaccion1.setFecha(new Date());

		transaccion1.setTipoTransaccion(tipoTransaccion);
		TransaccionLogica.grabar(transaccion1);
		
		Transaccion transaccion2 = new Transaccion();
		transaccion2.setCuenta(cuenDestino);
		transaccion2.setUsuario(usuario);
		transaccion2.setValor(valor);
		transaccion2.setFecha(new Date());
	
		transaccion2.setTipoTransaccion(tipoTransaccion);
		
		TransaccionLogica.grabar(transaccion2);
		
	}

}
