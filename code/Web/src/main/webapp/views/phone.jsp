<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript">
    var phoneList=[];
    var phoneTemplateRow=
                "<td>"
                    +"<input type='radio' name='phone-row' value=':index'/>"
                    +"<input type='hidden' name='personalDetails.phoneList[:index].id' value=':id'/>"
                    +"<input type='hidden' name='personalDetails.phoneList[:index].personId' value=':personId'/>"
                +"</td>"
                +"<td>"
                    +"<label>:type</label>"
                    +"<input type='hidden' name='personalDetails.phoneList[:index].phoneType' value=':type'/>"
                +"</td>"
                +"<td>"
                    +"<label>:number</label>"
                    +"<input type='hidden' name='personalDetails.phoneList[:index].phoneNumber' value=':number'/>"
               +"</td>";

    $(document).ready(function(){
         <c:forEach var="phone" items= "${personalDetails.phoneList}" varStatus="index">
            phoneList.push({
            index : ${index.count}-1,
            id : "${phone.id}",
            type:"${phone.phoneType}",
            number:"${phone.phoneNumber}",
            personId:"${phone.personId}"});
         </c:forEach>

        renderPhoneTable();

        //on click of add
        $('.nk-phone-list .add').click(function(){
            $("#phoneModal input").val("");
            $('#phoneModal').modal('show');
        });

        //on click of edit
        $('.nk-phone-list .edit').click(function(){
            var selectedIndex = $('input:radio[name=phone-row]:checked').val();
            if(!selectedIndex) return;
            $('#phoneModal .selected-index').val(phoneList[selectedIndex].index);
            $('#phoneModal .phone-type').val(phoneList[selectedIndex].type);
            $('#phoneModal .phone-number').val(phoneList[selectedIndex].number);
            $('#phoneModal').modal('show');
        });

        //on click of delete
        $('.nk-phone-list .delete').click(function(){
            var selectedIndex =$('input:radio[name=phone-row]:checked').val();
            phoneList.splice(selectedIndex,1);
            renderPhoneTable();
        })
        //on click of save
        $('#phoneModal .save-phone').click(function(){
            if (!validPhoneDetails()) return;
            var index= $('#phoneModal .selected-index').val();
            if(index){
                phoneList[index].index=$('#phoneModal .selected-index').val();
                phoneList[index].type=$('#phoneModal .phone-type').val();
                phoneList[index].number=$('#phoneModal .phone-number').val();
            }else{
                phoneList.push({
                index : phoneList.length,
                id : 0,
                personId : 0,
                type:$('#phoneModal .phone-type').val(),
                number:$('#phoneModal .phone-number').val()});
            }
            $("#phoneModal .close").click();
            $("#phoneModal input").attr('disabled',true);
            renderPhoneTable();
        });

;
    });

    function validPhoneDetails() {
        var errors = "";
        var errorMessageTemplate = "<label class='label label-important'>:message</label>"
        $(".phone-edit-errors").html("");
        if (isEmpty(jqVal("#phone-number"))) {
            errors += errorMessageTemplate.replace(/:message/g, "Phone number cannot be empty");
        }
        if (!isEmpty(jqVal("#phone-number")) && !canParseNumber(jqVal("#phone-number"))) {
            errors += errorMessageTemplate.replace(/:message/g, "Phone number can only have digits");
        }
        if (errors) {
            $(".phone-edit-errors").html(errors + "<br/");
            return false;
        }
        return true;
    }

    function renderPhoneTable(){
        var rows="";
        $.each(phoneList,function(index,value){
            if(value){
                var row = "<tr>"+phoneTemplateRow
                .replace(/:index/g,index)
                .replace(/:id/g,value.id)
                .replace(/:personId/g,value.personId)
                .replace(/:type/g,value.type)
                .replace(/:number/g,value.number)+"</tr>";
                rows += row;
            }
        });
        $(".nk-phone-list table tbody").html(rows);
    }
</script>
<div id="phoneModal" class="modal hide" tabindex="-1" role="dialog" aria-labelledby="phoneModalLabel" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h3 id="myModalLabel">Modal Heading</h3>
    </div>
    <div class="modal-body">
        <input type="hidden" class="selected-index" value="">
        <div class="phone-edit-errors"></div>
        <label>Type</label><select class="phone-type"><c:forEach var="phoneType" items="${phoneTypes}"><option value="${phoneType}">${phoneType}</option></c:forEach></select>
        <label>Number</label> <input type="text" id="phone-number" class="phone-number" maxlength="15"/>
    </div>
    <div class="modal-footer">
        <a class="save-phone btn btn-primary">Ok</a>
    </div>
</div>
