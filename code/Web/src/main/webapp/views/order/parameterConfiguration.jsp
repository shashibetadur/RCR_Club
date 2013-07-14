<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="nk" uri="/WEB-INF/custom-tags.tld" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap-modal.js"></script>
<div class="row well">
    <form method="POST" action="<%=request.getContextPath()%>/parameter/configuration/save">
        <legend>Parameter Configuration</legend>
        <div class="span8 nk-form-section">
            <div class="parameter-configurations">
                <table class="nk-table table table-striped table-bordered">
                    <thead>
                    <th></th>
                    <th>Parameter Name</th>
                    <th>Parameter Value</th>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
            </div>
            <div class="parameter-configurations-buttons btn-group">
                    <a id="addParameter" class="btn add"><i class = 'icon-plus'></i> &nbsp Add</a>
                    <a id="editParameter" class="btn edit"><i class = 'icon-edit'></i> &nbsp Edit</a>
                    <a id="deleteParameter" class="btn delete"><i class = 'icon-trash'></i> &nbsp Delete</a>
            </div>
        </div>
        <legend/>
        <div class="span8 nk-form-section">
            <nk:security operations="parameters-save">
                <button type="submit" class="btn btn-primary"><i class = 'icon-ok'></i> &nbsp Save</button>
                <button type="reset" class="btn btn-warning"><i class = 'icon-refresh'></i> &nbsp Clear</button>
            </nk:security>
        </div>
    </form>
</div>

<script type="text/javascript">
    var parameterConfigurations = [];
    var parameterConfigurationsRow =
            "<td>"
                    + "<input type='radio' name='parameter-configuration-row' value=':index'/>"
                    + "<input type='hidden' name='parameterList[:index].id' value=':id'/>"
                    + "</td>"
                    + "<td>"
                    + "<label>:parameterName</label>"
                    + "<input type='hidden' name='parameterList[:index].parameterName' value=':parameterName'/>"
                    + "</td>"
                    + "<td>"
                    + "<label>:parameterValue</label>"
                    + "<input type='hidden' name='parameterList[:index].parameterValue' value=':parameterValue'/>"
                    + "</td>";

    $(document).ready(function () {
        <c:forEach var="parameter" items= "${parameterConfig.parameterList}" varStatus="index">
        parameterConfigurations.push({
            index:${index.count}-1,
            id:"${parameter.id}",
            parameterName:"${parameter.parameterName}",
            parameterValue:"${parameter.parameterValue}"});
        </c:forEach>

        renderParameterConfigurationTable();

        //on click of add
        $('#addParameter').click(function () {
            $("#parameterConfigurationModal input").val("");
            $('#parameterConfigurationModal').modal('show');
        });

        //on click of edit
        $('#editParameter').click(function () {
            var selectedIndex = $('input:radio[name=parameter-configuration-row]:checked').val();
            if (!selectedIndex) return;
            $('#parameterConfigurationModal .selected-index').val(parameterConfigurations[selectedIndex].index);
            $('#parameterConfigurationModal .parameterName').val(parameterConfigurations[selectedIndex].parameterName);
            $('#parameterConfigurationModal .parameterValue').val(parameterConfigurations[selectedIndex].parameterValue);
            $('#parameterConfigurationModal').modal('show');
        });

        //on click of delete
        $('#deleteParameter').click(function () {
            var selectedIndex = $('input:radio[name=parameter-configuration-row]:checked').val();
            parameterConfigurations.splice(selectedIndex, 1);
            renderParameterConfigurationTable();
        });
		
        //on click of save
        $('#parameterConfigurationModal .save-parameter-configuration').click(function () {
            if (!validTaxConfiguration()) return false;
            var index = $('#parameterConfigurationModal .selected-index').val();
            if (index) {
                parameterConfigurations[index].index = $('#parameterConfigurationModal .selected-index').val();
                parameterConfigurations[index].parameterName = $('#parameterConfigurationModal .parameterName').val();
                parameterConfigurations[index].parameterValue = $('#parameterConfigurationModal .parameterValue').val();
            } else {
                parameterConfigurations.push({
						index:parameterConfigurations.length,
						id:0,
						parameterName:$('#parameterConfigurationModal .parameterName').val(),
						parameterValue:$('#parameterConfigurationModal .parameterValue').val(),
					});
            }
            $("#parameterConfigurationModal .close").click();
            renderParameterConfigurationTable();
         });
    });
    function renderParameterConfigurationTable() {
        var rows = "";
        $.each(parameterConfigurations, function (index, value) {
            if (value) {
                var row = "<tr>" + parameterConfigurationsRow
                        .replace(/:index/g, index)
                        .replace(/:id/g, value.id)
                        .replace(/:parameterName/g, value.parameterName)
                        .replace(/:parameterValue/g, value.parameterValue)+ "</tr>";
                rows += row;
            }
        });
        $(".parameter-configurations table tbody").html(rows);
    }

    function validTaxConfiguration() {
        var errors = "";
        var errorMessageTemplate = "<label class='label label-important'>:message</label>"

        return true;
    }

    $("input[type='text']").blur(function () {
        $(this).val(specialTrim($(this).val()));
    });
</script>


<div id="parameterConfigurationModal" class="modal hide" tabindex="-1" role="dialog" aria-labelledby="parameterConfigurationModalLabel" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h3 id="myModalLabel">Parameter Configuration</h3>
    </div>
    <div class="modal-body">
        <input type="hidden" class="selected-index" value="">

        <div class="parameter-configuration-errors"></div>
        <label>Parameter Name</label> <input type="text" id="tax-type" class="parameterName" maxlength="200"/>
        <label>Parameter Value</label> <input type="text" id="tax-type" class="parameterValue" maxlength="200"/>
    </div>
    <div class="modal-footer">
        <a class="save-parameter-configuration btn btn-primary"><i class = 'icon-ok'></i> &nbsp Okay</a>
    </div>
</div>