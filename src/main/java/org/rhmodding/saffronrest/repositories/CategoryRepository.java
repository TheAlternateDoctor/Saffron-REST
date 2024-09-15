package org.rhmodding.saffronrest.repositories;

import org.rhmodding.saffronrest.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer>  {

}
