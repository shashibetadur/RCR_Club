<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="nk" uri="/WEB-INF/custom-tags.tld" %>
<div class="row well">
    <legend>Membership Type List</legend>
    <c:if test="${fn:length(memberTypeList) > 0}">
        <table class="nk-table table table-bordered table-striped">
            <thead>
            <th></th>
            <th>Name</th>
            <th>Validity(Months)</th>
            <th>Enrollment Fees</th>
            <th>Fees</th>
            </thead>
            <tbody>
            <c:forEach var="memberType" items="${memberTypeList}">
                <tr>
                    <td><input type='radio' name='row' value='${memberType.id}'/></td>
                    <td><label>${memberType.name}</label></td>
                    <td><label>${memberType.validity}</label></td>
                    <td><label>${memberType.enrollmentFees}</label></td>
                    <td><label>${memberType.fees}</label></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
    <div class="btn-group">
        <nk:security operations="member-type-create">
            <a href="<%=request.getContextPath()%>/member/type/createForm" class="btn  add-member-type"><i class = 'icon-plus'></i> &nbsp Add New</a>
        </nk:security>
        <nk:security operations="member-type-edit">
            <a href="<%=request.getContextPath()%>/member/type/editForm" class="btn  edit-member-type"><i class = 'icon-edit'></i> &nbsp Edit</a>
        </nk:security>
        <nk:security operations="member-type-delete">
            <a href="<%=request.getContextPath()%>/member/type/deleteForm" class="btn  delete-member-type"><i class = 'icon-trash'></i> &nbsp Delete</a>
        </nk:security>
    </div>
    <br/><br/>
</div>
<div id="modal" class="modal hide" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h3 id="modalLabel"></h3>
    </div>
    <div class="modal-body"></div>
</div>
<script type="text/javascript">
    $(function () {
        function showModal(urlToLoad, headerLabel) {
            $.ajax({type:'GET', url:urlToLoad, success:function (data) {
                $("#modal .modal-body").html(data);
            }});
            $("#modal #modalLabel").html(headerLabel);
            $("#modal").modal('show');
        }

        $(".add-member-type").click(function (event) {
            event.preventDefault();
            showModal($(this).attr('href'), "New Membership Type");
        });

        $(".edit-member-type").click(function (event) {
            event.preventDefault();
            var selectedIndex = $('input:radio[name=row]:checked').val();
            if (selectedIndex > 0) {
                showModal($(this).attr('href') + "/" + selectedIndex, "Edit Membership Type");
            }
        });
        $(".delete-member-type").click(function (event) {
            event.preventDefault();
            var selectedIndex = $('input:radio[name=row]:checked').val();
            if (selectedIndex > 0) {
                showModal($(this).attr('href') + "/" + selectedIndex, "Delete Membership Type");
            }
        });
        $('#modal').on('hidden', function () {
            location.reload();
        });
    });
</script>



