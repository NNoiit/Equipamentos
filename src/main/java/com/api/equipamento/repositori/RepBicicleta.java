package com.api.equipamento.repositori;

import com.api.equipamento.model.Bicicleta;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Qualifier("bicicletas")
@Repository
public interface RepBicicleta extends CrudRepository<Bicicleta, UUID> {
    List<Bicicleta> findAll();

    Bicicleta findById(int id);

    int countById(int id);
}
