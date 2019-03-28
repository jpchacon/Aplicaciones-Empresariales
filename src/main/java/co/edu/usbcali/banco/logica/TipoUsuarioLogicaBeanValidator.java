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

import co.edu.usbcali.banco.dao.ITipoUsuarioDAO;
import co.edu.usbcali.banco.modelo.TipoUsuario;

@Service
@Scope("singleton")
public class TipoUsuarioLogicaBeanValidator implements ITipoUsuarioLogica{
	
	private Logger log = LoggerFactory.getLogger(TipoUsuarioLogicaBeanValidator.class);
	
	@Autowired
	private ITipoUsuarioDAO tipoUsuarioDAO;
	
	@Autowired
	private Validator validator;

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void grabar(TipoUsuario tipoUsuario) throws Exception {
		log.info("Inicio el grabado del tipo de usuario");
		
		if(tipoUsuario == null) {
			throw new Exception("El tipo de usuario no puede ser Nulo");
		}
		
		validarTipoUsuario(tipoUsuario);
		
		if(consultarPorId(tipoUsuario.getTiusId()) != null) {
			throw new Exception("El tipo de usuario ya existe");
		}
		
		tipoUsuarioDAO.grabar(tipoUsuario);
		log.info("Finalizo el grabado del tipo de usuario");
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void modificar(TipoUsuario tipoUsuario) throws Exception {
		log.info("Inicio el modificado del tipo de usuario");
		
		if(tipoUsuario == null) {
			throw new Exception("El tipo de usuario no puede ser Nulo");
		}
		
		validarTipoUsuario(tipoUsuario);
		
		tipoUsuarioDAO.modificar(tipoUsuario);
		log.info("Finalizo el modificado del tipo de usuario");
		
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void borrar(TipoUsuario tipoUsuario) throws Exception {
		log.info("Inicio el borrado del tipo de usuario");
		
		if(tipoUsuario == null) {
			throw new Exception("El tipo de usuario no puede ser nulo");
		}
		
		validarTipoUsuario(tipoUsuario);
		
		TipoUsuario entity = tipoUsuarioDAO.consultarPorId(tipoUsuario.getTiusId());
		tipoUsuarioDAO.borrar(entity);
		
		log.info("Finalizao el borrado del usuario");
		
	}

	@Override
	@Transactional(readOnly=true)
	public TipoUsuario consultarPorId(long tiusId) {
		return tipoUsuarioDAO.consultarPorId(tiusId);
	}

	@Override
	@Transactional(readOnly=true)
	public List<TipoUsuario> consultarTodos() {
		return tipoUsuarioDAO.consultarTodos();
	}
	
	public void validarTipoUsuario(TipoUsuario tipoUsuario) throws Exception {
	    try {
	        Set<ConstraintViolation<TipoUsuario>> constraintViolations = validator.validate(tipoUsuario);

	        if (constraintViolations.size() > 0) {
	            StringBuilder strMessage = new StringBuilder();

	            for (ConstraintViolation<TipoUsuario> constraintViolation : constraintViolations) {
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
