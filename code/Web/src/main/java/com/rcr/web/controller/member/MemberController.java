package com.rcr.web.controller.member;

import com.rcr.common.DateUtils;
import com.rcr.domain.*;
import com.rcr.domain.account.MembershipPayment;
import com.rcr.domain.account.Payment;
import com.rcr.domain.member.Member;
import com.rcr.domain.member.MemberSearchCriteria;
import com.rcr.domain.member.MemberSummary;
import com.rcr.domain.member.MembershipDetails;
import com.rcr.service.member.MemberService;
import com.rcr.service.member.MembershipService;
import com.rcr.web.model.MemberAutoComplete;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.apache.commons.collections.CollectionUtils.collect;
import static org.springframework.util.StringUtils.isEmpty;

@Controller
@RequestMapping("/member")
public class MemberController {

    private MemberService memberService;
    private MembershipService membershipService;

    //requited for proxy creation
    protected MemberController() {
    }

    @Autowired
    public MemberController(MemberService memberService, MembershipService membershipService) {
        this.memberService = memberService;
        this.membershipService = membershipService;
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
        Member memberDetails = memberService.getMemberDetails(memberId);
        MembershipDetails membershipDetails = membershipService.getMembershipDetails(memberId);
        List<MembershipPayment> membershipPaymentDetails = (List<MembershipPayment>) membershipService.getMembershipPaymentDetails(memberId);
        MemberSummary memberSummary = new MemberSummary(memberDetails, membershipDetails, membershipPaymentDetails);

        ModelAndView modelAndView = new ModelAndView("member/viewForm", "member", memberDetails);
        modelAndView.getModelMap().put("membershipDetails", membershipDetails);
        modelAndView.getModelMap().put("membershipPayments", membershipPaymentDetails);
        modelAndView.getModelMap().put("memberSummary", memberSummary);
        return modelAndView;
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
        for (Member member : memberList) {
            try {
                if (DateUtils.getNowDate().after(membershipService.getMembershipDetails(member.getId()).getCurrentMembership().getEndDate())) {
                    member.setStatus("E");
                }
            } catch (Exception e) {
                member.setStatus("E");
            }

        }
        return new ModelAndView("member/searchResults", "memberList", memberList);
    }

    @RequestMapping(value = "/memberList", method = RequestMethod.GET)
    public ModelAndView listMembers() {
        List<Member> memberList = memberService.getAllMembers();
        for (Member member : memberList) {
            try {
                if (DateUtils.getNowDate().after(membershipService.getMembershipDetails(member.getId()).getCurrentMembership().getEndDate())) {
                    member.setStatus("E");
                }
            } catch (Exception e) {
                member.setStatus("E");
            }

        }
        return new ModelAndView("member/memberList", "memberList", memberList);
    }

    @RequestMapping(value = "/search/name", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Authorize(value = {Operation.MEMBER_SEARCH})
    public
    @ResponseBody
    List<MemberAutoComplete> searchItems(@RequestParam("searchToken") String searchToken) {
        if (isEmpty(searchToken) || isEmpty(searchToken.trim())) return new ArrayList<MemberAutoComplete>();
        List<Member> result = new ArrayList<Member>();
        String[] tokens = searchToken.split(" ");
        for (String token : tokens) {
            token = token.trim();
            List<Member> searchByFirstName = memberService.search(new MemberSearchCriteria(null, token, null));
            List<Member> searchByLastName = memberService.search(new MemberSearchCriteria(null, null, token));
            Collection union = CollectionUtils.union(searchByFirstName, searchByLastName);
            result = (List<Member>) (result.isEmpty() ? union : CollectionUtils.intersection(result, union));

        }

        return (List<MemberAutoComplete>) collect(result, new Transformer() {
            @Override
            public Object transform(Object input) {
                return new MemberAutoComplete((Member) input);
            }
        });
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

}
