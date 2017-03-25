package com.qiita.nimzo6689.etude.jpa.dao;

import com.qiita.nimzo6689.etude.jpa.code.AuthorityType;
import com.qiita.nimzo6689.etude.jpa.entity.Users;
import com.qiita.nimzo6689.etude.jpa.entity.Users_;
import java.util.List;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import lombok.extern.log4j.Log4j2;

/**
 * UsersDao
 *
 * @author nimzo6689
 */
@Log4j2
public class UsersDao extends AbstractDao {
    
    private static final long serialVersionUID = 3208415200806376976L;
    
    public static void main(String[] args) {
        UsersDao usersDao = new UsersDao();
        log.info(usersDao.findByLoginId("br"));
        
        usersDao.findAll().stream().forEach(log::info);
        usersDao.findByAuthorityType(AuthorityType.NORMAL).stream()
                .map(tuple -> "{" + tuple.get(0) + ", " + tuple.get(1) + "}")
                .forEach(log::info);
    }
    
    public Users findByLoginId(String loginId) {
        try {
            String sql = "SELECT u FROM Users u WHERE u.loginId = :loginId";
            return entityManager.createQuery(sql, Users.class)
                    .setParameter("loginId", loginId)
                    .getSingleResult();
        } finally {
            entityManager.clear();
        }
    }
    
    public List<Users> findAll() {
        try {
            return entityManager.createNamedQuery("Users.findAll", Users.class)
                    .getResultList();
        } finally {
            entityManager.clear();
        }
    }
    
    public List<Tuple> findByAuthorityType(AuthorityType authorityType) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tuple> query = criteriaBuilder.createTupleQuery();
        
        Root<Users> root = query.from(Users.class);
        
        query.select(criteriaBuilder.tuple(
                root.get(Users_.userName), root.get(Users_.authorityType)))
                .where(
                        criteriaBuilder.equal(root.get(Users_.authorityType), authorityType),
                        criteriaBuilder.equal(root.get(Users_.isDeleted), false)
                ).orderBy(criteriaBuilder.asc(root.get(Users_.createdAt)));
        return entityManager.createQuery(query)
                .getResultList();
    }
    
}
