package com.rcr.service.member;


import com.rcr.domain.member.Member;
import com.rcr.domain.member.MemberSearchCriteria;
import com.rcr.domain.member.MemberSummary;
import com.rcr.repository.member.MemberRepository;
import com.rcr.repository.member.MembershipDetailRepository;
import com.rcr.repository.member.MembershipTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<Member> search(MemberSearchCriteria memberSearchCriteria) {
        List<Member> memberList = new ArrayList<Member>();
        if(memberSearchCriteria.hasMemberId()){
            Member member = memberRepository.get(Long.valueOf(memberSearchCriteria.getMemberId()));
            if(member != null) memberList.add(member);
            return memberList;
        }
        return memberRepository.findBy(memberSearchCriteria);
    }

    @Override
    public List<Member> getAllMembers() {
        return memberRepository.getAll();
    }
}
