package co.edu.usbcali.banco.modelo;
// Generated 30/01/2018 10:10:31 AM by Hibernate Tools 5.2.3.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

/**
 * TipoDocumento generated by hbm2java
 */

@Entity
@Table(name = "tipo_documento", schema = "public")
public class TipoDocumento implements java.io.Serializable {
	
	@NotNull(message="El id no puede ser nulo")
	@Positive
	private long tdocId;
	
	@NotNull
	@Size(max=20)
	@NotEmpty
	private String nombre;
	
	private char activo;
	private Set<Cliente> clientes = new HashSet<Cliente>(0);

	public TipoDocumento() {
	}

	public TipoDocumento(long tdocId, String nombre, char activo) {
		this.tdocId = tdocId;
		this.nombre = nombre;
		this.activo = activo;
	}

	public TipoDocumento(long tdocId, String nombre, char activo, Set<Cliente> clientes) {
		this.tdocId = tdocId;
		this.nombre = nombre;
		this.activo = activo;
		this.clientes = clientes;
	}

	@Id

	@Column(name = "tdoc_id", unique = true, nullable = false)
	public long getTdocId() {
		return this.tdocId;
	}

	public void setTdocId(long tdocId) {
		this.tdocId = tdocId;
	}

	@Column(name = "nombre", nullable = false)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "activo", nullable = false, length = 1)
	public char getActivo() {
		return this.activo;
	}

	public void setActivo(char activo) {
		this.activo = activo;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tipoDocumento")
	public Set<Cliente> getClientes() {
		return this.clientes;
	}

	public void setClientes(Set<Cliente> clientes) {
		this.clientes = clientes;
	}

}
