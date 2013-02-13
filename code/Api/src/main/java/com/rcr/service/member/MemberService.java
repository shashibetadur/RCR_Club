package com.rcr.service.member;


import com.rcr.domain.Member;

public interface MemberService {

    void saveMemberDetails(Member member);

    Member getMemberDetails(long memberId);
}
