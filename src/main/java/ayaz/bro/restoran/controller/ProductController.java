package ayaz.bro.restoran.controller;

import ayaz.bro.restoran.entity.Product;
import ayaz.bro.restoran.exception.DataNotFoundException;
import ayaz.bro.restoran.service.ProductService;
import ayaz.bro.restoran.util.DataErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/product")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public Product getProduct(@RequestParam(defaultValue = "id") String searchBy, @RequestParam(defaultValue = "1") String value) {
        if(searchBy.equals("id"))
            return productService.findById(Integer.parseInt(value));
        else {
            return productService.findByName(value);
        }
    }

    @GetMapping("/products")
    public List<Product> getProducts(@RequestParam(defaultValue = "id") String category) {
        return productService.findAll(category);
    }

    @ExceptionHandler
    private ResponseEntity<DataErrorResponse> handleException(DataNotFoundException e) {
        DataErrorResponse response=new DataErrorResponse(
                "Product doesn't found",
                System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);//NOT FOUND - 404 status
    }
}