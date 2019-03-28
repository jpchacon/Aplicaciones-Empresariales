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
public class ConsignarVista {
	
	private InputText txtCuendId;
	private InputText txtNombreCliente;
	private InputText txtValorConsignacion;
	private InputText txtSaldoCuenta;

	private CommandButton btnConsignar;
	private CommandButton btnLimpiar;
	
	@ManagedProperty(value="#{delegadoDeNegocio}")
	private IDelegadoDeNegocio delegadoDeNegocio;
	
	public void txtCuendIdListener() throws Exception {
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
			txtValorConsignacion.resetValue();
			txtSaldoCuenta.resetValue();
			
			btnConsignar.setDisabled(true);
		}else {
			Cliente cliente = delegadoDeNegocio.consultarClientePorId(cuenta.getCliente().getClieId());
			
			if(cuenta.getSaldo().intValue() < 200000) {
				cuenta.setActiva('N');
				cuenta.setClave(cuenta.getClave());
				cuenta.setCliente(cuenta.getCliente());
				cuenta.setCuenId(cuenta.getCuenId());
				cuenta.setSaldo(cuenta.getSaldo());
				delegadoDeNegocio.modificarCuenta(cuenta);
				FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO,"La cuenta debe tener un minimo de $200.000 para estar activa",""));
			}
			
			if(cuenta.getSaldo().intValue() >= 200000) {
				cuenta.setActiva('S');
				cuenta.setClave(cuenta.getClave());
				cuenta.setCliente(cuenta.getCliente());
				cuenta.setCuenId(cuenta.getCuenId());
				cuenta.setSaldo(cuenta.getSaldo());
				delegadoDeNegocio.modificarCuenta(cuenta);
				FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO,"Su cuenta se encuentra activa",""));
			}
			txtNombreCliente.setValue(cliente.getNombre());
			txtSaldoCuenta.setValue(cuenta.getSaldo());
			txtSaldoCuenta.setDisabled(true);
			txtNombreCliente.setDisabled(true);
			
			btnConsignar.setDisabled(false);
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

	public InputText getTxtValorConsignacion() {
		return txtValorConsignacion;
	}

	public void setTxtValorConsignacion(InputText txtValorConsignacion) {
		this.txtValorConsignacion = txtValorConsignacion;
	}

	public InputText getTxtSaldoCuenta() {
		return txtSaldoCuenta;
	}

	public void setTxtSaldoCuenta(InputText txtSaldoCuenta) {
		this.txtSaldoCuenta = txtSaldoCuenta;
	}

	public CommandButton getBtnConsignar() {
		return btnConsignar;
	}

	public void setBtnConsignar(CommandButton btnRetirar) {
		this.btnConsignar = btnRetirar;
	}

	public CommandButton getBtnLimpiar() {
		return btnLimpiar;
	}

	public void setBtnLimpiar(CommandButton btnLimpiar) {
		this.btnLimpiar = btnLimpiar;
	}

	public IDelegadoDeNegocio getDelegadoDeNegocio() {
		return delegadoDeNegocio;
	}

	public void setDelegadoDeNegocio(IDelegadoDeNegocio delegadoDeNegocio) {
		this.delegadoDeNegocio = delegadoDeNegocio;
	}
	
	public String consignarAction() {
		
		try {
			Cuenta cuenta = delegadoDeNegocio.consultarCuentaPorId(txtCuendId.getValue().toString());
			BigDecimal valor = new BigDecimal(txtValorConsignacion.getValue().toString());
			Usuario usuario = (Usuario) FacesUtils.getfromSession("usuario");
			
			
			
			delegadoDeNegocio.consignar(cuenta.getCuenId(), valor, usuario.getUsuUsuario());
			txtCuendIdListener();
			txtValorConsignacion.resetValue();
			
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO,"La consignación fue exitosa",""));
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
		txtValorConsignacion.resetValue();
		txtSaldoCuenta.resetValue();
		
		
		btnConsignar.setDisabled(true);
		return "";
	}

}
