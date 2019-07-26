package cc.alpgo.srms.user.dao.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "account")
@Getter
@Setter
@Accessors(chain = true)
public class Account {
    @Id
    private String id;
    private String number;
    private String customerId;
    private int amount;

    public Account(String number, String customerId, int amount) {
        this.number = number;
        this.customerId = customerId;
        this.amount = amount;
    }
}
