package com.restapi.firstjavacrud.database;

import org.springframework.data.jpa.repository.JpaRepository;
import com.restapi.firstjavacrud.entitys.ProductData;

// Creating a repository for the ProductData class.
public interface RepositoryProductData extends JpaRepository<ProductData, Long> {

}
