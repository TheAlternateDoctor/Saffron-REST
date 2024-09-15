package org.rhmodding.saffronrest.repositories;

import org.rhmodding.saffronrest.models.Pics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PicsRepository extends JpaRepository<Pics, Integer> {

}
