package com.adobe.prj.client;

import com.adobe.prj.dao.MobileDao;
import com.adobe.prj.dao.MobileDaoDbImpl;
import com.adobe.prj.dao.MobileDaoFactory;
import com.adobe.prj.dao.MobileDaoMongoImpl;
import com.adobe.prj.entity.Mobile;

public class MobileClient {
    public static void main(String[] args) {
        Mobile m = new Mobile(34, "Samsung", 9000.00, "4G");
//        MobileDao mobileDao = new MobileDaoDbImpl();  // program to interface
//        MobileDao mobileDao = new MobileDaoMongoImpl();
        MobileDao mobileDao = MobileDaoFactory.getMobileDao();
        mobileDao.addMobile(m);
    }
}
