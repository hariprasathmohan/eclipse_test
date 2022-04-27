<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome</title>
</head>
<body>

<s:form action="test">
<s:textfield key="name" label="Name"></s:textfield>
<s:submit name="submit" label="Login"></s:submit>
</s:form>

</body>
</html>