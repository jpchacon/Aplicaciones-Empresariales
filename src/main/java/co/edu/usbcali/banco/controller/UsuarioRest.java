package co.edu.usbcali.banco.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usbcali.banco.dto.UsuarioDTO;
import co.edu.usbcali.banco.logica.IUsuarioLogica;
import co.edu.usbcali.banco.mapper.IUsuarioMapper;
import co.edu.usbcali.banco.modelo.Usuario;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/usuario")
public class UsuarioRest {
	
	@Autowired
	private IUsuarioLogica usuarioLogica;
	
	@Autowired
	private IUsuarioMapper usuarioMapper;
	
	@GetMapping("/{id}")
	public UsuarioDTO consultarPorId(@PathVariable("id") String id) {
		Usuario usuario = usuarioLogica.consultarPorId(id);
		UsuarioDTO usuarioDTO = usuarioMapper.usuarioToUsuarioDTO(usuario); 
		return usuarioDTO;
	}
	
	@GetMapping("/")
	public List<UsuarioDTO> consultarTodos(){
		List<Usuario> listaUsuario = usuarioLogica.consultarTodos();
		List<UsuarioDTO> listaUsuariosDTO = usuarioMapper.listaUsuarioToListaUsuarioDTO(listaUsuario);
		return listaUsuariosDTO;
	}
	
	@PostMapping("/crear")
	public void crear(@RequestBody UsuarioDTO usuarioDTO) throws Exception {
		Usuario usuario = usuarioMapper.UsuarioDTOToUsuario(usuarioDTO);
		usuarioLogica.grabar(usuario);
	}
	
	@PutMapping("/modificar")
	public void modificar(@RequestBody UsuarioDTO usuarioDTO) throws Exception{
		Usuario usuario = usuarioMapper.UsuarioDTOToUsuario(usuarioDTO);
		usuarioLogica.modificar(usuario);
	}
	
	@DeleteMapping("/borrar/{id}")
	public void borrar(@PathVariable("id") String id) throws Exception{
		Usuario usuario = usuarioLogica.consultarPorId(id);
		usuarioLogica.borrar(usuario);
	}

}
