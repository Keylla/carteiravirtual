<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<head>
<style>
#pagamento {
	display: none;
	position: absolute;
	top: 50%;
	left: 50%;
	margin-left: -150px;
	margin-top: -100px;
	padding: 10px;
	width: 300px;
	height: 200px;
	border: 1px solid #d0d0d0;
	background: #F8F8F8
}
</style>
<style type="text/css">
#transparencia {
 position: fixed;
 z-index: 9999999;
 background-color: rgba(0,0,0,0.5);
 width: 100%;
 height: 100%;
 top: 0;
 left: 0;
 display: none;
}
</style>

</head>
<body
	style="background-image: url('<c:url value="/resource/images/background6.png"/>');">
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
						value="${despesa.dataVencimento.time}" pattern="dd/MM/yyyy" /></td>
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
						<td><a class="efetuarPagamento" href="#"
							onclick="efetuaClick(${despesa.id})"> Efetuar Pagamento </a></td>
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
	<div id="transparencia"><div id="pagamento" ></div></div>
	

	<script type="text/javascript"> 
	function efetuaClick(e){
		var divPai = $('#pagamento');
		$('#pagamento').empty();
		document.getElementById('transparencia').style.display='block';
		document.getElementById('pagamento').style.display='block';
		divPai.append("<input id='idDespesa' type='hidden' value='"+e+"'/> ");
	    divPai.append("<label>Valor Pagamento</label><br><input id='valorPagamento' type = 'text' style='width: 178px' class='form-control'/>");
	    divPai.append("<label>Data do Pagamento</label> <br> <input  style='width: 178px' type='text'  class='form-control calendario'  id='dataPagamento' maxlength='10' />");
	    divPai.append("<br><input id='pagar' type='submit' class='btn btn-success btn-md' value='Salvar'/><input id='cancelar' type='button' class='btn btn-danger btn-md' value='Cancelar'/>");
	}

	 </script>

	<script type="text/javascript">
	 $('#pagamento').on('click', '#cancelar', function(e) {
		    $(this).closest("#pagamento").css('display','none');
		    $(this).closest("#transparencia").css('display','none');
		    $('#pagamento').empty();
		});
	 </script>

	<script type="text/javascript">
	 
	 $('#pagamento').on('click', '#pagar', function(e) {
		 $.ajax({
	            type: "POST",
	            url: "efetuar-pagamento",
	            data: {id: $('#idDespesa').val(), valorPagamento : $('#valorPagamento').val(), dataPagamento: $('#dataPagamento').val()},
	            success: function() {               
	                    window.location.href = "listar-todas" 
	            }
	        });

		 $(this).closest("#pagamento").css('display','none');
		 $(this).closest("#transparencia").css('display','none');
		 
		});
	 </script>

</body>
