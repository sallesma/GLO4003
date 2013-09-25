<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page import="model.UserViewModel"%>

<%@ include file="header.jsp"%>

<div class="container">

	<c:forEach var="warning" items="${entry.warning}">
		<c:out value="${warning}" />
	</c:forEach>

	<h2>Description du match</h2>
	<div class="row">
		<div class="col-md-2">
			<strong>Sport</strong>
		</div>
		<div class="col-md-4">
			<p>${match.sport} ${match.gender}</p>
		</div>
	</div>
	<div class="row">
		<div class="col-md-2">
			<strong>Date</strong>
		</div>
		<div class="col-md-4">
			<p>${match.date}</p>
		</div>
	</div>
	<div class="row">
		<div class="col-md-2">
			<strong>Adversaire</strong>
		</div>
		<div class="col-md-4">
			<p>${match.adversaire}</p>
		</div>
	</div>
	<div class="row">
		<div class="col-md-2">
			<strong>Ville</strong>
		</div>
		<div class="col-md-4">
			<p>${match.city}</p>
		</div>
	</div>
	<div class="row">
		<div class="col-md-2">
			<strong>Terrain</strong>
		</div>
		<div class="col-md-4">
			<p>${match.terrain}</p>
		</div>
	</div>
	<div class="row">
		<div class="col-md-2">
			<strong>Billets disponibles</strong>
		</div>
		<div class="col-md-4">
			<p>${match.nbRemainingTickets}</p>
		</div>
	</div>

	<h2>Détail des billets</h2>

	<table class="table table-hover">
		<tr>
			<th>Catégorie</th>
			<th>Prix</th>
			<th>Billets restants</th>
			<th></th>
			<th></th>
		</tr>
		<c:forEach var="billet" items="${match.billets}">
			<tr>
				<td>${billet.name}</td>
				<td>${billet.price}</td>
				<td>${billet.nbRemainingTickets}</td>
				<td>
					<button type="button" class="btn btn-danger">Achat</button>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>

<%@ include file="footer.jsp"%>