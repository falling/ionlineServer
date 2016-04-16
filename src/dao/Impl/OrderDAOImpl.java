package dao.Impl;

import bean.OrderBean;
import dao.DAO;
import servlet.OrderServlet;

import java.util.List;


/**
 * Created by falling on 2016/3/12.
 */
public class OrderDAOImpl extends DAO<OrderBean> {

    public static final String STATE_DELETE = "-1";
    public static final String STATE_FAILED = "-2";
    public static final String STATE_RELEASE = "0";
    public static final String STATE_ACCEPT = "1";
    public static final String STATE_COMPLETE = "2";
    public static final String START_LOCATION = "startLocation";
    public static final String END_LOCATION = "endLocation";

    /**
     * 发布任务
     *
     * @param orderBean
     * @return 返回成功或者失败
     */
    public boolean insert(OrderBean orderBean) {
        String sql = "INSERT INTO OrderTable("+ OrderServlet.RELEASE_STUDENT_NUMBER+ ",time," + START_LOCATION + "," + END_LOCATION + ",content,lable,tip,state) values(?,now(),?,?,?,?,?,?)";
        return update(sql, orderBean.getRelease_student_number(),orderBean.getStartLocation(),orderBean.getEndLocation(), orderBean.getContent(), orderBean.getLable(), orderBean.getTip(), STATE_RELEASE);
    }

    /**
     * 接受任务
     *
     * @param orderBean
     * @return
     */
    public synchronized boolean acceptOrder(OrderBean orderBean) {
        String sql = "update OrderTable set "+ OrderServlet.ACCEPTANCE_STUDENT_NUMBER +" = ?,state = ?  where state = ? and order_id = ? and "+ OrderServlet.RELEASE_STUDENT_NUMBER+" != ?";
        return update(sql, orderBean.getAcceptance_student_number(), STATE_ACCEPT, STATE_RELEASE, orderBean.getOrder_id(), orderBean.getAcceptance_student_number());
    }

    /**
     * 放弃任务
     *
     * @param orderBean
     * @return
     */
    public boolean giveUpOrder(OrderBean orderBean) {
        String sql = "update OrderTable set state = ? where order_id = ? and state = 1";
        return update(sql, STATE_FAILED, orderBean.getOrder_id());
    }


    /**
     * 任务完成
     *
     * @param orderBean
     * @return
     */
    public boolean completeOrder(OrderBean orderBean) {
        String sql = "update OrderTable set state = ? where order_id = ? and "+OrderServlet.RELEASE_STUDENT_NUMBER+" = ? and state = ?";
        if(update(sql, STATE_COMPLETE, orderBean.getOrder_id(), orderBean.getRelease_student_number(), STATE_ACCEPT)) {
            String acceptance_user_id = get("select * from OrderTable where order_id = ?", orderBean.getOrder_id()).getAcceptance_student_number();
            sql = "update UserTable set amount = amount + 1 where student_number = ?";
            return update(sql, acceptance_user_id);
        }
        return false;
    }

    /**
     * 删除未接单的任务
     *
     * @param orderBean
     * @return
     */
    public boolean deleteOrder(OrderBean orderBean) {
        String sql = "update OrderTable set state = ? where order_id  = ? and state  = 0 and "+OrderServlet.RELEASE_STUDENT_NUMBER+" = ?";
        return update(sql, STATE_DELETE, orderBean.getOrder_id(), orderBean.getRelease_student_number());
    }


    /**
     * 获取所有可以接的任务
     * @return
     */
    public List<OrderBean> getOrderAll() {
        String sql = "select * from OrderTable where state = 0";
        return getForList(sql);
    }


    /**
     * 根据ID获取order
     * @param orderBean
     * @return
     */
    public List<OrderBean> getOrderById(OrderBean orderBean) {
        String sql = "select * from OrderTable where order_id = ?";
        return getForList(sql,orderBean.getOrder_id());
    }


    /**
     * 根据Lable获取可接任务
     * @param orderBean
     * @return
     */
    public List<OrderBean> getOrderByLable(OrderBean orderBean) {
        String sql = "select * from OrderTable where state = 0 and lable = ?";
        return getForList(sql,orderBean.getLable());
    }

    /**
     * 根据地点获取可接任务
     * @param orderBean
     * @return
     */
    public List<OrderBean> getOrderByLocation(OrderBean orderBean) {
        String sql = "select * from OrderTable where state = 0 ";

        if(orderBean.getStartLocation()!=null && orderBean.getEndLocation()!=null) {
            sql += "and startLocation = ? and endLocation = ?";
            return getForList(sql, orderBean.getStartLocation(), orderBean.getEndLocation());
        }
        if(orderBean.getStartLocation()!=null){
            sql += "and startLocation = ?";
            return getForList(sql,orderBean.getStartLocation());
        }
        if(orderBean.getEndLocation()!=null){
            sql += "and endLocation = ?";
            return getForList(sql,orderBean.getEndLocation());
        }
        return null;
    }



    /**
     * 获取未评分的order
     *
     * @param orderBean
     * @return
     */
    public List<OrderBean> getAllNoScore(OrderBean orderBean) {
        String sql = "select * from OrderTable where "+OrderServlet.RELEASE_STUDENT_NUMBER+" = ? and score = -1 and state = 2 ";
        return getForList(sql, orderBean.getRelease_student_number());
    }


    /**
     * 获取你发布的任务
     * @param orderBean
     * @return
     */
    public List<OrderBean> getReleasedOrder(OrderBean orderBean){
        String sql = "select * from OrderTable where " +OrderServlet.RELEASE_STUDENT_NUMBER + " = ?";
        return getForList(sql , orderBean.getRelease_student_number());
    }

    /**
     * 获取你接受的任务
     * @param orderBean
     * @return
     */

    public List<OrderBean> getAcceptedOrder(OrderBean orderBean){
        String sql = "select * from OrderTable where " + OrderServlet.ACCEPTANCE_STUDENT_NUMBER + " = ?";
        return getForList(sql,orderBean.getAcceptance_student_number());
    }


    /**
     * 评分
     *
     * @param orderBean
     * @return
     */
    public boolean updateScore(OrderBean orderBean) {
        String sql = "update OrderTable set score =  ? where order_id = ? and "+OrderServlet.RELEASE_STUDENT_NUMBER+"  = ?  and score = -1 and state = 2 ";
        if(update(sql, orderBean.getScore(), orderBean.getOrder_id(), orderBean.getRelease_student_number())) {
            String user_number = get("select * from OrderTable where order_id = ?", orderBean.getOrder_id()).getAcceptance_student_number();
            sql = "update userTable set average_score = (average_score * amount + ? )/amount where student_number = ?";
            return update(sql, orderBean.getScore(), user_number);
        }
        return false;
    }

}
