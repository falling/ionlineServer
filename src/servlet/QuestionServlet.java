package servlet;

import bean.FedbackBean;
import bean.QuestionBean;
import com.google.gson.Gson;
import dao.Impl.QuestionDAOImpl;
import stringvalue.FinalString;
import sun.security.jgss.GSSCaller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by falling on 2016/4/16.
 */
@WebServlet(name = "QuestionServlet", urlPatterns = "/question")
public class QuestionServlet extends HttpServlet {
    public static final String STUDENT_NUMBER = "studentNumber";
    public static final String QUESTION = "question";
    public static final String METHOD = "method";
    public static final String LOSE_METHOD = "lose method";
    private QuestionDAOImpl questionDAO = new QuestionDAOImpl();
    Gson gson = new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String method = request.getHeader(METHOD);
        String apikey = request.getHeader(FinalString.APIKEY);
        if (method != null && apikey != null && apikey.equals(FinalString.APIKEY_TRUE)) {
            switch (method) {
                case "getQuestions":
                    response.getWriter().print(gson.toJson(questionDAO.getAllOrder()));
                    break;
                case "putQuestions":
                    String studentNumber = request.getParameter(STUDENT_NUMBER);
                    String question = request.getParameter(QUESTION);
                    if (studentNumber != null && question != null && question.length() > 0 && question.length() > 0) {
                        QuestionBean bean = new QuestionBean(studentNumber, question);
                        if (questionDAO.insert(bean)) {
                            response.getWriter().print(FinalString.SUCCESS);
                        } else {
                            response.getWriter().print(FinalString.FAILED);
                        }
                    } else {
                        response.getWriter().print(FinalString.LOSE_PARAMETERS);
                    }
                    break;
            }


        } else {
            response.getWriter().print(FinalString.ERROR_APIKEY + "OR" + LOSE_METHOD);
        }
    }
}
