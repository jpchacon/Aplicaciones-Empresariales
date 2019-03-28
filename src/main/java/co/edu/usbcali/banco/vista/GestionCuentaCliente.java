package co.edu.usbcali.banco.vista;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.commandbutton.CommandButton;

import co.edu.usbcali.banco.modelo.Cuenta;
import co.edu.usbcali.banco.util.FacesUtils;

@ViewScoped
@ManagedBean
public class GestionCuentaCliente {
	
	private String cuenId;
	private String txtSaldo;
	private String txtPassword;
	
	private CommandButton btnModificar;
	
	
	@ManagedProperty(value="#{delegadoDeNegocio}")
	private IDelegadoDeNegocio delegadoDeNegocio;


	public String getCuenId() {
		return cuenId;
	}
	

	public void setCuenId(String cuenId) {
		this.cuenId = cuenId;
	}


	public String getTxtPassword() {
		return txtPassword;
	}


	public void setTxtPassword(String txtPassword) {
		this.txtPassword = txtPassword;
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
	
	public String getTxtSaldo() {
		return txtSaldo;
	}


	public void setTxtSaldo(String txtSaldo) {
		this.txtSaldo = txtSaldo;
	}


	@PostConstruct
	public void init() {
		Cuenta cuenta = (Cuenta) FacesUtils.getfromSession("cuenta");
		cuenId = cuenta.getCuenId();
		txtSaldo = cuenta.getSaldo().toString();
		txtPassword = cuenta.getClave();
	}
	
	public String modificarAction() {
		
		try {
			Cuenta cuenta = (Cuenta) FacesUtils.getfromSession("cuenta");
			
			cuenta.setActiva(cuenta.getActiva());
			cuenta.setClave(txtPassword);
			cuenta.setCliente(cuenta.getCliente());
			cuenta.setCuenId(cuenta.getCuenId());
			cuenta.setSaldo(cuenta.getSaldo());

			delegadoDeNegocio.modificarCuenta(cuenta);
			
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO,"Usted cambio su contraseña con exito",""));
			
			
		} catch (Exception e) {
			FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
			FacesContext.getCurrentInstance().addMessage("", mensaje);
		}
		return "";
	}
	
}
