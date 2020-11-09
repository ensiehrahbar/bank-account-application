<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer Manager Home</title>
    </head>
    <body>
    	<div align="center">
	        <h1>Contact List</h1>
	        <h3><a href="newCustomer">New Customer</a></h3>
	        <table border="1">
	        	<th>No</th>
	        	<th>IBAN</th>
	        	<th>FirstName</th>
	        	<th>LastName</th>
	        	<th>Action</th>
	        	
				<c:forEach var="customer" items="${listCustomer}" varStatus="status">
	        	<tr>
	        		<td>${status.index + 1}</td>
					<td>${customer.iban}</td>
					<td>${customer.lname}</td>
					<td>${customer.fname}</td>
					<td>
						<a href="editCustomer?id=${customer.id}">Edit</a>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="deleteCustomer?id=${customer.id}">Delete</a>
					</td>
							
	        	</tr>
				</c:forEach>	        	
			</table>
    	</div>
    </body>
</html>
