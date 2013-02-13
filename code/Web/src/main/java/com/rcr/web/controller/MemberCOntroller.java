package com.rcr.web.controller;

import com.rcr.domain.Address;
import com.rcr.domain.Member;
import com.rcr.domain.Phone;
import com.rcr.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
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
    public ModelAndView memberCreationForm() {
        Member member = new Member();
        ModelAndView modelAndView = new ModelAndView("member/createForm", "member", member);
        modelAndView.getModelMap().addAttribute("personalDetails", member.getPersonalDetails());
        return modelAndView;
    }

    @RequestMapping(value = "/editForm/{memberId}", method = RequestMethod.GET)
    public ModelAndView memberEditForm(@PathVariable("memberId") long memberId) {
        Member member = memberService.getMemberDetails(memberId);
        ModelAndView modelAndView = new ModelAndView("member/editForm", "member", member);
        modelAndView.getModelMap().addAttribute("personalDetails", member.getPersonalDetails());
        return modelAndView;
    }

    @RequestMapping(value = "/viewForm/{memberId}", method = RequestMethod.GET)
    public ModelAndView memberViewForm(@PathVariable("memberId") long memberId) {
        return new ModelAndView("member/viewForm", "member", memberService.getMemberDetails(memberId));
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveMember(Member member, Model model) {
        model.asMap().clear();
        memberService.saveMemberDetails(member);
        return "redirect:/member/viewForm/" + member.getId();
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
