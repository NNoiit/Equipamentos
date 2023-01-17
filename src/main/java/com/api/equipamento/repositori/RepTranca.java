package com.api.equipamento.repositori;

import com.api.equipamento.model.Tranca;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Qualifier("Tranca")
@Repository
public interface RepTranca extends CrudRepository<Tranca, UUID> {

    List<Tranca> findAll();
    Tranca findByUuid(UUID id);

    int countByUuid(UUID id);
}
