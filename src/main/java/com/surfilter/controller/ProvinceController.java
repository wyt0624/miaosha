package com.surfilter.controller;

import com.surfilter.error.BusinessException;
import com.surfilter.error.EmBusinessError;
import com.surfilter.response.CommonReturnType;
import com.surfilter.util.MyApplicationRunner;
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
