package dao.Impl;

import bean.QuestionBean;
import dao.DAO;

import java.util.List;

/**
 * Created by falling on 2016/4/16.
 */
public class QuestionDAOImpl extends DAO<QuestionBean> {

    /**
     * 获取问题列表
     * @return
     */
    public List<QuestionBean> getAllOrder(){
        String sql ="select * from questionTable order by id DESC";
        return getForList(sql);
    }

    /**
     * 提问
     * @param bean
     * @return
     */
    public  boolean insert(QuestionBean bean){
        String sql = "insert into questionTable(student_number,question,time) values(?,?,now())";
        return update(sql,bean.getStudent_number(),bean.getQuestion());
    }

}
