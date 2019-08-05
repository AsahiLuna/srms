package cc.alpgo.srms.product;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.reactive.FluxSender;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductApplicationTests {

    @Autowired
    private FluxSender sender;
    @Test
    public void contextLoads() {
    }

    @Test
    public void hello() throws Exception{
        sender.send(Flux.just("Test"));
    }

}
