package com.rcr.service.member;

import com.rcr.domain.member.MemberVisit;

import java.util.Date;
import java.util.List;

public interface MemberVisitService {

    void saveVisitDetails(List<MemberVisit> visits);

    List<MemberVisit> getVisitDetails(Date date);

    void deleteVisitDetails(List<MemberVisit> visitList);
}
