package org.rhmodding.saffronrest.repositories;

import org.rhmodding.saffronrest.models.Mods;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModsRepository extends JpaRepository<Mods, Integer> {
    
}
