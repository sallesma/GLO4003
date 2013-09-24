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

		<h1>Matchs à venir</h1>

		<h2>Filtre</h2>

		<form:form action="/matchs" method="post" class="form-horizontal"
			modelAttribute="sports">
			<form:select path="" class="form-control" onchange="onsubmit()">
				<form:option value="-1">Choissez un sport</form:option>
				<form:options items="${sports}" />
			</form:select>
			<button type="submit" class="btn btn-primary">Filtrer</button>
		</form:form>

		<h2>Liste des matchs à venir</h2>

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