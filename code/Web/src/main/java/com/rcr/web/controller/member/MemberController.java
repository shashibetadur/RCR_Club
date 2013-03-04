package com.rcr.web.controller.member;

import com.rcr.domain.*;
import com.rcr.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/member")
public class MemberController {

    private MemberService memberService;

    //requited for proxy creation
    protected MemberController() {
    }

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @RequestMapping(value = "/creationForm", method = RequestMethod.GET)
    @Authorize(Operation.MEMBER_CREATE)
    public ModelAndView memberCreationForm() {
        Member member = new Member();
        ModelAndView modelAndView = new ModelAndView("member/createForm", "member", member);
        modelAndView.getModelMap().addAttribute("personalDetails", member.getPersonalDetails());
        return modelAndView;
    }

    @RequestMapping(value = "/editForm/{memberId}", method = RequestMethod.GET)
    @Authorize(Operation.MEMBER_EDIT)
    public ModelAndView memberEditForm(@PathVariable("memberId") long memberId) {
        Member member = memberService.getMemberDetails(memberId);
        ModelAndView modelAndView = new ModelAndView("member/editForm", "member", member);
        modelAndView.getModelMap().addAttribute("personalDetails", member.getPersonalDetails());
        return modelAndView;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @Authorize({Operation.MEMBER_CREATE, Operation.MEMBER_EDIT})
    public String saveMember(Member member, Model model) {
        model.asMap().clear();
        memberService.saveMemberDetails(member);
        return "redirect:/member/viewForm/" + member.getId();
    }

    @RequestMapping(value = "/viewForm/{memberId}", method = RequestMethod.GET)
    @Authorize(Operation.MEMBER_VIEW)
    public ModelAndView memberViewForm(@PathVariable("memberId") long memberId) {
        ModelAndView modelAndView = new ModelAndView("member/viewForm", "member", memberService.getMemberDetails(memberId));
        modelAndView.getModelMap().put("membershipDetails", memberService.getMembershipDetails(memberId));
        modelAndView.getModelMap().put("memberSummary", memberService.getMemberSummary(memberId));
        return modelAndView;
    }

    @RequestMapping(value = "/membership/renew/{memberId}", method = RequestMethod.GET)
    @Authorize(Operation.MEMBERSHIP_RENEW)
    public ModelAndView renewMembershipGet(@PathVariable("memberId") long memberId) {
        MembershipDetail membershipDetail = new MembershipDetail();
        membershipDetail.setMemberId(memberId);
        membershipDetail.setStartDate(memberService.getRenewalDate(memberId));
        ModelAndView modelAndView = new ModelAndView("member/membership/renew", "membershipDetail", membershipDetail);
        return modelAndView;
    }

    @RequestMapping(value = "/membership/edit/{id}", method = RequestMethod.GET)
    @Authorize(Operation.MEMBERSHIP_RENEW)
    public ModelAndView editMembershipGet(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("member/membership/edit", "membershipDetail", memberService.getMembershipDetail(id));
        return modelAndView;
    }

    @RequestMapping(value = "/membership/renew", method = RequestMethod.POST)
    @Authorize(Operation.MEMBERSHIP_RENEW)
    public String renewMembershipPost(MembershipDetail membershipDetail, Model model) {
        model.asMap().clear();
        memberService.renewMembership(membershipDetail);
        return "redirect:/member/viewForm/" + membershipDetail.getMemberId();
    }

    @RequestMapping(value = "/searchForm", method = RequestMethod.GET)
    @Authorize(value = {Operation.MEMBER_SEARCH})
    public ModelAndView memberSearchForm() {
        return new ModelAndView("member/searchForm");
    }

    @RequestMapping(value = "/searchForm", method = RequestMethod.POST)
    @Authorize(value = {Operation.MEMBER_SEARCH})
    public ModelAndView memberSearchQuery(MemberSearchCriteria memberSearchCriteria) {
        List<Member> memberList = memberService.search(memberSearchCriteria);
        return new ModelAndView("member/searchResults", "memberList", memberList);
    }


    @ModelAttribute("addressTypes")
    public List<String> addressTypes() {
        List<String> addressTypes = new ArrayList<String>();
        for (Address.Type addressType : Address.Type.values()) {
            addressTypes.add(addressType.toString());
        }
        return addressTypes;
    }

    @ModelAttribute("phoneTypes")
    public List<String> phoneTypes() {
        List<String> phoneTypes = new ArrayList<String>();
        for (Phone.Type addressType : Phone.Type.values()) {
            phoneTypes.add(addressType.toString());
        }
        return phoneTypes;
    }

    @ModelAttribute("membershipTypes")
    public List<MembershipType> membershipTypes() {
        return memberService.lisMembershipTypes();
    }
}
