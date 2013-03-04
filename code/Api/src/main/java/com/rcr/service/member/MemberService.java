package com.rcr.service.member;


import com.rcr.domain.*;

import java.util.Date;
import java.util.List;

public interface MemberService {

    void saveMemberDetails(Member member);

    Member getMemberDetails(long memberId);

    List<MembershipType> lisMembershipTypes();

    MembershipType getMembershipTypeDetails(long id);

    void saveMembershipTypeDetails(MembershipType membershipType);

    void deleteMembershipTypeDetails(MembershipType membershipType);

    MembershipDetails getMembershipDetails(long memberId);

    MemberSummary getMemberSummary(long memberId);

    void renewMembership(MembershipDetail membershipDetail);

    MembershipDetail getMembershipDetail(long id);

    Date getRenewalDate(long memberId);
}
