package cc.alpgo.srms.product.handler;

import cc.alpgo.srms.product.dao.model.Product;
import cc.alpgo.srms.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Service
public class ProductHandler {

    @Autowired
    private ProductService productService;

    public Mono<ServerResponse> getProducts(ServerRequest serverRequest) {
        return ServerResponse.ok().body(productService.getProducts(), Product.class);
    }

    public Mono<ServerResponse> getProductById(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        return ServerResponse.ok().body(productService.getProductById(id), Product.class);
    }

    public Mono<ServerResponse> createProduct(ServerRequest serverRequest) {
        Mono<Product> product = serverRequest.bodyToMono(Product.class);
        return ServerResponse.ok().body(productService.createOrUpdateProduct(product), Product.class);
    }

    public Mono<ServerResponse> deleteProduct(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        return ServerResponse.ok().body(productService.deleteProduct(id), Void.class);
    }
}
