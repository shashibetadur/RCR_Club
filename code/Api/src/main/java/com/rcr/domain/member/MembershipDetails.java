package com.rcr.domain.member;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MembershipDetails {

    private List<MembershipDetail> membershipDetailList = new ArrayList<MembershipDetail>();

    public MembershipDetails(List<MembershipDetail> membershipDetailList) {
        this.membershipDetailList = membershipDetailList;
    }

    public List<MembershipDetail> getMembershipDetailList() {
        return membershipDetailList;
    }

    public MembershipDetail getCurrentMembership() {
        Date renewalDate = null;
        MembershipDetail currentMembershipDetail = null;
        for (MembershipDetail membershipDetail : getMembershipDetailList()) {
            if (renewalDate == null) {
                renewalDate = membershipDetail.getEndDate();
                currentMembershipDetail = membershipDetail;
            } else if (renewalDate.compareTo(membershipDetail.getEndDate()) < 0) {
                renewalDate = membershipDetail.getEndDate();
                currentMembershipDetail = membershipDetail;
            }
        }
        return currentMembershipDetail;
    }

    public double getMembershipAmount() {
        double amount = 0;
        for (MembershipDetail detail : membershipDetailList) {
            amount += detail.getFees();
            amount += detail.getEnrollmentFees();
        }
        return amount;
    }
}
