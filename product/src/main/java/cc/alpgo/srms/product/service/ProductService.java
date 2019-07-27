package cc.alpgo.srms.product.service;

import cc.alpgo.srms.product.dao.model.Product;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface ProductService {
    Flux<Product> getProducts();

    Mono<Product> getProductById(final String id);

    Mono<Product> createOrUpdateProduct(final Mono<Product> product);

    Mono<Void> deleteProduct(final String id);
}
