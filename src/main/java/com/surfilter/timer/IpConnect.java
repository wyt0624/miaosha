package com.surfilter.timer;

import com.surfilter.dao.ProgramDOMapper;
import com.surfilter.dataobject.ProgramDO;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class IpConnect {
    private static List<ProgramDO> successProgramList = new ArrayList<>();
    private static List<ProgramDO> failProgramList = new ArrayList<>();

    @Autowired
    private ProgramDOMapper programDOMapper;

    private List<ProgramDO> connectIpServer(){
        int timeOut = 3000;
        boolean status = false;
        int count = 0;
        List<ProgramDO> programDOList = programDOMapper.selectAllProgram();
        int total = programDOList.size();
        for(ProgramDO programDO : programDOList){

            try {
                status = InetAddress.getByName(programDO.getIpAddress()).isReachable(timeOut);
                if (status == false){
                    failProgramList.add(programDO);
                    count++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }
 }
