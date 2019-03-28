package co.edu.usbcali.banco.test;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.usbcali.banco.modelo.TipoDocumento;

class TipoDocumentoJPATest {
	
	private final static Logger log = LoggerFactory.getLogger(TipoDocumento.class);
	
	static EntityManagerFactory entityManagerFactory;
	static EntityManager entityManager;
	long tdocId = new Long(4);
	
	
	@Test
	@DisplayName("CrearTipoDocumento")
	void aTest() {
		assertNotNull(entityManager,"El entityManager esta nulo");
		TipoDocumento tipoDocumento = entityManager.find(TipoDocumento.class, tdocId);
		assertNull(tipoDocumento, "El usuario ya existe");
		
		tipoDocumento = new TipoDocumento();
		tipoDocumento.setTdocId(tdocId);
		tipoDocumento.setNombre("CIUDADANIA");
		tipoDocumento.setActivo('S');
		
		entityManager.getTransaction().begin();
		entityManager.persist(tipoDocumento);
		entityManager.getTransaction().commit();
	}
	
	@Test
	@DisplayName("ConsultarTipoDocumento")
	void bTest() {
		assertNotNull(entityManager,"El entityManager esta nulo");
		TipoDocumento tipoDocumento = entityManager.find(TipoDocumento.class, tdocId);
		assertNotNull(tipoDocumento);
		
		log.info(tipoDocumento.getNombre());
	}
	
	
	@Test
	@DisplayName("ModificarTipoDocumento")
	void cTest() {
		assertNotNull(entityManager,"El entityManager esta nulo");
		TipoDocumento tipoDocumento = entityManager.find(TipoDocumento.class, tdocId);
		assertNotNull(tipoDocumento, "El usuario no existe");
		
		tipoDocumento.setNombre("RESIDENTE");
		tipoDocumento.setActivo('S');
		
		entityManager.getTransaction().begin();
		entityManager.merge(tipoDocumento);
		entityManager.getTransaction().commit();
	}
	
	@Test
	@DisplayName("BorrarTipoDocumento")
	void dTest() {
		assertNotNull(entityManager,"El entityManager esta nulo");
		TipoDocumento tipoDocumento = entityManager.find(TipoDocumento.class, tdocId);
		assertNotNull(tipoDocumento, "El usuario no existe");
		
		entityManager.getTransaction().begin();
		entityManager.remove(tipoDocumento);
		entityManager.getTransaction().commit();
	}
	
	
	@BeforeAll
	public static void inicializarTodo() {
		entityManagerFactory = Persistence.createEntityManagerFactory("banco-persitencia");
		entityManager = entityManagerFactory.createEntityManager();
	}
	
	@AfterAll
	public static void cerrarTodo() {
		entityManager.close();
		entityManagerFactory.close();
	}

}
