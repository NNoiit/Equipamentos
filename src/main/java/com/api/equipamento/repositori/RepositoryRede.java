package com.api.equipamento.repositori;

import com.api.equipamento.model.Rede;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface RepositoryRede extends CrudRepository<Rede, UUID> {

}
