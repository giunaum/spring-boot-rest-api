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
@RequestMapping(value = "/pessoas")
public class PessoaController implements Serializable {

	@Autowired
	private PessoaBusiness pessoaBusiness;

	/**
	 * Recupera as Pessoas.
	 *
	 * @return
	 * @throws PessoaBusinessException
	 */
	@GetMapping
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
	@GetMapping(value = "/{id}")
	@ResponseBody
	public Pessoa getPessoaById(@PathVariable Integer id) throws PessoaBusinessException {
		return pessoaBusiness.getPessoaById(id);
	}

	/**
	 * Salva a entidade {@link Pessoa} conforme os parâmetros fornecidos.
	 *
	 * @param pessoa
	 * @return
	 * @throws PessoaBusinessException
	 */
	@PostMapping
	@ResponseBody
	public Pessoa salvarPessoa(@RequestBody Pessoa pessoa) throws PessoaBusinessException {
		return pessoaBusiness.salvarPessoa(pessoa);
	}

	/**
	 * Salva a entidade {@link Pessoa}.
	 *
	 * @param id
	 * @return
	 * @throws PessoaBusinessException
	 */
	@DeleteMapping(value = "/{id}")
	@ResponseBody
	public Integer excluirPessoa(@PathVariable Integer id) throws PessoaBusinessException {
		return pessoaBusiness.excluirPessoa(id);
	}
}
