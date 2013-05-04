package com.rcr.service.member;


import com.rcr.domain.member.Member;
import com.rcr.domain.member.MemberSearchCriteria;
import com.rcr.domain.member.MemberSummary;

import java.util.List;

public interface MemberService {

    void saveMemberDetails(Member member);

    Member getMemberDetails(long memberId);

    List<Member> search(MemberSearchCriteria memberSearchCriteria);
}
