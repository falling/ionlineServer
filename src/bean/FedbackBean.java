package bean;

import java.util.Date;

/**
 * Created by falling on 2016/4/16.
 */
public class FedbackBean {
    private int id;
    private String student_number;
    private String content;
    private Date time;


    public FedbackBean(String student_number, String content) {
        this.student_number = student_number;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getStudent_number() {
        return student_number;
    }

    public void setStudent_number(String student_number) {
        this.student_number = student_number;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
