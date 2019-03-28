package co.edu.usbcali.banco.vista;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectonemenu.SelectOneMenu;

import co.edu.usbcali.banco.modelo.Cliente;
import co.edu.usbcali.banco.modelo.Cuenta;
import co.edu.usbcali.banco.modelo.CuentaRegistrada;
import co.edu.usbcali.banco.modelo.TipoDocumento;
import co.edu.usbcali.banco.util.FacesUtils;

@ViewScoped
@ManagedBean
public class CuentaRegistradaVista {
	
	private InputText txtIdCuenta;
	private InputText txtIdentificacion;
	private InputText txtNombreCliente;
	
	private SelectOneMenu somTipoDocumento;
	
	private CommandButton btnRegistrar;
	private CommandButton btnLimpiar;
	
	private List<CuentaRegistrada> lasCuentasRegistradas;
	private List<SelectItem> losTipoDocumentosSelectItem;
	
	@ManagedProperty(value="#{delegadoDeNegocio}")
	private IDelegadoDeNegocio delegadoDeNegocio;
	
	

	
	public void txtIdentificacionListener() {
		
		BigDecimal id = null;
		
		try {
			
			id = new BigDecimal(txtIdentificacion.getValue().toString());
			
		} catch (Exception e) {
			
			FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "La identificación debe ser un número", "");
			FacesContext.getCurrentInstance().addMessage("", mensaje);
		}
		
		Cliente cliente = delegadoDeNegocio.consultarClientePorId(id);
		
		if(cliente == null) {
			txtNombreCliente.setValue("Este cliente no existe");;
		}else {
			txtNombreCliente.setValue(cliente.getNombre());
		
			somTipoDocumento.setValue(cliente.getTipoDocumento().getTdocId());
		}
		
	}
	
	public List<SelectItem> getLosTipoDocumentosSelectItem() {
		if(losTipoDocumentosSelectItem == null) {
			List<TipoDocumento> losTipoDocumento = delegadoDeNegocio.consultarTipoDocumentoTodos();
			losTipoDocumentosSelectItem = new ArrayList<>();
			for (TipoDocumento tipoDocumento : losTipoDocumento) {
				losTipoDocumentosSelectItem.add(new SelectItem(tipoDocumento.getTdocId(), tipoDocumento.getNombre()));
			}
		}
		return losTipoDocumentosSelectItem;
	}

	public void setLosTipoDocumentosSelectItem(List<SelectItem> losTipoDocumentosSelectItem) {
		this.losTipoDocumentosSelectItem = losTipoDocumentosSelectItem;
	}

	public InputText getTxtIdCuenta() {
		return txtIdCuenta;
	}

	public void setTxtIdCuenta(InputText txtIdCuenta) {
		this.txtIdCuenta = txtIdCuenta;
	}

	public CommandButton getBtnRegistrar() {
		return btnRegistrar;
	}

	public void setBtnRegistrar(CommandButton btnRegistrar) {
		this.btnRegistrar = btnRegistrar;
	}

	public CommandButton getBtnLimpiar() {
		return btnLimpiar;
	}

	public void setBtnLimpiar(CommandButton btnLimpiar) {
		this.btnLimpiar = btnLimpiar;
	}

	public List<CuentaRegistrada> getLasCuentasRegistradas() {
		if(lasCuentasRegistradas == null) {
			Cuenta cuenta = (Cuenta) FacesUtils.getfromSession("cuenta");
			lasCuentasRegistradas = delegadoDeNegocio.consultarTodasLasCuentasRegistradasPorId(cuenta.getCliente().getClieId());
		}
		return lasCuentasRegistradas;
	}

	public void setLasCuentasRegistradas(List<CuentaRegistrada> lasCuentasRegistradas) {
		this.lasCuentasRegistradas = lasCuentasRegistradas;
	}

	public IDelegadoDeNegocio getDelegadoDeNegocio() {
		return delegadoDeNegocio;
	}

	public void setDelegadoDeNegocio(IDelegadoDeNegocio delegadoDeNegocio) {
		this.delegadoDeNegocio = delegadoDeNegocio;
	}
	
	public InputText getTxtIdentificacion() {
		return txtIdentificacion;
	}

	public void setTxtIdentificacion(InputText txtIdentificacion) {
		this.txtIdentificacion = txtIdentificacion;
	}
	
	public InputText getTxtNombreCliente() {
		return txtNombreCliente;
	}

	public void setTxtNombreCliente(InputText txtNombreCliente) {
		this.txtNombreCliente = txtNombreCliente;
	}
	
	public SelectOneMenu getSomTipoDocumento() {
		return somTipoDocumento;
	}

	public void setSomTipoDocumento(SelectOneMenu somTipoDocumento) {
		this.somTipoDocumento = somTipoDocumento;
	}

	public String registrarAction() {
			
			try {
				CuentaRegistrada cuentaRegistrada = new CuentaRegistrada();
				
				Cuenta cuenta = delegadoDeNegocio.consultarCuentaPorId(txtIdCuenta.getValue().toString());
				cuentaRegistrada.setCuenta(cuenta);
				
				Cuenta cuentaUsu = (Cuenta) FacesUtils.getfromSession("cuenta");
				cuentaRegistrada.setCliente(cuentaUsu.getCliente());
				
				Cliente cliente = delegadoDeNegocio.consultarClientePorId(cuenta.getCliente().getClieId());
				
				TipoDocumento tipoDocumento = delegadoDeNegocio.consultarTipoDocumentoPorId(cliente.getTipoDocumento().getTdocId());
				
				Long tipoDoc = new Long(somTipoDocumento.getValue().toString());
				BigDecimal indenficicacionClienteIngresada = new BigDecimal(txtIdentificacion.getValue().toString());
				BigDecimal indenficicacionCliente = cliente.getClieId();
				
				
				
				if((tipoDocumento.getTdocId()) != (tipoDoc) ) {
					FacesContext.getCurrentInstance().addMessage("",new FacesMessage(FacesMessage.SEVERITY_ERROR, "El tipo de documento no pertenece al cliente", ""));
	            	return "";
				}
				
				if(indenficicacionClienteIngresada.doubleValue() != indenficicacionCliente.doubleValue()) {
					FacesContext.getCurrentInstance().addMessage("",new FacesMessage(FacesMessage.SEVERITY_ERROR, "La cuenta no pertenece a este cliente", ""));
	            	return "";
				}
				
				delegadoDeNegocio.grabarCuentaRegistrada(cuentaRegistrada);
				
				FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO,"La cuenta se registro con exito",""));
				
				txtIdCuenta.resetValue();
				lasCuentasRegistradas = null;
				
			} catch (Exception e) {
				FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
				FacesContext.getCurrentInstance().addMessage("", mensaje);
			}
			return "";
		}

	public String limpiarAction() {
		
		txtIdentificacion.resetValue();
		txtNombreCliente.resetValue();
		
		somTipoDocumento.resetValue();
		txtIdCuenta.resetValue();
		return "";
	}
}
