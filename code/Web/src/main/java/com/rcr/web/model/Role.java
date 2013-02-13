package com.rcr.web.model;


import com.rcr.web.Operation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Role {

    public static Role ADMIN = new Role(Arrays.asList(Operation.ALL));
    public static Role OPERATOR = new Role(Arrays.asList(Operation.ALL));

    private List<String> operations = new ArrayList<String>();

    public Role(List<String> operations) {
        this.operations = operations;
    }

    public List<String> getOperations() {
        return operations;
    }
}
