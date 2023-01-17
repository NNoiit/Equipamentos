package com.api.equipamento.repositori;

import com.api.equipamento.model.Bicicleta;
import org.hibernate.sql.Delete;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.yaml.snakeyaml.events.Event;

import java.util.List;
import java.util.UUID;
@Qualifier("bicicletas")
@Repository
public interface RepBicicleta extends CrudRepository<Bicicleta, UUID> {
    List<Bicicleta> findAll();

    Bicicleta findByUuid(UUID uuid);

    int countByUuid(UUID id);
}
