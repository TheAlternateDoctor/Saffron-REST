package org.rhmodding.saffronrest.repositories;

import java.util.List;

import org.rhmodding.saffronrest.models.Files;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FilesRepository extends JpaRepository<Files, Integer>{
    
    @Query("FROM Files f WHERE f.mod.id = ?1")
    List<Files> findByModId(Integer modId);
}
