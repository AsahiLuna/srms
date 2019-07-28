package cc.alpgo.srms.product.listener;

import cc.alpgo.srms.product.dao.model.Order;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;

@EnableBinding(Sink.class)
public class MessageListener {
    @StreamListener(Sink.INPUT)
    public void consume(Message<Order> message) {
        System.out.println(message.getPayload());
    }
}
