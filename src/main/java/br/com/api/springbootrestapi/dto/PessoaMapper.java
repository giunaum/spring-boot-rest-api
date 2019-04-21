package br.com.api.springbootrestapi.dto;

import br.com.api.springbootrestapi.entities.Pessoa;
import br.com.api.springbootrestapi.util.Util;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Classe de conversão entre {@link Pessoa} e {@link PessoaDTO}.
 */
@Component
public class PessoaMapper implements Mapper<Pessoa, PessoaDTO> {

	@Override
	public Pessoa toEntity(PessoaDTO pessoaDTO) {
		if (pessoaDTO == null) {
			return null;
		}

		Pessoa pessoa = new Pessoa();
		pessoa.setId(pessoaDTO.getId());
		pessoa.setNome(pessoaDTO.getNome());
		pessoa.setIdade(pessoaDTO.getIdade());

		return pessoa;
	}

	@Override
	public PessoaDTO toDto(Pessoa pessoa) {
		if (pessoa == null) {
			return null;
		}

		return new PessoaDTO((Integer) pessoa.getId(), pessoa.getNome(), pessoa.getIdade());
	}

	@Override
	public List<Pessoa> toEntitys(List<PessoaDTO> pessoaDTOS) {
		if (Util.isEmpty(pessoaDTOS)) {
			return Collections.emptyList();
		}

		List<Pessoa> pessoas = new ArrayList<>();
		pessoaDTOS.forEach(pessoaDTO -> pessoas.add(toEntity(pessoaDTO)));
		return pessoas;
	}

	@Override
	public List<PessoaDTO> toDtos(List<Pessoa> pessoas) {
		if (Util.isEmpty(pessoas)) {
			return Collections.emptyList();
		}

		List<PessoaDTO> pessoaDTOS = new ArrayList<>();
		pessoas.forEach(pessoa -> pessoaDTOS.add(toDto(pessoa)));
		return pessoaDTOS;
	}
}
