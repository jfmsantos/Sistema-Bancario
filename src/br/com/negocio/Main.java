package br.com.negocio;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import br.com.negocio.cliente.Cliente;
import br.com.negocio.cliente.GerenciadoraClientes;
import br.com.negocio.conta.ContaCorrente;
import br.com.negocio.conta.GerenciadoraContas;

public class Main {
	
	static Logger logger = Logger.getLogger(Main.class.getName());	

	private static final String DIGITE_O_ID_DA_CONTA = "Digite o ID da conta: ";
	private static final String CONTA_NAO_ENCONTRADA = "\n>>>>Conta não encontrada!";
	private static final String CLIENTE_NAO_ENCONTRADO = "\n>>>>Cliente não encontrado!";
	private static final String DIGITE_O_ID_DO_CLIENTE = "Digite o ID do cliente: ";
	static GerenciadoraClientes gerClientes;
	static GerenciadoraContas gerContas;

	private static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {

		inicializaSistemaBancario();
		opcaoMenuPrincipal();
	}

	private static void printMenuPrincipal() {

		System.out.println("Selecione uma Opção: \n");
		System.out.println("1) Cliente");
		System.out.println("2) Conta corrente");
		System.out.println("3) Sair");
		System.out.println();

	}

	private static void opcaoMenuPrincipal() {

		boolean continua = true;

		while (continua) {

			printMenuPrincipal();
			int opcao = scan.nextInt();
			switch (opcao) {
			//Cliente
			case 1:
				opcaoMenuCliente();
				pulalinha();
				break;
			//Conta corrente
			case 2:
				opcaoMenuContaCorrente();
				pulalinha();
				break;

			// Sair
			case 3:
				continua = false;
				System.out.println("################# Sistema encerrado #################");
				break;

			default:
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println();
				break;

			}
		}
		scan.close();
	}

	private static void pulalinha() {
		System.out.println("\n");
	}

	private static void printMenuCliente() {

		System.out.println("O que você deseja fazer? \n");
		System.out.println("1) Consultar por um cliente");
		System.out.println("2) Ativar um cliente");
		System.out.println("3) Desativar um cliente");
		System.out.println("4) Remover um cliente");
		System.out.println("5) Listar clientes");
		System.out.println("6) Transferência");
		System.out.println("7) Sair");
		System.out.println();

	}

	private static void opcaoMenuCliente() {

		boolean continua = true;
		while (continua) {

			printMenuCliente();
			int opcao = scan.nextInt();
			switch (opcao) {
			// Consultar por um cliente
			case 1:
				consultarCliente();
				pulalinha();
				break;
			// Ativar um cliente
			case 2:
				ativarCliente();
				pulalinha();
				break;
			// Desativar um cliente
			case 3:
				desativarCliente();
				pulalinha();
				break;
			// Remover um cliente
			case 4:
				removerCliente();
				pulalinha();
				break;
			// Listar um cliente
			case 5:
				gerClientes.getClientesDoBanco().forEach(System.out::print);
				pulalinha();
				break;
			// Fazer Tranferência
			case 6:
				fazerTranferencia();
				pulalinha();
				break;
			// Sair
			case 7:
				continua = false;
				break;
			default:
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println();
				break;
			}

		}
	}

	private static void fazerTranferencia() {
		ContaCorrente conta;
		int idContaOrigem;
		double valor;
		int idContaDestino;
		do {
			System.out.println("Digite o ID da Conta Origem: ");
			idContaOrigem = scan.nextInt();
			conta = gerContas.pesquisaConta(idContaOrigem);

			if (conta == null)
				System.out.println(CONTA_NAO_ENCONTRADA);
			pulalinha();
		} while (conta == null);

		System.out.println("\n>>valor:");
		valor = scan.nextInt();
		
		do {
			System.out.println("Digite o ID da Conta Destino: ");
			idContaDestino = scan.nextInt();
			conta = gerContas.pesquisaConta(idContaDestino);

			if (conta == null)
				System.out.println(CONTA_NAO_ENCONTRADA);
			pulalinha();
		} while (conta == null);

		if (gerContas.transfereValor(idContaOrigem, valor, idContaDestino)) {
			System.out.println("\n>>>>Transferência realizada com sucesso!");
		} else {
			System.out.println("\n>>>>Não foi possível realizar a Transferência");
		}
	}

	private static void removerCliente() {
		int idCliente;
		System.out.print(DIGITE_O_ID_DO_CLIENTE);
		idCliente = scan.nextInt();
		if (gerClientes.removeCliente(idCliente)) {
			System.out.println("\n>>>>Cliente Removido com sucesso!");
		} else {
			System.out.println(CLIENTE_NAO_ENCONTRADO);
		}
	}

	private static void desativarCliente() {
		Cliente cliente;
		int idCliente;
		System.out.print(DIGITE_O_ID_DO_CLIENTE);
		idCliente = scan.nextInt();
		cliente = gerClientes.pesquisaCliente(idCliente);

		if (cliente != null) {
			cliente.setAtivo(false);
			System.out.println("\n>>>>Cliente desativado com sucesso!");
		} else
			System.out.println(CLIENTE_NAO_ENCONTRADO);
	}

	private static void ativarCliente() {
		Cliente cliente;
		int idCliente;
		System.out.print(DIGITE_O_ID_DO_CLIENTE);
		idCliente = scan.nextInt();
		cliente = gerClientes.pesquisaCliente(idCliente);

		if (cliente != null) {
			cliente.setAtivo(true);
			System.out.println("\n>>>>Cliente ativado com sucesso!");
		} else
			System.out.println(CLIENTE_NAO_ENCONTRADO);
	}

	private static void consultarCliente() {
		Cliente cliente;
		int idCliente;
		System.out.print(DIGITE_O_ID_DO_CLIENTE);
		idCliente = scan.nextInt();
		cliente = gerClientes.pesquisaCliente(idCliente);

		if (cliente != null)
			System.out.println(cliente.toString());
		else
			System.out.println(CLIENTE_NAO_ENCONTRADO);
	}

	private static void printMenuContaCorrente() {

		System.out.println("O que você deseja fazer? \n");
		System.out.println("1) Consultar uma conta corrente");
		System.out.println("2) Ativar conta corrente");
		System.out.println("3) Desativar conta corrente");
		System.out.println("4) Remover conta corrente");
		System.out.println("5) Listar conta corrente");
		System.out.println("6) Sair");
		System.out.println();

	}

	private static void opcaoMenuContaCorrente() {
		
		boolean continua = true;
		while (continua) {

			printMenuContaCorrente();
			int opcao = scan.nextInt();
			switch (opcao) {
			// Consultar Conta
			case 1:
				consultarConta();
				pulalinha();
				break;
			// Ativar uma conta
			case 2:
				ativarConta();
				pulalinha();
				break;
			// Desativar um conta
			case 3:
				desativarConta();
				pulalinha();
				break;
			// Remover uma conta
			case 4:
				removerConta();
				pulalinha();
				break;
			// Listar Conta
			case 5:
				gerContas.getContasDoBanco().forEach(System.out::print);
				pulalinha();
				break;
			// Sair
			case 6:
				continua = false;
				break;

			default:
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println();
				break;
			}
		}
	}

	private static void removerConta() {
		System.out.print(DIGITE_O_ID_DA_CONTA);
		int idConta4 = scan.nextInt();
		if (gerContas.removeConta(idConta4)) {
			System.out.println("\n>>>>Conta Removida com sucesso!");
		} else {
			System.out.println(CONTA_NAO_ENCONTRADA);
		}
	}

	private static void desativarConta() {
		System.out.print(DIGITE_O_ID_DA_CONTA);
		int idConta3 = scan.nextInt();
		ContaCorrente conta3 = gerContas.pesquisaConta(idConta3);

		if (conta3 != null) {
			conta3.setAtiva(false);
			System.out.println("\n>>>>Conta desativado com sucesso!");
		} else
			System.out.println(CONTA_NAO_ENCONTRADA);
	}

	private static void ativarConta() {
		System.out.print(DIGITE_O_ID_DA_CONTA);
		int idConta2 = scan.nextInt();
		ContaCorrente conta2 = gerContas.pesquisaConta(idConta2);

		if (conta2 != null) {
			conta2.setAtiva(true);
			System.out.println("\n>>>>Conta ativada com sucesso!");
		} else
			System.out.println(CONTA_NAO_ENCONTRADA);
	}

	private static void consultarConta() {
		System.out.print("Digite o ID da Conta: ");
		int idConta = scan.nextInt();
		ContaCorrente conta = gerContas.pesquisaConta(idConta);

		if (conta != null)
			System.out.println(conta.toString());
		else
			System.out.println(CONTA_NAO_ENCONTRADA);
	}

	private static void inicializaSistemaBancario() {
		// criando lista vazia de contas e clientes
		List<ContaCorrente> contasDoBanco = new ArrayList<>();
		List<Cliente> clientesDoBanco = new ArrayList<>();

		// criando e inserindo duas contas na lista de contas correntes do banco
		ContaCorrente conta01 = new ContaCorrente(1, 100, true);
		ContaCorrente conta02 = new ContaCorrente(2, 100, true);
		ContaCorrente conta03 = new ContaCorrente(3, 100, true);
		contasDoBanco.add(conta01);
		contasDoBanco.add(conta02);
		contasDoBanco.add(conta03);

		// criando dois clientes e associando as contas criadas acima a eles
		Cliente cliente01 = new Cliente(1, "Fabrício Menezes", 31, "fabriciomenezes@gmail.com", conta01.getId(), true);
		Cliente cliente02 = new Cliente(2, "Felipe Augusto", 34, "felipeaugusto@gmail.com", conta02.getId(), true);
		Cliente cliente03 = new Cliente(3, "Alvaro Oliveira", 34, "alvaro.oliveira@gmail.com", conta03.getId(), true);
		// inserindo os clientes criados na lista de clientes do banco
		clientesDoBanco.add(cliente01);
		clientesDoBanco.add(cliente02);
		clientesDoBanco.add(cliente03);

		gerClientes = new GerenciadoraClientes(clientesDoBanco);
		gerContas = new GerenciadoraContas(contasDoBanco);

	}
}
