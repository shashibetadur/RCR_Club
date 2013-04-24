package com.rcr.service.member;

import com.rcr.domain.member.MemberVisit;
import com.rcr.repository.member.MemberRepository;
import com.rcr.repository.member.MemberVisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MemberVisitServiceImpl implements MemberVisitService {

    private MemberVisitRepository memberVisitRepository;
    private MemberRepository memberRepository;

    @Autowired
    public MemberVisitServiceImpl(MemberVisitRepository memberVisitRepository, MemberRepository memberRepository) {
        this.memberVisitRepository = memberVisitRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public void saveVisitDetails(List<MemberVisit> visits) {
        for (MemberVisit visit : visits) {
            memberVisitRepository.save(visit);
        }
    }

    @Override
    public List<MemberVisit> getVisitDetails(Date date) {
        List<MemberVisit> visitList = memberVisitRepository.findVisitDetailsByDate(date);
        for (MemberVisit memberVisit : visitList) {
            memberVisit.setMemberDetails(memberRepository.get(memberVisit.getMemberId()));
        }
        return visitList;
    }

    @Override
    public void deleteVisitDetails(List<MemberVisit> visitList) {
        for (MemberVisit memberVisit : visitList) {
            memberVisitRepository.delete(memberVisit);
        }
    }
}
