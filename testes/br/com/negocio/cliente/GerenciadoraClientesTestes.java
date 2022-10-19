package br.com.negocio.cliente;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.negocio.IdadeNaoPermitidaException;

public class GerenciadoraClientesTestes {

	private static final String FABRICIOMENEZES_GMAIL_COM = "fabriciomenezes@gmail.com";
	private static final String FABRICIO_MENEZES = "Fabrício Menezes";
	private GerenciadoraClientes gerClientes;
	private int idCLiente01 = 1;
	private int idCLiente02 = 2;

	@Before
	public void setUp() {

		/* ========== Montagem do cenário ========== */

		// criando alguns clientes
		Cliente cliente01 = new Cliente(idCLiente01, FABRICIO_MENEZES, 31, FABRICIOMENEZES_GMAIL_COM, 1, true);
		Cliente cliente02 = new Cliente(idCLiente02, "Felipe Augusto", 34, "felipeaugusto@gmail.com", 1, true);

		// inserindo os clientes criados na lista de clientes do banco
		List<Cliente> clientesDoBanco = new ArrayList<>();
		clientesDoBanco.add(cliente01);
		clientesDoBanco.add(cliente02);

		gerClientes = new GerenciadoraClientes(clientesDoBanco);
	}

	@After
	public void tearDown() {
		gerClientes.limpa();
	}

	@Test
	public void testPesquisaCliente() {

		/* ========== Execução ========== */
		Cliente cliente = gerClientes.pesquisaCliente(idCLiente01);

		/* ========== Verificações ========== */
		assertThat(cliente.getId(), is(idCLiente01));
		assertThat(cliente.getEmail(), is(FABRICIOMENEZES_GMAIL_COM));

	}

	@Test
	public void testPesquisaClienteInexistente() {

		/* ========== Execução ========== */
		Cliente cliente = gerClientes.pesquisaCliente(1001);

		/* ========== Verificações ========== */
		assertNull(cliente);

	}

	@Test
	public void testRemoveCliente() {

		/* ========== Execução ========== */
		boolean clienteRemovido = gerClientes.removeCliente(idCLiente02);

		/* ========== Verificações ========== */
		assertThat(clienteRemovido, is(true));
		assertThat(gerClientes.getClientesDoBanco().size(), is(1));
		assertNull(gerClientes.pesquisaCliente(idCLiente02));

	}
	
	@Test
	public void testRemoveClienteInexistente() {

		/* ========== Execução ========== */
		boolean clienteRemovido = gerClientes.removeCliente(1001);

		/* ========== Verificações ========== */
		assertThat(clienteRemovido, is(false));
		assertThat(gerClientes.getClientesDoBanco().size(), is(2));

	}
	
	@Test
	public void testClienteIdadeAceitavel() throws IdadeNaoPermitidaException {

		/* ========== Montagem do Cenário ========== */
		Cliente cliente = new Cliente(1, FABRICIO_MENEZES, 25, FABRICIOMENEZES_GMAIL_COM, 1, true);

		/* ========== Execução ========== */
		boolean idadeValida = gerClientes.validaIdade(cliente.getIdade());

		/* ========== Verificações ========== */
		assertTrue(idadeValida);
	}

	@Test
	public void testClienteIdadeAceitavel18() throws IdadeNaoPermitidaException {

		/* ========== Montagem do Cenário ========== */
		Cliente cliente = new Cliente(1, FABRICIO_MENEZES, 18, FABRICIOMENEZES_GMAIL_COM, 1, true);

		/* ========== Execução ========== */
		boolean idadeValida = gerClientes.validaIdade(cliente.getIdade());

		/* ========== Verificações ========== */
		assertTrue(idadeValida);
	}

	@Test
	public void testClienteIdadeAceitavel65() throws IdadeNaoPermitidaException {

		/* ========== Montagem do Cenário ========== */
		Cliente cliente = new Cliente(1, FABRICIO_MENEZES, 65, FABRICIOMENEZES_GMAIL_COM, 1, true);

		/* ========== Execução ========== */
		boolean idadeValida = gerClientes.validaIdade(cliente.getIdade());

		/* ========== Verificações ========== */
		assertTrue(idadeValida);
	}

	@Test
	public void testClienteIdadeAceitavel17() throws IdadeNaoPermitidaException {

		/* ========== Montagem do Cenário ========== */
		Cliente cliente = new Cliente(1, FABRICIO_MENEZES, 17, FABRICIOMENEZES_GMAIL_COM, 1, true);

		/* ========== Execução ========== */
		try {
			gerClientes.validaIdade(cliente.getIdade());
			fail();
		} catch (Exception e) {
			/* ========== Verificações ========== */
			assertThat(e.getMessage(), is(IdadeNaoPermitidaException.MSG_IDADE_INVALIDA));
		}
	}

	@Test
	public void testClienteIdadeAceitavel66() throws IdadeNaoPermitidaException {

		/* ========== Montagem do Cenário ========== */
		Cliente cliente = new Cliente(1, FABRICIO_MENEZES, 66, FABRICIOMENEZES_GMAIL_COM, 1, true);
		/* ========== Execução ========== */
		try {
			gerClientes.validaIdade(cliente.getIdade());
			fail();
		} catch (Exception e) {
			/* ========== Verificações ========== */
			assertThat(e.getMessage(), is(IdadeNaoPermitidaException.MSG_IDADE_INVALIDA));
		}
	}

}