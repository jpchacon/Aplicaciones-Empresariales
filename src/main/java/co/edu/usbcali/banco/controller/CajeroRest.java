package co.edu.usbcali.banco.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import co.edu.usbcali.banco.dto.ResultadoRest;
import co.edu.usbcali.banco.logica.ICuentaLogica;
import co.edu.usbcali.banco.logica.ITransaccionesBancariasLogica;
import co.edu.usbcali.banco.modelo.Cuenta;

@RestController
@RequestMapping("/cajero")
public class CajeroRest {
	
	@Autowired
	private ICuentaLogica cuentaLogica;
	ResultadoRest res = new ResultadoRest();
	
	@Autowired
	private ITransaccionesBancariasLogica transaccionesBancariasLogica;
	
	@CrossOrigin(origins="*")
	@RequestMapping("retirar/{cuentaid}/{contraseña}/{valor}")
	public ResultadoRest consultarClientePorId(@PathVariable("valor") BigDecimal valor,@PathVariable("cuentaid") String cuentaid,@PathVariable("contraseña") String contraseña) {
		
		String usuario = "banco";
		
		try {
			
			Cuenta cuenta =  cuentaLogica.consultarPorId(cuentaid);
			
			if((cuenta != null) && (cuenta.getClave().equals(contraseña))) {
				
				transaccionesBancariasLogica.retirar(cuenta.getCuenId(), valor, usuario);
				
				Cuenta cuentaDesdesDeRetiro = cuentaLogica.consultarPorId(cuentaid);
				
				res.setMensaje("Retiro exitoso. Saldo Anterioir : "+cuenta.getSaldo()+", Nuevo Saldo: "+cuentaDesdesDeRetiro.getSaldo());
				res.setCodigo(1);
				
			}else {
				
				res.setCodigo(-1);
				res.setMensaje("Cuenta o contraseña incorrecta, por favor verifique que el saldo sea mayor a 0");
				return res;
				
			}
			
		} catch (Exception e) {
			
			res.setCodigo(-1);
			res.setMensaje("No fue posible hacer el retiro:" + e.getMessage());
		}
		return res;
	}

}
