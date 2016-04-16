package dao.Impl;

import bean.LocationBean;
import dao.DAO;

import java.util.List;

/**
 * Created by falling on 2016/4/16.
 */
public class LocationDAOImpl extends DAO<LocationBean>{
    public List<LocationBean> getLocationList(String school){
        String sql = "select * from schoolLocation where school = ?";
        return getForList(sql,school);
    }
}
