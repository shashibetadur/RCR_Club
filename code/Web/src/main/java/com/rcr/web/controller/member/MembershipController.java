package com.rcr.web.controller.member;

import com.rcr.domain.Authorize;
import com.rcr.domain.member.MembershipDetail;
import com.rcr.domain.member.MembershipType;
import com.rcr.domain.Operation;
import com.rcr.service.member.MemberService;
import com.rcr.service.member.MembershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/member/membership")
public class MembershipController {

    private MemberService memberService;
    private MembershipService membershipService;

    @Autowired
    public MembershipController(MemberService memberService, MembershipService membershipService) {
        this.memberService = memberService;
        this.membershipService = membershipService;
    }

    @RequestMapping(value = "/renew/{memberId}", method = RequestMethod.GET)
    @Authorize(Operation.MEMBERSHIP_RENEW)
    public ModelAndView renewMembershipGet(@PathVariable("memberId") long memberId) {
        MembershipDetail membershipDetail = new MembershipDetail();
        membershipDetail.setMemberId(memberId);
        membershipDetail.setStartDate(membershipService.getRenewalDate(memberId));
        ModelAndView modelAndView = new ModelAndView("member/membership/renew", "membershipDetail", membershipDetail);
        return modelAndView;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @Authorize(Operation.MEMBERSHIP_RENEW)
    public ModelAndView editMembershipGet(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("member/membership/edit", "membershipDetail", membershipService.getMembershipDetail(id));
        return modelAndView;
    }

    @RequestMapping(value = "/renew", method = RequestMethod.POST)
    @Authorize(Operation.MEMBERSHIP_RENEW)
    public String renewMembershipPost(MembershipDetail membershipDetail, Model model) {
        model.asMap().clear();
        membershipService.renewMembership(membershipDetail);
        return "redirect:/member/viewForm/" + membershipDetail.getMemberId();
    }

    @ModelAttribute("membershipTypes")
    public List<MembershipType> membershipTypes() {
        return membershipService.lisMembershipTypes();
    }
}
