package com.rcr.domain;

import java.util.ArrayList;
import java.util.List;

public class MembershipDetails {

    private List<MembershipDetail> membershipDetailList = new ArrayList<MembershipDetail>();

    public MembershipDetails(List<MembershipDetail> membershipDetailList) {
        this.membershipDetailList = membershipDetailList;
    }

    public List<MembershipDetail> getMembershipDetailList() {
        return membershipDetailList;
    }
}
