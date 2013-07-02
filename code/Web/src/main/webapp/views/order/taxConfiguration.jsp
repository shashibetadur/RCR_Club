<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="nk" uri="/WEB-INF/custom-tags.tld" %>
<div class="row well">
    <form method="POST" action="<%=request.getContextPath()%>/tax/configuration/save">
        <legend>Tax Configuration</legend>
        <div class="span8 nk-form-section">
            <div class="tax-configurations">
                <table class="nk-table table table-striped table-bordered">
                    <thead>
                    <th></th>
                    <th>Tax Type</th>
                    <th>Order Type</th>
                    <th>Percentage(%)</th>
                    <th>Description</th>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
            </div>
            <div class="tax-configurations-buttons btn-group">
                <nk:security operations="tax-save">
                    <a class="btn add"><i class = 'icon-plus'></i> &nbsp Add</a>
                    <a class="btn edit"><i class = 'icon-edit'></i> &nbsp Edit</a>
                    <a class="btn delete"><i class = 'icon-trash'></i> &nbsp Delete</a>
                </nk:security>
            </div>
        </div>
        <legend/>
        <div class="span8 nk-form-section">
            <nk:security operations="tax-save">
                <button type="submit" class="btn btn-primary"><i class = 'icon-ok'></i> &nbsp Save</button>
                <button type="reset" class="btn btn-warning"><i class = 'icon-refresh'></i> &nbsp Clear</button>
            </nk:security>
        </div>
    </form>
</div>
<div id="taxConfigurationModal" class="modal hide" tabindex="-1" role="dialog" aria-labelledby="taxConfigurationModalLabel" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h3 id="myModalLabel">Tax Configuration</h3>
    </div>
    <div class="modal-body">
        <input type="hidden" class="selected-index" value="">

        <div class="tax-configuration-errors"></div>
        <label>Tax Type</label> <input type="text" id="tax-type" class="tax-type" maxlength="200"/>
        <label>Order Type</label><select class="order-type"><c:forEach var="orderType" items="${orderTypes}">
        <option value="${orderType}">${orderType}</option>
    </c:forEach></select>
        <label>Percentage</label> <input type="text" id="tax-percentage" class="percentage" maxlength="200"/>
        <label>Description</label> <input type="text" class="description" maxlength="200"/>
    </div>
    <div class="modal-footer">
        <a class="save-tax-configuration btn btn-primary"><i class = 'icon-ok'></i> &nbspOkay</a>
    </div>
</div>

<script type="text/javascript">
    var taxConfigurations = [];
    var taxConfigurationsRow =
            "<td>"
                    + "<input type='radio' name='tax-configuration-row' value=':index'/>"
                    + "<input type='hidden' name='taxList[:index].id' value=':id'/>"
                    + "</td>"
                    + "<td>"
                    + "<label>:taxType</label>"
                    + "<input type='hidden' name='taxList[:index].taxType' value=':taxType'/>"
                    + "</td>"
                    + "<td>"
                    + "<label>:orderType</label>"
                    + "<input type='hidden' name='taxList[:index].orderType' value=':orderType'/>"
                    + "</td>"
                    + "<td>"
                    + "<label>:percentage</label>"
                    + "<input type='hidden' name='taxList[:index].percentage' value=':percentage'/>"
                    + "</td>"
                    + "<td>"
                    + "<label>:description</label>"
                    + "<input type='hidden' name='taxList[:index].description' value=':description'/>"
                    + "</td>";

    $(document).ready(function () {
        <c:forEach var="taxConfiguration" items= "${taxConfiguration.taxList}" varStatus="index">
        taxConfigurations.push({
            index:${index.count}-1,
            id:"${taxConfiguration.id}",
            taxType:"${taxConfiguration.taxType}",
            orderType:"${taxConfiguration.orderType}",
            percentage:"${taxConfiguration.percentage}",
            description:"${taxConfiguration.description}"});
        </c:forEach>

        renderTaxConfigurationTable();

        //on click of add
        $('.tax-configurations-buttons .add').click(function () {
            $("#taxConfigurationModal input").val("");
            $('#taxConfigurationModal').modal('show');
        });

        //on click of edit
        $('.tax-configurations-buttons .edit').click(function () {
            var selectedIndex = $('input:radio[name=tax-configuration-row]:checked').val();
            if (!selectedIndex) return;
            $('#taxConfigurationModal .selected-index').val(taxConfigurations[selectedIndex].index);
            $('#taxConfigurationModal .tax-type').val(taxConfigurations[selectedIndex].taxType);
            $('#taxConfigurationModal .order-type').val(taxConfigurations[selectedIndex].orderType);
            $('#taxConfigurationModal .percentage').val(taxConfigurations[selectedIndex].percentage);
            $('#taxConfigurationModal .description').val(taxConfigurations[selectedIndex].description);
            $('#taxConfigurationModal').modal('show');
        });

        //on click of delete
        $('.tax-configurations-buttons .delete').click(function () {
            var selectedIndex = $('input:radio[name=tax-configuration-row]:checked').val();
            taxConfigurations.splice(selectedIndex, 1);
            renderTaxConfigurationTable();
        })
        //on click of save
        $('#taxConfigurationModal .save-tax-configuration').click(function () {
            if (!validTaxConfiguration()) return false;
            var index = $('#taxConfigurationModal .selected-index').val();
            if (index) {
                taxConfigurations[index].index = $('#taxConfigurationModal .selected-index').val();
                taxConfigurations[index].taxType = $('#taxConfigurationModal .tax-type').val();
                taxConfigurations[index].orderType = $('#taxConfigurationModal .order-type').val();
                taxConfigurations[index].percentage = $('#taxConfigurationModal .percentage').val();
                taxConfigurations[index].description = $('#taxConfigurationModal .description').val();
            } else {
                taxConfigurations.push({
                    index:taxConfigurations.length,
                    id:0,
                    taxType:$('#taxConfigurationModal .tax-type').val(),
                    orderType:$('#taxConfigurationModal .order-type').val(),
                    percentage:$('#taxConfigurationModal .percentage').val(),
                    description:$('#taxConfigurationModal .description').val()});
            }
            $("#taxConfigurationModal .close").click();
            renderTaxConfigurationTable();
        });
    });

    function validTaxConfiguration() {
        var errors = "";
        var errorMessageTemplate = "<label class='label label-important'>:message</label>"
        $(".tax-configuration-errors").html("");
        if (isEmpty(jqVal("#tax-type"))) {
            errors += errorMessageTemplate.replace(/:message/g, "Tax type cannot be empty")
        }
        if (!isEmpty(jqVal("#tax-type")) && !canParseOnlyAlphabetsWithSpace(jqVal("#tax-type"))) {
            errors += errorMessageTemplate.replace(/:message/g, "Tax type can only have alphabets with space")
        }
        if (isEmpty(jqVal("#tax-percentage"))) {
            errors += errorMessageTemplate.replace(/:message/g, "Tax percentage cannot be empty")
        }
        if (!isEmpty(jqVal("#tax-percentage")) && !canParseDecimal(jqVal("#tax-percentage"))) {
            errors += errorMessageTemplate.replace(/:message/g, "Tax percentage can only have digits with decimal")
        }
        if (errors) {
            $(".tax-configuration-errors").html(errors + "<br/");
            return false;
        }
        return true;
    }

    function renderTaxConfigurationTable() {
        var rows = "";
        $.each(taxConfigurations, function (index, value) {
            if (value) {
                var row = "<tr>" + taxConfigurationsRow
                        .replace(/:index/g, index)
                        .replace(/:id/g, value.id)
                        .replace(/:taxType/g, value.taxType)
                        .replace(/:orderType/g, value.orderType)
                        .replace(/:percentage/g, value.percentage)
                        .replace(/:description/g, value.description) + "</tr>";
                rows += row;
            }
        });
        $(".tax-configurations table tbody").html(rows);
    }
    $("input[type='text']").blur(function () {
        $(this).val(specialTrim($(this).val()));
    });
</script>