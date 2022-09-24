package com.restapi.javacrudapicrud.database;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restapi.javacrudapicrud.entitys.PurchaseCartData;

// Creating a repository for the PurchaseCartData entity.
public interface RepositoryPurchaseCartData extends JpaRepository<PurchaseCartData, Long> {

}
