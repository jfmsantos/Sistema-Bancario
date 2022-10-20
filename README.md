<h1 align="center">
  Sistema Bancario - Java 8
</h1>

O projeto teve por finalidade aplicar a Utilização de testes unitário juntamente com lambdas expressions em Java 8.

# Exemplos usados
Abaixo alguns exemplos práticos de utilização de lambdas expressions do Java 8.

<details><summary>pesquisaCliente</summary>
<p>

#### Retona o Cliente ou nulo caso o Id informado não seja localizado.

# Antes
```JAVA
public Cliente pesquisaCliente (int idCliente) {
		for (Cliente cliente : clientesDoBanco) {
			if(cliente.getId() == idCliente)
				return cliente;
		}
		return null;
}
```
# Depois
```JAVA
public Cliente pesquisaCliente (int idCliente) {
		return clientesDoBanco.stream()
		        .filter(cliente -> cliente.getId() == idCliente)
		        .findFirst()
		        .orElse(null);
}
```

</p>
</details>

<details><summary>removeCliente</summary>
<p>

#### Busca Id informado retornando verdadeiro caso sucesso na remoção do usuário.

# Antes
```JAVA
public boolean removeCliente (int idCliente) {
		boolean clienteRemovido = false;
		for (int i = 0; i < clientesDoBanco.size(); i++) {
			Cliente cliente = clientesDoBanco.get(i);
			if(cliente.getId() == idCliente){
				clientesDoBanco.remove(i);
				clienteRemovido = true;
				break;
			}
		}
		return clienteRemovido;
}
```
# Depois
```JAVA
public boolean removeCliente (int idCliente) {
		return clientesDoBanco.removeIf(cliente -> cliente.getId() == idCliente);
}
```

</p>
</details>

<details><summary>clienteAtivo</summary>
<p>

#### Retorna verdadeiro ou falso de acordo com o Id informado.

# Antes
```JAVA
public boolean clienteAtivo (int idCliente) {
		boolean clienteAtivo = false;
		for (int i = 0; i < clientesDoBanco.size(); i++) {
			Cliente cliente = clientesDoBanco.get(i);
			if(cliente.getId() == idCliente)
				if(cliente.isAtivo()){
					clienteAtivo = true;
					break;
				}
		}
		return clienteAtivo;
}
```
# Depois
```JAVA
public boolean clienteAtivo (int idCliente) {
		return clientesDoBanco.stream()
		          .filter(c -> c.getId() == idCliente)
		          .anyMatch(Cliente::isAtivo);
}
```

</p>
</details>