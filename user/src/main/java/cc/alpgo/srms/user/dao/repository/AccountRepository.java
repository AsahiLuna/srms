package cc.alpgo.srms.user.dao.repository;

import cc.alpgo.srms.user.dao.model.Account;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface AccountRepository extends ReactiveCrudRepository<Account, String> {
    Flux<Account> findByCustomerId(String customerId);
}
