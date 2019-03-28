package co.edu.usbcali.banco.test;

import static org.junit.jupiter.api.Assertions.*;

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

import co.edu.usbcali.banco.dto.ResultadoCuentaDTO;
import co.edu.usbcali.banco.modelo.Cliente;

class SelectJPQLTest {
	
	private final static Logger log = LoggerFactory.getLogger(SelectJPQLTest.class);
	
	static EntityManagerFactory entityManagerFactory;
	static EntityManager entityManager;

	@Test
	@DisplayName("Consultar todos los clientes")
	void selectAll() {
		
		String  jpql = "SELECT cli FROM Cliente cli";
		
		List<Cliente> losClientes = entityManager.createQuery(jpql).getResultList();
		
		for (Cliente cliente : losClientes) {
			log.info("Nombre: " + cliente.getNombre());
			log.info("Email: " + cliente.getEmail());
			log.info("Id: " + cliente.getClieId());
		}
	}
	
	@Test
	@DisplayName("Consultar clientes con Where")
	void selectWhere() {
		
		String  jpql = "SELECT cli FROM Cliente cli WHERE cli.tipoDocumento.tdocId = 2";
		
		List<Cliente> losClientes = entityManager.createQuery(jpql).getResultList();
		
		losClientes.forEach(cliente->{
			log.info("Nombre: " + cliente.getNombre());
			log.info("Email: " + cliente.getEmail());
			log.info("Id: " + cliente.getClieId());
			log.info("Nombre tipo documento: " + cliente.getTipoDocumento().getNombre());
		});
	}
	
	@Test
	@DisplayName("Consultar clientes con Like")
	void selectLike() {
		
		String  jpql = "SELECT cli FROM Cliente cli WHERE cli.nombre LIKE 'J%'";
		
		List<Cliente> losClientes = entityManager.createQuery(jpql).getResultList();
		
		losClientes.forEach(cliente->{
			log.info("Nombre: " + cliente.getNombre());
			log.info("Email: " + cliente.getEmail());
			log.info("Id: " + cliente.getClieId());
			log.info("Nombre tipo documento: " + cliente.getTipoDocumento().getNombre());
		});
	}
	
	@Test
	@DisplayName("Consultar Aritmetrica")
	void selectAricmetrico() {
		
		String  jpql = "SELECT COUNT(cli) FROM Cliente cli";
		
		Long cantidadClientes = (Long) entityManager.createQuery(jpql).getSingleResult();
		log.info("Cantidad de clientes: " + cantidadClientes);
		
		//cuantas cuentas hay
		jpql = "SELECT COUNT(cue), MIN(cue.saldo), MAX(cue.saldo), AVG(cue.saldo) FROM Cuenta cue";
		Object []resultado = (Object[]) entityManager.createQuery(jpql).getSingleResult();
		log.info("Count: " + resultado[0]);
		log.info("MIN: " + resultado[1]);
		log.info("MAX: " + resultado[2]);
		log.info("AVG: " + resultado[3]);
		
		jpql = "SELECT new co.edu.usbcali.banco.dto.ResultadoCuentaDTO( COUNT(cue), MIN(cue.saldo), MAX(cue.saldo), AVG(cue.saldo)) FROM Cuenta cue";
		ResultadoCuentaDTO resultadoCuentaDTO = (ResultadoCuentaDTO) entityManager.createQuery(jpql).getSingleResult();
		log.info("Count: " + resultadoCuentaDTO.getCount());
		log.info("MIN: " + resultadoCuentaDTO.getMin());
		log.info("MAX: " + resultadoCuentaDTO.getMax());
		log.info("AVG: " + resultadoCuentaDTO.getAvg());
		
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
