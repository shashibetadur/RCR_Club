<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="nk" uri="/WEB-INF/custom-tags.tld" %>
<div class="row well">
    <legend>Account List</legend>
    <c:if test="${fn:length(accountList) > 0}">
        <table class="nk-table table table-bordered table-striped">
            <thead>
            <th></th>
            <th>Name</th>
            <th>Number</th>
            </thead>
            <tbody>
            <c:forEach var="account" items="${accountList}">
                <tr>
                    <td style="width:3%"><input type='radio' name='row' value='${account.id}'/></td>
                    <td><label>${account.name}</label></td>
                    <td><label>${account.accountNumber}</label></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
    <div class="btn-group">
        <nk:security operations="account-create">
            <a href="<%=request.getContextPath()%>/account/createForm" class="btn  add-account">Add New</a>
        </nk:security>
        <nk:security operations="account-edit">
            <a href="<%=request.getContextPath()%>/account/editForm" class="btn  edit-account">Edit</a>
        </nk:security>
        <nk:security operations="account-delete">
            <a href="<%=request.getContextPath()%>/account/deleteForm" class="btn  delete-account">Delete</a>
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

        $(".add-account").click(function (event) {
            event.preventDefault();
            showModal($(this).attr('href'), "New Account");
        });

        $(".edit-account").click(function (event) {
            event.preventDefault();
            var selectedIndex = $('input:radio[name=row]:checked').val();
            if (selectedIndex > 0) {
                showModal($(this).attr('href') + "/" + selectedIndex, "Edit Account");
            }
        });
        $(".delete-account").click(function (event) {
            event.preventDefault();
            var selectedIndex = $('input:radio[name=row]:checked').val();
            if (selectedIndex > 0) {
                showModal($(this).attr('href') + "/" + selectedIndex, "Delete Account");
            }
        });
        $('#modal').on('hidden', function () {
            location.reload();
        });
    });
</script>



