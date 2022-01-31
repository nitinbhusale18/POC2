<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

 <div class="card-block">
                    <form:form  class="form" action="${context}/ragister" method="POST" modelAttribute="usrDTO">
                            <form:input type="text" maxlength="8" class="form-control alphaonly" id="userName" path="userName" placeholder="User Name" required autofocus="autofocus"/>
                            <form:input type="text" maxlength="25" class="form-control form-control-lg input-lg" id="password" placeholder="PASSWORD" path="password" required/>
                            <br>
                        <button type="submit" class="btn btn-primary blockuie">Submit</button>
                    </form:form>
                </div>
                
                
</body>
</html>