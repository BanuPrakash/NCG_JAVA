package com.adobe.prj.dao;

import com.adobe.prj.entity.Mobile;

// Realization
public class MobileDaoDbImpl implements MobileDao{

    @Override
    public void addMobile(Mobile m) {
        System.out.println("SQL mobile insert ...");
    }
}
