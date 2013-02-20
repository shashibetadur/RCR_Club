package com.rcr.service.member;


import com.rcr.domain.Member;
import com.rcr.domain.MembershipType;

import java.util.List;

public interface MemberService {

    void saveMemberDetails(Member member);

    Member getMemberDetails(long memberId);

    List<MembershipType> lisMembershipTypes();

    MembershipType getMembershipTypeDetails(long id);

    void saveMembershipTypeDetails(MembershipType membershipType);

    void deleteMembershipTypeDetails(MembershipType membershipType);
}
