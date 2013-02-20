package com.rcr.web.controller.member;

import com.rcr.domain.Authorize;
import com.rcr.domain.MembershipType;
import com.rcr.domain.Operation;
import com.rcr.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/member/type")
public class MembershipTypeController {

    private MemberService memberService;

    @Autowired
    public MembershipTypeController(MemberService memberService) {
        this.memberService = memberService;
    }

    @RequestMapping("/list")
    @Authorize(Operation.MEMBERSHIP_TYPE_LIST)
    public ModelAndView list() {
        return new ModelAndView("member/type/list", "memberTypeList", memberService.lisMembershipTypes());
    }

    @RequestMapping("/createForm")
    @Authorize(Operation.MEMBERSHIP_TYPE_CREATE)
    public ModelAndView createForm() {
        return new ModelAndView("member/type/createForm", "memberType", new MembershipType());
    }

    @RequestMapping("/editForm/{id}")
    @Authorize(Operation.MEMBERSHIP_TYPE_EDIT)
    public ModelAndView editForm(@PathVariable("id") long id) {
        return new ModelAndView("member/type/editForm", "memberType", memberService.getMembershipTypeDetails(id));
    }

    @RequestMapping("/deleteForm/{id}")
    @Authorize(Operation.MEMBERSHIP_TYPE_DELETE)
    public ModelAndView deleteForm(@PathVariable("id") long id) {
        return new ModelAndView("member/type/deleteForm", "memberType", memberService.getMembershipTypeDetails(id));
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @Authorize({Operation.MEMBERSHIP_TYPE_CREATE, Operation.MEMBERSHIP_TYPE_EDIT})
    public
    @ResponseBody
    MembershipType save(MembershipType membershipType, Model model) {
        model.asMap().clear();
        memberService.saveMembershipTypeDetails(membershipType);
        return membershipType;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @Authorize(Operation.MEMBERSHIP_TYPE_DELETE)
    public
    @ResponseBody
    MembershipType delete(MembershipType membershipType, Model model) {
        model.asMap().clear();
        memberService.deleteMembershipTypeDetails(membershipType);
        return membershipType;
    }
}
