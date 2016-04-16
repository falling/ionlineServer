package servlet;

import com.google.gson.Gson;
import dao.Impl.LocationDAOImpl;
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
@WebServlet(name = "LocationServlet", urlPatterns = "/location")
public class LocationServlet extends HttpServlet {

    public static final String SCHOOL_NAME = "school";
    LocationDAOImpl locationDAO = new LocationDAOImpl();
    Gson gson = new Gson();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String apikey = request.getHeader(FinalString.APIKEY);
        String schoolName = request.getParameter(SCHOOL_NAME);
        if (apikey != null && apikey.equals(FinalString.APIKEY_TRUE)) {
            response.getWriter().print(gson.toJson(locationDAO.getLocationList(schoolName)));
        }else {
            response.getWriter().print(FinalString.ERROR_APIKEY);
        }
    }
}
