package com.rcr.web.controller.member;

import com.rcr.domain.MemberVisit;
import com.rcr.service.member.MemberVisitService;
import com.rcr.web.model.MemberVisitsPageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/member/visit")
public class MemberVisitController {

    private MemberVisitService memberVisitService;

    protected MemberVisitController() {
    }

    @Autowired
    public MemberVisitController(MemberVisitService memberVisitService) {
        this.memberVisitService = memberVisitService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String membersVisitForm() {
        return "member/visit";
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    String saveMembersVisitForm(MemberVisitsPageView memberVisitsPageView, Model model) {
        model.asMap().clear();
        List<MemberVisit> memberVisits = memberVisitsPageView.getVisitList();
        List<MemberVisit> oldVisitDetails = memberVisitService.getVisitDetails(memberVisitsPageView.getVisitDate());
        memberVisitService.deleteVisitDetails(oldVisitDetails);
        memberVisitService.saveVisitDetails(memberVisits);
        return "success";
    }

    @RequestMapping(value = "/{date}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    List<MemberVisit> getMembersVisits(@PathVariable("date") Date date) {
        return memberVisitService.getVisitDetails(date);
    }

}
