package com.rcr.service.member;


import com.rcr.domain.Member;
import com.rcr.domain.MembershipType;
import com.rcr.repository.member.MemberRepository;
import com.rcr.repository.member.MembershipTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    private MemberRepository memberRepository;
    private MembershipTypeRepository membershipTypeRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository, MembershipTypeRepository membershipTypeRepository) {
        this.memberRepository = memberRepository;
        this.membershipTypeRepository = membershipTypeRepository;
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
}
