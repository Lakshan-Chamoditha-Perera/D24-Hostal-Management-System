package com.example.hms.dao;

import com.example.hms.dao.util.DaoTypes;

public class DaoFactory {
    private static DaoFactory instance;

    public static DaoFactory getDaoFactory() {
        return instance == null ? instance = new DaoFactory() : instance;
    }

    private DaoFactory(){
    }

    public <T> SuperDao getDao(DaoTypes type){
        return null;
    }
}
