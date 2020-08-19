package com.graduationrecons;

import com.graduationrecons.Dao.Celler.CellerMapper;
import com.graduationrecons.POJO.CellerInOut;
import com.graduationrecons.Service.CellerService.CellerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest
class GraduationreconsApplicationTests {


    @Autowired
    CellerService cellerService;
    @Autowired
    CellerMapper cellerMapper;

    @Test
    void contextLoads() throws SQLException{

        CellerInOut celler=new CellerInOut();

        celler.setPeriod(-1);
        celler.setJarid(0);
        celler.setGroupid(0);


        List<CellerInOut> list=cellerService.SearchCeller(celler);
        return;





    }

}
