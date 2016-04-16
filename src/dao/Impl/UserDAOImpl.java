package dao.Impl;

import bean.UserBean;
import dao.DAO;

/**
 * Created by falling on 2016/3/11.
 */
public class UserDAOImpl extends DAO<UserBean> {

    /**
     * @param userBean
     * @return 插入成功返回 true, 有重复学号失败返回false
     */
    public boolean insert(UserBean userBean) {
        if (getByNumber(userBean.getStudent_number()) == null) {
            String sql = "INSERT INTO userTable(student_number,password,student_name,sex,cell_phone,school) values(?,?,?,?,?,?)";
            update(sql, userBean.getStudent_number(), userBean.getPassword(), userBean.getStudent_name(), userBean.getSex(), userBean.getCell_phone(), userBean.getSchool());
            return true;
        } else
            return false;
    }

    /**
     * 按学号查找个人信息
     * @param number
     * @return
     */
    public UserBean getByNumber(String number) {
        String sql = "select * from userTable where student_number = ?";
        UserBean userBean = get(sql, number);
        if(userBean!=null) {
            userBean.setPassword("");
        }
        return userBean;
    }




    /**
     * 登陆
     * @param number
     * @param password
     * @return
     */
    public boolean login(String number,String password){
        String sql = "select * from userTable where student_number = ? and password = ?";
        return get(sql,number,password) != null;
    }



}
