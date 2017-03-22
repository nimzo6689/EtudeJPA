package com.qiita.nimzo6689.etude.jpa.dao;

import com.qiita.nimzo6689.etude.jpa.entity.Users;
import java.util.List;
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
        usersDao.method("br").stream().forEach(log::info);
    }

    public List<Users> method(String loginId) {
        try {
            String sql = "SELECT u FROM Users u WHERE u.loginId = :loginId";
            return entityManager.createQuery(sql, Users.class)
                    .setParameter("loginId", loginId)
                    .getResultList();
        } finally {
            entityManager.clear();
        }
    }

}
