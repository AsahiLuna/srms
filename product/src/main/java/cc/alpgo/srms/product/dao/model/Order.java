package cc.alpgo.srms.product.dao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Data
@AllArgsConstructor
public class Order {
    @Id
    private String id;
    private Date orderDate;
    private String productId;
}
