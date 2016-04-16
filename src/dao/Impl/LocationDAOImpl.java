package dao.Impl;

import bean.LocationBean;
import dao.DAO;

import java.util.List;

/**
 * Created by falling on 2016/4/16.
 */
public class LocationDAOImpl extends DAO<LocationBean>{

    /**
     * 根据学校获取该学校的地点信息
     * @param school
     * @return 学校的地点信息
     */
    public List<LocationBean> getLocationList(String school){
        String sql = "select * from schoolLocation where school = ?";
        return getForList(sql,school);
    }
}
