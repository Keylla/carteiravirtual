<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
 <body style="background-image: url('<c:url value="/resource/images/background6.png"/>');">       	
			<table id="tabelaDespesas" class="table" >
					
					<thead>
					
					<tr>
					    <th> <p align=center> Data Despesa</p></th>
						<th> <p align=center> Vencimento </p> </th>
						<th> <p align=center> Credor </p> </th>
						<th> <p align=center> Categoria </p> </th>
						<th> <p align=center> Valor Despesa</p> </th>
						<th> <p align=center> Parcelas</p> </th>	
						<th> <p align=center> Valor Pago</p> </th>
						<th> <p align=center> Pagamento</p> </th>
						<th> <p align=center> Fixa</p> </th>						
						
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
						    
						    <td align=center> <fmt:formatDate value="${despesa.dataDespesa.time}" pattern="dd/MM/yyyy"/>  </td>
							<td align=center> <fmt:formatDate value="${despesa.dataVencimento.time}" pattern="dd/MM/yyyy"/>  </td>
							<td align=center> ${despesa.credor.nomeFantasia} </td>					
							<td align=center> ${despesa.categoria.descricao} </td>
							<td align=center> <fmt:formatNumber type="currency" value= "${despesa.valorDespesa}" />  </td>
							<td align=center> ${despesa.numParcelas}</td>
							<td align=center> <fmt:formatNumber type="currency" value= "${despesa.valorPago}" />  </td>
							<td align=center> <fmt:formatDate value="${despesa.dataPagamento.time}" pattern="dd/MM/yyyy"/>  </td>
							<c:if test="${despesa.flagMensal == 'off'}">
								<td align=center>Não</td>
								</c:if>
							
							<c:if test="${despesa.flagMensal == 'on'}">
							   <td align=center>Sim</td>
								</c:if>
							
							<c:choose>
							  <c:when test="${despesa.estadoDespesa != 'PAGO'}">  
							  	<td> <a href="form-altera?id=${despesa.id}"> Editar  </a> </td>
								<td> <a href="remover?id=${despesa.id}"> Excluir </a> </td>  
							  	<td> <a href="efetuar-pagamento?id=${despesa.id}"> Efetuar Pagamento </a> </td>
							  </c:when>
 
							</c:choose>		
						</tr>
					</c:forEach>
					
					</tbody>
					
					<tfoot>
						<tr>
						
						<td align=center><label> Valor Total </label> </td>
						<td> </td>
						<td> </td>
						<td> </td>
						<td align=center><label><fmt:formatNumber type="currency" value= "${totalDespesa}" /></label></td>
						<td> </td>
						<td> </td>
						<td> </td>
						</tr>
					
					</tfoot>
					
					
					</table>
</body>
				