<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
 <body style="background-image: url('<c:url value="/resource/images/background6.png"/>');">       	
	<table class="table">
		<thead>
			<tr>
				<th> <p> Vencimento </p> </th>
				<th> <p> Credor </p> </th>
				<th> <p> Valor Despesa</p> </th>
				<th> <p> </p> </th>	
				<th> <p> </p> </th>
				<th> <p> </p> </th>										
			</tr>
				
		</thead>
		<tbody>
			<c:forEach var="despesa" items="${despesas}">
				<c:if test="${despesa.estadoDespesa == 'EM_ABERTO'}">
					<tr class="default">
				</c:if>
				
				<c:if test="${despesa.estadoDespesa == 'EM_ATRASO'}">
					<tr class="danger">
				</c:if>
					
				<c:if test="${despesa.estadoDespesa == 'PAGO'}">
					<tr class="success">
				</c:if>
					
				<c:if test="${despesa.estadoDespesa != 'PAGO'}">
					<c:set var="totalDespesa" value="${despesa.valorDespesa + totalDespesa}" />
				</c:if>						
					
				<td> <fmt:formatDate value="${despesa.dataVencimento.time}" pattern="dd/MM/yyyy"/>  </td>
				<td> ${despesa.credor.nomeFantasia} </td>	
				<td> <fmt:formatNumber type="currency" value= "${despesa.valorDespesa}" />  </td>

						
				<c:choose>
					 <c:when test="${despesa.estadoDespesa != 'PAGO'}">  
					    <td> <a href="form-altera?id=${despesa.id}"> Editar  </a> </td>
						<td> <a href="remover?id=${despesa.id}"> Excluir </a> </td>  
						<td> <a href="efetuar-pagamento?id=${despesa.id}"> Efetuar Pagamento </a> </td>
					 </c:when>
						 
					 <c:when test="${despesa.estadoDespesa == 'PAGO'}">  
						 <td>  </td>
						  <td> <a href="remover?id=${despesa.id}"> Excluir </a> </td>  
						  <td> Pago </td>
					</c:when>
						 
				</c:choose>		
			</tr>
				</c:forEach>
		</tbody>				
				<tfoot>
					<tr>
					<td> Valor Total </td>
					<td> </td>
					<td> </td>
					<td> <fmt:formatNumber type="currency" value= "${totalDespesa}" />  </td>
					<td> </td>
					<td> </td>
					<td> </td>
					</tr>
				
		</tfoot>
	</table>
</body>
				