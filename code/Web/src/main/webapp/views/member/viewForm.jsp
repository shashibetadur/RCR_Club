<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="nk" uri="/WEB-INF/custom-tags.tld" %>
<div class="row well">
    <legend>Member Details</legend>
    <div class="member-details">
        <div class="span12 nk-form-section">
            <div class="accordion" id="member-details-accordion">

                <div class="accordion-group">
                    <div class="accordion-heading">
                        <a class="accordion-toggle" data-toggle="collapse" data-parent="member-details-accordion"
                           href="#collapse-personal-details"><h4>
                            Personal Details</h4></a>
                    </div>
                    <div id="collapse-personal-details" class="accordion-body collapse in">
                        <div class="accordion-inner">
                            <jsp:include page="viewPersonalDetails.jsp"/>
                        </div>
                    </div>
                </div>

                <div class="accordion-group">
                    <div class="accordion-heading">
                        <a class="accordion-toggle" data-toggle="collapse" data-parent="member-details-accordion"
                           href="#collapse-summary"><h4>
                            Summary</h4></a>
                    </div>
                    <div id="collapse-summary" class="accordion-body collapse in">
                        <div class="accordion-inner">
                            <jsp:include page="viewSummaryDetails.jsp"/>
                        </div>
                    </div>
                </div>

                <div class="accordion-group">
                    <div class="accordion-heading">
                        <a class="accordion-toggle" data-toggle="collapse" data-parent="member-details-accordion"
                           href="#collapse-membership-details"><h4>
                            Membership Details</h4></a>
                    </div>
                    <div id="collapse-membership-details" class="accordion-body collapse in">
                        <div class="accordion-inner">
                                <jsp:include page="viewMembershipDetails.jsp"/>
                        </div>
                    </div>
                </div>

                <div class="accordion-group">
                    <div class="accordion-heading">
                        <a class="accordion-toggle" data-toggle="collapse" data-parent="member-details-accordion"
                           href="#collapse-payment-details"><h4>
                            Payment Details</h4></a>
                    </div>
                    <div id="collapse-payment-details" class="accordion-body collapse in">
                        <div class="accordion-inner">
                            <jsp:include page="viewPaymentDetails.jsp"/>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(".accordion").collapse();
</script>




