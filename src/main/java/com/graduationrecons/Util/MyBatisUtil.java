package com.graduationrecons.Util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisUtil {

    private static SqlSessionFactory sqlSessionFactory;
    static {
        String name = "Mybatis-config.xml";

        try {
            InputStream in = Resources.getResourceAsStream(name);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }


    public  static SqlSession getSqlSession()
    {

        return sqlSessionFactory.openSession();
    }

}
