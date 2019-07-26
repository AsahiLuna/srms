package cc.alpgo.srms.user.controller;

import cc.alpgo.srms.user.dao.model.Account;
import cc.alpgo.srms.user.dao.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("account")
public class AccountController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountRepository repository;

    @GetMapping("customer/{customer}")
    public Flux findByCustomer(@PathVariable("customer") String customerId) {
        LOGGER.info("findByCustomer: customerId={}", customerId);
        return repository.findByCustomerId(customerId);
    }

    @GetMapping
    public Flux findAll() {
        LOGGER.info("findAll");
        return repository.findAll();
    }

    @GetMapping("{id}")
    public Mono findById(@PathVariable("id") String id) {
        LOGGER.info("findById: id={}", id);
        return repository.findById(id);
    }

    @PostMapping
    public Mono create(@RequestBody Account account) {
        LOGGER.info("create: {}", account);
        return repository.save(account);
    }
}
