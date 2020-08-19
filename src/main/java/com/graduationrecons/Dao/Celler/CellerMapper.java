package com.graduationrecons.Dao.Celler;


import com.graduationrecons.POJO.CellerInOut;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CellerMapper {

    @Insert("Insert into 出窖入窖 (时间,入窖时间,出窖时间,周期,预期时间,组号,罐号) values(#{time},#{intime},#{outtime},#{period},#{Etime},#{groupid},#{jarid})")
    public int AddCeller(CellerInOut celler);

    @Delete("delete from 出窖入窖  where 时间=#{time} and 入窖时间=#{intime} and 出窖时间=#{outtime} and 组号=#{groupid} and 罐号=#{jarid} and 周期=#{period}")
    public int DeleteCeller(CellerInOut celler);

    @Update("update  出窖入窖 set 时间=#{newceller.time},周期=#{newceller.period},出窖时间=#{newceller.outtime},组号=#{newceller.groupid},罐号=#{newceller.jarid} where 时间=#{oldceller.time} and 入窖时间=#{oldceller.intime} and 出窖时间=#{oldceller.outtime} and 组号=#{oldceller.groupid} and 罐号=#{oldceller.jarid} and 周期=#{oldceller.period}")
    public int EditCeller(@Param("oldceller") CellerInOut oudceller, @Param("newceller") CellerInOut newceller);


    public List<CellerInOut> SelectAllCeller(CellerInOut celler);
}
