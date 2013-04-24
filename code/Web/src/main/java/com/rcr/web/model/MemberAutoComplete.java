package com.rcr.web.model;

import com.rcr.domain.member.Member;

public class MemberAutoComplete {

    private Member member;

    public MemberAutoComplete(Member member) {
        this.member = member;
    }

    public long getId() {
        return member.getId();
    }

    public String getValue() {
        return member.getPersonalDetails().getFirstName() + " " + member.getPersonalDetails().getLastName() + ", " + member.getPersonalDetails().getPhoneListAsString();
    }

    public Member getMember() {
        return member;
    }
}
