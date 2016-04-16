package servlet;

import bean.OrderBean;
import com.google.gson.Gson;
import dao.Impl.OrderDAOImpl;
import stringvalue.FinalString;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Created by falling on 2016/3/12.
 */
@WebServlet(name = "OrderServlet", urlPatterns = "/order")
public class OrderServlet extends HttpServlet {

    public static final String RELEASE_ORDER = "releaseOrder";
    public static final String DELETE_ORDER = "deleteOrder";
    public static final String ACCEPT_ORDER = "acceptOrder";
    public static final String COMPLETE_ORDER = "completeOrder";
    public static final String GIVE_UP_ORDER = "giveUpOrder";
    public static final String LOSE_METHOD = "lose method";
    public static final String END_LOCATION = "endLocation";
    public static final String START_LOCATION = "startLocation";
    public static final String RELEASE_STUDENT_NUMBER = "release_student_number";
    public static final String CONTENT = "content";
    public static final String LABLE = "lable";
    public static final String TIP = "tip";
    public static final String ACCEPTANCE_STUDENT_NUMBER = "acceptance_student_number";
    public static final String SCORE = "score";
    public static final String STATE = "state";
    public static final String ORDER_ID = "order_id";
    public static final String METHOD = "method";
    public static final String GET_ORDER_ALL = "getOrderAll";
    public static final String LOSE_LABLE = "lose lable";
    public static final String UPDATE_SCORE = "updateScore";
    public static final String GET_ALL_NO_SCORE = "getAllNoScore";
    public static final String GET_ORDER_BY_LOCATION = "getOrderByLocation";
    public static final String GET_ORDER_BY_LABLE = "getOrderByLable";
    public static final String LOSE_ORDER_ID = "lose order_id";
    public static final String GET_ORDER_BY_ID = "getOrderById";
    public static final String GET_ACCEPTED_ORDER = "getAcceptedOrder";
    public static final String GET_RELEASED_ORDER = "getReleasedOrder";
    public static final String COMPLAINT = "complaint";
    public static final String COMPLAINT_STUDENT_NUMBER = "complaint_student_number";
    public static final String COMPLAINT_CONTENT = "complaint_content";
    private OrderDAOImpl orderDAO = new OrderDAOImpl();
    private Gson gson = new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String method = request.getHeader(METHOD);
        String apikey = request.getHeader(FinalString.APIKEY);

        if (method != null && apikey != null && apikey.equals(FinalString.APIKEY_TRUE)) {

            OrderBean orderBean = getBean(request);

            switch (method) {
                case RELEASE_ORDER: //发布任务
                    if (orderBean.check(RELEASE_ORDER)) {
                        if (orderDAO.insert(orderBean)) {
                            response.getWriter().print(FinalString.SUCCESS);
                        } else {
                            response.getWriter().print(FinalString.FAILED);
                        }
                    } else {
                        response.getWriter().print(FinalString.CHECK_FAILED);
                    }

                    break;
                case DELETE_ORDER: //删除任务
                    if (orderDAO.deleteOrder(orderBean)) {
                        response.getWriter().print(FinalString.SUCCESS);
                    } else {
                        response.getWriter().print(FinalString.FAILED);
                    }

                    break;
                case ACCEPT_ORDER: //接受任务
                    if (orderBean.check(ACCEPT_ORDER)) {
                        if (orderDAO.acceptOrder(orderBean)) {
                            response.getWriter().print(FinalString.SUCCESS);
                        } else {
                            response.getWriter().print(FinalString.FAILED);
                        }
                    } else {
                        response.getWriter().print(FinalString.CHECK_FAILED);
                    }

                    break;
                case COMPLETE_ORDER: //完成任务
                    if (orderDAO.completeOrder(orderBean)) {
                        response.getWriter().print(FinalString.SUCCESS);
                    } else {
                        response.getWriter().print(FinalString.FAILED);
                    }

                    break;
                case GIVE_UP_ORDER: //放弃任务
                    if (orderDAO.giveUpOrder(orderBean)) {
                        response.getWriter().print(FinalString.SUCCESS);
                    } else {
                        response.getWriter().print(FinalString.FAILED);
                    }
                    break;
                case GET_ORDER_ALL:  //获取全部可接任务
                    response.getWriter().print(gson.toJson(orderDAO.getOrderAll()));
                    break;

                case GET_ORDER_BY_ID:  //按Id获取任务
                    if (orderBean.getOrder_id() > 0) {
                        response.getWriter().print(gson.toJson(orderDAO.getOrderById(orderBean)));
                    } else {
                        response.getWriter().print(LOSE_ORDER_ID);
                    }
                    break;

                case GET_ORDER_BY_LABLE:  //按lable获取任务
                    if (orderBean.getLable() != null && orderBean.getLable().length() > 0) {
                        response.getWriter().print(gson.toJson(orderDAO.getOrderByLable(orderBean)));
                    } else {
                        response.getWriter().print(LOSE_LABLE);
                    }
                    break;

                case GET_ORDER_BY_LOCATION://按地点获取任务
                        response.getWriter().print(gson.toJson(orderDAO.getOrderByLocation(orderBean)));
                    break;

                case GET_ALL_NO_SCORE:  //获取未评分的Order
                    response.getWriter().print(gson.toJson(orderDAO.getAllNoScore(orderBean)));
                    break;

                case GET_RELEASED_ORDER: //获取你发布的任务
                    response.getWriter().print(gson.toJson(orderDAO.getReleasedOrder(orderBean)));
                    break;

                case GET_ACCEPTED_ORDER://获取你接受的任务
                    response.getWriter().print(gson.toJson(orderDAO.getAcceptedOrder(orderBean)));
                    break;

                case UPDATE_SCORE:   //评分

                    if (orderBean.check(UPDATE_SCORE)) {
                        if (orderDAO.updateScore(orderBean)) {
                            response.getWriter().print(FinalString.SUCCESS);
                        } else {
                            response.getWriter().print(FinalString.FAILED);
                        }
                    } else {
                        response.getWriter().print(FinalString.CHECK_FAILED);
                    }
                    break;

                case COMPLAINT:
                    if(orderBean.check(COMPLAINT)){
                        if (orderDAO.complaint(orderBean)) {
                            response.getWriter().print(FinalString.SUCCESS);
                        } else {
                            response.getWriter().print(FinalString.FAILED);
                        }
                    }else {
                        response.getWriter().print(FinalString.CHECK_FAILED);
                    }
                    break;


            }
        } else {
            response.getWriter().print(FinalString.ERROR_APIKEY + " OR " + LOSE_METHOD);
        }
    }


    private OrderBean getBean(HttpServletRequest request) {
        OrderBean orderBean = new OrderBean();
        orderBean.setOrder_id(request.getParameter(ORDER_ID) == null ? -1 : Integer.parseInt(request.getParameter(ORDER_ID)));
        orderBean.setRelease_student_number(request.getParameter(RELEASE_STUDENT_NUMBER));
        orderBean.setTime(new Date());
        orderBean.setEndLocation(request.getParameter(END_LOCATION));
        orderBean.setStartLocation(request.getParameter(START_LOCATION));
        orderBean.setContent(request.getParameter(CONTENT));
        orderBean.setLable(request.getParameter(LABLE));
        orderBean.setTip(request.getParameter(TIP) == null ? -1 : Double.parseDouble(request.getParameter(TIP)));
        orderBean.setAcceptance_student_number(request.getParameter(ACCEPTANCE_STUDENT_NUMBER));
        orderBean.setScore(request.getParameter(SCORE) == null ? -1 : Double.parseDouble(request.getParameter(SCORE)));
        orderBean.setState(request.getParameter(STATE));
        orderBean.setComplaint_content(request.getParameter(COMPLAINT_CONTENT));
        orderBean.setComplaint_student_number(request.getParameter(COMPLAINT_STUDENT_NUMBER));

        return orderBean;
    }
}
