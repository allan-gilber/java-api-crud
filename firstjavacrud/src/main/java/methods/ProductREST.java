package methods;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.restapi.firstjavacrud.database.RepositoryProductData;
import com.restapi.firstjavacrud.entitys.ProductData;

@RestController
@RequestMapping("/product")
public class ProductREST {
    @Autowired
    private RepositoryProductData base;

    @GetMapping
    public List<ProductData> listAll() {
        return base.findAll();
    }

    @PostMapping
    public void save(@RequestBody ProductData productData) {
        base.save(productData);
    }

    @PutMapping
    public void modifyUser(@RequestBody ProductData productData) {
        if (productData.getProduct_id() > 0)
            base.save(productData);
    }

    @DeleteMapping
    public void deleteUser(@RequestBody ProductData productData) {
        base.delete(productData);
    }
}
