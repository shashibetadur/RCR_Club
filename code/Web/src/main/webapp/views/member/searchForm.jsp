<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div>
    <div class="row well">
        <form class="nk-search-form form-inline">
            <input type="text" class="phone input-large" name="phone" placeholder="Mobile" maxlength="15"/>
            <label class="condition">OR</label>
            <input type="text" class="first-name input-large" name="firstName" placeholder="First Name"
                   maxlength="100"/>
            <input type="text" class="last-name input-large" name="lastName" placeholder="Last Name" maxlength="100"/>
            <br/><br/>
            <a href="#" class="nk-search-button btn btn-small btn-primary"><i class = 'icon-search'></i> &nbsp Search Members</a>
        </form>
    </div>
    <div class="search-results">

    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        $('.nk-search-button').click(function () {
            var formData = $('form').serialize();
            $.ajax({
                type:'POST',
                url:'<%=request.getContextPath()%>/member/searchForm',
                processData:true,
                async:false,
                timeout:3000,
                data:formData,
                success:function (data) {
                    $(".search-results").html(data);
                },
                error:function (data) {
                    $(".search-results").html("error fetching search results");
                }
            });
        });
    });
    $("input[type='text']").blur(function () {
        $(this).val($(this).val().trim());
    });
</script>



