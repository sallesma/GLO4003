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
        				<td>${ticket.id}</td>
        				<td>${ticket.sport}</td>
        				<td>${ticket.date}</td>
        				<td>${ticket.opponent}</td>
        				<td>${ticket.city}</td>
        				<td>${ticket.field}</td>
        				
        				
	        			<c:choose>
		        			<c:when test="${ticket.class.simpleName=='InstantiateGeneralAdmissionTicketViewModel'}">
		        				<td>${ticket.nbPlaces}</td>
		        				<td>Libre</td>
		        			</c:when>
		        			<c:when test="${ticket.class.simpleName=='InstantiateReservedTicketViewModel'}">
		        				<td>1</td>
		        				<td>${ticket.placement}</td>
		        			</c:when>
        				</c:choose>
        			</tr>
        		</c:forEach>
        </table>
        <p class="text-danger">
				<c:out value="${noTicket}" />
		</p>
        
        <a href="/payment"><button type="button" class="btn btn-primary">Valider et payer</button></a>
      </div>

    
    <%@ include file="footer.jsp" %>