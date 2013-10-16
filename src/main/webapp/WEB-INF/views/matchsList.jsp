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
			<p>Sélectionner vos critères de recherche :</p>
			<form:form action="/matchsList" method="POST" class="form-horizontal" modelAttribute="filter" id="filterForm">
				
				<c:choose>
					<c:when test="${empty filter.sport}">
						<form:select path="sport" name="sport" class="form-control filterSelect">
							<form:option value="">Tous les sports</form:option>
							<c:forEach var="sport" items="${filter.sportsList}">
								<form:option value="${sport}">${sport}</form:option>
								</c:forEach>
						</form:select>
					</c:when>
					<c:otherwise>
						<div class="btn-group">
  							<button type="button" class="btn btn-primary" disabled="disabled">Sport : ${filter.sport}</button>
  							<button type="button" class="btn btn-primary removeFilter">x</button>
  						</div>
  						<input type="hidden" name="sport" value="${filter.sport}">
  						<br>
					</c:otherwise>
				</c:choose>
				<br/>
				<c:choose>
					<c:when test="${empty filter.gender}">
						<form:select path="gender" name="gender" class="form-control filterSelect">
							<form:option value="">Tous les genres</form:option>
							<c:forEach var="gender" items="${filter.genderList}">
								<form:option value="${gender}">${gender}</form:option>
							</c:forEach>
						</form:select>
					</c:when>
					<c:otherwise>
						<div class="btn-group">
  							<button type="button" class="btn btn-primary" disabled="disabled">Genre : ${filter.gender}</button>
  							<button type="button" class="btn btn-primary removeFilter">x</button>
  						</div>
  						<input type="hidden" name="gender" value="${filter.gender}">
  						<br>
					</c:otherwise>
				</c:choose>
				<br/>
				<c:choose>
					<c:when test="${empty filter.opponent}">
						<form:select path="opponent" name="opponent" class="form-control filterSelect">
							<form:option value="">Tous les adversaires</form:option>
							<c:forEach var="opponent" items="${filter.opponentsList}">
								<form:option value="${opponent}">${opponent}</form:option>
							</c:forEach>
						</form:select>
					</c:when>
					<c:otherwise>
						<div class="btn-group">
  							<button type="button" class="btn btn-primary" disabled="disabled">Adversaire : ${filter.opponent}</button>
  							<button type="button" class="btn btn-primary removeFilter">x</button>
  						</div>
  						<input type="hidden" name="opponent" value="${filter.opponent}">
  						<br>
					</c:otherwise>
				</c:choose>
				<br />
				<div class="input-group">
					<span class="input-group-addon">Du</span>
					<form:input id="fromDate" path="fromDate" type="text" class="form-control filterSelect" value="${filter.fromDate}"/>
				</div>
				<br />
				<div class="input-group">
					<span class="input-group-addon">Au</span>
					<form:input id="toDate" path="toDate" type="text" class="form-control filterSelect"/>
				</div>
				<br />
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
				<c:forEach var="match" items="${filter.matchList}">
					<tr>
						<td>${match.sport}</td>
						<td>${match.gender}</td>
						<td>${match.date}</td>
						<td>${match.opponent}</td>
						<td>${match.city}</td>
						<td>${match.field}</td>
						<td>${match.numberRemainingTickets}</td>
						<td><a href="match?matchID=${match.matchID }">
								<button type="button" class="btn btn-primary">Info</button>
						</a></td>
					</tr>
				</c:forEach>
			</table>
			<p class="text-danger">
				<c:out value="${noMatch}" />
			</p>
		</div>
	</div>
</div>


<%@ include file="footer.jsp"%>