<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page import="com.glo4003.project.user.model.view.UserViewModel"%>
<%@ page import="com.glo4003.project.database.model.AbstractTicketCategory"%>
<%@ include file="header.jsp"%>

<div class="container">

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
                        <p>${match.opponent}</p>
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
                        <p>${match.field}</p>
                </div>
        </div>
        <div class="row">
                <div class="col-md-2">
                        <strong>Billets disponibles</strong>
                </div>
                <div class="col-md-4">
                        <p>${match.numberRemainingTickets}</p>
                </div>
        </div>

        <h2>Détail des billets</h2>

        <table class="table table-hover">
                <tr>
                        <th>Catégorie</th>
                        <th>Placement</th>
                        <th>Prix</th>
                        <th>Billets restants</th>
                        <th></th>
                        <th></th>
                </tr>
                <c:forEach var="billet" items="${match.tickets}" varStatus="status">
                        <tr>
                                <td>${billet.name}</td>
                                <td>${billet.category}</td>                                
                                <td>${billet.price} $</td>
                                <td>${billet.numberRemainingTickets}</td>
                                <td><c:set var="billetReserve" value="<%=AbstractTicketCategory.RESERVED_TICKET%>" />
                                <c:choose>
                                                <c:when test="${billet.category == billetReserve}">
                                                        <a href="#" class="btn btn-success choosePlace" data-toggle="popover"
                                                                title="Choisir votre place" data-html="true"
                                                                data-content='<form:form action="/add" method="get" class="form-horizontal" modelAttribute="match">
                                <form:select path="" name="place" class="form-control">
                                        <form:options items="${billet.placements}" />
                                </form:select>
                                <input type="hidden" name="matchID" value="${match.matchID }"></input>
                                <input type="hidden" name="catID" value="${status.index}"></input>
                                <br>
                                <button type="submit" class="btn btn-primary">Choisir</button>
                        </form:form>' <% if (userModel == null) { %> disabled="true"<%} %>>Ajouter au panier</a>
                                                </c:when>

                                                <c:otherwise>

                                                        <a href="#" class="btn btn-success choosePlace" data-toggle="popover"
                                                                title="Choisir votre place" data-html="true"
                                                                data-content='<form:form action="/add" method="get" class="form-horizontal" modelAttribute="match">
                                <input type="text" name ="nbPlace" placeholder="Nombre de place"></input>
                                <br><br>
                                <input type="hidden" name="matchID" value="${match.matchID }"></input>
                                <input type="hidden" name="catID" value="${status.index}"></input>
                                
                                <button type="submit" class="btn btn-primary">Choisir</button>
                        </form:form>'  <% if (userModel == null) { %> disabled="true"<%} %>>Ajouter au panier</a>
                                                </c:otherwise>
                                        </c:choose></td>
                                        <% if ( (userModel != null ) && (userModel.isAdmin())) { %>
                                                <%@ include file="admin/addTickets.jsp"%>
                                        <% } %>        
                        </tr>
                </c:forEach>
        </table>
</div>
<%@ include file="footer.jsp"%>
<script type="text/javascript">        

$(".choosePlace").popover();
</script>