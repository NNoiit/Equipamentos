package com.api.equipamento.repositori;

import com.api.equipamento.model.Tranca;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface RepTranca extends CrudRepository<Tranca, UUID> {

    Tranca findById(int id);

    int countById(int id);
}
