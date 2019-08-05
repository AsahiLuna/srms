package cc.alpgo.srms.product.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@ToString
public class Event implements Serializable {
    private Long id;
}
