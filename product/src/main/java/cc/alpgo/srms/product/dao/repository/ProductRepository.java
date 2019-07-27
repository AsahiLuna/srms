package cc.alpgo.srms.product.dao.repository;

import cc.alpgo.srms.product.dao.model.Product;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductRepository extends ReactiveCrudRepository<Product, String> {
    Flux<Product> findByProductCode(String productCode);
}
