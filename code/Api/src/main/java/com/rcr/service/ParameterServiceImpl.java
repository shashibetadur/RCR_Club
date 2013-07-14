package com.rcr.service;

import com.rcr.domain.Parameters;
import com.rcr.domain.member.ParameterConfiguration;
import com.rcr.repository.ParameterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ParameterServiceImpl implements ParameterService{

    private ParameterRepository parameterRepository;

    @Autowired
    public ParameterServiceImpl(ParameterRepository parameterRepository){
        this.parameterRepository = parameterRepository;
    }

    public Parameters getParameterValue(String parameterName){
        return parameterRepository.getParameterValue(parameterName);
    }

    @Override
    public ParameterConfiguration getAll() {
        return new ParameterConfiguration(parameterRepository.getAll());
    }

    @Override
    public void saveParameterConfigurations(ParameterConfiguration parameterConfiguration) {
        Map<Long, Parameters> parameterMap = new HashMap<Long, Parameters>();
        for (Parameters parameter : parameterConfiguration.getParameterList()) {
            parameterMap.put(parameter.getId(), parameter);
        }
        //update existing
        for (Parameters parameter : parameterRepository.getAll()) {
            if (parameterMap.containsKey(parameter.getId())) {
                parameter.copyFrom(parameterMap.get(parameter.getId()));
                parameterMap.remove(parameter.getId());
            } else {
                parameter.setDeleteFlag('Y');
            }
            parameterRepository.save(parameter);
        }

        //create new
        for (Long parameterId : parameterMap.keySet()) {
            parameterRepository.save(parameterMap.get(parameterId));
        }
    }
}
