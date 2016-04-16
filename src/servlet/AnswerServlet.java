package servlet;

import bean.AnswerBean;
import com.google.gson.Gson;
import dao.Impl.AnswerDAOImpl;
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
@WebServlet(name = "AnswerServlet", urlPatterns = "/answer")
public class AnswerServlet extends HttpServlet {

    public static final String LOSE_METHOD = "lose method";
    public static final String STUDENT_NUMBER = "studentNumber";
    public static final String CONTENT = "content";
    public static final String GET_ANSWERS = "getAnswers";
    public static final String PUT_ANSWER = "putAnswer";
    public static final String QUESTION_ID = "question_id";

    Gson gson = new Gson();
    AnswerDAOImpl answerDAO = new AnswerDAOImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String method = request.getHeader("method");
        String apikey = request.getHeader(FinalString.APIKEY);

        if (method != null && apikey != null && apikey.equals(FinalString.APIKEY_TRUE)) {
            int id = Integer.parseInt(request.getParameter(QUESTION_ID));
            switch (method) {
                case GET_ANSWERS:
                    response.getWriter().print(gson.toJson(answerDAO.getAllAnswerByQuestionId(id)));
                    break;

                case PUT_ANSWER:
                    String studentNumber = request.getParameter(STUDENT_NUMBER);
                    String content = request.getParameter(CONTENT);
                    if (studentNumber != null && content != null && studentNumber.length() > 0 && content.length() > 0) {
                        AnswerBean answerBean = new AnswerBean(id, studentNumber, content);
                        if (answerDAO.answerTheQuestion(answerBean)) {
                            response.getWriter().print(FinalString.SUCCESS);
                        } else {
                            response.getWriter().print(FinalString.FAILED);
                        }
                    }else{
                        response.getWriter().print(FinalString.LOSE_PARAMETERS);
                    }
                    break;
            }

        } else {
            response.getWriter().print(FinalString.ERROR_APIKEY + " OR " + LOSE_METHOD);
        }
    }
}
