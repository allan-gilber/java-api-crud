package com.restapi.firstjavacrud.database;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restapi.firstjavacrud.entitys.PurchaseCartData;

// Creating a repository for the PurchaseCartData entity.
public interface RepositoryPurchaseCartData extends JpaRepository<PurchaseCartData, Long> {

}
