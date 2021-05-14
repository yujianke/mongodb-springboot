package com.jbf.cn.service.serviceImpl;

import com.jbf.cn.dao.PersonDao;
import com.jbf.cn.model.Person;
import com.jbf.cn.service.IpersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class personServiceImpl implements IpersonService {
    @Autowired
    private PersonDao personDao;

    @Autowired
    private MongoTemplate mongoTemplate;
    @Override
    public Page<Person> pagefind(String name, Integer begin, Integer end, Pageable pageable) {
        return personDao.findByNameLikeAndAgeBetween(name, begin, end, pageable);
    }
    @Override
    public Person findById(String id) {
        Optional<Person> optional = personDao.findById(id);
        Person person=null;
        if(optional != null && optional.isPresent()) {
             person = optional.get();
        }
        return person;
    }
    @Override
    public void save(Person person) {
        personDao.save(person);
    }
    @Override
    public void update(Person person) {
        personDao.save(person);
    }
    @Override
    public void updateAge(String _id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(_id));
        Update update = new Update();
        update.inc("age", 1);
        mongoTemplate.updateFirst(query, update, "person");
    }
    @Override
    public void deleteById(String id) {
        personDao.deleteById(id);
    }
}
