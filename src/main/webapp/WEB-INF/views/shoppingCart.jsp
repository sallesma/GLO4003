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
		<p class="text-danger">
				<c:out value="${impossibleChange}" />
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
	        				<td><input type="checkbox" name="ticketId" value="${ticket.id}"></td>
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
		        					<td><a href="#" class="btn btn-success choosePlace" data-toggle="popover" data-placement="left"
                                                                title="Modifier le nombre de tickets" data-html="true"
                                                                data-content='<form:form action="/modifyTicket" method="get" class="form-horizontal" modelAttribute="user"><input type="hidden" name="id" value="${ticket.id}"></input><br>
                                 <input type="text" name="nbPlaceInput" value="${ticket.nbPlaces}"></input>                     
                                <button type="submit" class="btn btn-primary">Valider</button>
                        </form:form>'>Modifier</a></td>   
		        				</c:when>
		        				<c:when test="${ticket.class.simpleName=='InstantiateReservedTicketViewModel'}">
		        					<td>1</td>
		        					<td>${ticket.placement}</td>
		        					<td><a href="#" class="btn btn-success choosePlace" data-toggle="popover" data-placement="left"
                                                                title="Changer de place" data-html="true"
                                                                data-content='<form:form action="/modifyTicket" method="get" class="form-horizontal" modelAttribute="user"><input type="hidden" name="id" value="${ticket.id}"></input><br>
                                 <form:select path="" name="placement" class="form-control">
                                        <form:options items="${ticket.placements}"/>
                                </form:select>
                                <br>                        
                                <button type="submit" class="btn btn-primary">Valider</button>
                                
                        </form:form>'>Modifier</a></td>
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
    
    <c:if test="${paymentOk == true}">
    	<script>
    		confirm("Votre commande a bien été prise en compte.");
    	</script>
    </c:if>
    
  <script type="text/javascript">        

$(".choosePlace").popover();
</script>