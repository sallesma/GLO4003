<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page import="com.glo4003.project.database.dto.AbstractTicketCategory"%>

<td>
	<c:set var="billetReserve" value="<%=AbstractTicketCategory.RESERVED_TICKET%>" />
	<c:choose>
		<c:when test="${billet.category == billetReserve}">
			<a href="#" class="btn btn-danger addPlace" data-toggle="popover"
				title="Ajouter une place" data-html="true"
				data-content='<form:form action="/addPlacementTickets?matchID=${match.id}" method="get" class="form-horizontal" modelAttribute="match">
				<form:input path="" type="hidden" name="matchID" class="form-control" value="${match.id}"/>
				<form:input path="" type="hidden" name="category" class="form-control" value="${billet.name}"/>  
				<label name="existingTickets">Billets existant</label>
				<form:select path="" name="existingTickets" class="form-control">
					<form:options items="${billet.placements}" />
				</form:select>
				<br/>
				<label name="newTickets">Nouveau billet</label>
				<form:input path="" name="newTicket" class="form-control" placeholder="Nom"/>
	
				<br/>
				<button type="submit" class="btn btn-primary">Ajouter la place</button>
				</form:form>'>
					Ajouter un billet
			</a>
		</c:when>
		
		<c:otherwise>
			<a href="#" class="btn btn-danger addPlace" data-toggle="popover"
				title="Ajouter des places" data-html="true"
				data-content='<form:form action="/addGeneralTickets" method="get" class="form-horizontal" modelAttribute="match">
				<form:input path="" type="hidden" name="matchID" class="form-control" value="${match.id}"/>
				<form:input path="" type="hidden" name="category" class="form-control" value="${billet.name}"/>
				<form:input path="" name="ticketNumber" class="form-control" placeholder="Nombre"/>
				<br>
				<button type="submit" class="btn btn-primary">Ajouter les places</button>
				</form:form>'>
					Ajouter des billets
			</a>
		</c:otherwise>
	</c:choose>
</td>

<script src="./resources/javascript/jquery.js"></script>
<script src="./resources/javascript/bootstrap.min.js"></script>
<script type="text/javascript">	

$(".addPlace").popover();
</script>