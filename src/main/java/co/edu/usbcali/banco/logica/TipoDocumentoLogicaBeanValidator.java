package co.edu.usbcali.banco.logica;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.banco.dao.ITipoDocumentoDAO;
import co.edu.usbcali.banco.modelo.TipoDocumento;

@Service
@Scope("singleton")
public class TipoDocumentoLogicaBeanValidator implements ITipoDocumentoLogica {
	
	private Logger log = LoggerFactory.getLogger(TipoDocumentoLogicaBeanValidator.class);
	
	@Autowired
	private ITipoDocumentoDAO tipoDocumentoDAO;
	
	@Autowired
	private Validator validator;

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void grabar(TipoDocumento tipoDocumento) throws Exception {

		if(tipoDocumento == null) {
			throw new Exception("El tipo de documento no puede ser Nulo");
		}
		
		validarTipoDocumento(tipoDocumento);
		
		if(consultarPorId(tipoDocumento.getTdocId()) != null) {
			throw new Exception("El tipo de documento ya existe");
		}
		
		tipoDocumentoDAO.grabar(tipoDocumento);

	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void modificar(TipoDocumento tipoDocumento) throws Exception {

		if(tipoDocumento == null) {
			throw new Exception("El tipo de documento no puede ser Nulo");
		}
		
		validarTipoDocumento(tipoDocumento);
		
		tipoDocumentoDAO.modificar(tipoDocumento);

	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void borrar(TipoDocumento tipoDocumento) throws Exception {
		
		if(tipoDocumento == null) {
			throw new Exception("El tipo de documento no puede ser Nulo");
		}
		
		validarTipoDocumento(tipoDocumento);
		
		TipoDocumento entity = tipoDocumentoDAO.consultarPorId(tipoDocumento.getTdocId());
		
		tipoDocumentoDAO.borrar(entity);

	}

	@Override
	@Transactional(readOnly=true)
	public TipoDocumento consultarPorId(long tdocId) {
		return tipoDocumentoDAO.consultarPorId(tdocId);
	}

	@Override
	@Transactional(readOnly=true)
	public List<TipoDocumento> consultarTodos() {
		return tipoDocumentoDAO.consultarTodos();
	}
	
	public void validarTipoDocumento(TipoDocumento tipoDocumento) throws Exception {
	    try {
	        Set<ConstraintViolation<TipoDocumento>> constraintViolations = validator.validate(tipoDocumento);

	        if (constraintViolations.size() > 0) {
	            StringBuilder strMessage = new StringBuilder();

	            for (ConstraintViolation<TipoDocumento> constraintViolation : constraintViolations) {
	                strMessage.append(constraintViolation.getPropertyPath().toString());
	                strMessage.append(" - ");
	                strMessage.append(constraintViolation.getMessage());
	                strMessage.append(". \n");
	            }

	            throw new Exception(strMessage.toString());
	        }
	    } catch (Exception e) {
	        throw e;
	    }
	}

}
