package co.edu.usbcali.banco.vista;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
import co.edu.usbcali.banco.modelo.TipoDocumento;

@ViewScoped
@ManagedBean
public class ClienteVista {
	
	private InputText txtIdentificacion;
	private InputText txtNombre;
	private InputText txtDireccion;
	private InputText txtTelefono;
	private InputText txtMail;
	private SelectOneMenu somTipoDocumento;
	private SelectOneMenu somActivo;
	
	private String cuenId;
	private String passwd;
	private String saldo;
	private String estado;
	
	private CommandButton btnCrear;
	private CommandButton btnModificar;
	private CommandButton btnLimpiar;
	
	private List<SelectItem> losTipoDocumentoSelectItem;
	
	@ManagedProperty(value="#{delegadoDeNegocio}")
	private IDelegadoDeNegocio delegadoDeNegocio;
	
	private List<Cliente> losClientes;
	
	public void txtIdentificacionListener() {
		BigDecimal id = null;
		
		try {
			
			id = new BigDecimal(txtIdentificacion.getValue().toString());
			
		} catch (Exception e) {
			
			FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "La identificación debe ser numérica", "");
			FacesContext.getCurrentInstance().addMessage("", mensaje);
		}
		
		Cliente cliente = delegadoDeNegocio.consultarClientePorId(id);
		
		if(cliente == null) {
			
			txtNombre.resetValue();
			txtDireccion.resetValue();
			txtTelefono.resetValue();
			txtMail.resetValue();
			somTipoDocumento.resetValue();
			somActivo.resetValue();
			
			btnCrear.setDisabled(false);
			btnModificar.setDisabled(true);
			
			
		}else {
			
			txtNombre.setValue(cliente.getNombre());
			txtDireccion.setValue(cliente.getDireccion());
			txtTelefono.setValue(cliente.getTelefono());
			txtMail.setValue(cliente.getEmail());
			somActivo.setValue(cliente.getActivo());
			somTipoDocumento.setValue(cliente.getTipoDocumento().getTdocId());
			
			
			btnModificar.setDisabled(false);
			btnCrear.setDisabled(true);
			
		}
	}
	
	
	
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getCuenId() {
		return cuenId;
	}
	public void setCuenId(String cuenId) {
		this.cuenId = cuenId;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getSaldo() {
		return saldo;
	}
	public void setSaldo(String saldo) {
		this.saldo = saldo;
	}
	public List<SelectItem> getLosTipoDocumentoSelectItem() {
		if(losTipoDocumentoSelectItem == null) {
			List<TipoDocumento> losTipoDocumento = delegadoDeNegocio.consultarTipoDocumentoTodos();
			losTipoDocumentoSelectItem = new ArrayList<>();
			for (TipoDocumento tipoDocumento : losTipoDocumento) {
				losTipoDocumentoSelectItem.add(new SelectItem(tipoDocumento.getTdocId(), tipoDocumento.getNombre()));
			}
		}
		return losTipoDocumentoSelectItem;
	}
	public void setLosTipoDocumentoSelectItem(List<SelectItem> losTipoDocumentoSelectItem) {
		this.losTipoDocumentoSelectItem = losTipoDocumentoSelectItem;
	}
	public IDelegadoDeNegocio getDelegadoDeNegocio() {
		return delegadoDeNegocio;
	}
	public void setDelegadoDeNegocio(IDelegadoDeNegocio delegadoDeNegocio) {
		this.delegadoDeNegocio = delegadoDeNegocio;
	}
	
	public String crearAction() {
		
		try {
			Cliente cliente = new Cliente();
			cliente.setActivo(somActivo.getValue().toString().charAt(0));
			cliente.setClieId(new BigDecimal(txtIdentificacion.getValue().toString()));
			cliente.setDireccion(txtDireccion.getValue().toString());
			cliente.setEmail(txtMail.getValue().toString());
			cliente.setNombre(txtNombre.getValue().toString());
			cliente.setTelefono(txtTelefono.getValue().toString());
			TipoDocumento tipoDocumento = delegadoDeNegocio
					.consultarTipoDocumentoPorId(Long.parseLong(somTipoDocumento.getValue().toString()));
			cliente.setTipoDocumento(tipoDocumento);
			
			delegadoDeNegocio.grabarCliente(cliente);
			
			crearCuenta(new BigDecimal(txtIdentificacion.getValue().toString()));
			
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO,"El cliente se creo con exito",""));
			losClientes = null;
			
		} catch (Exception e) {
			FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
			FacesContext.getCurrentInstance().addMessage("", mensaje);
		}
		return "";
	}
	
	public String modificarAction() {
		
		try {
			Cliente cliente = new Cliente();
			cliente.setActivo(somActivo.getValue().toString().charAt(0));
			cliente.setClieId(new BigDecimal(txtIdentificacion.getValue().toString()));
			cliente.setDireccion(txtDireccion.getValue().toString());
			cliente.setEmail(txtMail.getValue().toString());
			cliente.setNombre(txtNombre.getValue().toString());
			cliente.setTelefono(txtTelefono.getValue().toString());
			TipoDocumento tipoDocumento = delegadoDeNegocio
					.consultarTipoDocumentoPorId(Long.parseLong(somTipoDocumento.getValue().toString()));
			cliente.setTipoDocumento(tipoDocumento);
			
			delegadoDeNegocio.modificarCliente(cliente);
			
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO,"El cliente se modifico con exito",""));
			losClientes = null;
			
		} catch (Exception e) {
			FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
			FacesContext.getCurrentInstance().addMessage("", mensaje);
		}
		return "";
	}
	
	public String borrarAction() {
		
		try {
			Cliente cliente = new Cliente();
			cliente.setActivo(somActivo.getValue().toString().charAt(0));
			cliente.setClieId(new BigDecimal(txtIdentificacion.getValue().toString()));
			cliente.setDireccion(txtDireccion.getValue().toString());
			cliente.setEmail(txtMail.getValue().toString());
			cliente.setNombre(txtNombre.getValue().toString());
			cliente.setTelefono(txtTelefono.getValue().toString());
			TipoDocumento tipoDocumento = delegadoDeNegocio
					.consultarTipoDocumentoPorId(Long.parseLong(somTipoDocumento.getValue().toString()));
			cliente.setTipoDocumento(tipoDocumento);
			
			delegadoDeNegocio.borrarCliente(cliente);
			
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO,"El cliente se borro con exito",""));
			losClientes = null;
			
		} catch (Exception e) {
			FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
			FacesContext.getCurrentInstance().addMessage("", mensaje);
		}
		return "";
	}
	
	public String limpiarAction() {
		txtIdentificacion.resetValue();
		txtNombre.resetValue();
		txtDireccion.resetValue();
		txtTelefono.resetValue();
		txtMail.resetValue();
		somTipoDocumento.resetValue();
		somActivo.resetValue();
		
		this.cuenId = "";
		this.passwd = "";
		this.saldo = "";
		this.estado = "";
		
		
		btnModificar.setDisabled(true);
		btnCrear.setDisabled(true);
		return "";
	}
	
	public InputText getTxtIdentificacion() {
		return txtIdentificacion;
	}
	public void setTxtIdentificacion(InputText txtIdentificacion) {
		this.txtIdentificacion = txtIdentificacion;
	}
	public InputText getTxtNombre() {
		return txtNombre;
	}
	public void setTxtNombre(InputText txtNombre) {
		this.txtNombre = txtNombre;
	}
	public InputText getTxtDireccion() {
		return txtDireccion;
	}
	public void setTxtDireccion(InputText txtDireccion) {
		this.txtDireccion = txtDireccion;
	}
	public InputText getTxtTelefono() {
		return txtTelefono;
	}
	public void setTxtTelefono(InputText txtTelefono) {
		this.txtTelefono = txtTelefono;
	}
	public InputText getTxtMail() {
		return txtMail;
	}
	public void setTxtMail(InputText txtMail) {
		this.txtMail = txtMail;
	}
	public SelectOneMenu getSomTipoDocumento() {
		return somTipoDocumento;
	}
	public void setSomTipoDocumento(SelectOneMenu somTipoDocumento) {
		this.somTipoDocumento = somTipoDocumento;
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
	public SelectOneMenu getSomActivo() {
		return somActivo;
	}
	public void setSomActivo(SelectOneMenu somActivo) {
		this.somActivo = somActivo;
	}

	public List<Cliente> getLosClientes() {
		if(losClientes == null) {
			losClientes = delegadoDeNegocio.consultarClienteTodos();
		}
		return losClientes;
	}

	public void setLosClientes(List<Cliente> losClientes) {
		this.losClientes = losClientes;
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
	
	public void crearCuenta(BigDecimal idCliente) {
		try {
			Cuenta cuenta = new Cuenta();
			cuenta.setActiva('N');
			
			String idCuenta = generarNumeroCuenta();
			cuenta.setCuenId(idCuenta);
			cuenta.setSaldo(new BigDecimal(0));
			
			String passwd = generarPassword();
			cuenta.setClave(passwd);
			
			Cliente cliente = delegadoDeNegocio.consultarClientePorId(idCliente);
			cuenta.setCliente(cliente);
			
			
			this.cuenId = idCuenta;
			this.passwd = passwd;
			this.saldo = "0";
			this.estado = "N";
 			
			delegadoDeNegocio.grabarCuenta(cuenta);
			
			txtIdentificacionListener();
			
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO,"La cuenta se creo con exito",""));
			
		} catch (Exception e) {
			FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
			FacesContext.getCurrentInstance().addMessage("", mensaje);
		}
		
	}
	
}
