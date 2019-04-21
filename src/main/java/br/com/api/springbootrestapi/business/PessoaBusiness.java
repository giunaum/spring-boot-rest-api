package br.com.api.springbootrestapi.business;

import br.com.api.springbootrestapi.config.MessageCode;
import br.com.api.springbootrestapi.config.MessageConfig;
import br.com.api.springbootrestapi.entities.Pessoa;
import br.com.api.springbootrestapi.exceptions.DAOException;
import br.com.api.springbootrestapi.exceptions.PessoaBusinessException;
import br.com.api.springbootrestapi.persistence.PessoaDAO;
import br.com.api.springbootrestapi.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe de negócio responsável pelo cadastro da entidade {@link Pessoa}.
 */
@Service
@Transactional(rollbackFor = PessoaBusinessException.class)
public class PessoaBusiness {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PessoaDAO pessoaDAO;

	/**
	 * Recupera os pessoas.
	 *
	 * @return
	 * @throws PessoaBusinessException
	 */
	public List<Pessoa> getPessoas() throws PessoaBusinessException {
		List<Pessoa> pessoas = new ArrayList<>();
		try {
			pessoas.addAll(pessoaDAO.getAll());
		} catch (DAOException e) {
			String msg = MessageConfig.getMensagem(MessageCode.FALHA_RECUPERAR_PESSOAS);
			logger.error(msg, e);
			throw new PessoaBusinessException(msg, e);
		}

		if (Util.isEmpty(pessoas)) {
			throw new PessoaBusinessException(MessageCode.PESSOAS_NAO_ENCONTRADOS);
		}

		return pessoas;
	}

	/**
	 * Recupera o {@link Pessoa} conforme o ID fornecido.
	 *
	 * @param id
	 * @return
	 * @throws PessoaBusinessException
	 */
	public Pessoa getPessoaById(final Integer id) throws PessoaBusinessException {
		if (Util.isEmpty(id)) {
			throw new PessoaBusinessException("O ID não foi informado ou é igual a zero. Impossível prosseguir.");
		}

		Pessoa pessoa;

		try {
			pessoa = pessoaDAO.getById(id);
		} catch (DAOException e) {
			String msg = MessageConfig.getMensagem(MessageCode.FALHA_RECUPERAR_PESSOA);
			logger.error(msg, e);
			throw new PessoaBusinessException(msg, e);
		}

		if (pessoa == null) {
			throw new PessoaBusinessException(MessageCode.PESSOA_NAO_ENCONTRADO);
		}

		return pessoa;
	}

	/**
	 * Salva ou atualiza a entidade {@link Pessoa} conforme os parâmetros fornecidos.
	 *
	 * @param pessoa
	 * @return
	 * @throws PessoaBusinessException
	 */
	public Pessoa salvarPessoa(final Pessoa pessoa) throws PessoaBusinessException {
		if (pessoa == null) {
			throw new PessoaBusinessException("Pessoa não fornecida. Impossível prosseguir.");
		}

		List<String> parametros = new ArrayList<>();

		if (Util.isBlank(pessoa.getNome())) {
			parametros.add("Nome");
		}

		if (pessoa.getIdade() == null) {
			parametros.add("Idade");
		}

		if (!Util.isEmpty(parametros)) {
			throw new PessoaBusinessException(MessageCode.PARAMETROS_OBRIGATORIOS, parametros);
		}

		boolean isPersistido = !Util.isEmpty((Integer) pessoa.getId());

		try {
			return pessoaDAO.persistir(pessoa);
		} catch (DAOException e) {
			String msg = MessageConfig.getMensagem(isPersistido ? MessageCode.FALHA_ALTERAR_PESSOA : MessageCode.FALHA_SALVAR_PESSOA);
			logger.error(msg, e);
			throw new PessoaBusinessException(msg, e);
		}
	}

	/**
	 * Exclui a entidade {@link Pessoa}.
	 *
	 * @param id
	 * @throws PessoaBusinessException
	 * @return
	 */
	public Integer excluirPessoa(final Integer id) throws PessoaBusinessException {
		if (id == null) {
			throw new PessoaBusinessException(MessageCode.PESSOA_NAO_FORNECIDO);
		}

		try {
			Pessoa pessoa = getPessoaById(id);
			pessoaDAO.excluir(pessoa);
			return id;
		} catch (DAOException e) {
			String msg = MessageConfig.getMensagem(MessageCode.FALHA_EXCLUIR_PESSOA);
			logger.error(msg, e);
			throw new PessoaBusinessException(msg, e);
		}
	}
}
