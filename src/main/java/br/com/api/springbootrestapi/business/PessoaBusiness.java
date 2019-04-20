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
	 * @param nome
	 * @param idade
	 * @return
	 * @throws PessoaBusinessException
	 */
	public String salvarOuAtualizarPessoa(final String nome, final Integer idade) throws PessoaBusinessException {
		return salvarOuAtualizarPessoa(nome, idade, null);
	}

	/**
	 * Salva ou atualiza a entidade {@link Pessoa} conforme os parâmetros fornecidos.
	 *
	 * @param nome
	 * @param idade
	 * @param id
	 * @return
	 * @throws PessoaBusinessException
	 */
	public String salvarOuAtualizarPessoa(final String nome, final Integer idade, final Integer id) throws PessoaBusinessException {
		List<String> parametros = new ArrayList<>();

		if (Util.isBlank(nome)) {
			parametros.add("Nome");
		}

		if (idade == null) {
			parametros.add("Idade");
		}

		if (!Util.isEmpty(parametros)) {
			throw new PessoaBusinessException(MessageCode.PARAMETROS_OBRIGATORIOS, parametros);
		}

		boolean isPersistido = !Util.isEmpty(id) && id > BigInteger.ZERO.intValue();
		Pessoa pessoa = getPessoaPreenchido(id, nome, idade, isPersistido);
		Pessoa pessoaPersistido;

		try {
			pessoaPersistido = pessoaDAO.persistir(pessoa);
		} catch (DAOException e) {
			String msg = MessageConfig.getMensagem(isPersistido ? MessageCode.FALHA_ALTERAR_PESSOA : MessageCode.FALHA_SALVAR_PESSOA);
			logger.error(msg, e);
			throw new PessoaBusinessException(msg, e);
		}

		StringBuilder mensagem = new StringBuilder();
		if (pessoaPersistido == null) {
			throw new PessoaBusinessException(isPersistido ? MessageCode.PESSOA_NAO_ATUALIZADO : MessageCode.PESSOA_NAO_SALVO);
		} else {
			mensagem.append(MessageConfig.getMensagem(isPersistido ? MessageCode.SUCESSO_ALTERAR_PESSOA : MessageCode.SUCESSO_SALVAR_PESSOA));
			mensagem.append(" [ID: ").append(pessoa.getId().toString()).append("]");
		}

		return mensagem.toString();
	}

	/**
	 * Exclui a entidade {@link Pessoa}.
	 *
	 * @param id
	 * @throws PessoaBusinessException
	 * @return
	 */
	public String excluirPessoa(final Integer id) throws PessoaBusinessException {
		if (id == null) {
			throw new PessoaBusinessException(MessageCode.PESSOA_NAO_FORNECIDO);
		}

		try {
			Pessoa pessoa = getPessoaById(id);
			pessoaDAO.excluir(pessoa);
		} catch (DAOException e) {
			String msg = MessageConfig.getMensagem(MessageCode.FALHA_EXCLUIR_PESSOA);
			logger.error(msg, e);
			throw new PessoaBusinessException(msg, e);
		}

		return MessageConfig.getMensagem(MessageCode.SUCESSO_EXCLUIR_PESSOA);
	}

	/**
	 * Retorna o {@link Pessoa} preenchido conforme os parâmetros informados.
	 *
	 * @param id
	 * @param nome
	 * @param idade
	 * @param isPersistido
	 * @return
	 * @throws PessoaBusinessException
	 */
	private Pessoa getPessoaPreenchido(final Integer id, final String nome, final Integer idade, final boolean isPersistido) throws PessoaBusinessException {
		Pessoa pessoa = isPersistido ? getPessoaById(id) : new Pessoa();

		if (isPersistido) {
			pessoa.setId(id);
		}

		pessoa.setNome(nome);
		pessoa.setIdade(idade);

		return pessoa;
	}
}
