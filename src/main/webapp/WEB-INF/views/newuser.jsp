<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="model.UserViewModel"%>
<head>
<!-- Bootstrap core CSS -->
<link href="./resources/css/bootstrap.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="./resources/css/jumbotron.css" rel="stylesheet">
</head>


<table>
	<ul>
		<c:forEach var="warning" items="${entry.warning}">
			<li><span style="color: #FF0000;"> ${warning} </span></li>

		</c:forEach>
	</ul>
</table>

<form:form method="post" class="form-horizontal" modelAttribute="entry">
	<div class="form-group">
		<label for="name" class="col-lg-2 control-label">Prénom</label>
		<div class="col-lg-3">
			<form:input class="form-control" path="firstName" />
		</div>
	</div>

	<div class="form-group">
		<label for="name" class="col-lg-2 control-label">Nom de
			famille</label>
		<div class="col-lg-3">
			<form:input class="form-control" path="lastName" />
		</div>
	</div>

	<div class="form-group">
		<label for="phone" class="col-lg-2 control-label">Nom
			d'utilisateur</label>
		<div class="col-lg-3">
			<form:input class="form-control" path="username" />
		</div>
	</div>

	<div class="form-group">
		<label for="phone" class="col-lg-2 control-label">Mot de passe</label>
		<div class="col-lg-3">
			<form:input class="form-control" path="password" />
		</div>
	</div>

	<div class="form-group">
		<label for="phone" class="col-lg-2 control-label">Numéro de
			téléphone</label>
		<div class="col-lg-3">
			<form:input class="form-control" path="phoneNumber" />
		</div>
	</div>

	<div class="form-group">
		<label for="address" class="col-lg-2 control-label">Adresse</label>
		<div class="col-lg-3">
			<form:input class="form-control" path="address" />
		</div>
	</div>

	<div class="form-group">
		<div class="col-lg-offset-2 col-lg-10">
			<button type="submit" class="btn btn-default">Submit</button>
		</div>
	</div>
</form:form>

<p></p>
<a href="/">Retourner à la page principale</a>
