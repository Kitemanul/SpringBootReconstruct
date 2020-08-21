package com.graduationrecons.Service.CellerService;

import com.graduationrecons.Dao.Celler.CellerMapper;
import com.graduationrecons.POJO.CellerInOut;
import javafx.scene.chart.ValueAxis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
    @CacheEvict(value = "Cellerdata",allEntries = true)
    public int DeleteCeller(CellerInOut outceller) {

        return cellerMapper.DeleteCeller(outceller);
    }

    @Override
    @CacheEvict(value = "Cellerdata",allEntries = true)
    public int EditCeller(CellerInOut outceller, CellerInOut newceller) {

        return cellerMapper.EditCeller(outceller,newceller);
    }

    @Override
    @CacheEvict(value = "Cellerdata",allEntries = true)
    public int AddCeller(CellerInOut celler) {

        return cellerMapper.AddCeller(celler);
    }

    @Override
    @Cacheable(value = "Cellerdata")
    public List<CellerInOut> SearchCeller(CellerInOut celler) {

        return cellerMapper.SelectAllCeller(celler);
    }
}
