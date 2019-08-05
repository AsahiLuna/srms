package cc.alpgo.srms.product;

import cc.alpgo.srms.product.event.Event;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.cloud.stream.reactive.FluxSender;
import org.springframework.cloud.stream.reactive.StreamEmitter;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import reactor.core.publisher.Flux;

import java.time.Duration;

@SpringBootApplication
@EnableDiscoveryClient
@EnableReactiveMongoRepositories
//@EnableBinding({Source.class, Sink.class, Processor.class})
@EnableBinding({Source.class, Sink.class})
public class ProductApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }

    @StreamEmitter
    public void emit(@Output(Source.OUTPUT) FluxSender output) {
        output.send(Flux.interval(Duration.ofMillis(1000)).map(Event::new));
    }

    @StreamListener
    public void receive(@Input(Sink.INPUT) Flux<Event> events) {
        events.map(Object::toString)
                .subscribe(event -> LOGGER.info("Event: {}", event));
    }

//    @StreamListener
//    public void receive(@Input(Processor.INPUT) Flux<String> input, @Output(Processor.OUTPUT) FluxSender output) {
//        output.send(input.map(s -> s.toUpperCase()));
//    }
}
