package dao;

import db.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import utils.ReflectionUtils;

import java.sql.Connection;
import java.util.List;

/**
 * Created by Cabage on 2015/12/15.
 */
public class DAO<T> {
    private QueryRunner queryRunner = new QueryRunner();

    private Class<T> clazz;

    public DAO() {
        clazz = ReflectionUtils.getSuperGenericType(getClass());
    }

    public <E> E getForValue(String sql, Object... args) {
        Connection connection = null;

        try {
            connection = JdbcUtils.getConnection();
            return queryRunner.query(connection, sql, new ScalarHandler<E>(), args);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.releaseConnection(connection);
        }

        return null;
    }

    public List<T> getForList(String sql, Object... args) {

        Connection connection = null;

        try {
            connection = JdbcUtils.getConnection();
            return queryRunner.query(connection, sql, new BeanListHandler<>(clazz), args);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.releaseConnection(connection);
        }

        return null;
    }

    public T get(String sql, Object... args) {

        Connection connection = null;

        try {
            connection = JdbcUtils.getConnection();
            return queryRunner.query(connection, sql, new BeanHandler<>(clazz), args);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.releaseConnection(connection);
        }

        return null;
    }


    public boolean update(String sql, Object... args) {
        Connection connection = null;
        int rows_updated = 0;
        try {
            connection = JdbcUtils.getConnection();
            rows_updated = queryRunner.update(connection, sql, args);
            return rows_updated == 1;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.releaseConnection(connection);
        }
        return false;
    }
}
