package com.demo.dao.impl;

import com.demo.dao.IPersonDao;
import com.demo.entities.Person;
import com.demo.entities.Person_;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;

@Repository
public class PersonDaoImpl extends AbstractGenericDaoImpl<Person, Integer> implements IPersonDao {

    public PersonDaoImpl() {
        super(Person.class);
    }

    @Override
    public Person findByName(String name) {
        final CriteriaBuilder cb = this.em.getCriteriaBuilder();
        final CriteriaQuery<Person> query = cb.createQuery(Person.class);
        final Root<Person> person = query.from(Person.class);
        final Path<String> namePath = person.get(Person_.name);
        final Predicate predicate = cb.like(cb.upper(namePath), "%" + name.toUpperCase() + "%");
        final TypedQuery<Person> typedQuery = this.em.createQuery(query.select(person).where(predicate));
        return typedQuery.getResultList()
                .stream().findFirst().orElse(null);
    }
}
