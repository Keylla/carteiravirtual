package br.com.ifce.jwallet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import br.com.ifce.jwallet.controller.AutenticacaoController;
import br.com.ifce.jwallet.exception.DaoException;
import br.com.ifce.jwallet.model.Credor;
import br.com.ifce.jwallet.model.Usuario;

public class CredorDao {
	
	private Connection con;
	private PreparedStatement pstm;
	HttpSession session;
	Usuario userName;
	public CredorDao(){
		this.session = AutenticacaoController.getUsuarioSessao();
		this.userName = (Usuario) this.session.getAttribute("usuarioLogado");
		try {
			this.con = ConnectionFactory.getConnection();
		
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
	}

	public void insert(Credor credor) {
		
		String sql = "INSERT INTO TB_CREDOR (ID_CREDOR, NOME_FANTASIA, ENDERECO, ID_USUARIO) VALUES (NEXTVAL('seq_credor'),?,?,?)";
		
		try {
			
			pstm = con.prepareStatement(sql);
			pstm.setString(1, credor.getNomeFantasia());
			pstm.setString(2, credor.getEndereco());
			pstm.setLong(3, userName.getId());
			
			pstm.execute();		
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			ConnectionFactory.closeConnection(con, pstm);
		}
	}
	
	public void update(Credor credor) {
		
		String sql = "UPDATE TB_CREDOR SET NOME_FANTASIA = ?, ENDERECO = ?, ID_USUARIO = ?   WHERE ID_CREDOR = ?";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, credor.getNomeFantasia());
			pstm.setString(2, credor.getEndereco());
			pstm.setLong(3, userName.getId());
			pstm.setLong(4, credor.getId());
			pstm.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao atualizar credor no banco de dados.");
		} finally {
			ConnectionFactory.closeConnection(con, pstm);
		}
		
	}
	
	public void delete(Credor credor) {
		
		try {
			
			String sql = "DELETE FROM TB_CREDOR WHERE ID_CREDOR = ?";
			
			pstm = con.prepareStatement(sql);
			pstm.setLong(1, credor.getId());
			pstm.execute();

		} catch (Exception e) {
			
			e.printStackTrace();
			throw new RuntimeException("Erro ao excluir credor no banco de dados.");
		} finally {
			ConnectionFactory.closeConnection(con, pstm);
		}
		
	}
	
	public List<Credor> selectAll() {
		
		List<Credor> credores = new ArrayList<Credor>();
		String sql = "SELECT * FROM TB_CREDOR WHERE ID_USUARIO = ?  ORDER BY NOME_FANTASIA";
		
		
		try {
			
			pstm = con.prepareStatement(sql);
			pstm.setLong(1, userName.getId());
			ResultSet rs = pstm.executeQuery();
			while (rs.next()){
				
				Credor credor = new Credor();
				credor.setId(rs.getLong(1));
				credor.setNomeFantasia(rs.getString(2));
				credor.setEndereco(rs.getString(3));
				credor.setId_usuario(rs.getLong(4));
				credores.add(credor);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			ConnectionFactory.closeConnection(con, pstm);
		}
		
		return credores;
		
	}
	
	public Credor selectById(Long id) {

		Credor credor = null;
		String sql = "SELECT * FROM TB_CREDOR WHERE ID_CREDOR = ?";
		
		
		try {
			
			pstm = con.prepareStatement(sql);
			pstm.setLong(1, id);
			
			
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				
			  credor = new Credor();
			  credor.setNomeFantasia(rs.getString(2));
			  credor.setEndereco(rs.getString(3));
			  credor.setId_usuario(rs.getLong(4));
			  credor.setId(rs.getLong(1));
			  
			}
							
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("N�o foi possivel selecionar informa��es do banco de dados.");
		} finally {
			ConnectionFactory.closeConnection(con, pstm);
		}
		
		return credor;
		
	}
	
	
}
