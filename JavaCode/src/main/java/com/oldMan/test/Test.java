package com.oldMan.test;

import com.oldMan.bean.Old;
import com.oldMan.dao.HealthDocDao;
import com.oldMan.dao.OldDao;
import com.oldMan.dao.PreRegisterUserDao;

/**
 * @author XiaLuo
 * @version 1.0
 * @date 2024/1/8 09:07
 */
public class Test {
    public static void main(String[] args) {
        PreRegisterUserDao dao = new PreRegisterUserDao();
        System.out.println(dao.getAllPreUserList().toString());

    }
}
