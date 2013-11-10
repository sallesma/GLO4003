<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

	<%@ include file="header.jsp" %>
	

      <div class="container">
        <h1>Facture</h1>
        <table class="table table-hover">
				<tr>
					<th>id</th>
					<th>Sport</th>
					<th>Date</th>
					<th>Adversaire</th>
					<th>Ville</th>
					<th>Terrain</th>
					<th>Nombre tickets</th>
					<th>Place</th>
				</tr>
				
        		<c:forEach var="ticket" items="${billTickets}">
        			<tr>
        				<td>${ticket.ticketId}</td>
        				<td>${ticket.match.sport}</td>
        				<td>${ticket.match.date}</td>
        				<td>${ticket.match.opponent}</td>
        				<td>${ticket.match.city}</td>
        				<td>${ticket.match.field}</td>
        				<td>${ticket.nbPlace}</td>
        				<td>${ticket.numPlace}</td>
        			</tr>
        		</c:forEach>
        </table>
        <p class="text-danger">
				<c:out value="${noTicket}" />
		</p>
        
        <a href="/payment"><button type="button" class="btn btn-primary">Valider et payer</button></a>
      </div>

    
    <%@ include file="footer.jsp" %>