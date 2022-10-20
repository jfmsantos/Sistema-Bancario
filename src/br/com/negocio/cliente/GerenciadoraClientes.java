package br.com.negocio.cliente;

import java.util.List;

import br.com.negocio.IdadeNaoPermitidaException;

public class GerenciadoraClientes {

	private List<Cliente> clientesDoBanco;

	public GerenciadoraClientes(List<Cliente> clientesDoBanco) {
		this.clientesDoBanco = clientesDoBanco;
	}
	
	public List<Cliente> getClientesDoBanco() {
		return clientesDoBanco;
	}
	
	public Cliente pesquisaCliente (int idCliente) {

		return clientesDoBanco.stream()
		        .filter(cliente -> cliente.getId() == idCliente)
		        .findFirst()
		        .orElse(null);
	}
	
	public void adicionaCliente (Cliente novoCliente) {
		clientesDoBanco.add(novoCliente);
	}

	public boolean removeCliente (int idCliente) {
		return clientesDoBanco.removeIf(cliente -> cliente.getId() == idCliente);
	}

	public boolean clienteAtivo (int idCliente) {
		return clientesDoBanco.stream()
		          .filter(c -> c.getId() == idCliente)
		          .anyMatch(Cliente::isAtivo);
	}

	public void limpa() {
		this.clientesDoBanco.clear();
	}
	
	public boolean validaIdade(int idade) throws IdadeNaoPermitidaException {
	
		if(idade < 18 || idade > 65)
			throw new IdadeNaoPermitidaException(IdadeNaoPermitidaException.MSG_IDADE_INVALIDA);
		
		return true;
	}
}