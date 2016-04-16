package bean;

/**
 * Created by falling on 2016/3/11.
 */
public class UserBean {
    private int user_id;              //ID
    private String student_number;    //学号
    private String student_name;      //姓名
    private String password;          //密码
    private String sex;               //性别
    private String cell_phone;        //联系方式
    private String school;            //所属学校
    private double average_score;     //平均评分
    private int amount;               //任务数量

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getStudent_number() {
        return student_number;
    }

    public void setStudent_number(String student_number) {
        this.student_number = student_number;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCell_phone() {
        return cell_phone;
    }

    public void setCell_phone(String cell_phone) {
        this.cell_phone = cell_phone;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public double getAverage_score() {
        return average_score;
    }

    public void setAverage_score(double average_score) {
        this.average_score = average_score;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }


    /**
     * 判断注册信息是否完整
     *
     * @return
     */
    public boolean check() {

        return student_number != null
                && student_number.length() > 0
                && student_name != null
                && student_name.length() > 0
                && password != null
                && password.length() > 0
                && sex != null && sex.length() > 0
                && cell_phone != null && cell_phone.length() > 0
                && school != null && school.length() > 0;
    }
}
