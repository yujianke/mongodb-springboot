package com.jbf.cn.service;

import com.jbf.cn.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IpersonService {
    public Page<Person> pagefind(String name, Integer begin, Integer end, Pageable pageable);

    public Person findById(String id) ;

    public void save(Person person);

    public void update(Person person);

    public void updateAge(String _id);

    public void deleteById(String id);
}
