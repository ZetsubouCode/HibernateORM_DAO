<%@page import="com.servlet.util.Result"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="row">
    <div class="col-xs-12">
        <%
            Result res = (Result)request.getAttribute("res");
            String msg = (String)request.getAttribute("msj");
            if (msg != null) {
        %>
        <div class="alert alert-<%= (res == Result.Exit) ? "success" : ((res == Result.Error) ? "danger" : "warning") %> alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <strong><%= (res == Result.Exit) ? "Info" : ((res == Result.Error) ? "Error" : "Alert") %>:</strong> <%=msg%>
        </div>
        <%
            }
        %>
    </div>
</div>
