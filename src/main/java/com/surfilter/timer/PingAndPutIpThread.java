package com.surfilter.timer;


import com.surfilter.dataobject.ProgramDO;
import org.jboss.logging.Logger;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class PingAndPutIpThread extends  Thread {
    public static Logger log = Logger.getLogger(PingAndPutIpThread.class);
    private TaskArrayQueue<ProgramDO> taskArrayQueue;
    private List<ProgramDO> failProgramList;
    private List<ProgramDO> programDOS;
    private CountDownLatch latch;

    public PingAndPutIpThread(TaskArrayQueue<ProgramDO> taskArrayQueue, List<ProgramDO> programDOS, CountDownLatch latch) {
        this.taskArrayQueue=taskArrayQueue;
        this.programDOS=programDOS;
        this.latch = latch;
    }

    @Override
    public void run() {
        // 超时应该在3秒以上
        int timeOut = 3000;
        boolean status = false;
        try {
            for (ProgramDO programDO : programDOS) {
                status = InetAddress.getByName(programDO.getIpAddress()).isReachable(timeOut);
                if (status == false) {
                    log.info("to ping success ip: "+programDO.getIpAddress());
                    taskArrayQueue.push(programDO);
                    failProgramList.add(programDO);
                }
                Thread.sleep(10);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally{
            latch.countDown();//线程完毕，计数器减一
        }
    }

}
