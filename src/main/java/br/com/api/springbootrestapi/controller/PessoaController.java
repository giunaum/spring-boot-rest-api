package br.com.api.springbootrestapi.controller;

import br.com.api.springbootrestapi.business.PessoaBusiness;
import br.com.api.springbootrestapi.entities.Pessoa;
import br.com.api.springbootrestapi.exceptions.PessoaBusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

/**
 * Classe de controle responsável pela manipulação das informações da entidade {@link Pessoa}.
 */
@Controller
@RestController
@RequestMapping(value = "/pessoa")
public class PessoaController implements Serializable {

	@Autowired
	private PessoaBusiness pessoaBusiness;

	/**
	 * Recupera os clientes.
	 *
	 * @return
	 * @throws PessoaBusinessException
	 */
	@GetMapping(value = "/listar")
	@ResponseBody
	public List<Pessoa> getPessoas() throws PessoaBusinessException {
		return pessoaBusiness.getPessoas();
	}

	/**
	 * Recupera o {@link Pessoa} conforme o ID fornecido.
	 *
	 * @param id
	 * @return
	 * @throws PessoaBusinessException
	 */
	@GetMapping(value = "/obter/{id}")
	@ResponseBody
	public Pessoa getPessoaById(@PathVariable Integer id) throws PessoaBusinessException {
		return pessoaBusiness.getPessoaById(id);
	}

	/**
	 * Salva a entidade {@link Pessoa} conforme os parâmetros fornecidos.
	 *
	 * @param nome
	 * @param idade
	 * @throws PessoaBusinessException
	 * @return
	 */
	@GetMapping(value = "/salvar/{nome}/{idade}")
	@ResponseBody
	public String salvarPessoa(@PathVariable String nome, @PathVariable Integer idade) throws PessoaBusinessException {
		return pessoaBusiness.salvarOuAtualizarPessoa(nome, idade);
	}

	/**
	 * Salva a entidade {@link Pessoa} conforme os parâmetros fornecidos.
	 *
	 * @param nome
	 * @param idade
	 * @param id
	 * @throws PessoaBusinessException
	 * @return
	 */
	@GetMapping(value = "/atualizar/{nome}/{idade}/{id}")
	@ResponseBody
	public String alterarliente(@PathVariable String nome, @PathVariable Integer idade, @PathVariable Integer id) throws PessoaBusinessException {
		return pessoaBusiness.salvarOuAtualizarPessoa(nome, idade, id);
	}

	/**
	 * Salva a entidade {@link Pessoa}.
	 *
	 * @param id
	 * @throws PessoaBusinessException
	 */
	@GetMapping(value = "/excluir/{id}")
	@ResponseBody
	public String excluirPessoa(@PathVariable Integer id) throws PessoaBusinessException {
		return pessoaBusiness.excluirPessoa(id);
	}
}
