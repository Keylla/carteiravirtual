package br.com.ifce.jwallet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import br.com.ifce.jwallet.controller.AutenticacaoController;
import br.com.ifce.jwallet.exception.DaoException;
import br.com.ifce.jwallet.model.Categoria;
import br.com.ifce.jwallet.model.Despesa;
import br.com.ifce.jwallet.model.Despesa.EstadoDespesa;
import br.com.ifce.jwallet.model.Usuario;


public class DespesaDao {

	private Connection con;
	private PreparedStatement pstm;
	HttpSession session;
	Usuario userName;
	public DespesaDao(){
		this.session = AutenticacaoController.getUsuarioSessao();
		this.userName = (Usuario) this.session.getAttribute("usuarioLogado");
		
		try {
			this.con = ConnectionFactory.getConnection();
		
		} catch (DaoException e) {
			e.printStackTrace();
		}
				
		
	}
	
	
	public void insert(Despesa despesa){
		
		String sql = "INSERT INTO TB_DESPESAS (ID_DESPESA, "
				+"ID_CATEGORIA, "
				+ "ID_CREDOR,  "
				+ "DETALHE_DESPESA, "
				+ "VLR_DESPESA, "
				+ "DT_DESPESA ,"
				+ "DT_VENCIMENTO, "
				+ "ESTADO_DESPESA,"
				+ "DT_PAGAMENTO, "
				+ "VLR_PAGO, "
				+ "FLAG_PARCELADO, "
				+ "FLAG_MENSAL, "
				+ "NUM_PARCELAS, "
				+ "ID_USUARIO) VALUES"
				+ "(NEXTVAL('SEQ_DESPESAS'),?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		try {

			pstm = con.prepareStatement(sql);
			pstm.setLong(1, despesa.getCategoria().getId());
			if(despesa.getCredor().getId() != null){
				pstm.setLong(2, despesa.getCredor().getId());
			}
			else{
				pstm.setNull(2, Types.INTEGER);
			} 
			if (despesa.getDetalhe() != null){
				pstm.setString(3, despesa.getDetalhe());
			}
			else{
				pstm.setNull(3, Types.VARCHAR);
			}
			
			pstm.setDouble(4, despesa.getValorDespesa());
			pstm.setDate(5, new java.sql.Date(despesa.getDataDespesa().getTimeInMillis()));
			if(despesa.getDataVencimento()!=null){
				pstm.setDate(6, new java.sql.Date(despesa.getDataVencimento().getTimeInMillis()));
			}
			else{
				pstm.setDate(6, new java.sql.Date(despesa.getDataDespesa().getTimeInMillis()));
			}
			if(despesa.getValorPago() == null || despesa.getValorPago()==0){
			   pstm.setString(7, EstadoDespesa.EM_ABERTO.toString());
			}
			else{
				if(despesa.getValorPago() ==  despesa.getValorDespesa()){
					pstm.setString(7, EstadoDespesa.PAGO.toString());
				}
				else{
					pstm.setString(7, EstadoDespesa.PAGO_PARCIAL.toString());
				}
			}
			if(despesa.getDataPagamento() != null){
				pstm.setDate(8, new java.sql.Date(despesa.getDataPagamento().getTimeInMillis()));
			}
			else{
				pstm.setNull(8, Types.DATE);
			}
			if(despesa.getValorPago() != null){
				pstm.setDouble(9, despesa.getValorPago());
			}
			else{
				pstm.setNull(9, Types.DOUBLE);
			}
			if(despesa.getFlagParcelado() != null){
				pstm.setString(10, despesa.getFlagParcelado());
			}
			else{
				pstm.setString(10, "off");
			}
			if(despesa.getFlagMensal() != null){
				pstm.setString(11, despesa.getFlagMensal());
			}
			else{
				pstm.setString(11, "off");
			}
			if(despesa.getFlagParcelado() != null){
				pstm.setInt(12, despesa.getNumParcelas());
			}
			else{
				pstm.setNull(12, Types.INTEGER);
			}
			pstm.setLong(13, userName.getId());
			
			pstm.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			ConnectionFactory.closeConnection(con, pstm);
		}

	}
	
	public void update(Despesa despesa){
		
		String sql = "UPDATE TB_DESPESAS " 
				+"ID_CATEGORIA = ?, "
				+ "ID_CREDOR  = ?,  "
				+ "DETALHE_DESPESA = ?, "
				+ "VLR_DESPESA = ?, "
				+ "DT_DESPESA = ?, "
				+ "DT_VENCIMENTO = ?, "
				+ "ESTADO_DESPESA = ?, "
				+ "DT_PAGAMENTO = ?, "
				+ "VLR_PAGO = ?, "
				+ "FLAG_PARCELADO = ?, "
				+ "FLAG_MENSAL = ?, "
				+ "NUM_PARCELAS = ? "
				+ "WHERE ID_DESPESA = ?";
		
		try {

			pstm = con.prepareStatement(sql);
			pstm.setLong(1, despesa.getCategoria().getId());
			if(despesa.getCredor().getId() != null){
				pstm.setLong(2, despesa.getCredor().getId());
			}
			else{
				pstm.setNull(2, Types.INTEGER);
			} 
			if (despesa.getDetalhe() != null){
				pstm.setString(3, despesa.getDetalhe());
			}
			else{
				pstm.setNull(3, Types.VARCHAR);
			}
			pstm.setDouble(4, despesa.getValorDespesa());
			pstm.setDate(5, new java.sql.Date(despesa.getDataDespesa().getTimeInMillis()));
			if(despesa.getDataVencimento()!=null){
				pstm.setDate(6, new java.sql.Date(despesa.getDataVencimento().getTimeInMillis()));
			}
			else{
				pstm.setDate(6, new java.sql.Date(despesa.getDataDespesa().getTimeInMillis()));
			}
			if(despesa.getValorPago() == null || despesa.getValorPago()==0){
				   pstm.setString(7, EstadoDespesa.EM_ABERTO.toString());
				}
				else{
					if(despesa.getValorPago() ==  despesa.getValorDespesa()){
						pstm.setString(7, EstadoDespesa.PAGO.toString());
					}
					else{
						pstm.setString(7, EstadoDespesa.PAGO_PARCIAL.toString());
					}
				}
			if(despesa.getDataPagamento() != null){
				pstm.setDate(8, new java.sql.Date(despesa.getDataPagamento().getTimeInMillis()));
			}
			else{
				pstm.setNull(8, Types.DATE);
			}
			if(despesa.getValorPago() != null){
				pstm.setDouble(9, despesa.getValorPago());
			}
			else{
				pstm.setNull(9, Types.DOUBLE);
			}
			if(despesa.getFlagParcelado() != null){
				pstm.setString(10, despesa.getFlagParcelado());
			}
			else{
				pstm.setString(10, "off");
			}
			if(despesa.getFlagMensal() != null){
				pstm.setString(11, despesa.getFlagMensal());
			}
			else{
				pstm.setString(11, "off");
			}
			if(despesa.getFlagParcelado() != null){
				pstm.setInt(12, despesa.getNumParcelas());
			}
			else{
				pstm.setNull(12, Types.INTEGER);
			}

			pstm.setLong(13, despesa.getId());
			pstm.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			ConnectionFactory.closeConnection(con, pstm);
		}		
		
	}
	
	public void delete(Despesa despesa){
		
		String sql = "DELETE FROM TB_DESPESAS WHERE ID_DESPESA = ?";
				
		try {

			pstm = con.prepareStatement(sql);
			pstm.setLong(1, despesa.getId());
			pstm.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			ConnectionFactory.closeConnection(con, pstm);
		}			
		
	}
	
	public List<Despesa> selectAll(){
		
		List<Despesa> despesas = new ArrayList<Despesa>();
		
		String sql = "SELECT * FROM TB_DESPESAS WHERE ID_USUARIO = ? ORDER BY DT_VENCIMENTO";
		
		try {

			pstm = con.prepareStatement(sql);
			pstm.setLong(1, userName.getId());
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()){
				
				Despesa desp = new Despesa();
				CredorDao credorDao = new CredorDao();
				CategoriaDao categoriaDao = new CategoriaDao();
				UsuarioDao usuarioDao = new UsuarioDao();
				Calendar dataDespesa = Calendar.getInstance();
				Calendar dataVencimento = Calendar.getInstance();
				Calendar dataPagamento = Calendar.getInstance();
				
				
				desp.setId(rs.getLong("id_despesa"));
				desp.setCategoria(categoriaDao.selectById(rs.getLong("id_categoria")));
				if(rs.getLong("id_credor")>0){
					desp.setCredor(credorDao.selectById(rs.getLong("id_credor")));				
				}
				desp.setDetalhe(rs.getString("detalhe_despesa"));
				desp.setValorDespesa(rs.getDouble("vlr_despesa"));
				dataDespesa.setTime(rs.getDate("dt_despesa"));
				desp.setDataDespesa(dataDespesa);
				dataVencimento.setTime(rs.getDate("dt_vencimento"));
				desp.setDataVencimento(dataVencimento);
				desp.setEstadoDespesa(EstadoDespesa.valueOf(rs.getString("estado_despesa")));
				if (rs.getDate("dt_pagamento") != null){
				    dataPagamento.setTime(rs.getDate("dt_pagamento"));
					desp.setDataPagamento(dataPagamento);
				}
				if(rs.getDouble("vlr_pago") >0){
					desp.setValorPago(rs.getDouble("vlr_pago"));
				}
				desp.setFlagParcelado(rs.getString("flag_parcelado"));
				desp.setFlagMensal(rs.getString("flag_mensal"));
				if(rs.getInt("num_parcelas")>0){
					desp.setNumParcelas(rs.getInt("num_parcelas"));
				}
				desp.setUsuario(usuarioDao.selectById(rs.getLong("id_usuario")));
				despesas.add(desp);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			ConnectionFactory.closeConnection(con, pstm);
		}			
		
		
		return despesas;
		
	}
	
	
public List<Despesa> selectByPeriodo(int mes, int ano){
	    int mesIndex = mes-1;
		Calendar periodoInicial = Calendar.getInstance();
		periodoInicial.set(Calendar.YEAR,ano);
		periodoInicial.set(Calendar.MONTH,mesIndex);
		periodoInicial.set(Calendar.DATE,1);
		
		Calendar periodoFinal = Calendar.getInstance();
		periodoFinal.set(Calendar.YEAR,ano);
		periodoFinal.set(Calendar.MONTH,mesIndex);
		periodoFinal.set(Calendar.DATE,periodoFinal.getActualMaximum(Calendar.DAY_OF_MONTH));
 		
		List<Despesa> despesas = new ArrayList<Despesa>();
		
		String sql = "SELECT * FROM TB_DESPESAS "
				+ " WHERE  DT_VENCIMENTO BETWEEN ? AND ?  "
				+ "	AND	ESTADO_DESPESA = 'EM_ABERTO'"
				+ " AND ID_USUARIO = ?"
				+ " ORDER BY DT_VENCIMENTO";
		
		try {

			pstm = con.prepareStatement(sql);
			pstm.setDate(1, new java.sql.Date(periodoInicial.getTimeInMillis()));
			pstm.setDate(2, new java.sql.Date(periodoFinal.getTimeInMillis()));
			pstm.setLong(3, userName.getId());
			
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()){
				
				Despesa desp = new Despesa();
				CredorDao credorDao = new CredorDao();
				CategoriaDao categoriaDao = new CategoriaDao();
				UsuarioDao usuarioDao = new UsuarioDao();
				Calendar dataDespesa = Calendar.getInstance();
				Calendar dataVencimento = Calendar.getInstance();
				Calendar dataPagamento = Calendar.getInstance();
				
				
				desp.setId(rs.getLong("id_despesa"));
				desp.setCategoria(categoriaDao.selectById(rs.getLong("id_categoria")));
				if(rs.getLong("id_credor")>0){
					desp.setCredor(credorDao.selectById(rs.getLong("id_credor")));				
				}
				desp.setDetalhe(rs.getString("detalhe_despesa"));
				desp.setValorDespesa(rs.getDouble("vlr_despesa"));
				dataDespesa.setTime(rs.getDate("dt_despesa"));
				desp.setDataDespesa(dataDespesa);
				dataVencimento.setTime(rs.getDate("dt_vencimento"));
				desp.setDataVencimento(dataVencimento);
				desp.setEstadoDespesa(EstadoDespesa.valueOf(rs.getString("estado_despesa")));
				if (rs.getDate("dt_pagamento") != null){
				    dataPagamento.setTime(rs.getDate("dt_pagamento"));
					desp.setDataPagamento(dataPagamento);
				}
				if(rs.getDouble("vlr_pago") >0){
					desp.setValorPago(rs.getDouble("vlr_pago"));
				}
				desp.setFlagParcelado(rs.getString("flag_parcelado"));
				desp.setFlagMensal(rs.getString("flag_mensal"));
				if(rs.getInt("num_parcelas")>0){
					desp.setNumParcelas(rs.getInt("num_parcelas"));
				}
				desp.setUsuario(usuarioDao.selectById(rs.getLong("id_usuario")));
				despesas.add(desp);
		
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			ConnectionFactory.closeConnection(con, pstm);
		}			
		
		
		return despesas;
		
	}
		
public List<Despesa> selectByPeriodo(Calendar periodoInicial, Calendar periodoFinal){
	
	List<Despesa> despesas = new ArrayList<Despesa>();

	String sql = "select c.id_categoria, c.descricao, sum(d.vlr_despesa) total from tb_despesas d "
			+ "join tb_categoria c on (c.id_categoria = d.id_categoria) "
			+ "where d.dt_vencimento between ? and ? "
			+ " and d.estado_despesa = 'EM_ABERTO'"
			+ " and d.id_usuario = ?"
			+ "group by c.id_categoria";
	
	try {
		
		pstm = con.prepareStatement(sql);
		pstm.setDate(1, new java.sql.Date(periodoInicial.getTimeInMillis()));
		pstm.setDate(2, new java.sql.Date(periodoFinal.getTimeInMillis()));
		pstm.setLong(3, userName.getId());
ResultSet rs = pstm.executeQuery();
		
		while(rs.next()){
			
			Despesa desp = new Despesa();

			Categoria categoria = new Categoria();
			categoria.setId(rs.getLong("id_categoria"));
			categoria.setDescricao(rs.getString("descricao"));
			desp.setCategoria(categoria);
			desp.setValorDespesa(rs.getDouble("total"));
			
			despesas.add(desp);
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
		throw new RuntimeException(e);
	} finally {
		ConnectionFactory.closeConnection(con, pstm);
	}			
	
	
	return despesas;
	
}

public List<Despesa> selectCompetenciaValorByPeriodo(Calendar periodoInicial, Calendar periodoFinal){
	
	List<Despesa> despesas = new ArrayList<Despesa>();
	
	String sql = "select to_char(dt_vencimento,'MM/yyyy') comp,to_date('01/'||to_char(dt_vencimento,'MM/yyyy'),'dd/MM/yyyy') dt_vencimento, sum(d.vlr_despesa) valor from tb_despesas d "
			+ "where d.dt_vencimento between ? and ? "
			+ " and d.estado_despesa = 'EM_ABERTO'"
			+ " and id_usuario = ?"
			+ " group by comp"
			+ " order by comp";
	
	try {
		
		pstm = con.prepareStatement(sql);
		pstm.setDate(1, new java.sql.Date(periodoInicial.getTimeInMillis()));
		pstm.setDate(2, new java.sql.Date(periodoFinal.getTimeInMillis()));
		pstm.setLong(3, userName.getId());
		ResultSet rs = pstm.executeQuery();
		
		while(rs.next()){
			
			Despesa desp = new Despesa();
			Calendar dataVencimento = Calendar.getInstance();
			dataVencimento.setTime(rs.getDate("dt_vencimento"));
			desp.setDataVencimento(dataVencimento);
			desp.setValorDespesa(rs.getDouble("valor"));
			
			despesas.add(desp);
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
		throw new RuntimeException(e);
	} finally {
		ConnectionFactory.closeConnection(con, pstm);
	}			
	
	
	return despesas;
	
}

	public Despesa selectById(Long id){
		
		Despesa desp = new Despesa();
		
		String sql = "SELECT * FROM TB_DESPESAS WHERE ID_DESPESA = ? AND ID_USUARIO = ?";
		
		try {

			pstm = con.prepareStatement(sql);
			pstm.setLong(1, id);
			pstm.setLong(2, userName.getId());
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()){
				
				CredorDao credorDao = new CredorDao();
				CategoriaDao categoriaDao = new CategoriaDao();
				UsuarioDao usuarioDao = new UsuarioDao();
				Calendar dataDespesa = Calendar.getInstance();
				Calendar dataVencimento = Calendar.getInstance();
				Calendar dataPagamento = Calendar.getInstance();
				
				
				desp.setId(rs.getLong("id_despesa"));
				desp.setCategoria(categoriaDao.selectById(rs.getLong("id_categoria")));
				if(rs.getLong("id_credor")>0){
					desp.setCredor(credorDao.selectById(rs.getLong("id_credor")));				
				}
				desp.setDetalhe(rs.getString("detalhe_despesa"));
				desp.setValorDespesa(rs.getDouble("vlr_despesa"));
				dataDespesa.setTime(rs.getDate("dt_despesa"));
				desp.setDataDespesa(dataDespesa);
				dataVencimento.setTime(rs.getDate("dt_vencimento"));
				desp.setDataVencimento(dataVencimento);
				desp.setEstadoDespesa(EstadoDespesa.valueOf(rs.getString("estado_despesa")));
				if (rs.getDate("dt_pagamento") != null){
				    dataPagamento.setTime(rs.getDate("dt_pagamento"));
					desp.setDataPagamento(dataPagamento);
				}
				if(rs.getDouble("vlr_pago") >0){
					desp.setValorPago(rs.getDouble("vlr_pago"));
				}
				desp.setFlagParcelado(rs.getString("flag_parcelado"));
				desp.setFlagMensal(rs.getString("flag_mensal"));
				if(rs.getInt("num_parcelas")>0){
					desp.setNumParcelas(rs.getInt("num_parcelas"));
				}
				desp.setUsuario(usuarioDao.selectById(rs.getLong("id_usuario")));
				
		
				
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			ConnectionFactory.closeConnection(con, pstm);
		}			
		
				
		return desp;
	}
	
}