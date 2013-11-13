<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

	<%@ include file="header.jsp" %>
	

      <div class="container">
      	<br>
     	 <p class="text-danger">
				<c:out value="${nullTicket}" />
		</p>
		<p class="text-danger">
				<c:out value="${noTicket}" />
		</p>
        <h1>Panier d'achat</h1>
        <form:form action="/selectedTicketsAction" method="POST" class="form-horizontal" id="shoppingCartForm">
	        <table class="table table-hover">
					<tr>
						<th></th>
						<th>id</th>
						<th>Sport</th>
						<th>Date</th>
						<th>Adversaire</th>
						<th>Ville</th>
						<th>Terrain</th>
						<th>Nombre tickets</th>
						<th>Place</th>
					</tr>
					
	        		<c:forEach var="ticket" items="${user.tickets}">
	        			<tr>
	        				<td><input type="checkbox" name="ticketId" value="${ticket.ticketId}"></td>
	        				<td>${ticket.ticketId}</td>
	        				<td>${ticket.match.sport}</td>
	        				<td>${ticket.match.date}</td>
	        				<td>${ticket.match.opponent}</td>
	        				<td>${ticket.match.city}</td>
	        				<td>${ticket.match.field}</td>
	        				
	        				<c:choose>
		        				<c:when test="${ticket.class.simpleName=='InstantiateGeneralAdmissionTicket'}">
		        					<td>${ticket.nbPlaces}</td>
		        					<td>Libre</td>
		        				</c:when>
		        				<c:when test="${ticket.class.simpleName=='InstantiateReservedTicket'}">
		        					<td>1</td>
		        					<td>${ticket.numPlace}</td>
		        				</c:when>
		        			</c:choose>
	        				
	 
	        			</tr>
	        		</c:forEach>
	        </table>
		<button type="submit" class="btn btn-primary" name ="action" value="buy">Acheter</button>
		<button type="submit" class="btn btn-danger" name="action" value="delete">Supprimer les tickets séléctionner</button>
	    </form:form>
	    <br>
	    
        <a href="/emptyCart"><button class="btn btn-danger">Vider le panier au complet</button></a>
      </div>

    
    <%@ include file="footer.jsp" %>