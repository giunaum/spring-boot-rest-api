package br.com.api.springbootrestapi;

import br.com.api.springbootrestapi.business.PessoaBusiness;
import br.com.api.springbootrestapi.dto.PessoaDTO;
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
		PessoaDTO pessoa = new PessoaDTO("TesteSalvar", 25);

		PessoaDTO pessoaPesistida = pessoaBusiness.salvarPessoa(pessoa);
		assertNotNull(pessoaPesistida);
	}

	@Test
	public void alterarPessoa() throws PessoaBusinessException {
		PessoaDTO pessoa = new PessoaDTO("TesteAlterar", 27);

		PessoaDTO pessoaPesistida = pessoaBusiness.salvarPessoa(pessoa);
		assertNotNull(pessoaPesistida);

		PessoaDTO pessoaParaSerAlterada = new PessoaDTO(pessoaPesistida.getId(), "TesteAlterado", 26);

		PessoaDTO pessoaAlterada = pessoaBusiness.salvarPessoa(pessoaParaSerAlterada);
		assertNotNull(pessoaAlterada);
	}

	@Test
	public void getPessoas() throws PessoaBusinessException {
		PessoaDTO pessoa = new PessoaDTO("TestePessoas", 30);

		PessoaDTO pessoaPersistida = pessoaBusiness.salvarPessoa(pessoa);
		assertNotNull(pessoaPersistida);

		List<PessoaDTO> pessoas = pessoaBusiness.getPessoas();
		assertTrue(!Util.isEmpty(pessoas));
	}

	@Test
	public void getPessoaById() throws PessoaBusinessException {
		PessoaDTO pessoa = new PessoaDTO("TesteConsultar", 28);

		PessoaDTO pessoaPersistida = pessoaBusiness.salvarPessoa(pessoa);
		assertNotNull(pessoaPersistida);

		PessoaDTO pessoaConsultada = pessoaBusiness.getPessoaById(pessoaPersistida.getId());
		assertNotNull(pessoaConsultada);
	}

	@Test
	public void excluirPessoa() throws PessoaBusinessException {
		PessoaDTO pessoa = new PessoaDTO("TesteExcluir", 29);

		PessoaDTO pessoPersistida = pessoaBusiness.salvarPessoa(pessoa);
		assertNotNull(pessoPersistida);

		Integer idExcluido = pessoaBusiness.excluirPessoa(pessoPersistida.getId());
		assertTrue(!Util.isEmpty(idExcluido));
	}
}
