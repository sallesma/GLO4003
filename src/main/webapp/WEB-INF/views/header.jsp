<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page session="false"%>
<%@ page import="model.UserViewModel"%>
<%@ page import="database.DbHelper"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<link rel="shortcut icon" href="./resources/img/favicon.ico">

<title>Rouge et Or</title>

<!-- Bootstrap core CSS -->
<link href="./resources/css/bootstrap.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="./resources/css/jumbotron.css" rel="stylesheet">

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="../../assets/js/html5shiv.js"></script>
      <script src="../../assets/js/respond.min.js"></script>
    <![endif]-->
</head>

<body>

	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="/">Rouge et Or</a>
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li><a href="/">Accueil</a></li>
					<li><a href="matchsList">Matchs</a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">Dropdown <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="#">Action</a></li>
							<li><a href="#">Another action</a></li>
							<li><a href="#">Something else here</a></li>
							<li class="divider"></li>
							<li class="dropdown-header">Nav header</li>
							<li><a href="#">Separated link</a></li>
							<li><a href="#">One more separated link</a></li>
						</ul>
				</ul>			
									
					<% UserViewModel userModel = (UserViewModel) request.getSession().getAttribute("loggedUser"); %>
					<% if (userModel == null) { %>
						<form:form action="/" method="post" class="navbar-form navbar-right" modelAttribute="entry">					
							<div class="form-group">
								<form:input id="connectUsername" placeholder="Username" path="username" class="form-control" />
							</div>
							<div class="form-group">
								<form:input id="connectPassword" type="password" placeholder="Password" path="password" class="form-control" />
							</div>
							<button type="submit" class="btn btn-success">Se connecter</button>
						</form:form>
						<form method="get" action="/newuser" class="navbar-form navbar-right">
							<button type="submit" class="btn btn-primary" onclick="/">S'inscrire</button>
						</form>
					<% } else { %>
						
						<div class="btn-group navbar-right navbar-form">
						
							<button type="button" class="btn btn-default dropdown-toggle"
								data-toggle="dropdown">Bonjour, 
							<%= userModel.getFirstName() %> <span class="caret"></span>
							</button>
							<ul class="dropdown-menu" role="menu">
								<li><a href="settings">Paramètres du compte</a></li>
								<li class="divider"></li>
								<li><a href="/disconnect">Se déconnecter</a></li>
							</ul>
						</div>
						
				<%	}%>
					
			</div>
			<!--/.navbar-collapse -->
		</div>
	</div>