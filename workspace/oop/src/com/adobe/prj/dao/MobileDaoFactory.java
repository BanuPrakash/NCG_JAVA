package com.adobe.prj.dao;

import java.util.ResourceBundle;

public class MobileDaoFactory {
    private static String CLAZZ = "";
    static  {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("config");
        CLAZZ = resourceBundle.getString("MOBILE_DAO");
    }

    // factory method
    public static MobileDao getMobileDao() {
//        return  new MobileDaoMongoImpl();
        try {
            return (MobileDao) Class.forName(CLAZZ).newInstance();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return  null;
    }

}
