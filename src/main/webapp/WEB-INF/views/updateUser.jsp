<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.glo4003.project.user.model.view.UserViewModel"%>

<%@ include file="header.jsp"%>

<div class="container">

	<ul>
		<c:forEach var="warning" items="${userModel.warning}">
			<li><span style="color: #FF0000;"> ${warning} </span></li>

		</c:forEach>
	</ul>

	<h2>Modification</h2>
	<p>Veuillez modifier les champs dans le formulaire .</p>
	
	<form:form method="post" class="form-horizontal" modelAttribute="userModel">
		<div class="form-group">
			<label for="name" class="col-lg-2 control-label">Prénom</label>
			<div class="col-lg-3">
				<form:input id="newUserFirstName" class="form-control" path="firstName" />
			</div>
		</div>

		<div class="form-group">
			<label for="name" class="col-lg-2 control-label">Nom de
				famille</label>
			<div class="col-lg-3">
				<form:input id="newUserLastName" class="form-control" path="lastName" />
			</div>
		</div>

		<div class="form-group">
			<label for="phone" class="col-lg-2 control-label">Nom d'utilisateur</label>
			<div class="col-lg-3">
				<form:input id="newUserUsername" class="form-control" path="username" readonly="true"/>
				<!-- type="hidden" -->
			</div>
		</div>
		<div class="form-group">
			<label for="email" class="col-lg-2 control-label">Email</label>
			<div class="col-lg-3">
				<form:input id="newEmail" class="form-control" path="email"/>
			</div>
		</div>
		<div class="form-group">
			<label for="phone" class="col-lg-2 control-label">Mot de
				passe</label>
			<div class="col-lg-3">
				<form:input id="newUserPassword" class="form-control" path="password" type="password"/>
			</div>
		</div>

		<div class="form-group">
			<label for="phone" class="col-lg-2 control-label">Numéro de
				téléphone</label>
			<div class="col-lg-3">
				<form:input id="newUserPhoneNumber" class="form-control" path="phoneNumber" />
			</div>
		</div>

		<div class="form-group">
			<label for="address" class="col-lg-2 control-label">Adresse</label>
			<div class="col-lg-3">
				<form:input id="newUserAddress" class="form-control" path="address" />
			</div>
		</div>

		<div class="form-group">
			<div class="col-lg-offset-2 col-lg-10">
				<button id="newUserSubmit"type="submit" class="btn btn-success">Valider</button>
			</div>
		</div>
	</form:form>

</div>

<%@ include file="footer.jsp"%>