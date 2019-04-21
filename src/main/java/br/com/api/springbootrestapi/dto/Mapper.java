package br.com.api.springbootrestapi.dto;

import br.com.api.springbootrestapi.entities.Entidade;

import java.util.List;

/**
 * Interface de conversão entre entidade e dto.
 *
 * @param <E>
 * @param <D>
 */
public interface Mapper<E extends Entidade, D extends DTO> {

	/**
	 * Converte {@link DTO} em {@link Entidade}
	 *
	 * @param d
	 * @return
	 */
	E toEntity(D d);

	/**
	 * Converte {@link Entidade} em {@link DTO}
	 *
	 * @param e
	 * @return
	 */
	D toDto(E e);

	/**
	 * Converte uma lista de {@link DTO} em uma lista de {@link Entidade}
	 *
	 * @param ds
	 * @return
	 */
	List<E> toEntitys(List<D> ds);

	/**
	 * Converte uma lista de {@link Entidade} em uma lista de {@link DTO}
	 *
	 * @param es
	 * @return
	 */
	List<D> toDtos(List<E> es);
}
