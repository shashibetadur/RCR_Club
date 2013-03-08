package com.rcr.service.member;

import com.rcr.domain.MembershipDetail;
import com.rcr.domain.MembershipDetails;
import com.rcr.domain.MembershipType;

import java.util.Date;
import java.util.List;

public interface MembershipService {

    List<MembershipType> lisMembershipTypes();

    MembershipType getMembershipTypeDetails(long id);

    void saveMembershipTypeDetails(MembershipType membershipType);

    void deleteMembershipTypeDetails(MembershipType membershipType);

    MembershipDetails getMembershipDetails(long memberId);

    void renewMembership(MembershipDetail membershipDetail);

    MembershipDetail getMembershipDetail(long id);

    Date getRenewalDate(long memberId);

}
