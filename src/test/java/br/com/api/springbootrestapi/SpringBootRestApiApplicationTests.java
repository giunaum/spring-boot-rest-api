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

	@Autowired
	private PessoaBusiness pessoaBusiness;

	@Test
	public void salvarPessoa() throws PessoaBusinessException {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("TesteSalvar");
		pessoa.setIdade(25);

		Pessoa pessoaPesistida = pessoaBusiness.salvarPessoa(pessoa);
		assertNotNull(pessoaPesistida);
	}

	@Test
	public void alterarPessoa() throws PessoaBusinessException {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("TesteAlterar");
		pessoa.setIdade(27);

		Pessoa pessoaPesistida = pessoaBusiness.salvarPessoa(pessoa);
		assertNotNull(pessoaPesistida);

		Pessoa pessoaParaSerAlterada = new Pessoa();
		pessoaParaSerAlterada.setId((Integer) pessoaPesistida.getId());
		pessoaParaSerAlterada.setNome("TesteAlterado");
		pessoaParaSerAlterada.setIdade(26);

		Pessoa pessoaAlterada = pessoaBusiness.salvarPessoa(pessoaParaSerAlterada);
		assertNotNull(pessoaAlterada);
	}

	@Test
	public void getPessoas() throws PessoaBusinessException {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("TestePessoas");
		pessoa.setIdade(30);

		Pessoa pessoaPersistida = pessoaBusiness.salvarPessoa(pessoa);
		assertNotNull(pessoaPersistida);

		List<Pessoa> pessoas = pessoaBusiness.getPessoas();
		assertTrue(!Util.isEmpty(pessoas));
	}

	@Test
	public void getPessoaById() throws PessoaBusinessException {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("TesteConsultar");
		pessoa.setIdade(28);

		Pessoa pessoaPersistida = pessoaBusiness.salvarPessoa(pessoa);
		assertNotNull(pessoaPersistida);

		Pessoa pessoaConsultada = pessoaBusiness.getPessoaById((Integer) pessoaPersistida.getId());
		assertNotNull(pessoaConsultada);
	}

	@Test
	public void excluirPessoa() throws PessoaBusinessException {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("TesteExcluir");
		pessoa.setIdade(29);

		Pessoa pessoPersistida = pessoaBusiness.salvarPessoa(pessoa);
		assertNotNull(pessoPersistida);

		Integer idExcluido = pessoaBusiness.excluirPessoa((Integer) pessoPersistida.getId());
		assertTrue(!Util.isEmpty(idExcluido));
	}
}
