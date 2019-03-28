package co.edu.usbcali.banco.vista;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuModel;
import co.edu.usbcali.banco.modelo.Cuenta;
import co.edu.usbcali.banco.modelo.Usuario;
import co.edu.usbcali.banco.util.FacesUtils;

@ViewScoped
@ManagedBean
public class MenuDinamicoVista {
	
	private MenuModel model;
	
	private static final String url = "http://localhost:8080/banco-web/";
	
	@PostConstruct
	public void init() {
		
		model = new DefaultMenuModel();
		
		Usuario usuario = (Usuario) FacesUtils.getfromSession("usuario");
		Cuenta cuenta = (Cuenta) FacesUtils.getfromSession("cuenta");
		
		if((usuario == null) && (cuenta == null)) {
			
			//Home index
			DefaultMenuItem itemHomeNoLogin = new DefaultMenuItem("Home");
			itemHomeNoLogin.setUrl(url+"index.xhtml");
			model.addElement(itemHomeNoLogin);
			
			//Login usuario
			DefaultMenuItem loginUsuario = new DefaultMenuItem("Empleado");
			loginUsuario.setUrl(url+"loginUsuario.xhtml");
			model.addElement(loginUsuario);
			
			//Login cliente
			DefaultMenuItem loginCliente = new DefaultMenuItem("Cliente");
			loginCliente.setUrl(url+"loginCliente.xhtml");
			model.addElement(loginCliente);
		}
		else {
			//Menu Cajero Consignación
			DefaultMenuItem itemHome = new DefaultMenuItem("Home");
			itemHome.setUrl(url+"xhtml/principal.xhtml");
			model.addElement(itemHome);
			
			if(cuenta != null) {
				
				//Menu Cliente Registrar Cuentas 
				DefaultMenuItem item1 = new DefaultMenuItem("Registrar Cuentas");
				item1.setUrl(url+"cliente/cuentaRegistrada.xhtml");
				model.addElement(item1);
				
				//Menu Cliente Transacciones
				DefaultMenuItem item2 = new DefaultMenuItem("Transacciones");
				item2.setUrl(url+"cliente/informacionDeTransacciones.xhtml");
				model.addElement(item2);
				
				//Menu Cliente Transferencia entre cuentas
				DefaultMenuItem item3 = new DefaultMenuItem("Transferencia entre cuentas");
				item3.setUrl(url+"cliente/transferenciaEntreCuentas.xhtml");
				model.addElement(item3);
				
				//Menu Cliente Gestion De Cuenta
				DefaultMenuItem item4 = new DefaultMenuItem("Gestión De Cuenta");
				item4.setUrl(url+"cliente/gestionCuentaDelCliente.xhtml");
				model.addElement(item4);
				
				//Menu Cliente Gestion De Cliente
				DefaultMenuItem item5 = new DefaultMenuItem("Gestión De Cliente");
				item5.setUrl(url+"cliente/gestionInformacionDelCliente.xhtml");
				model.addElement(item5);
			}
			
			if(usuario != null) {
				
				if(usuario.getTipoUsuario().getTiusId() == 1L) {
					
					//Menu Cajero Consignación
					DefaultMenuItem item1 = new DefaultMenuItem("Consignar");
					item1.setUrl(url+"cajero/consignar.xhtml");
					model.addElement(item1);
					
					
					//Menu Cajero Retiro
					DefaultMenuItem item2 = new DefaultMenuItem("Retirar");
					item2.setUrl(url+"cajero/retirar.xhtml");
					model.addElement(item2);	
				}
				
				if(usuario.getTipoUsuario().getTiusId() == 2L) {
					
					//Menu Asesor Comercial Gestion Clientes
					DefaultMenuItem item1 = new DefaultMenuItem("Gestion Clientes");
					item1.setUrl(url+"comercial/cliente.xhtml");
					model.addElement(item1);
					
					//Menu Asesor Comercial Gestion Cuentas
					DefaultMenuItem item2 = new DefaultMenuItem("Gestion Cuentas");
					item2.setUrl(url+"comercial/cuenta.xhtml");
					model.addElement(item2);
				}
				
				if(usuario.getTipoUsuario().getTiusId() == 3L) {
					
					//Menu Administrador Gestion Clientes
					DefaultMenuItem item1 = new DefaultMenuItem("Gestion Clientes");
					item1.setUrl(url+"comercial/cliente.xhtml");
					model.addElement(item1);
					
					//Menu Administrador Gestion Cuentas
					DefaultMenuItem item2 = new DefaultMenuItem("Gestion Cuentas");
					item2.setUrl(url+"comercial/cuenta.xhtml");
					model.addElement(item2);
					
					//Menu Administrador Gestion Tipo Documento
					DefaultMenuItem item3 = new DefaultMenuItem("Gestion Tipo De Documento");
					item3.setUrl(url+"admin/tipoDocumento.xhtml");
					model.addElement(item3);
					
					//Menu Administrador Gestion Usuario
					DefaultMenuItem item4 = new DefaultMenuItem("Gestion De Usuario");
					item4.setUrl(url+"admin/usuario.xhtml");
					model.addElement(item4);
					
					//Menu Administrador Gestion Tipo Usuario
					DefaultMenuItem item5 = new DefaultMenuItem("Gestion Tipo de Usuario");
					item5.setUrl(url+"admin/tipoUsuario.xhtml");
					model.addElement(item5);
				}
			}
			
			
			
			//Menu Quit
			DefaultMenuItem itemQuit = new DefaultMenuItem("Salir");
			itemQuit.setUrl(url+"j_spring_security_logout");
			model.addElement(itemQuit);
		}
		
		
	}
	
	public MenuModel getModel() {
		return model;
	}

	public void setModel(MenuModel model) {
		this.model = model;
	}

	public static String getUrl() {
		return url;
	}

	

	
}
