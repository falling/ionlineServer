package servlet;

import bean.UserBean;
import com.google.gson.Gson;
import dao.Impl.UserDAOImpl;
import stringvalue.FinalString;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by falling on 2016/3/12.
 */
@WebServlet(name = "User", urlPatterns = "/user")
public class UserServlet extends HttpServlet {
    private Gson gson = new Gson();
    private UserDAOImpl userDAO = new UserDAOImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String apikey = request.getHeader(FinalString.APIKEY);
        if(apikey!=null && apikey.equals(FinalString.APIKEY_TRUE)){
            String userNameber = request.getParameter("student_number");
            if(userNameber!=null &&userNameber.length()>0){
                response.getWriter().print(gson.toJson(userDAO.getByNumber(userNameber)));
            }

        }else {
            response.getWriter().print(FinalString.ERROR_APIKEY);
        }
    }
}
