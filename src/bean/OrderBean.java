package bean;

import servlet.OrderServlet;

import java.util.Date;

/**
 * Created by falling on 2016/3/11.
 */
public class OrderBean {
    private int order_id;          //单号id
    private String release_student_number;   //发布人学号
    private Date time;           //发布时间
    private String location;       //交任务地点
    private String content;        //任务内容
    private String lable;          //任务标签（跑腿，代买。。。）
    private double tip;            // 小费
    private String acceptance_student_number;//接单人学号
    private double score;             //评分
    private String state;          //状态
    private int MAX_SCORE = 5;

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getRelease_student_number() {
        return release_student_number;
    }

    public void setRelease_student_number(String release_student_number) {
        this.release_student_number = release_student_number;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }

    public double getTip() {
        return tip;
    }

    public void setTip(double tip) {
        this.tip = tip;
    }

    public String getAcceptance_student_number() {
        return acceptance_student_number;
    }

    public void setAcceptance_student_number(String acceptance_student_number) {
        this.acceptance_student_number = acceptance_student_number;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


    public boolean check(String method) {
        switch (method) {
            case OrderServlet.RELEASE_ORDER:
                return release_student_number != null
                        && release_student_number.length() > 0
                        && location != null && location.length() > 0
                        && content != null && content.length() > 0
                        && lable != null && lable.length() > 0;
            case OrderServlet.ACCEPT_ORDER:
                return acceptance_student_number != null && acceptance_student_number.length() > 0
                        &&order_id > 0;
            case OrderServlet.UPDATE_SCORE:
                return release_student_number != null && release_student_number.length() > 0
                        && order_id >= 0
                        && score >= 0 && score <= MAX_SCORE;
        }

        return false;
    }
}
