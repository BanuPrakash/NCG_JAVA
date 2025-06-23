package com.adobe.prj.dao;

import com.adobe.prj.entity.Mobile;

import java.util.ResourceBundle;

public class MobileDaoMongoImpl implements MobileDao{

    @Override
    public void addMobile(Mobile m) {
        System.out.println("Mongo Store...");
    }
}
