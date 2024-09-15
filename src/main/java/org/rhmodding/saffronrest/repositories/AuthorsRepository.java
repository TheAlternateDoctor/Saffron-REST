package org.rhmodding.saffronrest.repositories;

import java.util.List;

import org.rhmodding.saffronrest.models.Authors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AuthorsRepository extends JpaRepository<Authors, Integer> {
    
    @Query("FROM Authors a WHERE a.mod = ?1")
    List<Authors> findByModId(Integer modId);

}
