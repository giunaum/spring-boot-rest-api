package br.com.api.springbootrestapi.persistence;

import br.com.api.springbootrestapi.entities.Entidade;
import br.com.api.springbootrestapi.exceptions.DAOException;

import java.util.List;

/**
 * Define o que é um DAO (Data Acess Object).
 *
 * @param <T>
 */
public interface DAO<T extends Entidade> {

	/**
	 * Persiste a entidade passada como parâmetro. 'Persistir' pode atualizar ou inserir.
	 *
	 * @param entidade
	 * @return
	 * @throws DAOException
	 */
	public T persistir(T entidade) throws DAOException;

	/**
	 * Busca o objeto com o ID especificado.
	 *
	 * @param id
	 * @return
	 * @throws DAOException
	 */
	public T getById(Object id) throws DAOException;

	/**
	 * Retorna todas as entidades da classe especificada.
	 *
	 * @return
	 * @throws DAOException
	 */
	public List<T> getAll() throws DAOException;

	/**
	 * Exclui a entidade especificada.
	 *
	 * @param entidade
	 * @throws DAOException
	 */
	public void excluir(T entidade) throws DAOException;
}
