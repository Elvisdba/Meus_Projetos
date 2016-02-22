package br.com.jonas.drogaria.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.jonas.drogaria.domain.Pessoa;
import br.com.jonas.drogaria.domain.Usuario;

public class UsuarioDAOTest {
	
	@Test
	@Ignore
	public void salvar() {
		Long codigoPessoa = 10L;
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscarPorcodigo(codigoPessoa);
		
		if (pessoa == null) {
			System.out.println("Pessoa nao encontrada");
		} else {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			Usuario usuario = new Usuario();
			
			usuario.setAtivo(true);
			usuario.setPessoa(pessoa);
			usuario.setSenha("250987");
			usuario.setTipo('B');
			
			usuarioDAO.salvar(usuario);
			System.out.println("Usuario salvo com sucesso");
		}
	}
	
	@Test
	@Ignore
	public void listar(){
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		List<Usuario> usuarios = usuarioDAO.listar();
		
		if (usuarios == null) {
			System.out.println("Usuario nao encontrada");
		} else {
			for (Usuario usuario : usuarios) {
				System.out.println("");
				System.out.println("Nome do Usuario: " + usuario.getPessoa().getNome());
				System.out.println("Status do Usuario: " + usuario.getAtivo());
				System.out.println("Codigo do Usuario: " + usuario.getCodigo());
				System.out.println("Tipo do Usuario: " + usuario.getTipo());
			}
		}
	}
	
	@Test
	@Ignore
	public void buscaPorCodigo() {
		Long codigoBusca = 24L;
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscarPorcodigo(codigoBusca);
		
		if (usuario == null) {
			System.out.println("Usuario nao encontrada");
		} else {
			System.out.println("Nome do Usuario: " + usuario.getPessoa().getNome());
			System.out.println("Status do Usuario: " + usuario.getAtivo());
			System.out.println("Codigo do Usuario: " + usuario.getCodigo());
			System.out.println("Tipo do Usuario: " + usuario.getTipo());
		}
	}

	@Test
	@Ignore
	public void editar() {
		Long codigoEdicao = 24L;
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscarPorcodigo(codigoEdicao);
		
		if (usuario == null) {
			System.out.println("Usuario nao encontrado para edicao");
		} else {
			usuario.setAtivo(true);
			usuario.setTipo('A');
			usuario.setSenha("37845873");
			
			usuarioDAO.editar(usuario);
			System.out.println("Usuario alterado com sucesso");
		}
	}
	
	@Test
	@Ignore
	public void excluir() {
		Long codigoExclusao = 25L;
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscarPorcodigo(codigoExclusao);
		
		if (usuario == null) {
			System.out.println("Usuario nao encontrado para ser excluido");
		} else {
			usuarioDAO.excluir(usuario);
			System.out.println("Usuario excluido com sucesso");
			
		}
		
	}
}
