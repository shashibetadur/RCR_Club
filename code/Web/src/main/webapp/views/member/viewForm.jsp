<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="row well">
    <legend>Member Details</legend>
    <div class="nk-form-section">
        <div class="span10">
            <span class="nk-filed-label"><label>Name:</label></span>
            <span class="nk-filed"><label>${member.personalDetails.firstName} ${member.personalDetails.lastName}</label></span>
        </div>
    </div>
    <div class="nk-form-section">
        <div class="span10">
            <span class="nk-filed-label"><label>Designation:</label></span>
            <span class="nk-filed"><label>${member.personalDetails.designation}</label></span>
        </div>
    </div>
    <div class="nk-form-section">
        <div class="span10">
            <span class="nk-filed-label"><label>Status:</label></span>
            <span class="nk-filed"><label>${member.status}</label></span>
        </div>
    </div>
    <div class="nk-phone-list nk-form-section span4">
        <h4>Phone List</h4>

        <div class="phone-list-location">
            <table class="nk-table table table-striped table-bordered">
                <thead>
                <th>Type</th>
                <th>Number</th>
                </thead>
                <tbody>
                <c:forEach var="phone" items="${member.personalDetails.phoneList}" varStatus="index">
                    <tr>
                        <td>${phone.phoneType}</td>
                        <td>${phone.phoneNumber}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <div class="nk-address-list nk-form-section span10">
        <h4>Address List</h4>

        <div class="address-list-location">
            <table class="nk-table table table-striped table-bordered">
                <thead>
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
                <c:forEach varStatus="index" var="address" items="${member.personalDetails.addressList}">
                    <tr>
                        <td>${address.addressLineOne}</td>
                        <td>${address.addressLineTwo}</td>
                        <td>${address.locality}</td>
                        <td>${address.city}</td>
                        <td>${address.pinCode}</td>
                        <td>${address.state}</td>
                        <td>${address.landmark}</td>
                        <td>${address.addressType}</td>
                        <td>${address.notes}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <div class="span8 nk-form-section">
        <a href="<%=request.getContextPath()%>/member/editForm/${member.id}" class="btn">Edit</a>
    </div>
</div>



