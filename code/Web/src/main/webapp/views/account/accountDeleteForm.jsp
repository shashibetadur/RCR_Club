<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<form class="account-delete-form">
    <form:hidden path="account.id"/>
    <div class="account-delete-errors"></div>
    <div>
        <label>Are you sure you want delete [${account.name}] account</label>
    </div>
    <div>
        <a href="#" class="btn btn-small btn-primary submit">Yes</a>
    </div>
</form>
<script type="text/javascript">
    $('.submit').click(function () {
        var formData = $('.account-delete-form').serialize();
        $.ajax({
            type:'POST',
            url:'<%=request.getContextPath()%>/account/delete',
            processData:true,
            async:false,
            timeout:3000,
            data:formData,
            success:function (data) {
                $(".close").click();
            },
            error:function (data) {
                $('account-delete-errors').html("<label class='label error'>Delete Failed!!!</label>")
            }
        });
    });
</script>





