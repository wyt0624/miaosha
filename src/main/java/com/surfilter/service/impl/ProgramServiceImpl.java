package com.surfilter.service.impl;

import com.alibaba.druid.support.json.JSONParser;
import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.surfilter.dao.ProgramDOMapper;
import com.surfilter.dataobject.ProgramDO;
import com.surfilter.dataobject.ProvinceDO;
import com.surfilter.error.BusinessException;
import com.surfilter.error.EmBusinessError;
import com.surfilter.service.ProgramService;
import com.surfilter.service.model.ProgramModel;
import com.surfilter.util.MyApplicationRunner;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProgramServiceImpl implements ProgramService {

    @Autowired
    private ProgramDOMapper programDOMapper;


    @Override
    //根据省份信息获取程序列表
    public List<ProgramModel> getProgramList(Integer provinceId) throws BusinessException {

        List<ProgramDO> programDOList = programDOMapper.selectByProvinceId(provinceId);

        //lambda表达式 领域模型转换
        List<ProgramModel> programModelList = programDOList.stream().map(programDO -> {
            ProvinceDO provinceDO = MyApplicationRunner.provinceList.get(provinceId-1);
            ProgramModel programModel = convertModelFromDataObject(programDO,provinceDO);
            return programModel;
        }).collect(Collectors.toList());
        return programModelList;

    }

    @Override
    //根据省份获取程序总数
    public int getProgramTotal(Integer provinceId) {
        try {
            int num = programDOMapper.selectTotalByProvinceId(provinceId);
            return num;
        } catch (Exception e){
            e.printStackTrace();
            return 0;
        }

    }

    @Override
    public void addProgram(ProgramDO programDO) throws BusinessException {
        try {
            programDOMapper.insertSelective(programDO);

        } catch (Exception e){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"添加失败");
        }
    }

    @Override
    public void deleteProgram(Integer id) throws BusinessException {
        try {
            programDOMapper.deleteByPrimaryKey(id);
        } catch (Exception e){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"删除失败");
        }

    }



    @Override
    public List<ProgramModel> getLogs(String id, String province_id) throws BusinessException, UnknownHostException {
//        String statementField = "logsType";
//
//
//
//        TransportClient client = null;
//        //创建es客户端
//        PreBuiltTransportClient preBuiltTransportClient = new PreBuiltTransportClient(Settings.EMPTY);
//        client = preBuiltTransportClient.addTransportAddress(new TransportAddress(InetAddress.getByName("173.16.88.16"), 9300));
//        //创建并发送es请求
//        SearchResponse response = client.prepareSearch(province_id)//索引名称 省份编号
//                .setTypes("doc") //type类型，固定doc
//                .setRouting(id)//routing值，产品（程序）编号
//                .setQuery(QueryBuilders.boolQuery().filter(QueryBuilders.termQuery(statementField,id)))
//                .setExplain(true)
//                .addSort("fetchTime", SortOrder.DESC)
//                .execute().actionGet();
//
//
//        List<String> jsonList = new ArrayList<String>();
//        SearchHits hits = response.getHits();
//        for (SearchHit hit: hits) {
//            String  sourceAsString = hit.getSourceAsString();
//            jsonList.add(sourceAsString);
//        }
//        System.out.println("data size => " + jsonList.size());
//        for (String s: jsonList) {
//            System.out.println(s);
//        }
        List<String> list = new ArrayList<>();
        list.add("{\"fetchTime\":\"2019-01-23 10:13:36\",\"logsFrom\":\"1000\",\"logsType\":\"10000\",\"exceptionType\":\"java.net.UnknownHostException\",\"exception\":\"java.net.UnknownHost,: 173.16.21.2001\\n\\tat java.net.AbstractPlainSocketImpl.connect(AbstractPlainSocketImpl.java:178)\\n\\tat java.net.SocksSocketImpl.connect(SocksSocketImpl.java:392)\\n\\tat java.net.Socket.connect(Socket.java:579)\\n\\tat sun.net.NetworkClient.doConnect(NetworkClient.java:175)\\n\\tat sun.net.www.http.HttpClient.openServer(HttpClient.java:432)\\n\\tat sun.net.www.http.HttpClient.openServer(HttpClient.java:527)\\n\\tat sun.net.www.http.HttpClient.<init>(HttpClient.java:211)\\n\\tat sun.net.www.http.HttpClient.New(HttpClient.java:308)\\n\\tat sun.net.www.http.HttpClient.New(HttpClient.java:326)\\n\\tat sun.net.www.protocol.http.HttpURLConnection.getNewHttpClient(HttpURLConnection.java:997)\\n\\tat sun.net.www.protocol.http.HttpURLConnection.plainConnect(HttpURLConnection.java:933)\\n\\tat sun.net.www.protocol.http.HttpURLConnection.connect(HttpURLConnection.java:851)\\n\\tat com.surfilter.core.bb.util.HttpUtil.doPost(HttpUtil.java:54)\\n\\tat com.surfilter.core.bb.send.impl.FileSender$1.run(FileSender.java:76)\\n\\tat java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1145)\\n\\tat java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:615)\\n\\tat java.lang.Thread.run(Thread.java:745) \"}");
        list.add("{\"fetchTime\":\"2019-01-23 10:13:36\",\"logsFrom\":\"1000\",\"logsType\":\"10000\",\"exceptionType\":\"java.net.UnknownHostException\",\"exception\":\"java.net.UnknownHost,: 173.16.21.2001\\n\\tat java.net.AbstractPlainSocketImpl.connect(AbstractPlainSocketImpl.java:178)\\n\\tat java.net.SocksSocketImpl.connect(SocksSocketImpl.java:392)\\n\\tat java.net.Socket.connect(Socket.java:579)\\n\\tat sun.net.NetworkClient.doConnect(NetworkClient.java:175)\\n\\tat sun.net.www.http.HttpClient.openServer(HttpClient.java:432)\\n\\tat sun.net.www.http.HttpClient.openServer(HttpClient.java:527)\\n\\tat sun.net.www.http.HttpClient.<init>(HttpClient.java:211)\\n\\tat sun.net.www.http.HttpClient.New(HttpClient.java:308)\\n\\tat sun.net.www.http.HttpClient.New(HttpClient.java:326)\\n\\tat sun.net.www.protocol.http.HttpURLConnection.getNewHttpClient(HttpURLConnection.java:997)\\n\\tat sun.net.www.protocol.http.HttpURLConnection.plainConnect(HttpURLConnection.java:933)\\n\\tat sun.net.www.protocol.http.HttpURLConnection.connect(HttpURLConnection.java:851)\\n\\tat com.surfilter.core.bb.util.HttpUtil.doPost(HttpUtil.java:54)\\n\\tat com.surfilter.core.bb.send.impl.FileSender$1.run(FileSender.java:76)\\n\\tat java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1145)\\n\\tat java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:615)\\n\\tat java.lang.Thread.run(Thread.java:745) \"}");

        List<ProgramModel> programModelList = new ArrayList<>();
        ProgramModel programModel = new ProgramModel();
        for(int i=0;i<list.size();i++){
            programModel = JSONObject.parseObject(list.get(i),ProgramModel.class);
            programModelList.add(programModel);
        }


        return programModelList;


    }

    @Override
    public List<ProgramDO> selectAllProgram() {
        List<ProgramDO> programDOList = programDOMapper.selectAllProgram();
        if (programDOList == null){
            return null;
        }
        return programDOList;
    }

    private ProgramModel convertModelFromDataObject(ProgramDO programDO, ProvinceDO provinceDO){
        ProgramModel programModel = new ProgramModel();
        BeanUtils.copyProperties(programDO,programModel);
        programModel.setProvince(provinceDO.getProvince());
        return programModel;
    }

}
