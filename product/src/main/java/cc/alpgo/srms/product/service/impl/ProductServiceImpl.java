package cc.alpgo.srms.product.service.impl;

import cc.alpgo.srms.product.dao.model.Product;
import cc.alpgo.srms.product.dao.repository.ProductRepository;
import cc.alpgo.srms.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Override
    public Flux<Product> getProducts() {
        return productRepository.findAll().log("findAllProducts");
    }

    @Override
    public Mono<Product> getProductById(String id) {
        return productRepository.findById(id).log("findOneProduct");
    }

    @Override
    public Mono<Product> createOrUpdateProduct(Mono<Product> productMono) {
        return productMono.flatMap(productRepository::save).log("saveOneProduct");
    }

    @Override
    public Mono<Void> deleteProduct(String id) {
        return productRepository.deleteById(id).log("deleteOneProduct");
    }
}
