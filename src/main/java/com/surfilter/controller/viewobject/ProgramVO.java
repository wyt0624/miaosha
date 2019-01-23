package com.surfilter.controller.viewobject;

public class ProgramVO {

    //程序id
    private Integer id;

    //程序名称
    private String program;

    //所属部门
    private String department;

    //所属省份
    private String province;

    //服务器地址
    private String ipAddress;

    //异常日志
    private String exceptionLogs;

    //异常日志类型
    private String exceptionLogsType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getExceptionLogs() {
        return exceptionLogs;
    }

    public void setExceptionLogs(String exceptionLogs) {
        this.exceptionLogs = exceptionLogs;
    }

    public String getExceptionLogsType() {
        return exceptionLogsType;
    }

    public void setExceptionLogsType(String exceptionLogsType) {
        this.exceptionLogsType = exceptionLogsType;
    }
}
