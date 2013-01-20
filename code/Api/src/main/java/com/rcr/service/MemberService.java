package com.rcr.service;


import com.rcr.domain.Member;

public interface MemberService {

    void saveMemberDetails(Member member);

    Member getMemberDetails(long memberId);
}
