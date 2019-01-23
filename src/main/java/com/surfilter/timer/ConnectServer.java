package com.surfilter.timer;

import com.surfilter.dao.ProgramDOMapper;
import com.surfilter.dataobject.ProgramDO;
import com.surfilter.service.ProgramService;
import com.surfilter.timer.PingAndPutIpThread;
import com.surfilter.timer.TaskArrayQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;


public class ConnectServer {

    @Autowired
    private ProgramService programService;

    private static final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(15,30, 5, TimeUnit.SECONDS,new SynchronousQueue<>());


    public void pingServer(){

    }



    public List<ProgramDO> startIpServer() {
        TaskArrayQueue<ProgramDO> taskArrayQueue = new TaskArrayQueue<>();
        List<ProgramDO> programDOList = programService.selectAllProgram();
        int ipcount = programDOList.size();
        if (ipcount == 0) {
            return null;
        }
        // 线程数
        int threadNum = programDOList.size();
        // 每个线程处理的数量
        long count = 1;
        if (ipcount > threadNum) {
            count = Math.round(Math.ceil(1.00 * ipcount / threadNum));
        }
        CountDownLatch latch = new CountDownLatch(threadNum);
        List<ProgramDO> programDOS = new ArrayList<>();
        for (int i = 1; i <= programDOList.size(); i++) {
            programDOS.add(programDOList.get(i - 1));
            if (i % count == 0) {
                PingAndPutIpThread pingAndPutIpThread = new PingAndPutIpThread(taskArrayQueue, programDOS, latch);
                pingAndPutIpThread.start();
                programDOS = new ArrayList<>();
            }
        }
        if (programDOS != null && programDOS.size() > 0) {
            PingAndPutIpThread pingAndPutIpThread = new PingAndPutIpThread(taskArrayQueue, programDOS, latch);
            pingAndPutIpThread.start();
        }
        try {
            latch.await(); // 等待所有完成工作
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (taskArrayQueue.getSize() > 0) {
            ProgramDO programDO = taskArrayQueue.pop();
            programDOList.add(programDO);
        }
        return programDOList;
    }



}
