package com.surfilter.service.model;

public class ProgramModel {
    private Integer id;

    private String program;

    private Integer provinceId;

    private Integer department;

    private String ipAddress;

    private String province;

    private String exceptionLogs;

    private String exceptionLogsType;

    private String exceptionType;

    private String fetchTime;

    private String logsFrom;

    private String logsType;

    private String exception;




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

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Integer getDepartment() {
        return department;
    }

    public void setDepartment(Integer department) {
        this.department = department;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
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

    public String getExceptionType() {
        return exceptionType;
    }

    public void setExceptionType(String exceptionType) {
        this.exceptionType = exceptionType;
    }

    public String getFetchTime() {
        return fetchTime;
    }

    public void setFetchTime(String fetchTime) {
        this.fetchTime = fetchTime;
    }

    public String getLogsFrom() {
        return logsFrom;
    }

    public void setLogsFrom(String logsFrom) {
        this.logsFrom = logsFrom;
    }

    public String getLogsType() {
        return logsType;
    }

    public void setLogsType(String logsType) {
        this.logsType = logsType;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }
}
