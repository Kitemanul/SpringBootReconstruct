package com.graduationrecons.Dao.Temperature;

import com.graduationrecons.POJO.WorkShop;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Mapper
@Repository
public interface TemperatureMapper {

    List<WorkShop> SearchTemperature(@Param("map") HashMap<String, String> map);

    List<WorkShop> SearchErrorTemperature(@Param("map") HashMap<String, String> map);
}
