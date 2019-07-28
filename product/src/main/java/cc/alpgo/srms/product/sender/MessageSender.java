package cc.alpgo.srms.product.sender;

import cc.alpgo.srms.product.dao.model.Order;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;

@EnableBinding(Source.class)
public class MessageSender {

    @Resource
    private MessageChannel output;

    public void send(Order order) {
        this.output.send(MessageBuilder.withPayload(order).build());
    }
}
