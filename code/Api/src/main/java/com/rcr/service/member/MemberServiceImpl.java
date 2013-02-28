package com.rcr.service.member;


import com.rcr.domain.*;
import com.rcr.repository.member.MemberRepository;
import com.rcr.repository.member.MembershipDetailRepository;
import com.rcr.repository.member.MembershipTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    private MemberRepository memberRepository;
    private MembershipTypeRepository membershipTypeRepository;
    private MembershipDetailRepository membershipDetailRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository, MembershipTypeRepository membershipTypeRepository, MembershipDetailRepository membershipDetailRepository) {
        this.memberRepository = memberRepository;
        this.membershipTypeRepository = membershipTypeRepository;
        this.membershipDetailRepository = membershipDetailRepository;
    }

    @Override
    public void saveMemberDetails(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member getMemberDetails(long memberId) {
        return memberRepository.get(memberId);
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
    public MemberSummary getMemberSummary(long memberId) {
        MemberSummary memberSummary = new MemberSummary();
        memberSummary.setMembershipType("Gold");
        memberSummary.setStatus("Active");
        memberSummary.setStatus("Active");
        return memberSummary;
    }

    @Override
    public void renewMembership(MembershipDetail membershipDetail) {
        membershipDetailRepository.save(membershipDetail);
    }

    @Override
    public MembershipDetail getMembershipDetail(long id) {
        return membershipDetailRepository.get(id);
    }
}
