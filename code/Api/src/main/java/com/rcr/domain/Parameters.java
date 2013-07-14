package com.rcr.domain;

public class Parameters extends Entity{

    private String parameterName;
    private String parameterValue;

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public String getParameterValue() {
        return this.parameterValue;
    }

    public void setParameterValue(String parameterValue) {
        this.parameterValue = parameterValue;
    }

    public void copyFrom(Parameters parameter) {
        this.parameterName = parameter.getParameterName();
        this.parameterValue = parameter.getParameterValue();
    }
}
