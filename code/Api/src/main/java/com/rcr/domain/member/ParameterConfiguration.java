package com.rcr.domain.member;

import com.rcr.domain.Parameters;

import java.util.ArrayList;
import java.util.List;

public class ParameterConfiguration {

    List<Parameters> parameterList = new ArrayList<Parameters>();

    public ParameterConfiguration() {
    }

    public ParameterConfiguration(List<Parameters> parameterList) {
        this.parameterList.addAll(parameterList);
    }

    public List<Parameters> getParameterList() {
        return parameterList;
    }
}
