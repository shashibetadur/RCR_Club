<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="row well">
    <form method="POST" action="<%=request.getContextPath()%>/member/save">
        <legend>New Member Form</legend>
        <form:hidden path="member.id"/>
        <form:hidden path="member.personalDetails.id"/>
        <div class="nk-form-section">
            <div class="span5">
                <span class="nk-filed-label"><label for="input01">First Name</label></span>
                <span class="nk-filed"><form:input type="text" id="input01" path="member.personalDetails.firstName" maxlength="100"/></span>
            </div>
            <div class="span5">
                <span class="nk-filed-label"><label for="input01">Last Name</label></span>
                <span class="nk-filed"><form:input type="text" id="input01" path="member.personalDetails.lastName" maxlength="100"/></span>
            </div>
        </div>
        <br/>

        <div class="nk-phone-list nk-form-section span4">
            <h4>Phone List</h4>

            <div class="phone-list-location">
                <table class="nk-table table table-striped table-bordered">
                    <thead>
                    <th></th>
                    <th>Type</th>
                    <th>Number</th>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>

            <div class="phone-buttons btn-group">
                <a class="btn add">Add</a>
                <a class="btn  edit">Edit</a>
                <a class="btn  delete">Delete</a>
            </div>
        </div>
        <div class="nk-address-list nk-form-section span10">
            <h4>Address List</h4>

            <div class="address-list-location">
                <table class="nk-table table table-striped table-bordered">
                    <thead>
                    <th></th>
                    <th>Address Line One</th>
                    <th>Address Line Two</th>
                    <th>Locality</th>
                    <th>City</th>
                    <th>Pin Code</th>
                    <th>State</th>
                    <th>Landmark</th>
                    <th>Address Type</th>
                    <th>Notes</th>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>

            <div class="address-buttons btn-group">
                <a class="btn add">Add</a>
                <a class="btn  edit">Edit</a>
                <a class="btn  delete">Delete</a>
            </div>
        </div>
        <br/>

        <div class="span8 nk-form-section">
            <button type="submit" class="btn btn-primary">Save changes</button>
            <button type="reset" class="btn">Cancel</button>
        </div>
    </form>
</div>
<jsp:include page="../phone.jsp"/>
<jsp:include page="../address.jsp"/>



