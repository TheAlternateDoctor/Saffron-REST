package org.rhmodding.saffronrest.repositories;

import org.rhmodding.saffronrest.models.Mods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModsRepository extends JpaRepository<Mods, Integer> {
    
}
