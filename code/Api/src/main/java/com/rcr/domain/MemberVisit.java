package com.rcr.domain;

import java.util.Date;

public class MemberVisit extends Entity {

    private long memberId;

    private long guestCount;

    private Date visitDate;

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public long getGuestCount() {
        return guestCount;
    }

    public void setGuestCount(long guestCount) {
        this.guestCount = guestCount;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }
}
