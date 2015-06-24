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

import org.springframework.asm.Type;

import br.com.ifce.jwallet.controller.AutenticacaoController;
import br.com.ifce.jwallet.exception.DaoException;
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
				+ "NUM_PARCELAS, "
				+ "ID_USUARIO) VALUES"
				+ "(NEXTVAL('SEQ_DESPESAS'),?,?,?,?,?,?,?,?,?,?,?,?)";
		
		try {

			pstm = con.prepareStatement(sql);
			pstm.setLong(1, despesa.getCategoria().getId());
			pstm.setLong(2, despesa.getCredor().getId());
			if (despesa.getDetalhe() != null){
				pstm.setString(3, despesa.getDetalhe());
			}
			else{
				pstm.setNull(3, Types.VARCHAR);
			}
			if(despesa.getValorDespesa() !=null){
				pstm.setDouble(4, despesa.getValorDespesa());
			}
			else{
				pstm.setNull(4, Types.DOUBLE);
			}
			pstm.setDate(5, new java.sql.Date(despesa.getDataDespesa().getTimeInMillis()));
			if(despesa.getDataVencimento()!=null){
				pstm.setDate(6, new java.sql.Date(despesa.getDataVencimento().getTimeInMillis()));
			}
			else{
				pstm.setNull(6, Types.DATE);
			}
			pstm.setString(7, EstadoDespesa.EM_ABERTO.toString());
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
				pstm.setNull(10, Types.VARCHAR);
			}
			if(despesa.getFlagParcelado() != null){
				pstm.setInt(11, despesa.getNumParcelas());
			}
			else{
				pstm.setNull(11, Types.INTEGER);
			}
			pstm.setLong(12, userName.getId());
			
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
				+ "NUM_PARCELAS = ?, "
				+ "ID_USUARIO = ? "
				+ "WHERE ID_DESPESA = ?";
		
		try {

			pstm = con.prepareStatement(sql);
			pstm.setLong(1, despesa.getCategoria().getId());
			pstm.setLong(2, despesa.getCredor().getId());
			pstm.setString(3, despesa.getDetalhe());
			pstm.setDouble(4, despesa.getValorDespesa());
			pstm.setDate(5, new java.sql.Date(despesa.getDataDespesa().getTimeInMillis()));
			pstm.setDate(6, new java.sql.Date(despesa.getDataVencimento().getTimeInMillis()));
			pstm.setString(7, EstadoDespesa.EM_ABERTO.toString());
			pstm.setDate(8, new java.sql.Date(despesa.getDataPagamento().getTimeInMillis()));
			pstm.setDouble(9, despesa.getValorPago());
			pstm.setString(10, despesa.getFlagParcelado());
			pstm.setInt(11, despesa.getNumParcelas());
			pstm.setLong(12, despesa.getUsuario().getId());
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
				desp.setCredor(credorDao.selectById(rs.getLong("id_credor")));
				desp.setDetalhe(rs.getString("detalhe_despesa"));
				desp.setValorDespesa(rs.getDouble("vlr_despesa"));
				dataDespesa.setTime(rs.getDate("dt_despesa"));
				desp.setDataDespesa(dataDespesa);
				dataVencimento.setTime(rs.getDate("dt_vencimento"));
				desp.setDataVencimento(dataVencimento);
				desp.setEstadoDespesa(EstadoDespesa.valueOf(rs.getString("estado_despesa")));
				dataPagamento.setTime(rs.getDate("dt_pagamento"));
				desp.setDataPagamento(dataPagamento);
				desp.setValorPago(rs.getDouble("vlr_pago"));
				desp.setFlagParcelado(rs.getString("flag_parcelado"));
				desp.setNumParcelas(rs.getInt("num_parcelas"));
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
	
	
public List<Despesa> selectByPeriodo(int mes){
	
		mes = mes -1 ;

		Calendar dataAtual = Calendar.getInstance();
		
		Calendar periodoInicial = Calendar.getInstance();
		periodoInicial.set(Calendar.MONTH,mes);
		periodoInicial.set(Calendar.DATE,1);
		
		Calendar periodoFinal = Calendar.getInstance();
		periodoFinal.set(Calendar.MONTH,mes);
		periodoFinal.set(Calendar.DATE,1);
		
		
		periodoFinal.add(Calendar.MONTH, 1);
		periodoFinal.add(Calendar.DATE, -1);
		
		List<Despesa> despesas = new ArrayList<Despesa>();
		
		String sql = "SELECT * FROM TB_DESPESAS "
				+ " WHERE ( DT_VENCIMENTO BETWEEN ? AND ?)  OR"
				+ "		( (DT_VENCIMENTO < ?) AND (ESTADO_DESPESA = 'EM_ABERTO'))"
				+ " AND ID_USUARIO = ?"
				+ " ORDER BY DT_VENCIMENTO";
		
		try {

			pstm = con.prepareStatement(sql);
			pstm.setDate(1, new java.sql.Date(periodoInicial.getTimeInMillis()));
			pstm.setDate(2, new java.sql.Date(periodoFinal.getTimeInMillis()));
			pstm.setDate(3, new java.sql.Date(dataAtual.getTimeInMillis()));
			pstm.setLong(4, userName.getId());
			
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
				desp.setCredor(credorDao.selectById(rs.getLong("id_credor")));
				desp.setDetalhe(rs.getString("detalhe_despesa"));
				desp.setValorDespesa(rs.getDouble("vlr_despesa"));
				dataDespesa.setTime(rs.getDate("dt_despesa"));
				desp.setDataDespesa(dataDespesa);
				dataVencimento.setTime(rs.getDate("dt_vencimento"));
				desp.setDataVencimento(dataVencimento);
				desp.setEstadoDespesa(EstadoDespesa.valueOf(rs.getString("estado_despesa")));
				dataPagamento.setTime(rs.getDate("dt_pagamento"));
				desp.setDataPagamento(dataPagamento);
				desp.setValorPago(rs.getDouble("vlr_pago"));
				desp.setFlagParcelado(rs.getString("flag_parcelado"));
				desp.setNumParcelas(rs.getInt("num_parcelas"));
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
			+ "join tb_sub_categoria sc on (d.id_sub_categoria = sc.id_sub_categoria) "
			+ "join tb_categoria c on (c.id_categoria = sc.id_categoria) "
			+ "where (d.dt_vencimento between ? and ?) or "
			+ "   ( (d.dt_vencimento < ?) and (d.estado_despesa = 'EM_ABERTO'))"
			+ " and id_usuario = ?"
			+ "group by c.id_categoria";
	
	try {
		
		pstm = con.prepareStatement(sql);
		pstm.setDate(1, new java.sql.Date(periodoInicial.getTimeInMillis()));
		pstm.setDate(2, new java.sql.Date(periodoFinal.getTimeInMillis()));
		pstm.setDate(3, new java.sql.Date(periodoFinal.getTimeInMillis()));
		pstm.setLong(4, userName.getId());
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
			desp.setCredor(credorDao.selectById(rs.getLong("id_credor")));
			desp.setDetalhe(rs.getString("detalhe_despesa"));
			desp.setValorDespesa(rs.getDouble("vlr_despesa"));
			dataDespesa.setTime(rs.getDate("dt_despesa"));
			desp.setDataDespesa(dataDespesa);
			dataVencimento.setTime(rs.getDate("dt_vencimento"));
			desp.setDataVencimento(dataVencimento);
			desp.setEstadoDespesa(EstadoDespesa.valueOf(rs.getString("estado_despesa")));
			dataPagamento.setTime(rs.getDate("dt_pagamento"));
			desp.setDataPagamento(dataPagamento);
			desp.setValorPago(rs.getDouble("vlr_pago"));
			desp.setFlagParcelado(rs.getString("flag_parcelado"));
			desp.setNumParcelas(rs.getInt("num_parcelas"));
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

public List<Despesa> selectCompetenciaValorByPeriodo(Calendar periodoInicial, Calendar periodoFinal){
	
	List<Despesa> despesas = new ArrayList<Despesa>();
	
	String sql = "select to_char(dt_vencimento,'MM/yyyy') comp, sum(d.vlr_despesa) valor from tb_despesas d "
			+ "where (d.dt_vencimento between ? and ?) or "
			+ "   ( (d.dt_vencimento < ?) and (d.estado_despesa = 'EM_ABERTO')) "
			+" and id_usuario = ?"
			+ "group by comp "
			+ "order by comp";
	
	try {
		
		pstm = con.prepareStatement(sql);
		pstm.setDate(1, new java.sql.Date(periodoInicial.getTimeInMillis()));
		pstm.setDate(2, new java.sql.Date(periodoFinal.getTimeInMillis()));
		pstm.setDate(3, new java.sql.Date(periodoFinal.getTimeInMillis()));
		pstm.setLong(4, userName.getId());
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
				desp.setCredor(credorDao.selectById(rs.getLong("id_credor")));
				desp.setDetalhe(rs.getString("detalhe_despesa"));
				desp.setValorDespesa(rs.getDouble("vlr_despesa"));
				dataDespesa.setTime(rs.getDate("dt_despesa"));
				desp.setDataDespesa(dataDespesa);
				dataVencimento.setTime(rs.getDate("dt_vencimento"));
				desp.setDataVencimento(dataVencimento);
				desp.setEstadoDespesa(EstadoDespesa.valueOf(rs.getString("estado_despesa")));
				dataPagamento.setTime(rs.getDate("dt_pagamento"));
				desp.setDataPagamento(dataPagamento);
				desp.setValorPago(rs.getDouble("vlr_pago"));
				desp.setFlagParcelado(rs.getString("flag_parcelado"));
				desp.setNumParcelas(rs.getInt("num_parcelas"));
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