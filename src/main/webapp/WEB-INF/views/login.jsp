<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

 <div class="card-block">
                    <form id="login" class="form-horizontal form-simple" action="${context}/login" method="POST">
                            <input type="text" maxlength="8" class="form-control alphaonly" id="email" name="email" placeholder="User Name" required autofocus="autofocus"/>
                            <input type="password" maxlength="25" class="form-control form-control-lg input-lg" id="password" placeholder="PASSWORD" name="password" required/>
                        <input type="submit" class="btn btn-primary btn-lg btn-block" value="Sign In"/>
                    </form>
                </div>
                
                <c:if test="${(not empty error)}">
                 	      <div class="alert bg-danger alert-icon-left mb-1" role="alert">
				                <strong>${error}</strong>
				            </div>
                 	    </c:if>
                 	    <c:if test="${(not empty msg)}">
                 	      <div class="alert bg-danger alert-icon-left mb-1" role="alert">
				                <strong>${msg}</strong>
				            </div>
                 	    </c:if>
</body>
</html>