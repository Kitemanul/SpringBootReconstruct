package com.graduationrecons.Controller.TemperatureController;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.graduationrecons.POJO.CellerInOut;
import com.graduationrecons.POJO.WorkShop;
import com.graduationrecons.Service.TemperatureService.TemperatureService;
import com.graduationrecons.Util.DateUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/Temperature")
@Slf4j
@Api(value = "温度数据管理类")
public class TemperatureController {

    @Autowired
    TemperatureService temperatureService;

    @PostMapping("/SearchTemperature")
    @ResponseBody
    @ApiOperation("查找温度数据")
    public String SearchTempeartureData(CellerInOut celler, int rate)
    {
        List<WorkShop> res=temperatureService.SearchTempearture(rate,celler);
        JSONArray jsonArray=new JSONArray();
        if(res.size()!=0)
        {
            for(WorkShop t:res)
            {
                JSONObject jsonObject=new JSONObject();
                jsonObject.put("时间", DateUtils.Date2String(t.getTime()));
                jsonObject.put("温度",t.getTemperature(celler.getJarid()));
                jsonObject.put("罐号",celler.getJarid());
                jsonObject.put("组号",celler.getGroupid());
                jsonObject.put("周期",celler.getPeriod());
                jsonObject.put("变化率",t.getRate());
                jsonArray.add(jsonObject);

            }
        }
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("data",jsonArray);
        return jsonObject.toString();
    }

    @PostMapping("/Compare")
    @ResponseBody
    @ApiOperation("温度比较数据")
    public String Conparedata(@RequestBody List<CellerInOut> cellers)
    {
        log.info(cellers.toString());

        List<WorkShop> res1=temperatureService.SearchTempearture(cellers.get(0).getRate(),cellers.get(0));
        List<WorkShop> res2=temperatureService.SearchTempearture(cellers.get(1).getRate(),cellers.get(1));

        JSONArray jsonArray=new JSONArray();
        for(WorkShop temperature:res1)
        {
            JSONObject jsonObject = new JSONObject();
            // 添加键值对
            jsonObject.put("分类", "1组");
            jsonObject.put("温度",temperature.getTemperature(cellers.get(0).getJarid()));
            jsonObject.put("变化率",temperature.getRate());
            jsonArray.add(jsonObject);
        }

        for(WorkShop temperature:res2) {

            JSONObject jsonObject = new JSONObject();
            // 添加键值对
            jsonObject.put("分类", "2组");
            jsonObject.put("温度", temperature.getTemperature(cellers.get(0).getJarid()));
            jsonObject.put("变化率", temperature.getRate());
            jsonArray.add(jsonObject);
        }

        log.info("ok");
        return jsonArray.toString();
    }


    @PostMapping("/ErrorTemperatureSearch")
    @ResponseBody
    @ApiOperation("查找错误温度数据")
    public String SearchErrorData(CellerInOut celler)
    {
        WorkShop workShop=new WorkShop();
        workShop.setJarid("罐"+celler.getJarid());
        workShop.setGroupid(Integer.valueOf(celler.getGroupid()));
        List<WorkShop> list=temperatureService.SearchErrorTempearture(workShop);

        JSONArray jsonArray=new JSONArray();
        for(WorkShop t:list)
        {
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("时间", DateUtils.Date2String(t.getTime()));
            jsonObject.put("组号",celler.getGroupid());
            jsonObject.put("罐号",celler.getJarid());
            jsonObject.put("温度",t.getTemperature(celler.getJarid()));
            jsonArray.add(jsonObject);
        }

        JSONObject jsonObject=new JSONObject();
        jsonObject.put("data",jsonArray);
        return jsonObject.toString();
    }

}
