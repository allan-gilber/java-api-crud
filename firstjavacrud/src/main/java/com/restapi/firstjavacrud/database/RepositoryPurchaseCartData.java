package com.restapi.firstjavacrud.database;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restapi.firstjavacrud.entitys.PurchaseCartData;

public interface RepositoryPurchaseCartData extends JpaRepository<PurchaseCartData, Long> {

}
