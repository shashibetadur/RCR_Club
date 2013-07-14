<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="row well">
    <p>Welcome to RCR Raichur!!!</p>
</div>
 <div class="row well">
    <legend>Action Items</legend>
    <div class="info-details">
        <div class="span12 nk-form-section">
            <div class="accordion" id="info-details-accordion">

                <div class="accordion-group">
                    <div class="accordion-heading">
                        <a class="accordion-toggle" data-toggle="collapse" data-parent="new-bills-accordion"
                           href="#collapse-new-bills"><h4>New Bills</h4></a>
                    </div>
                    <div id="collapse-new-bills" class="accordion-body collapse">
                        <div class="accordion-inner">
                            <jsp:include page="bill/searchResults.jsp"/>
                        </div>
                    </div>
                </div>

                <div class="accordion-group">
                    <div class="accordion-heading">
                        <a class="accordion-toggle" data-toggle="collapse" data-parent="pos-accordion"
                           href="#collapse-pos"><h4>Purchase Orders</h4></a>
                    </div>
                    <div id="collapse-pos" class="accordion-body collapse">
                        <div class="accordion-inner">
                            <jsp:include page="order/searchResults.jsp"/>
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