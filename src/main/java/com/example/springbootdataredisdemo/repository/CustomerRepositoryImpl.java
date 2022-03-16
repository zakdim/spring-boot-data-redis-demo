package com.example.springbootdataredisdemo.repository;

import com.example.springbootdataredisdemo.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Map;

@Slf4j
@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    private static final String KEY = "CUSTOMER";

    private RedisTemplate<String, Object> redisTemplate;
    private HashOperations<String, Long, Customer> hashOperations;

    public CustomerRepositoryImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void postConstruct() {
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void save(Customer customer) {
        log.info("save customer: {}", customer);
        hashOperations.put(KEY, customer.getId(), customer);
    }

    @Override
    public Customer find(Long id) {
        Customer found = hashOperations.get(KEY, id);
        log.info("find customer by id {}: {}", id, found);

        return found;
    }

    @Override
    public Map<Long, Customer> findAll() {
        Map<Long, Customer> customers = hashOperations.entries(KEY);
        log.info("findAll customers: {}", customers);

        return customers;
    }

    @Override
    public void update(Customer customer) {
        log.info("update customer {}", customer);
        hashOperations.put(KEY, customer.getId(), customer);
    }

    @Override
    public void delete(Long... ids) {
        if (!(ids.length > 0)) throw new IllegalArgumentException("list of IDs should not be empty");

        log.info("delete customers by IDs {}", Arrays.asList(ids));
        hashOperations.delete(KEY, ids);

    }
}
