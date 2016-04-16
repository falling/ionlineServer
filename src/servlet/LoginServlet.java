package servlet;

import dao.Impl.UserDAOImpl;
import stringvalue.FinalString;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登陆的servlet
 * Created by falling on 2016/3/11.
 */

@WebServlet( name = "login" , urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    public static final String LOGIN_SUCCESS = "success";
    public static final String LOGIN_FAILED = "failed";
    UserDAOImpl userDAO = new UserDAOImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String apikey = request.getHeader(FinalString.APIKEY);
        if(apikey!=null && apikey.equals(FinalString.APIKEY_TRUE)){
            if(userDAO.login(request.getParameter("student_number"),request.getHeader("password"))) {
                response.getWriter().print(LOGIN_SUCCESS);
            }else{
                response.getWriter().print(LOGIN_FAILED);
            }
        }else {
            response.getWriter().print(FinalString.ERROR_APIKEY);
        }
    }
}
