package co.edu.usbcali.banco.vista;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.primefaces.component.calendar.Calendar;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectonemenu.SelectOneMenu;

import co.edu.usbcali.banco.modelo.TipoTransaccion;

@ViewScoped
@ManagedBean
public class TransaccionVista {
	
	private InputText txtTransaccionId;
	private InputText txtCuentaId;
	private InputText txtUsuarioId;
	private InputText txtValor;
	private Calendar calFecha;
	private SelectOneMenu somTipoTransaccion;
	
	private CommandButton btnCrear;
	private CommandButton btnModificar;
	private CommandButton btnBorrar;
	private CommandButton btnLimpiar;
	
	private List<SelectItem> losTipoTransaccionSelectItem;
	
	@ManagedProperty(value="#{delegadoDeNegocio}")
	private IDelegadoDeNegocio delegadoDeNegocio;
	
	
	public String crearAction() {
		return "";
	}
	public String modificarAction() {
		return "";
	}
	public String borrarAction() {
		return "";
	}
	public String limpiarAction() {
		return "";
	}
	public InputText getTxtTransaccionId() {
		return txtTransaccionId;
	}
	public void setTxtTransaccionId(InputText txtTransaccionId) {
		this.txtTransaccionId = txtTransaccionId;
	}
	public InputText getTxtCuentaId() {
		return txtCuentaId;
	}
	public void setTxtCuentaId(InputText txtCuentaId) {
		this.txtCuentaId = txtCuentaId;
	}
	public InputText getTxtUsuarioId() {
		return txtUsuarioId;
	}
	public void setTxtUsuarioId(InputText txtUsuarioId) {
		this.txtUsuarioId = txtUsuarioId;
	}
	public InputText getTxtValor() {
		return txtValor;
	}
	public void setTxtValor(InputText txtValor) {
		this.txtValor = txtValor;
	}
	public Calendar getCalFecha() {
		return calFecha;
	}
	public void setCalFecha(Calendar calFecha) {
		this.calFecha = calFecha;
	}
	public SelectOneMenu getSomTipoTransaccion() {
		return somTipoTransaccion;
	}
	public void setSomTipoTransaccion(SelectOneMenu somTipoTransaccion) {
		this.somTipoTransaccion = somTipoTransaccion;
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
	public List<SelectItem> getLosTipoTransaccionSelectItem() {
		if(losTipoTransaccionSelectItem == null) {
			List<TipoTransaccion> losTipoTransaccion = delegadoDeNegocio.consultarTipoTransaccionTodos();
			losTipoTransaccionSelectItem = new ArrayList<>();
			for (TipoTransaccion tipoTransaccion : losTipoTransaccion) {
				losTipoTransaccionSelectItem.add(new SelectItem(tipoTransaccion.getTitrId(), tipoTransaccion.getNombre()));
			}
		}
		return losTipoTransaccionSelectItem;
	}
	public void setLosTipoTransaccionSelectItem(List<SelectItem> losTipoTransaccionSelectItem) {
		this.losTipoTransaccionSelectItem = losTipoTransaccionSelectItem;
	}
	public IDelegadoDeNegocio getDelegadoDeNegocio() {
		return delegadoDeNegocio;
	}
	public void setDelegadoDeNegocio(IDelegadoDeNegocio delegadoDeNegocio) {
		this.delegadoDeNegocio = delegadoDeNegocio;
	}
	
	

}
