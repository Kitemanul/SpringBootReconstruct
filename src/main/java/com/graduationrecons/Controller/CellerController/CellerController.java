package com.graduationrecons.Controller.CellerController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.graduationrecons.POJO.CellerInOut;
import com.graduationrecons.Service.CellerService.CellerService;
import com.graduationrecons.Util.DateUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@Api("窖池控制类")
@Slf4j
@RequestMapping("/Celler")
public class CellerController {

    @Autowired
    CellerService cellerService;


    @InitBinder
    protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @PostMapping("/CellerData.do")
    @ResponseBody
    @ApiOperation("请求窖池数据")
    public String SearchCellerData(CellerInOut celle)
    {
        List<CellerInOut> res=cellerService.SearchCeller(celle);
        JSONArray jsonArray=new JSONArray();

        if(res.size()==0)
        {

            return "没有搜索到数据";
        }
        else
        {
            for(CellerInOut celler:res)
            {
                JSONObject jsonObject=new JSONObject();
                jsonObject.put("时间", DateUtils.Date2String(celler.getTime()));
                jsonObject.put("入窖时间",DateUtils.Date2String(celler.getIntime()));
                jsonObject.put("出窖时间",DateUtils.Date2String(celler.getOuttime()));
                jsonObject.put("周期",celler.getPeriod());
                jsonObject.put("组号",celler.getGroupid());
                jsonObject.put("罐号",celler.getJarid());
                jsonArray.add(jsonObject);

            }

        }
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("data",jsonArray);
        return  jsonObject.toString();

    }

}
