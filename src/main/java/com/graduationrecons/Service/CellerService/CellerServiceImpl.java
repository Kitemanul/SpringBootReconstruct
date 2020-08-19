package com.graduationrecons.Service.CellerService;

import com.graduationrecons.Dao.Celler.CellerMapper;
import com.graduationrecons.POJO.CellerInOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CellerServiceImpl implements CellerService {

    @Autowired
    CellerMapper cellerMapper;

    public void setCellerMapper(CellerMapper cellerMapper) {
        this.cellerMapper = cellerMapper;
    }

    @Override
    public int DeleteCeller(CellerInOut outceller) {

        return cellerMapper.DeleteCeller(outceller);
    }

    @Override
    public int EditCeller(CellerInOut outceller, CellerInOut newceller) {

        return cellerMapper.EditCeller(outceller,newceller);
    }

    @Override
    public int AddCeller(CellerInOut celler) {

        return cellerMapper.AddCeller(celler);
    }

    @Override
    public List<CellerInOut> SearchCeller(CellerInOut celler) {

        return cellerMapper.SelectAllCeller(celler);
    }
}
