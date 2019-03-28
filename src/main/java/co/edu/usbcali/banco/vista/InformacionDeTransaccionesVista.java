package co.edu.usbcali.banco.vista;



import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.component.commandbutton.CommandButton;

import co.edu.usbcali.banco.modelo.Cuenta;
import co.edu.usbcali.banco.modelo.Transaccion;
import co.edu.usbcali.banco.util.FacesUtils;



@ViewScoped
@ManagedBean
public class InformacionDeTransaccionesVista {
	
	private List<Transaccion> lasTransacciones;
	private String tipoTransaccion;
	
	
	private CommandButton btnRetiros;
	private CommandButton btnConsignaciones;
	private CommandButton btnTransferencias;
	
	private Integer valor;
	
	
	@ManagedProperty(value="#{delegadoDeNegocio}")
	private IDelegadoDeNegocio delegadoDeNegocio;


	public List<Transaccion> getLasTransacciones() {	
		return lasTransacciones;
	}


	public void setLasTransacciones(List<Transaccion> lasTransacciones) {
		this.lasTransacciones = lasTransacciones;
	}


	public CommandButton getBtnRetiros() {
		return btnRetiros;
	}


	public void setBtnRetiros(CommandButton btnRetiros) {
		this.btnRetiros = btnRetiros;
	}


	public CommandButton getBtnConsignaciones() {
		return btnConsignaciones;
	}


	public void setBtnConsignaciones(CommandButton btnConsignaciones) {
		this.btnConsignaciones = btnConsignaciones;
	}


	public CommandButton getBtnTransferencias() {
		return btnTransferencias;
	}


	public void setBtnTransferencias(CommandButton btnTransferencias) {
		this.btnTransferencias = btnTransferencias;
	}


	public IDelegadoDeNegocio getDelegadoDeNegocio() {
		return delegadoDeNegocio;
	}


	public void setDelegadoDeNegocio(IDelegadoDeNegocio delegadoDeNegocio) {
		this.delegadoDeNegocio = delegadoDeNegocio;
	}

	public String getTipoTransaccion() {
		return tipoTransaccion;
	}


	public void setTipoTransaccion(String tipoTransaccion) {
		this.tipoTransaccion = tipoTransaccion;
	}
	public Integer getValor() {
		return valor;
	}


	public void setValor(Integer valor) {
		this.valor = valor;
	}


	public String mostrarRetiros() {
		lasTransacciones = null;
		Cuenta cuenta = (Cuenta) FacesUtils.getfromSession("cuenta");
		lasTransacciones = delegadoDeNegocio.consultarPorCuentaYTipoTransaccion(cuenta.getCuenId(), 1L);
		tipoTransaccion = "Retiros";
		return "";
	}
	
	public String mostrarConsignaciones() {
		lasTransacciones = null;
		Cuenta cuenta = (Cuenta) FacesUtils.getfromSession("cuenta");
		lasTransacciones = delegadoDeNegocio.consultarPorCuentaYTipoTransaccion(cuenta.getCuenId(), 2L);
		tipoTransaccion = "Consignaciones";
		return "";
	}

	public String mostrarTransferencias() {
		lasTransacciones = null;
		Cuenta cuenta = (Cuenta) FacesUtils.getfromSession("cuenta");
		lasTransacciones = delegadoDeNegocio.consultarPorCuentaYTipoTransaccion(cuenta.getCuenId(), 3L);
		tipoTransaccion = "Transferencias";
		return "";
	}

}
