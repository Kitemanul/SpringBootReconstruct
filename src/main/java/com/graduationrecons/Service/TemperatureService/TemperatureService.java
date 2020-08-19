package com.graduationrecons.Service.TemperatureService;

import com.graduationrecons.POJO.CellerInOut;
import com.graduationrecons.POJO.WorkShop;

import java.util.List;

public interface TemperatureService {

    public List<WorkShop> SearchTempearture(int rate, CellerInOut in);

    public List<WorkShop> SearchErrorTempearture(WorkShop workShop);
}
