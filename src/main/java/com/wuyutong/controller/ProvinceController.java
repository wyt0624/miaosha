package com.wuyutong.controller;

import com.wuyutong.error.BusinessException;
import com.wuyutong.error.EmBusinessError;
import com.wuyutong.response.CommonReturnType;
import com.wuyutong.util.MyApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/province")
@CrossOrigin(origins = {"*"},allowCredentials = "true")
public class ProvinceController extends BaseController {


    //省份展示
    @RequestMapping(value = "/getProvince",method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType getProvince() throws BusinessException{
        if (!MyApplicationRunner.provinceList.isEmpty()&&MyApplicationRunner.provinceList != null){
            return CommonReturnType.create(MyApplicationRunner.provinceList);
        } else {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"省份不存在，请检查数据库");
        }

    }

}
