package bean;

import java.util.Date;

/**
 * Created by falling on 2016/4/16.
 */
public class AnswerBean {
    private int id;
    private int question_id;
    private Date timel;
    private String student_number;
    private String content;

    public AnswerBean(){}

    public AnswerBean(int question_id, String studentNumber, String content) {
        this.question_id = question_id;
        this.student_number = studentNumber;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public Date getTimel() {
        return timel;
    }

    public void setTimel(Date timel) {
        this.timel = timel;
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
