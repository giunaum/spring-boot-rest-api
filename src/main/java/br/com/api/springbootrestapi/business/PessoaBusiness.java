package br.com.api.springbootrestapi.business;

import br.com.api.springbootrestapi.config.MessageCode;
import br.com.api.springbootrestapi.config.MessageConfig;
import br.com.api.springbootrestapi.dto.PessoaDTO;
import br.com.api.springbootrestapi.dto.PessoaMapper;
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

	@Autowired
	private PessoaMapper pessoaMapper;

	/**
	 * Recupera os pessoas.
	 *
	 * @return
	 * @throws PessoaBusinessException
	 */
	public List<PessoaDTO> getPessoas() throws PessoaBusinessException {
		List<PessoaDTO> pessoaDTOS = new ArrayList<>();
		try {
			List<Pessoa> pessoas = pessoaDAO.getAll();
			pessoaDTOS.addAll(pessoaMapper.toDtos(pessoas));
		} catch (DAOException e) {
			String msg = MessageConfig.getMensagem(MessageCode.FALHA_RECUPERAR_PESSOAS);
			logger.error(msg, e);
			throw new PessoaBusinessException(msg, e);
		}

		if (Util.isEmpty(pessoaDTOS)) {
			throw new PessoaBusinessException(MessageCode.PESSOAS_NAO_ENCONTRADOS);
		}

		return pessoaDTOS;
	}

	/**
	 * Recupera o {@link Pessoa} conforme o ID fornecido.
	 *
	 * @param id
	 * @return
	 * @throws PessoaBusinessException
	 */
	public PessoaDTO getPessoaById(final Integer id) throws PessoaBusinessException {
		if (Util.isEmpty(id)) {
			throw new PessoaBusinessException("O ID não foi informado ou é igual a zero. Impossível prosseguir.");
		}

		PessoaDTO pessoaDTO;

		try {
			Pessoa pessoa = pessoaDAO.getById(id);
			pessoaDTO = pessoaMapper.toDto(pessoa);
		} catch (DAOException e) {
			String msg = MessageConfig.getMensagem(MessageCode.FALHA_RECUPERAR_PESSOA);
			logger.error(msg, e);
			throw new PessoaBusinessException(msg, e);
		}

		if (pessoaDTO == null) {
			throw new PessoaBusinessException(MessageCode.PESSOA_NAO_ENCONTRADO);
		}

		return pessoaDTO;
	}

	/**
	 * Salva ou atualiza a entidade {@link Pessoa} conforme os parâmetros fornecidos.
	 *
	 * @param pessoaDTO
	 * @return
	 * @throws PessoaBusinessException
	 */
	public PessoaDTO salvarPessoa(final PessoaDTO pessoaDTO) throws PessoaBusinessException {
		if (pessoaDTO == null) {
			throw new PessoaBusinessException("Pessoa não fornecida. Impossível prosseguir.");
		}

		List<String> parametros = new ArrayList<>();

		if (Util.isBlank(pessoaDTO.getNome())) {
			parametros.add("Nome");
		}

		if (pessoaDTO.getIdade() == null) {
			parametros.add("Idade");
		}

		if (!Util.isEmpty(parametros)) {
			throw new PessoaBusinessException(MessageCode.PARAMETROS_OBRIGATORIOS, parametros);
		}

		boolean isPersistido = !Util.isEmpty((Integer) pessoaDTO.getId());

		try {
			Pessoa pessoa = pessoaMapper.toEntity(pessoaDTO);
			Pessoa pessoaPersistida = pessoaDAO.persistir(pessoa);
			return pessoaMapper.toDto(pessoaPersistida);
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
	 * @return
	 * @throws PessoaBusinessException
	 */
	public Integer excluirPessoa(final Integer id) throws PessoaBusinessException {
		if (id == null) {
			throw new PessoaBusinessException(MessageCode.PESSOA_NAO_FORNECIDO);
		}

		try {
			Pessoa pessoa = pessoaDAO.getById(id);
			pessoaDAO.excluir(pessoa);
			return id;
		} catch (DAOException e) {
			String msg = MessageConfig.getMensagem(MessageCode.FALHA_EXCLUIR_PESSOA);
			logger.error(msg, e);
			throw new PessoaBusinessException(msg, e);
		}
	}
}
