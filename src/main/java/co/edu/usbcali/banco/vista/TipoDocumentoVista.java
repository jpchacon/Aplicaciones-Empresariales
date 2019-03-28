package co.edu.usbcali.banco.vista;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectonemenu.SelectOneMenu;

import co.edu.usbcali.banco.modelo.TipoDocumento;

@ViewScoped
@ManagedBean
public class TipoDocumentoVista {
	
	private InputText txtIdTipoDocumento;
	private InputText txtNombre;
	private SelectOneMenu somActivo;
	
	private CommandButton btnCrear;
	private CommandButton btnModificar;
	private CommandButton btnBorrar;
	private CommandButton btnLimpiar;
	
	@ManagedProperty(value="#{delegadoDeNegocio}")
	private IDelegadoDeNegocio delegadoDeNegocio;
	
	private List<TipoDocumento> losTipoDocumento;
	
	public void txtIdTipoDocumentoListener() {
		
		Long id = null;
		
		try {
			id = new Long(txtIdTipoDocumento.getValue().toString());
		} catch (Exception e) {
			FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "La identificación debe ser numérica", "");
			FacesContext.getCurrentInstance().addMessage("", mensaje);
		}
		
		TipoDocumento tipoDocumento = delegadoDeNegocio.consultarTipoDocumentoPorId(id);
		
		if(tipoDocumento == null) {
			txtNombre.resetValue();
			somActivo.resetValue();
			
			btnBorrar.setDisabled(true);
			btnModificar.setDisabled(true);
			btnCrear.setDisabled(false);
			
		}else {
			txtNombre.setValue(tipoDocumento.getNombre());
			somActivo.setValue(tipoDocumento.getActivo());
			
			btnBorrar.setDisabled(false);
			btnModificar.setDisabled(false);
			btnCrear.setDisabled(true);
			
		}
		
	}
	
	public IDelegadoDeNegocio getDelegadoDeNegocio() {
		return delegadoDeNegocio;
	}
	public void setDelegadoDeNegocio(IDelegadoDeNegocio delegadoDeNegocio) {
		this.delegadoDeNegocio = delegadoDeNegocio;
	}
	
	public String crearAction() {
		try {
			
			TipoDocumento tipoDocumento = new TipoDocumento();
			tipoDocumento.setActivo(somActivo.getValue().toString().charAt(0));
			tipoDocumento.setTdocId(new Long(txtIdTipoDocumento.getValue().toString()));
			tipoDocumento.setNombre(txtNombre.getValue().toString());
			
			delegadoDeNegocio.grabarTipoDocumento(tipoDocumento);
			
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO,"El tipo documento se creo con exito",""));
			
			btnCrear.setDisabled(true);
			losTipoDocumento = null;
		} catch (Exception e) {
			FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
			FacesContext.getCurrentInstance().addMessage("", mensaje);
		}
		return "";
	}
	public String modificarAction() {
		try {
			
			TipoDocumento tipoDocumento = new TipoDocumento();
			tipoDocumento.setActivo(somActivo.getValue().toString().charAt(0));
			tipoDocumento.setTdocId(new Long(txtIdTipoDocumento.getValue().toString()));
			tipoDocumento.setNombre(txtNombre.getValue().toString());
			
			delegadoDeNegocio.modificarTipoDocumento(tipoDocumento);
			
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO,"El tipo documento se modifico con exito",""));
			
			losTipoDocumento = null;
			
		} catch (Exception e) {
			FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
			FacesContext.getCurrentInstance().addMessage("", mensaje);
		}
		return "";
	}
	public String borrarAction() {
		try {
			
			TipoDocumento tipoDocumento = new TipoDocumento();
			tipoDocumento.setActivo(somActivo.getValue().toString().charAt(0));
			tipoDocumento.setTdocId(new Long(txtIdTipoDocumento.getValue().toString()));
			tipoDocumento.setNombre(txtNombre.getValue().toString());
			
			delegadoDeNegocio.borrarTipoDocumento(tipoDocumento);
			
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO,"El tipo documento se borro con exito",""));
			
			losTipoDocumento = null;
			
		} catch (Exception e) {
			FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
			FacesContext.getCurrentInstance().addMessage("", mensaje);
		}
		return "";
	}
	public String limpiarAction() {
		txtIdTipoDocumento.resetValue();
		txtNombre.resetValue();
		somActivo.resetValue();
		
		btnBorrar.setDisabled(true);
		btnModificar.setDisabled(true);
		btnCrear.setDisabled(true);
		return "";
	}
	
	
	
	public InputText getTxtIdTipoDocumento() {
		return txtIdTipoDocumento;
	}
	public void setTxtIdTipoDocumento(InputText txtIdTipoDocumento) {
		this.txtIdTipoDocumento = txtIdTipoDocumento;
	}
	public InputText getTxtNombre() {
		return txtNombre;
	}
	public void setTxtNombre(InputText txtNombre) {
		this.txtNombre = txtNombre;
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
	public CommandButton getBtnBorrar() {
		return btnBorrar;
	}
	public void setBtnBorrar(CommandButton btnBorrar) {
		this.btnBorrar = btnBorrar;
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

	public List<TipoDocumento> getLosTipoDocumento() {
		if(losTipoDocumento == null) {
			losTipoDocumento = delegadoDeNegocio.consultarTipoDocumentoTodos();
		}
		return losTipoDocumento;
	}

	public void setLosTipoDocumento(List<TipoDocumento> losTipoDocumento) {
		this.losTipoDocumento = losTipoDocumento;
	}
	
	
	
}
