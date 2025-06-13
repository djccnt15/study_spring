package com.djccnt15.study_spring.domain.member.controller;

import com.djccnt15.study_spring.db.model.MemberEntity;
import com.djccnt15.study_spring.domain.member.model.MemberForm;
import com.djccnt15.study_spring.domain.member.service.MemberServiceBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/members")
public class MemberControllerBean {
    
    private final MemberServiceBean memberServiceBean;
    
    @Autowired
    public MemberControllerBean(MemberServiceBean memberServiceBean) {
        this.memberServiceBean = memberServiceBean;
    }
    
    @GetMapping("/new")
    public String createMemberForm() {
        return "members/member-create-form";
    }
    
    @PostMapping("/new")
    public String createMember(MemberForm form) {
        var member = MemberEntity.builder().name(form.getName()).build();
        memberServiceBean.join(member);
        return "redirect:/member";
    }
    
    @GetMapping
    public String memberList(Model model) {
        var members = memberServiceBean.findMembers();
        model.addAttribute("members", members);
        return "members/member-list";
    }
}
