<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page import="model.UserViewModel"%>

<%@ include file="header.jsp"%>

<div class="container">

	<h1>Matchs à venir</h1>
	<div class="row">
		<div class="col-md-3">
			<h2>Filtre</h2>
			<p>Choisissez un sport</p>
			<form:form action="/matchsList" method="get" class="form-horizontal"
				modelAttribute="sports">
				<form:select path="" name="sport" class="form-control">
					<form:option value="">Tous les sports</form:option>
					<form:options items="${sports}" />
				</form:select>
				<br>
				<button type="submit" class="btn btn-primary">Filtrer</button>
			</form:form>
		</div>
		
		<div class="col-md-9">
			<h2>Liste des matchs à venir</h2>

			<table class="table table-hover">
				<tr>
					<th>Sport</th>
					<th>Genre</th>
					<th>Date</th>
					<th>Adversaire</th>
					<th>Ville</th>
					<th>Terrain</th>
					<th>Billets disponibles</th>
					<th>Informations</th>
				</tr>
				<c:forEach var="match" items="${matchs}">
					<tr>
						<td>${match.sport}</td>
						<td>${match.gender}</td>
						<td>${match.date}</td>
						<td>${match.adversaire}</td>
						<td>${match.city}</td>
						<td>${match.terrain}</td>
						<td>${match.nbRemainingTickets}</td>
						<td><a href="match?matchID=${match.matchID }">
							<button type="button" class="btn btn-primary">Info</button>
							</a></td>
					</tr>
				</c:forEach>
			</table>
			<p class="text-danger"><c:out value="${noMatch}" /></p>
		</div>
	</div>
</div>

<%@ include file="footer.jsp"%>