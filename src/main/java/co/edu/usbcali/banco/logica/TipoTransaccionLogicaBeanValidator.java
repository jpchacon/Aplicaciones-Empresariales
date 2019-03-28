package co.edu.usbcali.banco.logica;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.banco.dao.ITipoTransaccionDAO;
import co.edu.usbcali.banco.modelo.TipoTransaccion;


@Service
@Scope("singleton")
public class TipoTransaccionLogicaBeanValidator implements ITipoTransaccionLogica {
	
	@Autowired
	private ITipoTransaccionDAO tipoTransaccionDAO;
	
	@Autowired
	private Validator validator;

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void grabar(TipoTransaccion tipoTransaccion) throws Exception {
		
		if(tipoTransaccion == null) {
			throw new Exception("El Tipo de transaccion no puede ser Nulo");
		}
		
		validarTipoTramsaccion(tipoTransaccion);
		
		if(consultarPorId(tipoTransaccion.getTitrId()) != null) {
			throw new Exception("El Tipo de transaccion ya existe");
		}
		
		tipoTransaccionDAO.grabar(tipoTransaccion);
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void modificar(TipoTransaccion tipoTransaccion) throws Exception {
		
		if(tipoTransaccion == null) {
			throw new Exception("El Tipo de transaccion no puede ser Nulo");
		}
		
		validarTipoTramsaccion(tipoTransaccion);
		
		tipoTransaccionDAO.modificar(tipoTransaccion);
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void borrar(TipoTransaccion tipoTransaccion) throws Exception {
		
		if(tipoTransaccion == null) {
			throw new Exception("El Tipo de transaccion no puede ser Nulo");
		}
		
		validarTipoTramsaccion(tipoTransaccion);
		
		TipoTransaccion entity = tipoTransaccionDAO.consultarPorId(tipoTransaccion.getTitrId());
		tipoTransaccionDAO.borrar(entity);

	}

	@Override
	@Transactional(readOnly=true)
	public TipoTransaccion consultarPorId(long titrId) {
		return tipoTransaccionDAO.consultarPorId(titrId);
	}

	@Override
	@Transactional(readOnly=true)
	public List<TipoTransaccion> consultarTodos() {
		return tipoTransaccionDAO.consultarTodos();
	}
	
	public void validarTipoTramsaccion(TipoTransaccion tipoTransaccion) throws Exception {
	    try {
	        Set<ConstraintViolation<TipoTransaccion>> constraintViolations = validator.validate(tipoTransaccion);

	        if (constraintViolations.size() > 0) {
	            StringBuilder strMessage = new StringBuilder();

	            for (ConstraintViolation<TipoTransaccion> constraintViolation : constraintViolations) {
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
