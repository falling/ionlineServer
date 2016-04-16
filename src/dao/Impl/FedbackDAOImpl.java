package dao.Impl;

import bean.FedbackBean;
import dao.DAO;

/**
 * Created by falling on 2016/4/16.
 */
public class FedbackDAOImpl extends DAO<FedbackBean> {

    public boolean insert(FedbackBean bean){
        String sql ="insert into fedback(student_number,time,content) values(?,now(),?)";
        return update(sql,bean.getStudent_number(),bean.getContent());
    }
}
