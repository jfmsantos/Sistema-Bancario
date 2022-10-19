package br.com.negocio;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.negocio.cliente.GerenciadoraClientesTestes;
import br.com.negocio.conta.GerenciadoraContasTestes;

@RunWith(Suite.class)
@SuiteClasses({ GerenciadoraClientesTestes.class,GerenciadoraContasTestes.class })
public class TodosOsTestes {

}

















/*
  @SuiteClasses({ GerenciadoraClientesTest_Ex1.class, GerenciadoraClientesTest_Ex2.class,
	GerenciadoraClientesTest_Ex3.class, GerenciadoraClientesTest_Ex4.class, GerenciadoraClientesTest_Ex5.class,
	GerenciadoraClientesTest_Ex7.class, GerenciadoraClientesTest_Ex8.class, GerenciadoraContasTest_Ex3.class,
	GerenciadoraContasTest_Ex4.class, GerenciadoraContasTest_Ex6.class})
 */




