package br.com.jonas.drogaria.Bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.omnifaces.util.Messages;

import com.google.gson.Gson;

import br.com.jonas.drogaria.dao.UsuarioDAO;
import br.com.jonas.drogaria.domain.Pessoa;
import br.com.jonas.drogaria.domain.Usuario;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable {
	private Usuario usuario;
	private List<Usuario> usuarios;
	private List<Pessoa> pessoas;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public void novo() {
		try {
			usuario = new Usuario();

			// PessoaDAO pessoaDAO = new PessoaDAO();
			// pessoas = pessoaDAO.listar("nome");

			PessoaBean pessoaBean = new PessoaBean();
			pessoas = pessoaBean.listarComRetorno();

		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar listar pessoas");
			e.printStackTrace();
		}
	}

	@PostConstruct
	public void listar() {
		try {
			
			//usando service
			Client client = ClientBuilder.newClient();
			WebTarget webTarget = client.target("http://localhost:8081/Drogaria/rest/usuario");

			String json = webTarget.request().get(String.class);
			
			Gson gson = new Gson();
			Usuario[] vetorUsuario = gson.fromJson(json, Usuario[].class);
			usuarios = Arrays.asList(vetorUsuario);
			
//			UsuarioDAO usuarioDAO = new UsuarioDAO();
//			usuarios = usuarioDAO.listar();
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar listar Usuario");
			e.printStackTrace();
		}
	}
	
	
	public List<Usuario> listarComRetorno() {
		try {
			
			//usando service
			Client client = ClientBuilder.newClient();
			WebTarget webTarget = client.target("http://localhost:8081/Drogaria/rest/usuario");

			String json = webTarget.request().get(String.class);
			
			Gson gson = new Gson();
			Usuario[] vetorUsuario = gson.fromJson(json, Usuario[].class);
			usuarios = Arrays.asList(vetorUsuario);
			
//			UsuarioDAO usuarioDAO = new UsuarioDAO();
//			usuarios = usuarioDAO.listar();
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar listar Usuario");
			e.printStackTrace();
		}
		
		return usuarios;
	}

	public void salvar() {

		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.merge(usuario);

			novo();

			usuarios = usuarioDAO.listar();
			Messages.addFlashGlobalInfo("Usuario Armazenado com sucesso");
		} catch (RuntimeException e) {
			Messages.addFlashGlobalError("Erro ao tentar salvar Usuario");
			e.printStackTrace();
		}

	}

	public void editar(ActionEvent evento) {
		usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");
	}

	public void excluir(ActionEvent evento) {
		try {
			usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");

			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.excluir(usuario);

			usuarios = usuarioDAO.listar();
			Messages.addFlashGlobalInfo("Usuario Excluido com sucesso");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar excluir um usuario");
			e.printStackTrace();
		}
	}

}
