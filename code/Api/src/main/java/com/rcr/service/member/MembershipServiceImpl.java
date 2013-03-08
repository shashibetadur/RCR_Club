package com.rcr.service.member;

import com.rcr.domain.MembershipDetail;
import com.rcr.domain.MembershipDetails;
import com.rcr.domain.MembershipType;
import com.rcr.repository.member.MembershipDetailRepository;
import com.rcr.repository.member.MembershipTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class MembershipServiceImpl implements MembershipService {

    private MembershipTypeRepository membershipTypeRepository;
    private MembershipDetailRepository membershipDetailRepository;

    @Autowired
    public MembershipServiceImpl(MembershipTypeRepository membershipTypeRepository, MembershipDetailRepository membershipDetailRepository) {
        this.membershipTypeRepository = membershipTypeRepository;
        this.membershipDetailRepository = membershipDetailRepository;
    }

    @Override
    public List<MembershipType> lisMembershipTypes() {
        return membershipTypeRepository.getAllActive();
    }

    @Override
    public MembershipType getMembershipTypeDetails(long id) {
        return membershipTypeRepository.get(id);
    }

    @Override
    public void saveMembershipTypeDetails(MembershipType membershipType) {
        membershipTypeRepository.save(membershipType);
    }

    @Override
    public void deleteMembershipTypeDetails(MembershipType membershipType) {
        MembershipType membershipTypeDb = membershipTypeRepository.get(membershipType.getId());
        membershipTypeDb.setDeleted(true);
        membershipTypeRepository.save(membershipTypeDb);
    }

    @Override
    public MembershipDetails getMembershipDetails(long memberId) {
        List<MembershipDetail> membershipDetails = membershipDetailRepository.findMembershipDetailsByMemberId(memberId);
        return new MembershipDetails(membershipDetails);
    }

    @Override
    public void renewMembership(MembershipDetail membershipDetail) {
        membershipDetailRepository.save(membershipDetail);
    }

    @Override
    public MembershipDetail getMembershipDetail(long id) {
        return membershipDetailRepository.get(id);
    }

    @Override
    public Date getRenewalDate(long memberId) {
        Date renewalDate = null;
        MembershipDetails membershipDetails = getMembershipDetails(memberId);
        for (MembershipDetail membershipDetail : membershipDetails.getMembershipDetailList()) {
            if (renewalDate == null) renewalDate = membershipDetail.getEndDate();
            else if (renewalDate.compareTo(membershipDetail.getEndDate()) < 0)
                renewalDate = membershipDetail.getEndDate();

        }
        Calendar instance = Calendar.getInstance();
        instance.setTime(renewalDate);
        instance.add(Calendar.DATE, 1);
        return instance.getTime();
    }
}
