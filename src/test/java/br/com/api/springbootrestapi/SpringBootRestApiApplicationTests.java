package br.com.api.springbootrestapi;

import br.com.api.springbootrestapi.business.PessoaBusiness;
import br.com.api.springbootrestapi.config.MessageCode;
import br.com.api.springbootrestapi.config.MessageConfig;
import br.com.api.springbootrestapi.entities.Pessoa;
import br.com.api.springbootrestapi.exceptions.PessoaBusinessException;
import br.com.api.springbootrestapi.util.Util;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(value = MethodSorters.JVM)
public class SpringBootRestApiApplicationTests {

	private static final String REGEX_NUMBER = "[^0-9]";

	@Autowired
	private PessoaBusiness pessoaBusiness;

	@Test
	public void salvarPessoa() throws PessoaBusinessException {
		String msg = pessoaBusiness.salvarOuAtualizarPessoa("TesteSalvar", 25);
		assertTrue(msg.contains(MessageConfig.getMensagem(MessageCode.SUCESSO_SALVAR_PESSOA)));
	}

	@Test
	public void alterarPessoa() throws PessoaBusinessException {
		String msgSalvar = pessoaBusiness.salvarOuAtualizarPessoa("TesteAlterar", 27);
		assertTrue(msgSalvar.contains(MessageConfig.getMensagem(MessageCode.SUCESSO_SALVAR_PESSOA)));

		Integer id = Integer.parseInt(msgSalvar.replaceAll(REGEX_NUMBER, ""));

		String msgAlterar = pessoaBusiness.salvarOuAtualizarPessoa("TesteAlterado", 26, id);
		assertTrue(msgAlterar.contains(MessageConfig.getMensagem(MessageCode.SUCESSO_ALTERAR_PESSOA)));
	}

	@Test
	public void getPessoas() throws PessoaBusinessException {
		String msgPessoas = pessoaBusiness.salvarOuAtualizarPessoa("TestePessoas", 30);
		assertTrue(msgPessoas.contains(MessageConfig.getMensagem(MessageCode.SUCESSO_SALVAR_PESSOA)));

		List<Pessoa> clientesTO = pessoaBusiness.getPessoas();
		assertTrue(!Util.isEmpty(clientesTO));
	}

	@Test
	public void getPessoaById() throws PessoaBusinessException {
		String msgSalvar = pessoaBusiness.salvarOuAtualizarPessoa("TesteConsultar", 28);
		assertTrue(msgSalvar.contains(MessageConfig.getMensagem(MessageCode.SUCESSO_SALVAR_PESSOA)));

		Integer id = Integer.parseInt(msgSalvar.replaceAll(REGEX_NUMBER, ""));

		Pessoa pessoa = pessoaBusiness.getPessoaById(id);
		assertNotNull(pessoa);
	}

	@Test
	public void excluirPessoa() throws PessoaBusinessException {
		String msgSalvar = pessoaBusiness.salvarOuAtualizarPessoa("TesteExcluir", 29);
		assertTrue(msgSalvar.contains(MessageConfig.getMensagem(MessageCode.SUCESSO_SALVAR_PESSOA)));

		Integer id = Integer.parseInt(msgSalvar.replaceAll(REGEX_NUMBER, ""));

		String msgExcluir = pessoaBusiness.excluirPessoa(id);
		assertTrue(msgExcluir.contains(MessageConfig.getMensagem(MessageCode.SUCESSO_EXCLUIR_PESSOA)));
	}
}
