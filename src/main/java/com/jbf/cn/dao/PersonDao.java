package com.jbf.cn.dao;

import com.jbf.cn.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonDao extends MongoRepository<Person, String> {
    Page<Person> findByNameLikeAndAgeBetween(String name, Integer begin, Integer end, Pageable pageable);
}
