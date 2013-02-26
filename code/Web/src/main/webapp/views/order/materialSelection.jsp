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
        var materialTemplateRow =
                                "<td>"
                                        + "<input type='hidden' name='materialList[:index].id' value=':materialId'/>"
                                        + "<label>:itemName</label>"
                                        + "</td>"
                                        + "<td>"
                                        + "<input type='hidden' name='materialList[:index].price' value=':itemPrice'/>"
                                        + "<label>:itemPrice</label>"
                                        + "</td>"
                                        + "<td>"
                                        + "<input class='itemQty:index' type='text' name='materialList[:index].qty' value=':itemQty'/>"
                                        + "</td>"
                                        + "<td>"
                                        + "<label class='itemTotal:index'>:itemTotal</label>"
                                        + "</td>"
                                        + "<td>"
                                        + "<a href='javascript:deleteItem(:index)' class='delete-item'><i class = 'icon-trash'></i></a>"
                                        + "</td>";


        $(document).ready(function () {

            materialBasket = eval(${processOrderForm.materialListJason});
            renderTable();

            $("#material-name").autocomplete({
                source:function (request, response) {
                    $.getJSON("<%=request.getContextPath()%>/bar/material/search", {
                        searchToken:request.term
                    }, response);
                },
                appendTo:".menu-location",
                select:function (event, ui) {
                    addItem(ui.material);
                    return false;
                }
            });
        });

        function deleteItem(selectedIndex) {
            materialBasket.splice(selectedIndex, 1);
            renderTable();
        }

        function addItem(material) {
            materialBasket.push(material);
            renderTable();
        }
        function renderTable() {
            var rows = "";
            var total = 0;
            $.each(materialBasket, function (index, value) {
                  alert(index);

                    var row = "<tr  class='item-row'>" +
                    materialTemplateRow
                            .replace(/:index/g, index)
                            .replace(/:materialId/g, value.id)
                            .replace(/:itemName/g, value.value)
                            .replace(/:itemPrice/g, value.price)
                            .replace(/:itemQty/g, value.qty)
                            .replace(/:itemTotal/g, value.total) +
                             "</tr>";

                    rows += row;
                    alert(rows);

                    total += value.total;
            });
            if (materialBasket.length > 0) {
                rows += "<tr><td colspan='3'>Grand Total</td><td colspan='2'>" + (total + taxTotal) + "</td></tr>";
            }
            $(".item-list-location table tbody").html(rows);
            $("input[type='text']").blur(function () {
                $(this).val(specialTrim($(this).val()));
            });
        }

</script>