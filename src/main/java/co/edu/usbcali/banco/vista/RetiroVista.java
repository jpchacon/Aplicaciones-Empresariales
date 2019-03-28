package co.edu.usbcali.banco.vista;

import java.math.BigDecimal;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;

import co.edu.usbcali.banco.modelo.Cliente;
import co.edu.usbcali.banco.modelo.Cuenta;
import co.edu.usbcali.banco.modelo.Usuario;
import co.edu.usbcali.banco.util.FacesUtils;

@ViewScoped
@ManagedBean
public class RetiroVista {
	private InputText txtCuendId;
	private InputText txtNombreCliente;
	private InputText txtValorRetiro;
	private InputText txtSaldoCuenta;

	private CommandButton btnRetirar;
	private CommandButton btnLimpiar;
	
	@ManagedProperty(value="#{delegadoDeNegocio}")
	private IDelegadoDeNegocio delegadoDeNegocio;
	
	public void txtCuendIdListener() {
		
		String id = null;
		
		try {
			
			id = new String(txtCuendId.getValue().toString());
			
		} catch (Exception e) {
			FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "La identificación debe ser numérica", "");
			FacesContext.getCurrentInstance().addMessage("", mensaje);
		}
		
		Cuenta cuenta = delegadoDeNegocio.consultarCuentaPorId(id);
		if(cuenta == null) {
			txtCuendId.resetValue();
			txtNombreCliente.resetValue();
			txtValorRetiro.resetValue();
			txtSaldoCuenta.resetValue();
			
			btnRetirar.setDisabled(true);
		}else {
			Cliente cliente = delegadoDeNegocio.consultarClientePorId(cuenta.getCliente().getClieId());
			txtNombreCliente.setValue(cliente.getNombre());
			txtSaldoCuenta.setValue(cuenta.getSaldo());
			txtSaldoCuenta.setDisabled(true);
			txtNombreCliente.setDisabled(true);
			
			btnRetirar.setDisabled(false);
		}
		
	}
	public InputText getTxtCuendId() {
		return txtCuendId;
	}
	public void setTxtCuendId(InputText txtCuendId) {
		this.txtCuendId = txtCuendId;
	}
	public InputText getTxtNombreCliente() {
		return txtNombreCliente;
	}
	public void setTxtNombreCliente(InputText txtNombreCliente) {
		this.txtNombreCliente = txtNombreCliente;
	}
	public InputText getTxtSaldoCuenta() {
		return txtSaldoCuenta;
	}
	public void setTxtSaldoCuenta(InputText txtSaldoCuenta) {
		this.txtSaldoCuenta = txtSaldoCuenta;
	}
	public InputText getTxtValorRetiro() {
		return txtValorRetiro;
	}
	public void setTxtValorRetiro(InputText txtValorRetiro) {
		this.txtValorRetiro = txtValorRetiro;
	}
	public IDelegadoDeNegocio getDelegadoDeNegocio() {
		return delegadoDeNegocio;
	}
	public void setDelegadoDeNegocio(IDelegadoDeNegocio delegadoDeNegocio) {
		this.delegadoDeNegocio = delegadoDeNegocio;
	}
	public CommandButton getBtnRetirar() {
		return btnRetirar;
	}
	public void setBtnRetirar(CommandButton btnRetirar) {
		this.btnRetirar = btnRetirar;
	}
	public CommandButton getBtnLimpiar() {
		return btnLimpiar;
	}
	public void setBtnLimpiar(CommandButton btnLimpiar) {
		this.btnLimpiar = btnLimpiar;
	}
	public String retirarAction() {
		
		try {
			Cuenta cuenta = delegadoDeNegocio.consultarCuentaPorId(txtCuendId.getValue().toString());
			BigDecimal valor = new BigDecimal(txtValorRetiro.getValue().toString());
			Usuario usuario = (Usuario) FacesUtils.getfromSession("usuario");
			
			
			
			delegadoDeNegocio.retirar(cuenta.getCuenId(), valor, usuario.getUsuUsuario());
			txtCuendIdListener();
			txtValorRetiro.resetValue();
			
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO,"El retiro fue exitoso",""));
			//losClientes = null;
			
		} catch (Exception e) {
			FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
			FacesContext.getCurrentInstance().addMessage("", mensaje);
		}
			return "";
	}
	
	public String limpiarAction() {
		
		txtCuendId.resetValue();
		txtNombreCliente.resetValue();
		txtValorRetiro.resetValue();
		txtSaldoCuenta.resetValue();
		
		
		btnRetirar.setDisabled(true);
		return "";
	}
	
	
}
