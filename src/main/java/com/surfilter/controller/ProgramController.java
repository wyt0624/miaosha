package com.surfilter.controller;

import com.surfilter.controller.viewobject.ProgramVO;
import com.surfilter.dataobject.ProgramDO;
import com.surfilter.error.BusinessException;
import com.surfilter.response.CommonReturnType;
import com.surfilter.service.ProgramService;
import com.surfilter.service.model.ProgramModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.UnknownHostException;
import java.util.List;
import java.util.stream.Collectors;

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
        List<ProgramModel> programModelList = programService.getProgramList(provinceId);
        int programTotal = programService.getProgramTotal(provinceId);
        List<ProgramVO> programVOList = programModelList.stream().map(programModel -> {
            ProgramVO programVO = convertProgramVOFromProgramModel(programModel);
            return programVO;
        }).collect(Collectors.toList());

        return CommonReturnType.create(programVOList, 0, programTotal);
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
        programDO.setDepartment(department);

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


    /**
     * 获取程序日志
     */
    @RequestMapping(value = "/getLogs", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType getLogs(@RequestParam(name = "id") String id,@RequestParam(name = "province_id") String provinceId) throws BusinessException, UnknownHostException {
        List<ProgramModel> programModelList = programService.getLogs(id,provinceId);
        return CommonReturnType.create(programModelList);
    }



    private ProgramVO convertProgramVOFromProgramModel(ProgramModel programModel){
        if (programModel == null){
            return null;
        }
        ProgramVO programVO = new ProgramVO();
        BeanUtils.copyProperties(programModel,programVO);
        if (programModel.getDepartment() == 1){
            programVO.setDepartment("公安");
        } else if (programModel.getDepartment() == 2){
            programVO.setDepartment("网安");
        } else if (programModel.getDepartment() == 3){
            programVO.setDepartment("安全");
        }

        return programVO;
    }
}