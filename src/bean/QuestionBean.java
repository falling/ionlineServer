package bean;

import java.util.Date;

/**
 * Created by falling on 2016/4/16.
 */
public class QuestionBean {
    private int id;
    private String student_number;
    private String question;
    private Date  time;

    public QuestionBean() {}

    public QuestionBean(String student_number, String question) {
        this.student_number = student_number;
        this.question = question;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudent_number() {
        return student_number;
    }

    public void setStudent_number(String student_number) {
        this.student_number = student_number;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
