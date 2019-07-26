package cc.alpgo.srms.product.controller;

import cc.alpgo.srms.product.dao.model.Product;
import cc.alpgo.srms.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("")
    public Flux<Product> getProducts() {
        return productService.getProducts();
    }
    @GetMapping("/{id}")
    public Mono<Product> getProductById(@PathVariable String id) {
        return productService.getProductById(id);
    }
    @PostMapping("")
    public Mono<Product> createProduct(@RequestBody final Product product) {
        return productService.createOrUpdateProduct(product);
    }
    @DeleteMapping("/{id}")
    public Mono<Void> deleteProduct(@PathVariable String id) {
        return productService.deleteProduct(id);
    }
}