<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
  
  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

 
<html>
<script type="text/javascript">

//input the user data page


//validate fields for acuity max 16 min 0
function minmax(value) 
{
    
	if(parseInt(value) < 0 || isNaN(parseInt(value))) 
        return ""; 
    else if(parseInt(value) > 16) 
        return 16; 
    else return value;
}
//validate fields for not empty
window.onload = function () {
	  var form = document.getElementById('aiForm');
	  form.button.onclick = function (){
	    for(var i=0; i < form.elements.length; i++){
	      if(form.elements[i].value === '' && form.elements[i].hasAttribute('required')){
	        alert('There are some required fields!');
	        return false;
	      }
	    }
	  }; 
	};
</script>

<head>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ontology inference project</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/drop.css">
<link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Open+Sans|Space+Mono">
<link href="https://fonts.googleapis.com/css?family=Open+Sans|Space+Mono" rel="stylesheet">
</head>
<body background="${pageContext.request.contextPath}/images/rockBackground.gif">

<script language="javascript" type="text/javascript"></script>
<table border="0" align="center" width="1000" >



<tr>
<td><img src="${pageContext.request.contextPath}/images/banner1.jpg"></td>
</tr>

   
</table>

<form name="queryForm" id="aiForm" method="post" action="AcuityServlet">

  <p>
<div align="left">
  <center>
  <table border="0" width="1000" height="157" >
  
    <tr>
      <td width="1051" height="200">An ontology is a model of some part of the nursing domain. It can categorise things and infer new knowledge using an inbuilt 'reasoner'.<br>
      this is a simple artificial intelligence demonstration using a reasoner and OWL-DL ontology.<br>
      Enter the synthetic patient's data below. That is, select a patient, enter their age and 6 acuity scores.<br>
      Acuity scores measure how well (or poorly) a patient is faring in various areas of function.<br> 
      The scores range from 0 (great) to 16 (very concerning). <br>
       Basically, the reasoner/ontology uses your information to categorise a patient into acuity classes and infer treatment and suggestions.<br>
       Including the best nurse to care for the patient and the best ward. It also flags caution for patients in a 'grey area' of care.<br>
</td>
    </tr>
  </table>
  </center>
</div>
<div align="left">
  <center>
  <table border="0" width="1000" bgcolor="lightblue">
    <tr>
      <td width="514" valign="top">
<b><font color="blue">Input</font></b><p>

<b> Select a patient </b>
<div>
 <select  name="Patients">

 <option value="Patient_Doug">Doug</option>
  <option value="Patient_Joan">Joan</option>
  <option value="Patient_Sophie">Sophie</option>
  <option value="Patient_Zaphod">Zaphod</option>  
 </select><br>
 <p>
 Enter the patient's age in years------->
  <input type="text" name="hasAge"size="3" required><br>
  <p>
  <b>The acuity scores (0-16)<br>
 <font size="2"> 0-4 minimum, 5-8 medium, 9-12 high, 13-16 maximum</font><br></b>
  <p>
  Airway breathing and circulation score->
  <input type="text" name="hasABCValue" size="3" onkeyup="this.value = minmax(this.value)" required><br><p>
  The impact of symptoms score----------->
  <input type="text" name="hasImpactOfSymptomsValue" size="3" onkeyup="this.value = minmax(this.value)" required><br><p>
  The amount of supervision score-------->
  <input type="text" name="hasSupervisionValue" size="3" onkeyup="this.value = minmax(this.value)" required><br><p>
  Feeding score-------------------------->
  <input type="text" name="hasFeedingValue" size="3" onkeyup="this.value = minmax(this.value)" required><br><p>
  Hygiene and toileting score------------>
  <input type="text" name="hasHygieneToiletingValue"  size="3" onkeyup="this.value = minmax(this.value)" required><br><p>
  Mobility score------------------------->
  <input type="text" name="hasMobilityValue" size="3" onkeyup="this.value = minmax(this.value)" required><br><p>
  <p>
  <input type="submit" value="Submit" name="aScores"><input type="reset" value="Reset" name="B2">
</div>
</td>
      

      </table>
      
     
  </center>
</div>


</form>

</body>
