package servlet;

import bean.UserBean;
import dao.Impl.UserDAOImpl;
import stringvalue.FinalString;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * 注册的servlet
 * Created by falling on 2016/3/11.
 */

@WebServlet(name = "register", urlPatterns = "/register")
public class registerServlet extends HttpServlet {

    public static final String SAME_REGISTER = "the same student_number";
    public static final String STUDENT_NUMBER = "student_number";
    public static final String PASSWORD = "password";
    public static final String STUDENT_NAME = "student_name";
    public static final String SEX = "sex";
    public static final String SCHOOL = "school";
    public static final String CELL_PHONE = "cell_phone";

    UserDAOImpl userDAO = new UserDAOImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String apikey = request.getHeader(FinalString.APIKEY);
        if(apikey!=null && apikey.equals(FinalString.APIKEY_TRUE)){
            UserBean userBean =  getBean(request);
            if (userBean.check()) {
                //插入数据库
                if(userDAO.insert(userBean)) {
                    response.getWriter().print(FinalString.SUCCESS);
                }else{
                    response.getWriter().print(SAME_REGISTER);
                }

            }else{
                response.getWriter().print(FinalString.LOSE_PARAMETERS);
            }

        }else {
            response.getWriter().print(FinalString.ERROR_APIKEY);
        }
    }

    private UserBean getBean(HttpServletRequest request) {
        UserBean userBean = new UserBean();
        userBean.setStudent_number(request.getParameter(STUDENT_NUMBER));
        userBean.setPassword(request.getHeader(PASSWORD));
        userBean.setStudent_name(request.getParameter(STUDENT_NAME));
        userBean.setSex(request.getParameter(SEX));
        userBean.setCell_phone(request.getParameter(CELL_PHONE));
        userBean.setSchool(request.getParameter(SCHOOL));
        return userBean;
    }
}


