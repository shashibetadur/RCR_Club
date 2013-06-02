<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery-ui-1.9.0.custom.css" type="text/css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui-1.9.0.custom.min.js"></script>
<div class="row well">
    <form id="update-stock-form" method="POST" action="<%=request.getContextPath()%>/inventory/saveStock">
        <legend>Update Stock</legend>
        <div class="item-selection-section">
        <div class="span10 stock-update-errors"></div>
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
                            <th style="width: 15%">Current Stock</th>
                            <th style="width: 20%">Reduce Quantity</th>
                            <th style="width: 15%">Updated Quantity</th>
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
        <div class="span8 nk-form-section">
            <button type="submit" class="btn btn-primary">Save changes</button>
        </div>
    </form>
</div>
<script type="text/javascript">
        var materialBasket = [];
        var materialTemplateRow =
                                "<td>"
                                        + "<input type='hidden' name='displayMaterialList[:index].id' value=':materialId'/>"
                                        + "<label>:itemName</label>"
                                        + "</td>"
                                        + "<td>"
                                        + "<input type='hidden' name='displayMaterialList[:index].stock' value=':itemStock'/>"
                                        + "<label>:itemStock</label>"
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

            materialBasket = eval(${stockBasket.materialListJason});
            renderTable();

            $("#material-name").autocomplete({
                source:function (request, response) {
                    $.getJSON("<%=request.getContextPath()%>/bar/material/searchUpdateStock", {
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
            var qty = $(itemQtySelector).val();
            materialBasket[selectedIndex].qty = qty;
            materialBasket[selectedIndex].total = materialBasket[selectedIndex].stock - $(itemQtySelector).val();
            renderTable();
        }

        function addItem(item) {
            var errors = "";
            var errorMessageTemplate = "<label class='label label-important'>:message</label>"
            $(".stock-update-errors").html("");
            if(item.stock == 0){
                var value = item.stock;
                if (isEmpty(value) || value == 0) {
                    errors += errorMessageTemplate.replace(/:message/g, "Stock is Zero for Material: " + item.value);
                }
                if (!isEmpty(value) && !canParseNumber(value)) {
                    errors += errorMessageTemplate.replace(/:message/g, "Stock field can only have digits for Material: " + item.value);
                }
            }
            if (errors) {
                $(".stock-update-errors").html(errors + "<br/><br/>");
                return false;
            }
            materialBasket.push(item);
            renderTable();
        }

        $("#update-stock-form").submit(function () {
            var errors = "";
            var errorMessageTemplate = "<label class='label label-important'>:message</label>"
            $(".stock-update-errors").html("");
            $(".item-row input[type='text']").each(function () {
                var value = $(this).val();
                if (isEmpty(value) || value == 0) {
                    errors += errorMessageTemplate.replace(/:message/g, "Quantity field cannot be empty or Zero");
                }
                if (!isEmpty(value) && !canParseNumber(value)) {
                    errors += errorMessageTemplate.replace(/:message/g, "Quantity field can only have digits");
                }
            });
            if (errors) {
                $(".stock-update-errors").html(errors + "<br/><br/>");
                return false;
            }
            return true;
        });

        function renderTable() {
            var rows = "";
            var total = 0;
            $.each(materialBasket, function (index, value) {

                    var row = "<tr  class='item-row'>" +
                    materialTemplateRow
                            .replace(/:index/g, index)
                            .replace(/:materialId/g, value.id)
                            .replace(/:itemName/g, value.value)
                            .replace(/:itemStock/g, value.stock)
                            .replace(/:itemQty/g, value.qty)
                            .replace(/:itemTotal/g, value.total) +
                             "</tr>";

                    rows += row;
                    total += value.total;
            });

            $(".item-list-location table tbody").html(rows);
            $("input[type='text']").blur(function () {
                $(this).val(specialTrim($(this).val()));
            });
        }

</script>