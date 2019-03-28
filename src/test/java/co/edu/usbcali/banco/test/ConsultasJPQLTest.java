package co.edu.usbcali.banco.test;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.usbcali.banco.dto.ResultadoClienteCuentaRegistradaDTO;
import co.edu.usbcali.banco.dto.ResultadoCuentaConsignacionDTO;
import co.edu.usbcali.banco.dto.ResultadoCuentaMasDineroDTO;
import co.edu.usbcali.banco.dto.ResultadoRetirosMayoresACienMilDTO;
import co.edu.usbcali.banco.dto.ResultadoSumaSaldoTotalClienteDTO;
import co.edu.usbcali.banco.modelo.Cliente;
import co.edu.usbcali.banco.modelo.Transaccion;

class ConsultasJPQLTest {
	
	private final static Logger log = LoggerFactory.getLogger(SelectJPQLTest.class);
	
	static EntityManagerFactory entityManagerFactory;
	static EntityManager entityManager;
	
	//Punto 1
	@Test
	@DisplayName("Consultar cuenta y nombre de la cuenta con mas dinero ")
	void selectSaldoMaxCliente() {
		
 		String  jpql = "SELECT new co.edu.usbcali.banco.dto.ResultadoCuentaMasDineroDTO( cli.nombre, cue.cuenId, cue.saldo )"
 				+ "FROM Cliente cli, Cuenta cue "
 				+ "WHERE cue.saldo=(SELECT MAX(cue.saldo) FROM Cuenta cue) AND cli.clieId = cue.cliente.clieId";
		
 		ResultadoCuentaMasDineroDTO resultadoCuentaMasDineroDTO = (ResultadoCuentaMasDineroDTO) entityManager.createQuery(jpql).getSingleResult();
 		
		log.info("Nombre: " + resultadoCuentaMasDineroDTO.getNombre());
		log.info("Cuenta: " + resultadoCuentaMasDineroDTO.getCuenta());
		log.info("Saldo: " + resultadoCuentaMasDineroDTO.getSaldo());
	}
	
	//Punto 2
	@Test
	@DisplayName("Consultar la suma de los saldos disponibles en las cuentas del banco")
	void selectSumaSaldosDisponibles() {
		
 		String  jpql = "SELECT SUM(cue.saldo) "
 				+ "FROM Cuenta cue";
		
 		BigDecimal sumSaldosDisponibles = (BigDecimal) entityManager.createQuery(jpql).getSingleResult();
		log.info("Suma de saldos dispoblibles en las cuentas de banco: " + sumSaldosDisponibles);
	}
	
	//Punto 3
		@Test
		@DisplayName("Consultar la suma de los saldos disponibles de un usuario")
		void selectSumaSaldoCliente() {
			
			String idCliente= "337";
			
	 		String  jpql = "SELECT new co.edu.usbcali.banco.dto.ResultadoSumaSaldoTotalClienteDTO( cli.nombre, SUM(cue.saldo)) "
	 				+ "FROM Cuenta cue, Cliente cli "
	 				+ "WHERE cli.clieId = cue.cliente.clieId AND cue.cliente.clieId = "+idCliente+" GROUP BY (cli.clieId)";
	 		
	 		ResultadoSumaSaldoTotalClienteDTO ResultadoSumaSaldoTotalClienteDTO = (ResultadoSumaSaldoTotalClienteDTO) entityManager.createQuery(jpql).getSingleResult();
			log.info("Nombre : " + ResultadoSumaSaldoTotalClienteDTO.getNombre());
			log.info("Saldo total : " + ResultadoSumaSaldoTotalClienteDTO.getSaldo());
		}
	
	//Punto 4
		@Test
		@DisplayName("Consultar el promedio de los saldos disponibles en las cuentas del banco")
		void selectPromedioSaldosDisponibles() {
			
	 		String  jpql = "SELECT AVG(cue.saldo) "
	 				+ "FROM Cuenta cue";
			
	 		Double promedioSaldosDisponibles = (Double) entityManager.createQuery(jpql).getSingleResult();
			log.info("Promedio de saldos dispoblibles en el banco: " + promedioSaldosDisponibles);
		}
	
	//Punto 5  tra.usuario.nombre, tra.tipoTransaccion.nombre, tra.fecha, 
	@Test
	@DisplayName("Consultar cuenta y nombre de la cuenta con mas dinero ")
	void selectBetWeenRetiros() {
			
	 	String  jpql = "SELECT tra "
	 			+ "FROM Transaccion tra "
	 			+ "WHERE tra.tipoTransaccion.titrId = 1 AND tra.valor BETWEEN 100000 AND 15000000";
			
	 	List<Transaccion> todasLasTransacciones = entityManager.createQuery(jpql).getResultList();
	 	
	 	todasLasTransacciones.forEach(transaccion ->{
	 		log.info("Valor: " + transaccion.getValor());
	 	});
		
	}
	
	//Punto 6
	@Test
	@DisplayName("Consultar apariciones de la letra 'A' ")
	void selectAparicionesLetraA() {

		
		String  jpql = "FROM Cliente cli "
				+ "WHERE cli.nombre "
				+ "LIKE '%A%'";
		
		List<Cliente> losClientes = entityManager.createQuery(jpql).getResultList();
		
		losClientes.forEach(cliente->{
			log.info("ClieId: " + cliente.getClieId());
			log.info("Nombre: " + cliente.getNombre());
			log.info("Direccion: " + cliente.getDireccion());
			log.info("Telefono: " + cliente.getTelefono());
			log.info("Email: " + cliente.getEmail());
			log.info("Id: " + cliente.getActivo());
			log.info("Nombre tipo documento: " + cliente.getTipoDocumento().getNombre());
		});
	}
	
	//Punto 7   pendiente
		@Test
		@DisplayName("Consultar todos los clientes que tienen más de una cuenta")
		void selectClientesMasDeUnaCuenta() {

			String  jpql = "SELECT cli FROM Cliente cli, Cuenta cue WHERE cli.clieId=cue.cliente.clieId GROUP BY cli.clieId HAVING COUNT (cue.cuenId)>1";
			
			List<Cliente> cliente = entityManager.createQuery(jpql).getResultList();
			
			cliente.forEach(cli->{
				log.info("Nombre: " + cli.getNombre());
			});
		}
	
	//Punto 8
	@Test
	@DisplayName("Consultar todas las consignaciones por número de cuenta")
	void selectConsignacionPorCuenta() {
		
		String numeroCuenta = "1078-8795-8240-6760";
		
 		String  jpql = "SELECT new co.edu.usbcali.banco.dto.ResultadoCuentaConsignacionDTO( tra.cuenta.cuenId, tra.valor, ttra.nombre)  "
 				+ "FROM Transaccion tra, TipoTransaccion ttra "
 				+ "WHERE tra.tipoTransaccion.titrId = ttra.titrId and ttra.titrId = 2 and tra.cuenta.cuenId = '"+numeroCuenta+"'";
		
 		List<ResultadoCuentaConsignacionDTO> consignacionPorNumeroDeCuenta = entityManager.createQuery(jpql).getResultList();
 		
 		consignacionPorNumeroDeCuenta.forEach(consignacionCuenta->{
 			log.info("Cuenta: " + consignacionCuenta.getCuenta());
 			log.info("Valor: " + consignacionCuenta.getValor());
 			log.info("Tipo transaccion: " + consignacionCuenta.getTipoTransaccion());
 		});
	}
	
	//Punto 9
	@Test
	@DisplayName("Consultar los retiros mayores a 100.000")
	void selectRetirosMayoresACienMil() {
		
		
 		String  jpql = "SELECT new co.edu.usbcali.banco.dto.ResultadoRetirosMayoresACienMilDTO( ttra.nombre, tra.valor) "
 				+ "FROM Transaccion tra, TipoTransaccion ttra "
 				+ "WHERE tra.tipoTransaccion.titrId = ttra.titrId and ttra.titrId = 1 and tra.valor > 100000";
		
 		List<ResultadoRetirosMayoresACienMilDTO> resultadoRetirosMayoresACienMil = entityManager.createQuery(jpql).getResultList();
 		
 		resultadoRetirosMayoresACienMil.forEach(retirosMayoresACienMil ->{
 			log.info("Nombre: " + retirosMayoresACienMil.getTipoTransaccion());
 			log.info("Valor: " + retirosMayoresACienMil.getSaldo());
 		});
	}
	
	//Punto 10
		@Test
		@DisplayName("Consultar el número de las cuentas y el nombre del propietario de las cuentas registradas")
		void selectClienteCuentasRegistradas() {
			
			String clienteId = "43";
			
	 		String  jpql = "SELECT new co.edu.usbcali.banco.dto.ResultadoClienteCuentaRegistradaDTO( cli.nombre, cue.cuenId) "
	 				+ "FROM Cuenta cue, Cliente cli "
	 				+ "WHERE cli.clieId = cue.cliente.clieId and cue.cliente.clieId = " + clienteId;
			
	 		List<ResultadoClienteCuentaRegistradaDTO> resultadoClienteCuentaRegistradaDTO = entityManager.createQuery(jpql).getResultList();
	 		
	 		resultadoClienteCuentaRegistradaDTO.forEach(resultadoClienteCuenta->{
	 			log.info("Nombre: " + resultadoClienteCuenta.getNombre());
	 			log.info("Cuenta: " + resultadoClienteCuenta.getCuenta());
	 		});
		}
	
	@BeforeAll
	public static void inicializarTodo() {
		entityManagerFactory=Persistence.createEntityManagerFactory("banco-persitencia");
		entityManager=entityManagerFactory.createEntityManager();
	}	
	@AfterAll
	public static void cerrarTodo() {
		entityManager.close();
		entityManagerFactory.close();
	}

}
