package com.api.equipamento.repositori;

import com.api.equipamento.model.Totem;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Qualifier("Totem")
@Repository
public interface RepTotem extends CrudRepository<Totem, UUID> {
    List<Totem> findAll();
   Totem findById(int id);
}
