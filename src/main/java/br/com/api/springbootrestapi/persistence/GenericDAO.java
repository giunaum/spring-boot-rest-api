package br.com.api.springbootrestapi.persistence;

import br.com.api.springbootrestapi.entities.Entidade;
import br.com.api.springbootrestapi.exceptions.DAOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;

/**
 * Implementação padrão de DAO vinculada ao Hibernate.
 *
 * @param <T>
 */
@Transactional(rollbackFor = DAOException.class)
public abstract class GenericDAO<T extends Entidade> implements DAO<T> {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@PersistenceContext
	private EntityManager entityManager;

	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;

	protected abstract Class<T> getClasseEntidade();

	/**
	 * Retorna o EntityManager injetado e associado a thread/transacao atual.
	 *
	 * @return
	 */
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * Retorna o EntityManagerFactory injetado e associado a thread/transacao atual.
	 *
	 * @return
	 */
	protected EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}

	/**
	 * Recupera uma Entidade dada seu ID (Primary Key).
	 *
	 * @param id
	 * @return
	 * @throws DAOException
	 */
	public T getById(Object id) throws DAOException {
		if (getClasseEntidade() == null || id == null) {
			throw new DAOException("Argumento nulo: Classe ou ID nulos (Classe: " + getClasseEntidade() + "/Id: " + id + ")");
		}

		try {
			return getEntityManager().find(getClasseEntidade(), id);
		} catch (PersistenceException e) {
			String msg = "Erro buscando entidade por ID [Entidade: " + getClasseEntidade().getSimpleName() + "/ID: " + id + "]";
			logger.error(msg, e);
			throw new DAOException(msg, e);
		}
	}

	/**
	 * Retorna todos os objetos da entidade especificada.
	 *
	 * @return
	 * @throws DAOException
	 */
	public List<T> getAll() throws DAOException {
		if (getClasseEntidade() == null) {
			throw new DAOException("Argumento nulo: Classe nula (Classe: " + getClasseEntidade() + ")");
		}

		try {
			StringBuilder hql = new StringBuilder("FROM ").append(getClasseEntidade().getName());
			return getEntityManager().createQuery(hql.toString()).getResultList();
		} catch (PersistenceException e) {
			String msg = "Erro recuperar todas as entidades [Entidade: " + getClasseEntidade().getSimpleName() + "]";
			logger.error(msg, e);
			throw new DAOException(msg, e);
		}
	}

	/**
	 * Metodo de persistencia exclusivo para entidades do Hibernate.
	 *
	 * @param entidade
	 * @return
	 * @throws DAOException
	 */
	public T persistir(T entidade) throws DAOException {
		if (entidade == null) {
			throw new DAOException("Entidade nula, impossivel persistir!");
		}

		try {
			entidade = salvarOuAtualizar(entidade);
			getEntityManager().flush();
			return entidade;
		} catch (PersistenceException e) {
			String msg = "Erro persistindo Entidade [" + entidade + "]";
			logger.error(msg, e);
			throw new DAOException(msg, e);
		}
	}

	/**
	 * Decide se a entidade sera inserida (persist) ou atualizada (merge) de acordo com os criterios do Hibernate.
	 *
	 * @param entidade
	 * @return
	 */
	private T salvarOuAtualizar(T entidade) {
		boolean isNovaEntidade = false;

		if (entidade.getId() == null) {
			isNovaEntidade = true;
		} else {
			boolean isGeneratedValue = true;

			try {
				isGeneratedValue = getClasseEntidade().getDeclaredMethod("getId").isAnnotationPresent(GeneratedValue.class);
			} catch (SecurityException | NoSuchMethodException e) {
				logger.error("Impossivel validar se @GeneratedValue não esta presente no getId() da entidade [" + entidade + "]", e);
			}

			if (!isGeneratedValue) {
				isNovaEntidade = (getEntityManager().find(getClasseEntidade(), entidade.getId()) == null);
			}
		}

		if (isNovaEntidade) {
			getEntityManager().persist(entidade);
		} else {
			entidade = getEntityManager().merge(entidade);
		}

		return entidade;
	}

	/**
	 * Metodo de exclusão exclusivo para entidades do Hibernate.
	 *
	 * @param entidade
	 * @throws DAOException
	 */
	public void excluir(T entidade) throws DAOException {
		if (entidade == null) {
			throw new DAOException("Entidade nula, impossivel exclui-la!");
		}

		try {
			EntityManager em = getEntityManager();

			if (!em.contains(entidade)) {
				entidade = em.merge(entidade);
			}

			em.remove(entidade);
		} catch (PersistenceException e) {
			String msg = "Erro removendo Entidade [" + entidade + "]";
			logger.error(msg, e);
			throw new DAOException(msg, e);
		}
	}
}
