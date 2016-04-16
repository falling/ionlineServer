package dao.Impl;

import bean.AnswerBean;
import dao.DAO;

import java.util.List;

/**
 * Created by falling on 2016/4/16.
 */
public class AnswerDAOImpl extends DAO<AnswerBean> {


    /**
     * 获取问题的答案
     * @param id
     * @return
     */
    public List<AnswerBean> getAllAnswerByQuestionId(int id){
        String sql ="select * from answerTable where question_id = ? order by id asc";
        return getForList(sql,id);
    }

    /**
     * 回答问题
     * @param bean
     * @return
     */
    public boolean answerTheQuestion(AnswerBean bean){
        String sql = "insert into answerTable(question_id,time,answer_student_number,content) values(?,now(),?,?)";
        return update(sql,bean.getQuestion_id(),bean.getStudent_number(),bean.getContent());
    }
}
