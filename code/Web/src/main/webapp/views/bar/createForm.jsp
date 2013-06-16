<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="row well">
    <form id="create-material-form" method="POST" action="<%=request.getContextPath()%>/bar/material/save">
        <legend>Material Form</legend>
        <form:hidden path="material.id"/>
        <div class="span10 create-material-errors"></div>
        <div class="nk-form-section">
            <div class="span10">
                <span class="nk-filed-label"><label for="material-materialType">Type</label></span>
                <span class="nk-filed">
                    <form:select path="material.materialType" items="${materialTypes}"/>
                </span>
            </div>
        </div>

        <div class="nk-form-section">
            <div class="span10">
                <span class="nk-filed-label"><label for="material-name">Name</label></span>
                <span class="nk-filed"><form:input type="text" id="material-name" path="material.name" maxlength="100"/></span>
            </div>
        </div>

        <div class="nk-form-section">
            <div class="span10">
                <span class="nk-filed-label"><label for="material-description">Description</label></span>
                <span class="nk-filed"><form:textarea type="text" id="material-description" path="material.description" maxlength="100"/></span>
            </div>
        </div>

        <div class="nk-form-section">
            <div class="span10">
                <span class="nk-filed-label"><label for="material-unit">Unit</label></span>
                <span class="nk-filed"><form:input type="text" id="material-unit" path="material.unit" maxlength="100"/></span>
            </div>
        </div>

        <div class="nk-form-section">
            <div class="span10">
                <span class="nk-filed-label"><label for="material-price">Price</label></span>
                <span class="nk-filed"><form:input type="text" id="material-price" path="material.price" maxlength="100"/></span>
            </div>
        </div>

        <div class="span8 nk-form-section">
            <button type="submit" class="btn btn-primary"><i class = 'icon-ok'></i> &nbsp Save</button>
            <button type="reset" class="btn btn-warning"><i class = 'icon-refresh'></i> &nbsp Cancel</button>
            <c:if test="${material.id > 0}">
                <a class="btn btn-danger" href="<%=request.getContextPath()%>/bar/material/delete/${material.id}"><i class = 'icon-trash'></i> &nbspDelete</a>
            </c:if>
        </div>
    </form>
</div>
<script type="text/javascript">

        $("#create-material-form").submit(function () {
            var errors = "";
            var errorMessageTemplate = "<label class='label label-important'>:message</label>"
            $(".create-material-errors").html("");
            if (isEmpty(jqVal("#material-name"))) {
                errors += errorMessageTemplate.replace(/:message/g, "Material Name cannot be empty")
            }
            if (isEmpty(jqVal("#material-description"))) {
                errors += errorMessageTemplate.replace(/:message/g, "Material Description cannot be empty")
            }
            if (isEmpty(jqVal("#material-unit"))) {
                errors += errorMessageTemplate.replace(/:message/g, "Material Unit cannot be empty")
            }
            if (isEmpty(jqVal("#material-price"))) {
                errors += errorMessageTemplate.replace(/:message/g, "Material Price cannot be empty")
            }
            if (!isEmpty(jqVal("#material-price")) && !canParseDecimal(jqVal("#material-price"))) {
                errors += errorMessageTemplate.replace(/:message/g, "Material Price should be a number")
            }
            if (errors) {
                $(".create-material-errors").html(errors + "<br/><br/>");
                return false;
            }
            return true;
        });

        $('#delete').click(function () {

        });
</script>