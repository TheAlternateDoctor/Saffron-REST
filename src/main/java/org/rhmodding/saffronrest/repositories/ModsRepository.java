package org.rhmodding.saffronrest.repositories;

import org.rhmodding.saffronrest.models.Mods;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ModsRepository extends JpaRepository<Mods, Integer> {
    
    @Query("From Mods m " +
    "where (:name IS NULL OR m.name LIKE %:name%)"+
    "and (:legacy IS NULL OR m.isLegacy = :legacy)"+
    "and (:game IS NULL OR m.game =:game)"
    )
    public Page<Mods> findSorted(Pageable page,@Param("name") String name,@Param("legacy") Boolean legacy,@Param("game") Integer game);
}
