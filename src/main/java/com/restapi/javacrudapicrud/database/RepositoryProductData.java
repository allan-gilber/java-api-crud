package com.restapi.javacrudapicrud.database;

import org.springframework.data.jpa.repository.JpaRepository;
import com.restapi.javacrudapicrud.entitys.ProductData;

// Creating a repository for the ProductData class.
public interface RepositoryProductData extends JpaRepository<ProductData, Long> {

}
