package com.api.equipamento.repositori;

import com.api.equipamento.model.Bicicleta;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface RepBicicleta extends CrudRepository<Bicicleta, UUID> {
    List<Bicicleta> findAll();

    Bicicleta findById(int id);

    int countById(int id);
}
