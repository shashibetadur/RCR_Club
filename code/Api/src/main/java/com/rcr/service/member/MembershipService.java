package com.rcr.service.member;

import com.rcr.domain.account.MembershipPayment;
import com.rcr.domain.account.Payment;
import com.rcr.domain.member.MembershipDetail;
import com.rcr.domain.member.MembershipDetails;
import com.rcr.domain.member.MembershipType;

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

    List<Payment> getMembershipPaymentDetails(long memberId);
}
