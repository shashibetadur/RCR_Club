package com.rcr.service.member;


import com.rcr.domain.Member;
import com.rcr.domain.MemberSearchCriteria;
import com.rcr.domain.MemberSummary;

import java.util.List;

public interface MemberService {

    void saveMemberDetails(Member member);

    Member getMemberDetails(long memberId);

    MemberSummary getMemberSummary(long memberId);

    List<Member> search(MemberSearchCriteria memberSearchCriteria);
}
