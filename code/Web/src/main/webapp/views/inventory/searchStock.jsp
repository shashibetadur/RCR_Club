<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery-ui-1.9.0.custom.css" type="text/css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui-1.9.0.custom.min.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/datepicker.css" type="text/css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap-datepicker.js"></script>

<div class="row well">
    <form class="nk-search-form form-inline">
        <legend>Search Stock</legend>
        <div class="item-selection-section">
        <div class="span10 stock-update-errors"></div>
            <jq>
                <div>
                    <div class="nk-form-section">
                        <div class="span10">
                            <span class="nk-filed-label"><label for="on-date"><b>On Date</b></label></span>
                            <span class="nk-filed"><form:input type="text" id="on-date" path="stockBasket.onDate" maxlength="100"/></span>
                        </div>
                    </div>
                </div> <br/>
                <div class="nk-form-section">
                    <div class="span10">
                        <span class="nk-filed-label"><label for="material-name"><b>Select Material</b></label></span>
                        <span class="nk-filed"><input type="text" id="material-name" maxlength="100"/></span>
                    </div>
                </div><br/><br/><br/>
                <legend/>
                <div class="span8 nk-form-section">
                    <a href="#" class="nk-search-button btn btn-small btn-primary"><i class = 'icon-search'></i> &nbsp Search</a>
                </div>
                <div class="nk-form-section span10">
                    <h4>Material List</h4>

                    <div class="item-list-location">
                        <table class="nk-table table table-striped table-bordered">
                            <thead>
                            <th>Material Name</th>
                            <th style="width: 55%">Description</th>
                            <th style="width: 15%">Stock</th>
                            <th style="width: 5%">Action</th>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="search-results"></div>
            </jq>
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
                                        + "<input type='hidden' name='displayMaterialList[:index].description' value=':description'/>"
                                        + "<label>:description</label>"
                                        + "</td>"
                                        + "<td>"
                                        + "<input type='hidden' name='displayMaterialList[:index].stock' value=':itemStock'/>"
                                        + "<label>:itemStock</label>"
                                        + "</td>"
                                        + "<td>"
                                        + "<a href='javascript:deleteItem(:index)' class='delete-item'><i class = 'icon-trash'></i></a>"
                                        + "</td>";


        $(document).ready(function () {

            materialBasket = eval(${stockBasket.materialListJason});

            $("#on-date").datepicker({
                format:"dd-mm-yyyy"
            });

            $("#material-name").autocomplete({
                source:function (request, response) {
                    $.getJSON("<%=request.getContextPath()%>/bar/material/searchStock?onDate="+$('#on-date').val(), {
                        searchToken:request.term
                    }, response);
                },
                appendTo:".search-results",
                select:function (event, ui) {
                    addItem(ui.item);
                    $('#material-name').val("");
                    return false;
                }
            });

            $('.nk-search-button').click(function () {
                var formData = $('form').serialize();
                var length = materialBasket.length;
                var count = 0;
                for(i=0; i <  length; i++){
                    var mat = materialBasket[i];
                    $.getJSON("<%=request.getContextPath()%>/bar/material/searchMaterialStock?onDate="+$('#on-date').val(), {
                        materialId:mat.id
                    }
                    , function(data,status,xhr){
                        materialBasket.splice(count++, 1, data);
                        renderTable();
                    }
                    );
                }
            });
        });

        function deleteItem(selectedIndex) {
            materialBasket.splice(selectedIndex, 1);
            renderTable();
        }

        function addItem(item) {
            materialBasket.push(item);
            renderTable();
        }

        function renderTable() {
            var rows = "";
            $.each(materialBasket, function (index, value) {

                    var row = "<tr  class='item-row'>" +
                    materialTemplateRow
                            .replace(/:index/g, index)
                            .replace(/:materialId/g, value.id)
                            .replace(/:itemName/g, value.value)
                            .replace(/:description/g, value.description)
                            .replace(/:itemStock/g, value.stock) +
                             "</tr>";

                    rows += row;
            });

            $(".item-list-location table tbody").html(rows);
            $("input[type='text']").blur(function () {
                $(this).val(specialTrim($(this).val()));
            });
        }

</script>