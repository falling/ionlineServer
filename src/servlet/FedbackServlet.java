package servlet;

import bean.FedbackBean;
import dao.Impl.FedbackDAOImpl;
import stringvalue.FinalString;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by falling on 2016/4/16.
 */
@WebServlet(name = "FedbackServlet", urlPatterns = "/fedback")
public class FedbackServlet extends HttpServlet {
    private FedbackDAOImpl orderDAO = new FedbackDAOImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String apikey = request.getHeader(FinalString.APIKEY);
        if (apikey != null && apikey.equals(FinalString.APIKEY_TRUE)) {
            String content = request.getParameter("content");
            String student_number = request.getParameter("student_number");
            if (content != null && student_number != null && content.length() > 0 && student_number.length() > 0) {
                FedbackBean bean = new FedbackBean(student_number, content);
                if (orderDAO.insert(bean)) {
                    response.getWriter().print(FinalString.SUCCESS);
                } else {
                    response.getWriter().print(FinalString.FAILED);
                }
            } else {
                response.getWriter().print(FinalString.LOSE_PARAMETERS);
            }
        } else {
            response.getWriter().print(FinalString.ERROR_APIKEY);
        }
    }


}
