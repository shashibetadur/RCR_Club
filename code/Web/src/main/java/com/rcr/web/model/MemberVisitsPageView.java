package com.rcr.web.model;

import com.rcr.domain.MemberVisit;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MemberVisitsPageView {

    private Date visitDate;

    private List<MemberVisit> memberVisit = new ArrayList<MemberVisit>();

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public List<MemberVisit> getMemberVisit() {
        return memberVisit;
    }

    public void setMemberVisit(List<MemberVisit> memberVisit) {
        this.memberVisit = memberVisit;
    }

    public List<MemberVisit> getVisitList() {
        for (MemberVisit visit : memberVisit) {
            visit.setVisitDate(visitDate);
        }
        return memberVisit;
    }
}
