package com.rcr.service;


import com.rcr.domain.Parameters;
import com.rcr.domain.member.ParameterConfiguration;

import java.util.List;

public interface ParameterService {

    public Parameters getParameterValue(String parameterName);

    public ParameterConfiguration getAll();

    void saveParameterConfigurations(ParameterConfiguration parameterConfiguration);
}
