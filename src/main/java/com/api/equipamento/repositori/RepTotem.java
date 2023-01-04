package com.api.equipamento.repositori;

import com.api.equipamento.model.Totem;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.UUID;


public interface RepTotem extends CrudRepository<Totem, UUID> {
    List<Totem> findAll();
   Totem findById(int id);
}
