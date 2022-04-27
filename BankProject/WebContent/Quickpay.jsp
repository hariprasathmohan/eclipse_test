<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ include file="MainPage.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="style.css">
<title>Insert title here</title>
</head>
<body>
<div class="main">
    <p class="sign" align="center">Quick pay</p>
    <form class="form1" action="payment" method="post">
      <input class="un " type="text" name="name" placeholder="Name" required="required">
      <input class="un " type="text" name="accountNumber" placeholder="Account Number" required="required">
      <input class="un " type="text" name="ifsc" placeholder="IFSC code" required="required">
      <input class="un " type="text" name="purpose" placeholder="Purpose" required="required">
      <input class="un " type="number" name="amount" placeholder="Amount" required="required">
      <input class="submit" type="submit" value="Pay" /> <br><br>     
    </form>
    <br>
</div>
</body>
</html>