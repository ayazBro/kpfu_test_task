package ayaz.bro.restoran.service;

import ayaz.bro.restoran.entity.Product;
import ayaz.bro.restoran.exception.DataNotFoundException;
import ayaz.bro.restoran.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product findById(int id) {
        return productRepository.findById(id)
                .orElseThrow(DataNotFoundException::new);
    }

    public Product findByName(String name) {
        return productRepository.findProductByName(name)
                .orElseThrow(DataNotFoundException::new);
    }

    public List<Product> findAll(String category) {
        return productRepository.findAll(Sort.by(category));
    }
}