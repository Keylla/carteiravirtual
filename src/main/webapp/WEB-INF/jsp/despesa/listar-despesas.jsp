<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon"
	href="<c:url value="/resource/images/icon_pagamento.ico"/>"
	type="image/x-icon" />


<title>CarteiraVirtual - Despesas</title>

<style>
body {
	margin-top: 60px;
}
</style>

</head>

<body
	style="background-image: url('<c:url value="/resource/images/background6.png"/>');">

	<div class="smallDiv">
		<c:import url="../geral/cabecalho.jsp"></c:import>
	</div>

	<div class="container">

		<div class="row">
			<div class="col-lg-12">


				<table>

					<th>Mês <select id="comboMes" class="form-control"
						onload="load">
							<option value="1">Janeiro</option>
							<option value="2">Fevereiro</option>
							<option value="3">Março</option>
							<option value="4">Abril</option>
							<option value="5">Maio</option>
							<option value="6">Junho</option>
							<option value="7">Julho</option>
							<option value="8">Agosto</option>
							<option value="9">Setembro</option>
							<option value="10">Outubro</option>
							<option value="11">Novembro</option>
							<option value="12">Dezembro</option>
					</select>
					</th>
					<th>Ano <select id="comboAno" class="form-control">
							<option value="2014">2014</option>
							<option value="2015">2015</option>
							<option value="2016">2016</option>
					</select>
					</th>

				</table>

				<table id="tabelaDespesas" class="table">

					<thead>

						<tr>
							<th>
								<p align=center>Data Despesa</p>
							</th>
							<th>
								<p align=center>Vencimento</p>
							</th>
							<th>
								<p align=center>Credor</p>
							</th>
							<th>
								<p align=center>Categoria</p>
							</th>
							<th>
								<p align=center>Valor Despesa</p>
							</th>
							<th>
								<p align=center>Parcelas</p>
							</th>
							<th>
								<p align=center>Valor Pago</p>
							</th>
							<th>
								<p align=center>Pagamento</p>
							</th>
							<th>
								<p align=center>Fixa</p>
							</th>

						</tr>

					</thead>


					<tbody>

						<c:forEach var="despesa" items="${despesas}">

							<c:if test="${despesa.estadoDespesa == 'EM_ABERTO'}">
								<tr class="active">
							</c:if>

							<c:if test="${despesa.estadoDespesa == 'EM_ATRASO'}">
								<tr class="danger">
							</c:if>

							<c:if test="${despesa.estadoDespesa == 'PAGO'}">
								<tr class="success">
							</c:if>

							<c:if test="${despesa.estadoDespesa != 'PAGO'}">
								<c:set var="totalDespesa"
									value="${despesa.valorDespesa + totalDespesa}" />
							</c:if>

							<td align=center><fmt:formatDate
									value="${despesa.dataDespesa.time}" pattern="dd/MM/yyyy" /></td>
							<td align=center><fmt:formatDate
									value="${despesa.dataVencimento.time}" pattern="dd/MM/yyyy" />
							</td>
							<td align=center>${despesa.credor.nomeFantasia}</td>
							<td align=center>${despesa.categoria.descricao}</td>
							<td align=center><fmt:formatNumber type="currency"
									value="${despesa.valorDespesa}" /></td>
							<td align=center>${despesa.numParcelas}</td>
							<td align=center><fmt:formatNumber type="currency"
									value="${despesa.valorPago}" /></td>
							<td align=center><fmt:formatDate
									value="${despesa.dataPagamento.time}" pattern="dd/MM/yyyy" /></td>
							<c:if test="${despesa.flagMensal == 'off'}">
								<td align=center>Não</td>
							</c:if>

							<c:if test="${despesa.flagMensal == 'on'}">
								<td align=center>Sim</td>
							</c:if>
							<c:choose>

								<c:when test="${despesa.estadoDespesa != 'PAGO'}">
									<td><a href="form-altera?id=${despesa.id}"> Editar </a></td>
									<td><a href="remover?id=${despesa.id}"> Excluir </a></td>
									<td><a href="efetuar-pagamento?id=${despesa.id}">
											Efetuar Pagamento </a></td>
								</c:when>

							</c:choose>
							</tr>
						</c:forEach>

					</tbody>

					<tfoot>
						<tr>

							<td align=center><label> Valor Total </label></td>
							<td></td>
							<td></td>
							<td></td>
							<td align=center><label><fmt:formatNumber
										type="currency" value="${totalDespesa}" /></label></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>

					</tfoot>


				</table>


			</div>
		</div>

	</div>


	<script type="text/javascript">    

	$(document).ready(function () {
		
		var today = new Date();
		document.getElementById("comboMes").value = today.getMonth()+1;
		document.getElementById("comboAno").value = today.getFullYear();

		$.ajax({
            type: "GET",
            url: "listar-periodo",
            data: {mes: $('#comboMes').val(), ano : $('#comboAno').val()},
            success : function(data){
               	$("#tabelaDespesas").html(data);
            }
        });

	 	$('#comboMes').change(function() {
	 		$.ajax({
                type: "GET",
                url: "listar-periodo",
                data: {mes: this.value, ano : $('#comboAno').val()},
                success : function(data){
                   	$("#tabelaDespesas").html(data);
                }
            });
		});

	 	$('#comboAno').change(function() {
	 		$.ajax({
                type: "GET",
                url: "listar-periodo",
                data: {mes: $('#comboMes').val(), ano : this.value},
                success : function(data){
                   	$("#tabelaDespesas").html(data);
                }
            });
		});

	});
</script>

</body>
</html>