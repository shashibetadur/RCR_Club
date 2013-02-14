package com.rcr.web;

import com.rcr.domain.User;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.jstl.core.ConditionalTagSupport;

import static org.apache.commons.lang.StringUtils.isEmpty;


public class SecurityTag extends ConditionalTagSupport {

    private String operations;

    public void setOperations(String operations) {
        this.operations = operations;
    }

    @Override
    protected boolean condition() throws JspTagException {
        if (operations == null || isEmpty(operations.trim())) return false;
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);
        User user = (User) session.getAttribute("user");
        String[] operationList = operations.split(",");
        for (String operation : operationList) {
            if (user.canPerform(operation.trim())) return true;
        }
        return false;
    }
}
