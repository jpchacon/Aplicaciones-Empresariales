package co.edu.usbcali.banco.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.banco.modelo.Cliente;
import co.edu.usbcali.banco.modelo.Cuenta;
import co.edu.usbcali.banco.modelo.TipoDocumento;


@ExtendWith(SpringExtension.class)
@ContextConfiguration("/appContext.xml")
@Rollback(false)
class TestTipoDocumentoDAO {
	
	private final static Logger log = LoggerFactory.getLogger(TestTipoDocumentoDAO.class);
	
	@Autowired
	private ITipoDocumentoDAO tipoDocumentoDAO;
	
	static long tdocId = 4;
	
	@Test
	@DisplayName("Crear Tipo Documento")
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void aTest() {
		
		assertNotNull(tipoDocumentoDAO,"El tipoDocumentoDAO esta nulo");
		TipoDocumento tipoDocumento = tipoDocumentoDAO.consultarPorId(tdocId);
		assertNull(tipoDocumento,"El cliente ya existe");
		
		tipoDocumento=new TipoDocumento();
		tipoDocumento.setTdocId(tdocId);
		tipoDocumento.setNombre("Registro civil");
		tipoDocumento.setActivo('S');
		
		tipoDocumentoDAO.grabar(tipoDocumento);
	}
	
	
	@Test
	@DisplayName("Modificar Tipo Documento")
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void cTest() {
		assertNotNull(tipoDocumentoDAO,"El tipoDocumentoDAO esta nulo");
		TipoDocumento tipoDocumento = tipoDocumentoDAO.consultarPorId(tdocId);
		assertNotNull(tipoDocumento,"La cuenta no existe");
		
		tipoDocumento=new TipoDocumento();
		tipoDocumento.setTdocId(tdocId);
		tipoDocumento.setNombre("Registro de matrimonio");
		tipoDocumento.setActivo('S');
		
		tipoDocumentoDAO.modificar(tipoDocumento);
	}
	
	@Test
	@DisplayName("Borrar Tipo Documento")
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void dTest() {
		assertNotNull(tipoDocumentoDAO,"El tipoDocumentoDAO esta nulo");
		TipoDocumento tipoDocumento = tipoDocumentoDAO.consultarPorId(tdocId);
		assertNotNull(tipoDocumento,"El tipoDocumento no existe");
		
		tipoDocumentoDAO.borrar(tipoDocumento);
	}
	
	@Test
	@DisplayName("Consultar Tipo Documento por ID")
	@Transactional(readOnly=true)
	public void bTest() {
		assertNotNull(tipoDocumentoDAO);
		TipoDocumento tipoDocumento = tipoDocumentoDAO.consultarPorId(tdocId);
		assertNotNull(tipoDocumento);
		log.info("Id: " + tipoDocumento.getTdocId());
		log.info("Nombre de documento: " + tipoDocumento.getNombre());
		log.info("Estado: " + tipoDocumento.getActivo());
	}
	
	@Test
	@DisplayName("Consultar Todos Los Tipo Documento")
	@Transactional(readOnly=true)
	public void eTest() {
		assertNotNull(tipoDocumentoDAO);
		List<TipoDocumento> losTiposDocumento = tipoDocumentoDAO.consultarTodos();
		losTiposDocumento.forEach(tipoDocumento->{
			log.info("Id: " + tipoDocumento.getTdocId());
			log.info("Nombre de documento: " + tipoDocumento.getNombre());
			log.info("Estado: " + tipoDocumento.getActivo());
		});
		
	}

	

}
