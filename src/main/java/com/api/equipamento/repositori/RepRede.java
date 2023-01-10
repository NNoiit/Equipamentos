package com.api.equipamento.repositori;

import com.api.equipamento.model.Rede;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Qualifier("Rede")
@Repository
public interface RepRede extends CrudRepository<Rede, UUID> {
    List<Rede> findAll();

    Rede findByIdTotem(int id);

    //int contByIdTotem(int id);
}
