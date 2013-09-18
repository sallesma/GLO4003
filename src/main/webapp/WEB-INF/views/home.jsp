<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

	<%@ include file="header.jsp" %>
	
   <div class="jumbotron">
      <div class="container">
        <h1>Accueil</h1>
        <P>  Date : ${serverTime}. </P>
        <p><a class="btn btn-primary btn-lg">Learn more &raquo;</a></p>
      </div>
    </div>
    
    <%@ include file="footer.jsp" %>

