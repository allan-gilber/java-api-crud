package com.restapi.javacrudapicrud.methods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.restapi.javacrudapicrud.database.RepositoryPurchaseCartData;
import com.restapi.javacrudapicrud.entitys.PurchaseCartData;

/**
 * This class is a REST controller that handles the requests for the purchase
 * cart.
 */
@RestController
/**
 * This class is a REST controller that handles the requests for the purchase
 * cart.
 */
@RequestMapping("/purchase-cart")
public class PurchaseCartREST {
    @Autowired
    private RepositoryPurchaseCartData base;

    @GetMapping
    public ResponseEntity<?> listAll(Pageable pageable) {
        return new ResponseEntity<>(base.findAll(pageable), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody PurchaseCartData purchaseCartData) {
        base.save(purchaseCartData);
        return new ResponseEntity<>(base.save(purchaseCartData), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deletePurchaseCarItem(@RequestBody PurchaseCartData purchaseCartData) {
        base.delete(purchaseCartData);
        return new ResponseEntity<>("Item has been succesfuly deleted.", HttpStatus.OK);
    }
}
