package com.rcr.service;


import com.rcr.domain.Member;
import com.rcr.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    private MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void saveMemberDetails(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member getMemberDetails(long memberId) {
        return memberRepository.get(memberId);
    }
}
