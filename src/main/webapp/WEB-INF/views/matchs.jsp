<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page import="model.UserViewModel"%>

<%@ include file="header.jsp"%>

<div class="jumbotron">
	<div class="container">

		<c:forEach var="warning" items="${entry.warning}">
			<c:out value="${warning}" />
			<p></p>
		</c:forEach>

		<table class="table table-hover">
			<tr>
				<th>Sport</th>
				<th>Genre</th>
				<th>Date</th>
				<th>Ville</th>
				<th>Terrain</th>
				<th>Billets VIP</th>
				<th>Billets</th>
			</tr>
			<c:forEach var="match" items="${matchs}">
				<tr>
					<td>${match.sport}</td>
					<td>${match.gender}</td>
					<td>${match.date}</td>
					<td>${match.city}</td>
					<td>${match.terrain}</td>
					<td>${match.vipRemainingTickets}</td>
					<td>${match.normalRemainingTickets}</td>
				</tr>
			</c:forEach>
		</table>

	</div>
</div>

<%@ include file="footer.jsp"%>