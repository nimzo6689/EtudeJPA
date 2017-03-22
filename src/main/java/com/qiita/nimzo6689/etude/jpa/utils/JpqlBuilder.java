package com.qiita.nimzo6689.etude.jpa.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * JpqlBuilder
 *
 * @author nimzo6689
 */
@NoArgsConstructor
public class JpqlBuilder {

    private StringBuilder sql = new StringBuilder();
    private List<BindParameter> params = new ArrayList<>();

    /**
     *
     * @param sql
     */
    public JpqlBuilder(String sql) {
        this.append(sql);
    }

    /**
     *
     * @param sql
     * @param params
     */
    public JpqlBuilder(String sql, BindParameter... params) {
        this.append(sql);
        this.append(params);
    }

    /**
     *
     * @param sql
     * @return
     */
    public JpqlBuilder append(@NonNull String sql) {
        this.sql.append(" ").append(sql);
        return this;
    }

    /**
     *
     * @param params
     * @return
     */
    public JpqlBuilder append(BindParameter... params) {
        this.params.addAll(Arrays.asList(params));
        return this;
    }

    /**
     *
     * @param sql
     * @param params
     * @return
     */
    public JpqlBuilder append(String sql, BindParameter... params) {
        this.append(sql);
        this.append(params);
        return this;
    }

    /**
     *
     * @param <T>
     * @param entityManager
     * @param clazz
     * @return
     */
    public <T> TypedQuery<T> build(EntityManager entityManager, Class<T> clazz) {
        TypedQuery<T> query = entityManager.createQuery(sql.toString(), clazz);
        params.stream().forEach(param -> query.setParameter(param.name, param.value));
        return query;
    }

    /**
     * BindParameter
     *
     * @author nimzo6689
     */
    public static class BindParameter {

        private String name;
        private Object value;

        public BindParameter(String name, Object value) {
            this.name = name;
            this.value = value;
        }
    }

}
