<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery-ui-1.9.0.custom.css" type="text/css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui-1.9.0.custom.min.js"></script>
<div class="item-selection-section">
    <jq>
        <div class="nk-form-section">
            <div class="span10">
                <span class="nk-filed-label"><label for="material-name"><b>Select Material</b></label></span>
                <span class="nk-filed"><input type="text" id="material-name" maxlength="100"/></span>
            </div>
        </div>
        <div class="nk-form-section span10">
            <h4>Material List</h4>

            <div class="item-list-location">
                <table class="nk-table table table-striped table-bordered">
                    <thead>
                    <th>Material Name</th>
                    <th style="width: 15%">Price</th>
                    <th style="width: 20%">Quantity</th>
                    <th style="width: 15%">Total</th>
                    <th style="width: 5%">Action</th>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="menu-location"></div>
    </jq>
</div>
<script type="text/javascript">
        var materialBasket = [];
        var taxConfigurations = [];
        var materialTemplateRow =
                                "<td>"
                                        + "<input type='hidden' name='displayMaterialList[:index].id' value=':materialId'/>"
                                        + "<input type='hidden' name='displayMaterialList[:index].inventoryId' value=':inventoryId'/>"
                                        + "<label>:itemName</label>"
                                        + "</td>"
                                        + "<td>"
                                        + "<input type='hidden' name='displayMaterialList[:index].price' value=':itemPrice'/>"
                                        + "<label>:itemPrice</label>"
                                        + "</td>"
                                        + "<td>"
                                        + "<input class='itemQty:index' onchange='javascript:updateQty(:index)' type='text' name='displayMaterialList[:index].qty' value=':itemQty'/>"
                                        + "</td>"
                                        + "<td>"
                                        + "<label class='itemTotal:index'>:itemTotal</label>"
                                        + "</td>"
                                        + "<td>"
                                        + "<a href='javascript:deleteItem(:index)' class='delete-item'><i class = 'icon-trash'></i></a>"
                                        + "</td>";


        $(document).ready(function () {

            materialBasket = eval(${processOrderForm.materialListJason});
            taxConfigurations = eval(${processOrderForm.taxListJason});

            <c:forEach varStatus="index" var="taxConfiguration" items="${taxConfigurations}">
                taxConfigurations.push({
                    taxType:"${taxConfiguration.taxType}",
                    percentage:"${taxConfiguration.percentage}"});
            </c:forEach>
            renderTable();

            $("#material-name").autocomplete({
                source:function (request, response) {
                    $.getJSON("<%=request.getContextPath()%>/bar/material/search", {
                        searchToken:request.term
                    }, response);
                },
                appendTo:".menu-location",
                select:function (event, ui) {
                    addItem(ui.item);
                    $('#material-name').val("");
                    return false;
                }
            });
        });

        function deleteItem(selectedIndex) {
            materialBasket.splice(selectedIndex, 1);
            renderTable();
        }

        function updateQty(selectedIndex) {
            var itemQtySelector = '.itemQty' + selectedIndex;
            var itemTotalSelector = '.itemTotal' + selectedIndex;
            var qty = $(itemQtySelector).val();

                    var errors = "";
                    var errorMessageTemplate = "<label class='label label-important'>:message</label>"
                    $(".order-creation-errors").html("");
                    var value = qty;
                    if (isEmpty(value) || value == 0) {
                        errors += errorMessageTemplate.replace(/:message/g, "Quantity cannot be Zero");
                    }
                    if (!isEmpty(value) && !canParseNumber(value)) {
                        errors += errorMessageTemplate.replace(/:message/g, "Quantity field can only have digits");
                    }
                    if (errors) {
                        $(".order-creation-errors").html(errors + "<br/><br/>");
                        return false;
                    }

            var total = (materialBasket[selectedIndex].price) * qty;
            materialBasket[selectedIndex].qty = $(itemQtySelector).val();
            materialBasket[selectedIndex].total = total;
            $(itemTotalSelector).html(total);
            renderTable();
        }

        function addItem(item) {
            materialBasket.push(item);
            renderTable();
        }
        function renderTable() {
            var rows = "";
            var total = 0;
            var taxTotal = 0;
            $.each(materialBasket, function (index, value) {

                    var row = "<tr  class='item-row'>" +
                    materialTemplateRow
                            .replace(/:index/g, index)
                            .replace(/:materialId/g, value.id)
                            .replace(/:inventoryId/g, value.inventoryId)
                            .replace(/:itemName/g, value.value)
                            .replace(/:itemPrice/g, value.price)
                            .replace(/:itemQty/g, value.qty)
                            .replace(/:itemTotal/g, value.total) +
                             "</tr>";

                    rows += row;
                    total += value.total;
            });
            if (materialBasket.length > 0) {
                $.each(taxConfigurations, function (index, value) {
                    var taxValue = ((total * value.percentage) / 100);
                    taxValue = Math.round(taxValue * 100) / 100;
                    var row = "<tr><td colspan='3'>:taxType (:percentage)</td><td colspan='2'>:totalTax</td></tr>"
                            .replace(/:taxType/g, value.taxType)
                            .replace(/:percentage/g, value.percentage)
                            .replace(/:totalTax/g, taxValue);
                    rows += row;
                    taxTotal += taxValue;
                });
                taxTotal = Math.round(taxTotal * 100) / 100;
                if((taxTotal - Math.floor(taxTotal)) >= .5)
                    taxTotal = Math.ceil(taxTotal);
                else
                    taxTotal = Math.floor(taxTotal);
                rows += "<tr><td colspan='3'>Grand Total</td><td colspan='2'>" + (total + taxTotal) + "</td></tr>";
            }
            $(".item-list-location table tbody").html(rows);
            $("input[type='text']").blur(function () {
                $(this).val(specialTrim($(this).val()));
            });
        }

</script>