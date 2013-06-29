<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="nk" uri="/WEB-INF/custom-tags.tld" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/datepicker.css" type="text/css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap-datepicker.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery-ui-1.9.0.custom.css" type="text/css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui-1.9.0.custom.min.js"></script>
<jq>
    <div>
        <div class="row well">
            <div class="save-status"></div>
            <form class="nk-search-form form-inline">
                <input type="text" class="visit-date input-large" name="visitDate" placeholder="Visit Date"
                       maxlength="15"/>
                <br/><br/>
                <input type="text" id="member-name" class="name input-large" name="name" placeholder="Name"
                       maxlength="100"/>
                <br/><br/>

                <div class="selected-members">
                    <table class="nk-table table table-striped table-bordered">
                        <thead>
                        <th></th>
                        <th>Name</th>
                        <th>Number Of Guests</th>
                        <tbody></tbody>
                    </table>
                    <nk:security operations="member-visit-edit">
                        <a class="btn btn-primary save-visits"><i class = 'icon-ok'></i> &nbsp Save</a>
                        <a class="btn delete-member-visit"><i class = 'icon-trash'></i> &nbsp Delete</a>
                    </nk:security>
                </div>
            </form>
            <div class="list-location"></div>
        </div>
    </div>
</jq>
<script type="text/javascript">
    var selectedMembers = [];
    var selectedMemberTemplate =
            "<td><input type='radio' name='row' value=':index'><input type='hidden' name='memberVisit[:index].memberId' value=':memberId'><input type='hidden' name='memberVisit[:index].id' value=':id'></td>" +
                    "<td><label>:name</label></td>" +
                    "<td><label><input class='guestCount:index' onchange='javascript:updateGuestCount(:index)' name='memberVisit[:index].guestCount' value=':guestCount'></label></td>";
    $('.visit-date').datepicker({
        format:"dd-mm-yyyy",
        endDate:"+0d",
        autoclose:true
    });
    $('.visit-date').change(function(){
        var selectedDate = $(this).val();
        if (selectedDate && selectedDate.trim() != "") {
            selectedMembers = [];
            $(".save-status").html('');
            $.get('<%=request.getContextPath()%>/member/visit/' + selectedDate, [], function (data) {
                $.each(data,function(index,value){
                    selectedMembers.push({
                        member : value.memberDetails,
                        guestCount : value.guestCount,
                        id:value.id
                    });
                });
                renderSelectedMemberTable();
            });
        }
    });
    $('.save-visits').click(function(){
        if(!validateMemberVisitForm()) return false;
        var formData = $('form').serialize();
        $.ajax({
            type:'POST',
            url:'<%=request.getContextPath()%>/member/visit',
            processData:true,
            async:false,
            timeout:3000,
            data:formData
        });
        $(".save-status").html('<label class="label label-success">Saved!!!</label>');
    });

    function validateMemberVisitForm(){
        var errors = "";
        var errorMessageTemplate = "<label class='label label-important'>:message</label>"
        $(".save-status").html("");
        if(isEmpty(jqVal(".visit-date"))){
            errors += errorMessageTemplate.replace(/:message/g,"Visit date cannot be empty");
        }
        if(errors){
            $(".save-status").html(errors+"<br/><br/>");
            return false;
        }
        return true;
    }

    $(".delete-member-visit").click(function(){
        var selectedIndex =$('input:radio[name=row]:checked').val();
        selectedMembers.splice(selectedIndex,1);
        renderSelectedMemberTable();
    });

    function updateGuestCount(selectedIndex){
        selectedMembers[selectedIndex].guestCount = $('.guestCount'+selectedIndex).val();
    }

    $("#member-name").autocomplete({
        source:function (request, response) {
            $.getJSON("<%=request.getContextPath()%>/member/search/name", {
                searchToken:request.term
            }, response);
        },
        appendTo:".list-location",
        select:function (event, ui) {
            selectedMembers.push({
                member:ui.item.member,
                guestCount : 0,
                id:0
            });
            renderSelectedMemberTable();
            $('#member-name').val("");
            return false;
        }
    });

    function renderSelectedMemberTable() {
        var rows = "";
        $.each(selectedMembers, function (index, value) {
            if (value) {
                var row = "<tr>" + selectedMemberTemplate
                        .replace(/:index/g, index)
                        .replace(/:id/g, value.id)
                        .replace(/:memberId/g, value.member.id)
                        .replace(/:name/g, value.member.personalDetails.firstName + " " + value.member.personalDetails.lastName)
                        .replace(/:phoneList/g, value.member.personalDetails.firstName)
                        .replace(/:guestCount/g, value.guestCount)
                        .replace(/:addressList/g, value.member.personalDetails.firstName) + "</tr>";
                rows += row;
            }

        });
        $(".selected-members table tbody").html(rows);
    }

    $("input[type='text']").blur(function () {
        $(this).val($(this).val().trim());
    });
</script>



