package br.com.negocio.conta;

import java.util.List;

public class GerenciadoraContas {

	private List<ContaCorrente> contasDoBanco;

	public GerenciadoraContas(List<ContaCorrente> contasDoBanco) {
		this.contasDoBanco = contasDoBanco;
	}

	public List<ContaCorrente> getContasDoBanco() {
		return contasDoBanco;
	}

	public ContaCorrente pesquisaConta (int idConta) {

		return contasDoBanco.stream()
				.filter(conta-> conta.getId() == idConta)
				.findFirst()
				.orElse(null);
	}
	
	public void adicionaConta (ContaCorrente novaConta) {
		this.contasDoBanco.add(novaConta);
	}

	public boolean removeConta (int idConta) {
		
		return contasDoBanco.removeIf(conta -> conta.getId() == idConta);
	}

	public boolean contaAtiva (int idConta) {
		
		return contasDoBanco.stream()
				.filter(conta -> conta.getId()==idConta)
				.anyMatch(ContaCorrente::isAtiva);
	}
	
	public boolean transfereValor (int idContaOrigem, double valor, int idContaDestino) {
		
		boolean sucesso = false;
		
		ContaCorrente contaOrigem = pesquisaConta(idContaOrigem);
		ContaCorrente contaDestino = pesquisaConta(idContaDestino);
		
//		if(contaOrigem.getSaldo() >= valor){
			contaDestino.setSaldo(contaDestino.getSaldo() + valor);
			contaOrigem.setSaldo(contaOrigem.getSaldo() - valor);
			sucesso = true;
//		}
	
		return sucesso;
	}
}