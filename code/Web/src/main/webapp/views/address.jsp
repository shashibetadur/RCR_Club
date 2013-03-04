<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript">
    var addressList=[];
    var addressTemplateRow=
                "<td>"
                    +"<input type='radio' name='address-row' value=':index'/>"
                    +"<input type='hidden' name='personalDetails.addressList[:index].id' value=':id'/>"
                    +"<input type='hidden' name='personalDetails.addressList[:index].personId' value=':personId'/>"
                +"</td>"
                +"<td>"
                    +"<label>:addressLineOne</label>"
                    +"<input type='hidden' name='personalDetails.addressList[:index].addressLineOne' value=':addressLineOne'/>"
                +"</td>"
                +"<td>"
                    +"<label>:addressLineTwo</label>"
                    +"<input type='hidden' name='personalDetails.addressList[:index].addressLineTwo' value=':addressLineTwo'/>"
                +"</td>"
                +"<td>"
                    +"<label>:locality</label>"
                    +"<input type='hidden' name='personalDetails.addressList[:index].locality' value=':locality'/>"
                +"</td>"
                +"<td>"
                    +"<label>:city</label>"
                    +"<input type='hidden' name='personalDetails.addressList[:index].city' value=':city'/>"
                +"</td>"
                +"<td>"
                    +"<label>:pinCode</label>"
                    +"<input type='hidden' name='personalDetails.addressList[:index].pinCode' value=':pinCode'/>"
                +"</td>"
                +"<td>"
                    +"<label>:state</label>"
                    +"<input type='hidden' name='personalDetails.addressList[:index].state' value=':state'/>"
                +"</td>"
                +"<td>"
                    +"<label>:landmark</label>"
                    +"<input type='hidden' name='personalDetails.addressList[:index].landmark' value=':landmark'/>"
                +"</td>"
                +"<td>"
                    +"<label>:addressType</label>"
                    +"<input type='hidden' name='personalDetails.addressList[:index].addressType' value=':addressType'/>"
                +"</td>"
                +"<td>"
                    +"<label>:notes</label>"
                    +"<input type='hidden' name='personalDetails.addressList[:index].notes' value=':notes'/>"
                +"</td>";


    $(document).ready(function(){
         <c:forEach varStatus="index" var="address" items="${personalDetails.addressList}">
            addressList.push({
            index : ${index.count}-1,
            id : "${address.id}",
            personId : "${address.personId}",
            addressLineOne:"${address.addressLineOne}",
            addressLineTwo:"${address.addressLineTwo}",
            locality:"${address.locality}",
            city:"${address.city}",
            pinCode:"${address.pinCode}",
            state:"${address.state}",
            landmark:"${address.landmark}",
            addressType:"${address.addressType}",
            notes:"${address.notes}"});
         </c:forEach>


        renderAddressTable();

        //on click of add
        $('.nk-address-list .add').click(function(){
            $("#addressModal input").val("");
            $('#addressModal').modal('show');
        });

        //on click of edit
        $('.nk-address-list .edit').click(function(){
            var selectedIndex = $('input:radio[name=address-row]:checked').val();
            if(!selectedIndex) return;
            $('#addressModal .selected-index').val(addressList[selectedIndex].index);
            $('#addressModal .address-line-one').val(addressList[selectedIndex].addressLineOne);
            $('#addressModal .address-line-two').val(addressList[selectedIndex].addressLineTwo);
            $('#addressModal .locality').val(addressList[selectedIndex].locality);
            $('#addressModal .city').val(addressList[selectedIndex].city);
            $('#addressModal .pin-code').val(addressList[selectedIndex].pinCode);
            $('#addressModal .state').val(addressList[selectedIndex].state);
            $('#addressModal .landmark').val(addressList[selectedIndex].landmark);
            $('#addressModal .address-type').val(addressList[selectedIndex].addressType);
            $('#addressModal .notes').val(addressList[selectedIndex].notes);
            $('#addressModal').modal('show');
        });

        //on click of delete
        $('.nk-address-list .delete').click(function(){
            var selectedIndex =$('input:radio[name=address-row]:checked').val();
            addressList.splice(selectedIndex,1);
            renderAddressTable();
        });
        //on click of save
        $('#addressModal .save-address').click(function(){
            if (!validAddressDetails()) return;
            var index= $('#addressModal .selected-index').val();
            if(index){
                addressList[index].index=$('#addressModal .selected-index').val();
                addressList[index].addressLineOne=$('#addressModal .address-line-one').val();
                addressList[index].addressLineTwo=$('#addressModal .address-line-two').val();
                addressList[index].locality=$('#addressModal .locality').val();
                addressList[index].city=$('#addressModal .city').val();
                addressList[index].pinCode=$('#addressModal .pin-code').val();
                addressList[index].state=$('#addressModal .state').val();
                addressList[index].landmark=$('#addressModal .landmark').val();
                addressList[index].addressType=$('#addressModal .address-type').val();
                addressList[index].notes=$('#addressModal .notes').val();
            }else{
                addressList.push({
                index : addressList.length,
                id : 0,
                personId : 0,
                addressLineOne:$('#addressModal .address-line-one').val(),
                addressLineTwo:$('#addressModal .address-line-two').val(),
                locality:$('#addressModal .locality').val(),
                city:$('#addressModal .city').val(),
                pinCode:$('#addressModal .pin-code').val(),
                state:$('#addressModal .state').val(),
                landmark:$('#addressModal .landmark').val(),
                addressType:$('#addressModal .address-type').val(),
                notes:$('#addressModal .notes').val()});
            }
            $("#addressModal .close").click();
            renderAddressTable();
        });


    });

    function validAddressDetails() {
        var errors = "";
        var errorMessageTemplate = "<label class='label label-important'>:message</label>"
        $(".address-edit-errors").html("");
        if (isEmpty(jqVal(".address-line-one")) && isEmpty(jqVal(".address-line-two"))) {
            errors += errorMessageTemplate.replace(/:message/g, "Address cannot be empty")
        }
        if (isEmpty(jqVal(".locality"))) {
            errors += errorMessageTemplate.replace(/:message/g, "Locality cannot be empty")
        }
        if (!isEmpty(jqVal(".locality")) && !canParseOnlyAlphaNumericWithSpace(jqVal(".locality"))) {
            errors += errorMessageTemplate.replace(/:message/g, "Locality can only have alphabets and digits with space")
        }
        if (!isEmpty(jqVal(".city")) && !canParseOnlyAlphabetsWithSpace(jqVal(".city"))) {
            errors += errorMessageTemplate.replace(/:message/g, "City can only have alphabets with space")
        }
        if (!isEmpty(jqVal(".pin-code")) && !canParseOnlyAlphaNumeric(jqVal(".pin-code"))) {
            errors += errorMessageTemplate.replace(/:message/g, "Pin code can only have alphabets digits")
        }
        if (!isEmpty(jqVal(".state")) && !canParseOnlyAlphabetsWithSpace(jqVal(".state"))) {
            errors += errorMessageTemplate.replace(/:message/g, "State can only have alphabets with space")
        }
        if (errors) {
            $(".address-edit-errors").html(errors + "<br/");
            return false;
        }
        return true;
    }

    function renderAddressTable(){
        var rows="";
        $.each(addressList,function(index,value){
            if(value){
                var row = "<tr>"+addressTemplateRow
                .replace(/:index/g,index)
                .replace(/:id/g,value.id)
                .replace(/:personId/g,value.personId)
                .replace(/:addressLineOne/g,value.addressLineOne)
                .replace(/:addressLineTwo/g,value.addressLineTwo)
                .replace(/:locality/g,value.locality)
                .replace(/:city/g,value.city)
                .replace(/:pinCode/g,value.pinCode)
                .replace(/:state/g,value.state)
                .replace(/:landmark/g,value.landmark)
                .replace(/:addressType/g,value.addressType)
                .replace(/:notes/g,value.notes)+"</tr>";
                rows += row;
            }
        });
        $(".nk-address-list table tbody").html(rows);
    }
</script>


<div id="addressModal" class="modal hide" tabindex="-1" role="dialog" aria-labelledby="addressModalLabel" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h3 id="myModalLabel">Modal Heading</h3>
    </div>
    <div class="modal-body">
        <input type="hidden" class="selected-index" value="">
        <div class="address-edit-errors"></div>
        <label>Type</label><select class="address-type"><c:forEach var="addressType" items="${addressTypes}"><option value="${addressType}">${addressType}</option></c:forEach></select>
        <label>Address Line One</label> <input type="text" class="address-line-one" maxlength="200"/>
        <label>Address Line Two</label> <input type="text" class="address-line-two" maxlength="200"/>
        <label>Locality</label> <input type="text" class="locality" maxlength="100"/>
        <label>City</label> <input type="text" class="city" maxlength="100"/>
        <label>Pin Code</label> <input type="text" class="pin-code" maxlength="10"/>
        <label>State</label> <input type="text" class="state" maxlength="100"/>
        <label>Landmark</label> <input type="text" class="landmark" maxlength="200"/>
        <label>Notes</label> <input type="text" class="notes" maxlength="200"/>
    </div>
    <div class="modal-footer">
        <a class="save-address btn btn-primary">Ok</a>
    </div>
</div>
