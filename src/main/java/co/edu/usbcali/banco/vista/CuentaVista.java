package co.edu.usbcali.banco.vista;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectonemenu.SelectOneMenu;

import co.edu.usbcali.banco.modelo.Cliente;
import co.edu.usbcali.banco.modelo.Cuenta;

@ViewScoped
@ManagedBean
public class CuentaVista {
	
	private InputText txtCuentaId;
	private InputText txtSaldo;
	private InputText txtClave;
	private InputText txtIdCliente;
	private SelectOneMenu somActivo;
	
	private CommandButton btnCrear;
	private CommandButton btnModificar;
	private CommandButton btnLimpiar;
	
	@ManagedProperty(value="#{delegadoDeNegocio}")
	private IDelegadoDeNegocio delegadoDeNegocio;
	
	private List<Cuenta> lasCuentas;
	
	public void txtCuentaIdListener() {
		String id = null;
		
		try {
			
			id = new String(txtCuentaId.getValue().toString());
			
		} catch (Exception e) {
			FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "La identificación debe ser una cadena", "");
			FacesContext.getCurrentInstance().addMessage("", mensaje);
		}
		
		Cuenta cuenta = delegadoDeNegocio.consultarCuentaPorId(id);
		
		if(cuenta == null) {
			txtSaldo.resetValue();
			txtClave.resetValue();
			txtClave.setDisabled(true);
			somActivo.resetValue();
			txtIdCliente.resetValue();
			
			txtCuentaId.resetValue();
			txtCuentaId.setDisabled(true);
			
			btnModificar.setDisabled(true);
			btnCrear.setDisabled(false);
			
		}else {
			
			txtSaldo.setValue(cuenta.getSaldo());
			txtClave.setValue(cuenta.getClave());
			somActivo.setValue(cuenta.getActiva());
			
			txtClave.setDisabled(false);
			txtCuentaId.setDisabled(true);
			
			Cliente cliente = delegadoDeNegocio.consultarClientePorId(new BigDecimal(cuenta.getCliente().getClieId().toString()));
			txtIdCliente.setValue(cliente.getClieId());
			
			btnModificar.setDisabled(false);
			btnCrear.setDisabled(true);
			
		}
	}
	
	public InputText getTxtCuentaId() {
		return txtCuentaId;
	}
	public void setTxtCuentaId(InputText txtCuentaId) {
		this.txtCuentaId = txtCuentaId;
	}
	public InputText getTxtSaldo() {
		return txtSaldo;
	}
	public void setTxtSaldo(InputText txtSaldo) {
		this.txtSaldo = txtSaldo;
	}
	public InputText getTxtClave() {
		return txtClave;
	}
	public void setTxtClave(InputText txtClave) {
		this.txtClave = txtClave;
	}
	
	public SelectOneMenu getSomActivo() {
		return somActivo;
	}
	public void setSomActivo(SelectOneMenu somActivo) {
		this.somActivo = somActivo;
	}
	public CommandButton getBtnCrear() {
		return btnCrear;
	}
	public void setBtnCrear(CommandButton btnCrear) {
		this.btnCrear = btnCrear;
	}
	public CommandButton getBtnModificar() {
		return btnModificar;
	}
	public void setBtnModificar(CommandButton btnModificar) {
		this.btnModificar = btnModificar;
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
	
	public InputText getTxtIdCliente() {
		return txtIdCliente;
	}

	public void setTxtIdCliente(InputText txtIdCliente) {
		this.txtIdCliente = txtIdCliente;
	}
	
	public List<Cuenta> getLasCuentas() {
		if(lasCuentas == null) {
			lasCuentas = delegadoDeNegocio.consultarCuentaTodos();
		}
		return lasCuentas;
	}

	public void setLasCuentas(List<Cuenta> lasCuentas) {
		this.lasCuentas = lasCuentas;
	}
	
	private String generarPassword() throws NoSuchAlgorithmException {

			String[] symbols = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
			int length = 10;
			Random random = SecureRandom.getInstanceStrong(); // as of JDK 8, this should return the strongest algorithm available to the JVM
			StringBuilder sb = new StringBuilder(length);
			for (int i = 0; i < length; i++) {
			    int indexRandom = random.nextInt( symbols.length );
			    sb.append( symbols[indexRandom] );
			}
			
			String password = sb.toString();
			
		
		return password;
		
	}
	
	private String generarCuatroNumeros() throws NoSuchAlgorithmException {
		
		String[] symbols = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
		int length = 4;
		Random random = SecureRandom.getInstanceStrong(); // as of JDK 8, this should return the strongest algorithm available to the JVM
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
		    int indexRandom = random.nextInt( symbols.length );
		    sb.append( symbols[indexRandom] );
		}
		
		String cuatroNum = sb.toString();
		
		return cuatroNum;
	}
	
	private String generarNumeroCuenta() throws NoSuchAlgorithmException {
		String uno = generarCuatroNumeros();
		String dos = generarCuatroNumeros();
		String tres = generarCuatroNumeros();
		String cuatro = generarCuatroNumeros();
		String numeroCuenta = uno+"-"+dos+"-"+tres+"-"+cuatro;
		
		Cuenta cuenta = delegadoDeNegocio.consultarCuentaPorId(numeroCuenta);
		if(cuenta != null) {
			numeroCuenta = generarNumeroCuenta();
		}
		
		return numeroCuenta;
		
	}

	public String crearAction() {
		try {
			Cuenta cuenta = new Cuenta();
			cuenta.setActiva(somActivo.getValue().toString().charAt(0));
			
			String idCuenta = generarNumeroCuenta();
			cuenta.setCuenId(idCuenta);
			cuenta.setSaldo(new BigDecimal(txtSaldo.getValue().toString()));
			
			String passwd = generarPassword();
			cuenta.setClave(passwd);
			
			Cliente cliente = delegadoDeNegocio.consultarClientePorId(new BigDecimal(txtIdCliente.getValue().toString()));
			cuenta.setCliente(cliente);
			
			txtCuentaId.setValue(idCuenta);;
			
			delegadoDeNegocio.grabarCuenta(cuenta);
			
			txtCuentaIdListener();
			
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO,"La cuenta se creo con exito",""));
			
		} catch (Exception e) {
			FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
			FacesContext.getCurrentInstance().addMessage("", mensaje);
		}
		return "";
	}
	
	public String modificarAction() {
		try {
			Cuenta cuenta = new Cuenta();
			cuenta.setActiva(somActivo.getValue().toString().charAt(0));
			cuenta.setCuenId(txtCuentaId.getValue().toString());
			cuenta.setSaldo(new BigDecimal(txtSaldo.getValue().toString()));
			cuenta.setClave(txtClave.getValue().toString());
			Cliente cliente = delegadoDeNegocio.consultarClientePorId(new BigDecimal(txtIdCliente.getValue().toString()));
			cuenta.setCliente(cliente);
			
			
			delegadoDeNegocio.modificarCuenta(cuenta);
			
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO,"La cuenta se modifico con exito",""));
			
		} catch (Exception e) {
			FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
			FacesContext.getCurrentInstance().addMessage("", mensaje);
		}
		return "";
	}
	public String borrarAction() {
		try {
			Cuenta cuenta = new Cuenta();
			cuenta.setActiva(somActivo.getValue().toString().charAt(0));
			cuenta.setCuenId(txtCuentaId.getValue().toString());
			cuenta.setSaldo(new BigDecimal(txtSaldo.getValue().toString()));
			cuenta.setClave(txtClave.getValue().toString());
			Cliente cliente = delegadoDeNegocio.consultarClientePorId(new BigDecimal(txtIdCliente.getValue().toString()));
			cuenta.setCliente(cliente);
			
			
			delegadoDeNegocio.borrarCuenta(cuenta);
			
			limpiarAction();
			
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO,"La cuenta se borro con exito",""));
			
		} catch (Exception e) {
			FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
			FacesContext.getCurrentInstance().addMessage("", mensaje);
		}
		return "";
	}
	public String limpiarAction() {
		txtCuentaId.resetValue();
		txtSaldo.resetValue();
		txtClave.resetValue();
		somActivo.resetValue();
		txtIdCliente.resetValue();
		txtCuentaId.setDisabled(false);
		
		btnModificar.setDisabled(true);
		btnCrear.setDisabled(true);
		return "";
	}
}
