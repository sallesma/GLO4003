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
			<p>Sélectionnez vos critères de recherche :</p>
			<form:form action="/matchsList" method="POST" class="form-horizontal" modelAttribute="filter" id="filterForm">
				
				<c:choose>
					<c:when test="${empty filter.criterias.sport}">
						<form:select path="criterias.sport" name="criterias.sport" class="form-control filterSelect">
							<form:option value="">Tous les sports</form:option>
							<c:forEach var="sport" items="${filter.sportsList}">
								<form:option value="${sport}">${sport}</form:option>
								</c:forEach>
						</form:select>
					</c:when>
					<c:otherwise>
						<div class="btn-group">
  							<button type="button" class="btn btn-primary" disabled="disabled">Sport : ${filter.criterias.sport}</button>
  							<button type="button" class="btn btn-primary removeFilter">x</button>
  						</div>
  						<input type="hidden" name="criterias.sport" value="${filter.criterias.sport}">
  						<br>
					</c:otherwise>
				</c:choose>
				<br/>
				<c:choose>
					<c:when test="${empty filter.criterias.gender}">
						<form:select path="criterias.gender" name="criterias.gender" class="form-control filterSelect">
							<form:option value="">Tous les genres</form:option>
							<c:forEach var="gender" items="${filter.genderList}">
								<form:option value="${gender}">${gender}</form:option>
							</c:forEach>
						</form:select>
					</c:when>
					<c:otherwise>
						<div class="btn-group">
  							<button type="button" class="btn btn-primary" disabled="disabled">Genre : ${filter.criterias.gender}</button>
  							<button type="button" class="btn btn-primary removeFilter">x</button>
  						</div>
  						<input type="hidden" name="criterias.gender" value="${filter.criterias.gender}">
  						<br>
					</c:otherwise>
				</c:choose>
				<br/>
				<c:choose>
					<c:when test="${empty filter.criterias.city}">
						<form:select path="criterias.city" name="criterias.city" class="form-control filterSelect">
							<form:option value="">Toutes les villes</form:option>
							<c:forEach var="city" items="${filter.cityList}">
								<form:option value="${city}">${city}</form:option>
							</c:forEach>
						</form:select>
					</c:when>
					<c:otherwise>
						<div class="btn-group">
  							<button type="button" class="btn btn-primary" disabled="disabled">Genre : ${filter.criterias.city}</button>
  							<button type="button" class="btn btn-primary removeFilter">x</button>
  						</div>
  						<input type="hidden" name="criterias.city" value="${filter.criterias.city}">
  						<br>
					</c:otherwise>
				</c:choose>
				<br/>
				<c:choose>
					<c:when test="${empty filter.criterias.category}">
						<form:select path="criterias.category" name="criterias.category" class="form-control filterSelect">
							<form:option value="">Toutes les catégories</form:option>
							<c:forEach var="category" items="${filter.ticketCategory}">
								<form:option value="${category}">${category}</form:option>
							</c:forEach>
						</form:select>
					</c:when>
					<c:otherwise>
						<div class="btn-group">
  							<button type="button" class="btn btn-primary" disabled="disabled">Genre : ${filter.criterias.category}</button>
  							<button type="button" class="btn btn-primary removeFilter">x</button>
  						</div>
  						<input type="hidden" name="criterias.category" value="${filter.criterias.category}">
  						<br>
					</c:otherwise>
				</c:choose>
				<br/>
				<c:choose>
					<c:when test="${empty filter.criterias.opponent}">
						<form:select path="criterias.opponent" name="criterias.opponent" class="form-control filterSelect">
							<form:option value="">Tous les adversaires</form:option>
							<c:forEach var="opponent" items="${filter.opponentsList}">
								<form:option value="${opponent}">${opponent}</form:option>
							</c:forEach>
						</form:select>
					</c:when>
					<c:otherwise>
						<div class="btn-group">
  							<button type="button" class="btn btn-primary" disabled="disabled">Adversaire : ${filter.criterias.opponent}</button>
  							<button type="button" class="btn btn-primary removeFilter">x</button>
  						</div>
  						<input type="hidden" name="criterias.opponent" value="${filter.criterias.opponent}">
  						<br>
					</c:otherwise>
				</c:choose>
				<br />
				<div class="input-group">
					<span class="input-group-addon">Du</span>
					<form:input id="fromDate" path="criterias.fromDate" type="text" class="form-control filterSelect" value="${filter.criterias.fromDate}"/>
				</div>
				<br />
				<div class="input-group">
					<span class="input-group-addon">Au</span>
					<form:input id="toDate" path="criterias.toDate" type="text" class="form-control filterSelect"/>
				</div>
				<br />				
					<% if (userModel != null) { %>
					<hr style="width:200px;" /> 
					<div class="input-group">					
						<span class="input-group-addon">Sauvegarder la recherche</span>
					 	<input id="mustSave" name="mustSave" type="text" class="form-control filterSelect"/>					 	 	
					</div>					
					<div class="input-group">					
						<form:select path="customCriteria" name="customCriteria" class="form-control filterSelect">
								<form:option value="">Mes recherches sauvegardés</form:option>
									<c:forEach var="criteria" items="${customCriterias}">
										<form:option value="${criteria.searchName}">${criteria.searchName}</form:option>
									</c:forEach>
						</form:select>									
					</div>	
					
					<% } %> 				
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
						<td><a href="match?matchID=${match.matchID}">
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