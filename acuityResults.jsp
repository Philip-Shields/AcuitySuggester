<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
  
  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>

<head>




<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ontology inference project</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/drop.css">
<link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Open+Sans|Space+Mono">
<link href="https://fonts.googleapis.com/css?family=Open+Sans|Space+Mono" rel="stylesheet">
</head>
<body background="${pageContext.request.contextPath}/images/rockBackground.gif">

<script language="javascript" type="text/javascript"></script>
<table border="0" align="center" width="1000">



<tr>
<td><img src="${pageContext.request.contextPath}/images/banner1.jpg"></td>
</tr>

   
</table>




<p>
<div align="center">
  <center>

<table border="0" width="1000" height="700">
    <tr>
      <td width="510" height="579"><b><font color="blue">Output</font></b><br><p>
      
      Ontologies are very good at categorising people and things. In this case, it puts people in care areas inside the ontology.<br>
      
      <b><font color="blue">I have categorised </font>
      
      <%! String patient = null; %>
       <b><Font color="blue"><%=request.getAttribute("patient")%> in the following care areas:</b></Font><br>
      <p>
      
     <c:forEach var="i" begin = "0" step="1" end="${fn:length(severity)}">
       <c:out value= "${severity[i]}"/><br>
      </c:forEach>
      
      <p>
      Ontologies are also good at inferring knowledge from knowledge you provide. In this case, it suggests nurses with appropriate qualifications, suggests a hospital unit and flags risks.<br> 
      <b><font color="blue">I have inferred the following:</font></b><br><p>
      
      total score= "${ptTotal}"/100<br><p>
       
      <c:forEach var="i" begin = "0" step="1" end="${fn:length(nurses)}">
       <c:out value= "${nurses[i]}"/><br>
      </c:forEach>
      <p>
         <c:forEach var="i" begin = "0" step="1" end="${fn:length(allied)}">
       <c:out value= "${allied[i]}"/><br>
      </c:forEach>
      <p>  
      
 <c:forEach var="i" begin = "0" step="1" end="${fn:length(unit)}">
         <c:out value= "${unit[i]}"/><br>
      </c:forEach>
<p>        

 <c:forEach var="i" begin = "0" step="1" end="${fn:length(suggestion)}">
         <c:out value= "${suggestion[i]}"/><br>
      </c:forEach>
<p>
    

 <c:forEach var="i" begin = "0" step="1" end="${fn:length(risk)}">
       <b><font color="red">  <c:out value= "${risk[i]}"/></b></font><br>
      </c:forEach>
      </td>
      
    </tr>
  </table>

</center>
</div>

<p>

  
</body>
