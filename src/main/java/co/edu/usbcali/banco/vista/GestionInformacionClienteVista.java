package co.edu.usbcali.banco.vista;


import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.commandbutton.CommandButton;

import co.edu.usbcali.banco.modelo.Cliente;
import co.edu.usbcali.banco.modelo.Cuenta;
import co.edu.usbcali.banco.util.FacesUtils;

@ViewScoped
@ManagedBean
public class GestionInformacionClienteVista {
	
	private String cuenId;
	private String txtDireccion;
	private String txtTelefono;
	private String txtEmail;
	
	private CommandButton btnModificar;
	
	
	@ManagedProperty(value="#{delegadoDeNegocio}")
	private IDelegadoDeNegocio delegadoDeNegocio;
	
	
	
	
	public String getCuenId() {
		return cuenId;
	}



	public void setCuenId(String cuenId) {
		this.cuenId = cuenId;
	}



	public String getTxtDireccion() {
		return txtDireccion;
	}



	public void setTxtDireccion(String txtDireccion) {
		this.txtDireccion = txtDireccion;
	}



	public String getTxtTelefono() {
		return txtTelefono;
	}



	public void setTxtTelefono(String txtTelefono) {
		this.txtTelefono = txtTelefono;
	}



	public String getTxtEmail() {
		return txtEmail;
	}



	public void setTxtEmail(String txtEmail) {
		this.txtEmail = txtEmail;
	}



	public CommandButton getBtnModificar() {
		return btnModificar;
	}



	public void setBtnModificar(CommandButton btnModificar) {
		this.btnModificar = btnModificar;
	}



	public IDelegadoDeNegocio getDelegadoDeNegocio() {
		return delegadoDeNegocio;
	}



	public void setDelegadoDeNegocio(IDelegadoDeNegocio delegadoDeNegocio) {
		this.delegadoDeNegocio = delegadoDeNegocio;
	}



	@PostConstruct
	public void init() {
		Cuenta cuenta = (Cuenta) FacesUtils.getfromSession("cuenta");
		Cliente cliente = delegadoDeNegocio.consultarClientePorId(cuenta.getCliente().getClieId());
		
		cuenId = cuenta.getCuenId();
		txtDireccion = cliente.getDireccion();
		txtTelefono = cliente.getTelefono();
		txtEmail = cliente.getEmail();
	}

	

	public String modificarAction() {
		
		try {
			Cuenta cuenta = (Cuenta) FacesUtils.getfromSession("cuenta");
			Cliente cliente = delegadoDeNegocio.consultarClientePorId(cuenta.getCliente().getClieId());
			
			cliente.setActivo(cliente.getActivo());
			cliente.setClieId(cliente.getClieId());
			cliente.setDireccion(txtDireccion);
			cliente.setEmail(txtEmail);
			cliente.setNombre(cliente.getNombre());
			cliente.setTelefono(txtTelefono);
			cliente.setTipoDocumento(cliente.getTipoDocumento());
			

			delegadoDeNegocio.modificarCliente(cliente);
			
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO,"Usted modifico con exito sus datos",""));
			
			
		} catch (Exception e) {
			FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
			FacesContext.getCurrentInstance().addMessage("", mensaje);
		}
		return "";
	}
	
}
