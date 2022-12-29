package com.api.equipamento.repositori;

import com.api.equipamento.model.Tranca;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.UUID;

public interface RepTranca extends CrudRepository<Tranca, UUID> {

    List<Tranca> findAll();
    Tranca findById(int id);

    int countById(int id);
}
