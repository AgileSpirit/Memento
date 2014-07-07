package io.memento.infra.repository;

import io.memento.domain.model.Memento;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MementoRepository extends PagingAndSortingRepository<Memento, Long> {
}
