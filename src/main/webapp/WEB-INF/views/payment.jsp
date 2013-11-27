<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>

<%@ include file="header.jsp"%>
<div class="container">
	<h1>Mode de paiement</h1>
	
	<p>Un * indique les champs obligatoires</p>
	
	<form:form action="/payment" method="POST" class="form-horizontal" id="paymentForm">

		<div class="form-group">
			<label for="name" class="col-lg-3 control-label">Type de carte de paiement</label>
			<div class="col-lg-3">
				<select size="1" name="cardType" title="cardType">
					<option value=""></option>
					<option value="Mistercard">Mistercard</option>
					<option value="Vasi">Vasi</option>
					<option value="AmericanExpresso">AmericanExpresso</option>
				</select>
			</div>
		</div>

		<div class="form-group">
			<label for="name" class="col-lg-3 control-label">Titulaire de
				la carte*</label>
			<div class="col-lg-3">
				<input type="text" name="cardOwner" maxlength="35" size="20"
					value="">
			</div>
		</div>

		<div class="form-group">
			<label for="name" class="col-lg-3 control-label">Numéro de la
				carte*</label>
			<div class="col-lg-3">
				<input name="cardNumber" autocomplete="Off" maxlength="20"
					size="20" type="text" class="numberLtr">
			</div>
		</div>

		<div class="form-group">
			<label for="name" class="col-lg-3 control-label">Date
				d'expiration (mm/aaaa)*</label>
			<div class="col-lg-3">
				<select name="expirationMonth" size="1">
					<option value=""></option>
					<option value="01">01</option>
					<option value="02">02</option>
					<option value="03">03</option>
					<option value="04">04</option>
					<option value="05">05</option>
					<option value="06">06</option>
					<option value="07">07</option>
					<option value="08">08</option>
					<option value="09">09</option>
					<option value="10">10</option>
					<option value="11">11</option>
					<option value="12">12</option>

				</select>/<select name="expirationYear" size="1">

					<option value=""></option>
					<option value="2013">2013</option>
					<option value="2014">2014</option>
					<option value="2015">2015</option>
					<option value="2016">2016</option>
					<option value="2017">2017</option>
					<option value="2018">2018</option>
					<option value="2019">2019</option>
					<option value="2020">2020</option>
					<option value="2021">2021</option>
					<option value="2022">2022</option>
					<option value="2023">2023</option>
					<option value="2024">2024</option>
					<option value="2025">2025</option>
					<option value="2026">2026</option>
					<option value="2027">2027</option>
					<option value="2028">2028</option>
					<option value="2029">2029</option>
					<option value="2030">2030</option>
					<option value="2031">2031</option>
					<option value="2032">2032</option>
					<option value="2033">2033</option>
					<option value="2034">2034</option>
					<option value="2035">2035</option>
					<option value="2036">2036</option>
					<option value="2037">2037</option>
					<option value="2038">2038</option>

				</select>
			</div>
		</div>

		<div class="form-group">
			<label for="name" class="col-lg-3 control-label">Code de
				vérification de la carte*</label>
			<div class="col-lg-3">
				<input type="text" name="cardCode" autocomplete="Off"
					size="10" maxlength="10">
			</div>
		</div>

		<button type="submit" class="btn btn-primary">Je confirme mon
			paiement</button>
	</form:form>

</div>

<%@ include file="footer.jsp"%>
