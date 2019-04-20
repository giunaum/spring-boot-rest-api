package br.com.api.springbootrestapi.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Classe que representa a entidade Pessoa na tabela.
 */
@Entity
public class Pessoa implements Entidade {

	@Id
	@GeneratedValue
	private Integer id;

	@Column(length = 100, nullable = false)
	private String nome;

	@Column(nullable = false)
	private Integer idade;

	@Override
	public Object getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}
}