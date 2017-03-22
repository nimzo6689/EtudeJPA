package com.qiita.nimzo6689.etude.jpa.dao;

import java.io.Serializable;
import java.util.Optional;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import lombok.extern.log4j.Log4j2;

/**
 * AbstractDao
 *
 * @author nimzo6689
 */
@Log4j2
public abstract class AbstractDao implements Serializable {

    private static final long serialVersionUID = -1817873851958519808L;

    protected EntityManagerFactory entityManagerFactory;
    protected EntityManager entityManager;
    protected EntityTransaction entityTransaction;

    protected AbstractDao() {
        entityManagerFactory = Persistence.createEntityManagerFactory("default");
        entityManager = entityManagerFactory.createEntityManager();
        entityTransaction = entityManager.getTransaction();
    }

    protected <T> void persist(T entity) {
        Set<ConstraintViolation<T>> constraintViolation
                = Validation.buildDefaultValidatorFactory().getValidator().validate(entity);
        if (!constraintViolation.isEmpty()) {
            constraintViolation.stream().forEach(constraint
                    -> log.warn("[Constraint Violation!]" + constraint.getRootBeanClass().getName()
                            + ", " + constraint.getPropertyPath() + ", " + constraint.getMessage()));
            throw new IllegalArgumentException();
        }
        entityManager.persist(entity);
    }

    protected <T> Optional<T> merge(T entity) {
        Set<ConstraintViolation<T>> constraintViolation
                = Validation.buildDefaultValidatorFactory().getValidator().validate(entity);
        if (!constraintViolation.isEmpty()) {
            constraintViolation.stream().forEach(constraint
                    -> log.warn("[Constraint Violation!]" + constraint.getRootBeanClass().getName()
                            + ", " + constraint.getPropertyPath() + ", " + constraint.getMessage()));
            throw new IllegalArgumentException();
        }
        return Optional.ofNullable(entityManager.merge(entity));
    }

    protected <T> void remove(T entity) {
        entityManager.remove(entityManager.merge(entity));
    }

    protected <T> void detach(T entity) {
        entityManager.detach(entity);
    }

}
