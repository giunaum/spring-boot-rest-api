package br.com.api.springbootrestapi.persistence;

import br.com.api.springbootrestapi.entities.Pessoa;
import org.springframework.stereotype.Repository;

/**
 * Classe responsável por realizar a persistência de objetos da classe {@link Pessoa}.
 */
@Repository
public class PessoaDAO extends GenericDAO<Pessoa> {

	@Override
	protected Class<Pessoa> getClasseEntidade() {
		return Pessoa.class;
	}
}
