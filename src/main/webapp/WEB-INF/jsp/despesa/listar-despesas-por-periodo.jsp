<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<head>
	<link rel="stylesheet" href="../resource/css/jquery-ui.css" />
	<link href="<c:url value="/resource/css/divDinamica.css"/>" rel="stylesheet">
	<script src="../resource/js/jquery-1.11.3.js"></script>
	<script src="../resource/js/jquery-ui.js"></script>
	<script type="text/javascript" src="../resource/js/datepickerPB.js" charset="UTF-8"></script>
	<script type="text/javascript" src="../resource/js/formataData.js" charset="UTF-8"></script><base>
	<script src="../resource/js/pager.js"></script>
	<link href="<c:url value="/resource/css/pager.css"/>" rel="stylesheet">
	
</head>
<body style="background-image: url('<c:url value="/resource/images/background6.png"/>');">
	<br>
	<table id="tabelaDespesas" class="table">
		<thead>
			<tr >
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
				<th>
					<p align=center></p>
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
				<c:if test="${despesa.estadoDespesa == 'PAGO_PARCIAL'}">
					<tr class="warning">
				</c:if>
				<c:set var="totalDespesa" value="${despesa.valorDespesa + totalDespesa}" />
				<td align=center><fmt:formatDate value="${despesa.dataDespesa.time}" pattern="dd/MM/yyyy" />
				</td>
				<td align=center><fmt:formatDate value="${despesa.dataVencimento.time}" pattern="dd/MM/yyyy" />
				</td>
				<td align=center>${despesa.credor.nomeFantasia}</td>
				<td align=center>${despesa.categoria.descricao}</td>
				<td align=center><fmt:formatNumber type="currency" value="${despesa.valorDespesa}" /></td>
				<td align=center>${despesa.numParcelas}</td>
				<td align=center><fmt:formatNumber type="currency" value="${despesa.valorPago}" /></td>
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
					    <td>
						    <div class="dropdown pull-right">
								<button class="btn btn-primary dropdown-toggle" 
										type="button" id="dropdownMenu1"
										data-toggle="dropdown" 
										aria-haspopup="true"
										aria-expanded="true">Ações
									<span class="glyphicon glyphicon-collapse-down"></span>
								</button>								  
								<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
									<li>
										<a href="form-altera?id=${despesa.id}">
										 	<span class="glyphicon glyphicon-pencil pull-right"></span> Editar 
										</a>
									</li>
									<li> 
										<a href="remover?id=${despesa.id}">
											<span class="glyphicon glyphicon-remove pull-right" style="color:red"></span> Excluir 
										</a>
									</li>
									<li>
										<a class="efetuarPagamento" href="#" onclick="efetuaClick(${despesa.id})">
											<span class="glyphicon glyphicon-usd pull-right" style="color:green" ></span> Pagar 
										</a>
									</li>
								</ul>
							</div>
						</td>
					</c:when>
				</c:choose>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<td align=center>
					<label> Valor Total</label>
				</td>
				<td></td>
				<td></td>
				<td></td>
				<td align=center>
					<label>
						<fmt:formatNumber type="currency" value="${totalDespesa}" />
					</label>
				</td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
		</tfoot>
	</table>

	<div id="transparencia">
		<div id="divPrincipal" >
		    <label>Valor Pagamento</label>
		    <br>
		    <input id="valorPagamento" type = "text" style="width: 178px" class="form-control"/>
	    	<label>Data do Pagamento</label>
	    	<br>
	    	<input id="dataPagamento" 
	    			style="width: 178px" 
	    			type="text" 
	    			class="form-control calendario" 
	    			OnKeyPress="formatar(this)" 
	    			onBlur="return doDateVenc(this.id,this.value, 4);" 
	    			maxlength="10" />
	   		<br>
	   		<input id="pagar" 
	   			   type="submit"
	   			   class="btn btn-success btn-md"
	   			   value="Salvar"/>
	   	    <input id="cancelar" type="button" class="btn btn-danger btn-md" value="Cancelar"/>
		</div>
    </div>
	
	<script type="text/javascript"> 
		function efetuaClick(e){
			var divPai = $('#divPrincipal');
			document.getElementById('transparencia').style.display='block';
			document.getElementById('divPrincipal').style.display='block';
			divPai.append("<input id='idDespesa' type='hidden' value='"+e+"'/> ");
								}
	</script>

	<script type="text/javascript">
		$('#divPrincipal').on('click', '#cancelar', function(e) {
		    $(this).closest("#divPrincipal").css('display','none');
		    $(this).closest("#transparencia").css('display','none');
		});
	 </script>
	 
    <script type="text/javascript">
		$('#divPrincipal').on('click', '#pagar', function(e) {
			$.ajax({
	            type: "POST",
	            url: "efetuar-pagamento",
	            data: {id: $('#idDespesa').val(), valorPagamento : $('#valorPagamento').val(), dataPagamento: $('#dataPagamento').val()},
		        success: function() {               
	                    window.location.href = "listar-todas" 
	            }
	        });
		 	$(this).closest("#divPrincipal").css('display','none');
			$(this).closest("#transparencia").css('display','none'); 
		});
	</script>
</body>
