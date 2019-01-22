package com.wuyutong.controller;

import com.wuyutong.dataobject.ProgramDO;
import com.wuyutong.error.BusinessException;
import com.wuyutong.error.EmBusinessError;
import com.wuyutong.response.CommonReturnType;
import com.wuyutong.service.ProgramService;
import com.wuyutong.util.MyApplicationRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/program")
@CrossOrigin(origins = {"*"},allowCredentials = "true")
public class ProgramController extends BaseController {

    @Autowired
    private ProgramService programService;

    //省份展示
    @RequestMapping(value = "/getProgram", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType getProgress(@RequestParam(name = "province_id") Integer provinceId) throws BusinessException {
        List<ProgramDO> programDOList = new ArrayList<>();
        int programTotal = programService.getProgramTotal(provinceId);
        programDOList = programService.getProgramList(provinceId);

        return CommonReturnType.create(programDOList, 0, programTotal);
    }

    /**
     * 添加程序
     */
    @RequestMapping(value = "/addProgram", method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType getProgress(@RequestParam(name = "province_id") Integer provinceId, @RequestParam(name = "department") Integer department, @RequestParam(name = "program") String program, @RequestParam(name = "program") String ipAddress) throws BusinessException {

        ProgramDO programDO = new ProgramDO();
        programDO.setIpAddress(ipAddress);
        programDO.setProgram(program);
        programDO.setProvinceId(provinceId);
        programDO.setType(department);

        programService.addProgram(programDO);
        return CommonReturnType.create(null);
    }


    /**
     * 删除程序
     */
    @RequestMapping(value = "/deleteProgram", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType deleteProgress(@RequestParam(name = "id") Integer id) throws BusinessException {

        programService.deleteProgram(id);
        return CommonReturnType.create(null);
    }
}