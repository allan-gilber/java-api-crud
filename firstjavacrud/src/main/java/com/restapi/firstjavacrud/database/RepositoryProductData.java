package com.restapi.firstjavacrud.database;

import org.springframework.data.jpa.repository.JpaRepository;
import com.restapi.firstjavacrud.entitys.ProductData;

public interface RepositoryProductData extends JpaRepository<ProductData, Long> {

}
