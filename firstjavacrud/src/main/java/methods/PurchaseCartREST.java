package methods;

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
import com.restapi.firstjavacrud.database.RepositoryPurchaseCartData;
import com.restapi.firstjavacrud.entitys.PurchaseCartData;

@RestController
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
