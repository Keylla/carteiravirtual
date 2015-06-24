package br.com.ifce.jwallet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.ifce.jwallet.exception.DaoException;
import br.com.ifce.jwallet.model.Categoria;

public class CategoriaDao {
	
	private Connection con;
	private PreparedStatement pstm;
	
	public CategoriaDao(){
		
		try {
			this.con = ConnectionFactory.getConnection();
		
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
	}	
	
	
	public void insert(Categoria categoria){
		
		String sql = "INSERT INTO TB_CATEGORIA (ID_CATEGORIA, DESCRICAO) VALUES (NEXTVAL('seq_categoria'),?)";
		
		try {
			
			pstm = con.prepareStatement(sql);
			pstm.setString(1, categoria.getDescricao());
			
			pstm.execute();		
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			ConnectionFactory.closeConnection(con, pstm);
		}		
		
	}
	
	public void update(Categoria categoria){
		
		String sql = "UPDATE TB_CATEGORIA SET DESCRICAO = ? WHERE ID_CATEGORIA = ?";
		
		try {
			
			pstm = con.prepareStatement(sql);
			pstm.setString(1, categoria.getDescricao());
			pstm.setLong(2, categoria.getId());
			
			pstm.execute();		
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			ConnectionFactory.closeConnection(con, pstm);
		}			
		
	}
	
	public void delete(Categoria categoria){

		String sql = "DELETE FROM TB_CATEGORIA WHERE ID_CATEGORIA = ?";
		
		try {
			
			pstm = con.prepareStatement(sql);
			pstm.setLong(1, categoria.getId());
			
			pstm.execute();		
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			ConnectionFactory.closeConnection(con, pstm);
		}			
		
	}
	
	
	public List<Categoria> selectAll(){
		
		List<Categoria> categorias = new ArrayList<Categoria>();
		
		String sql = "SELECT * FROM TB_CATEGORIA ORDER BY DESCRICAO";
		
		try {
			
			pstm = con.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()){
				
				Categoria cat = new Categoria();
				cat.setId(rs.getLong("id_categoria"));
				cat.setDescricao(rs.getString("descricao"));
				
				categorias.add(cat);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			ConnectionFactory.closeConnection(con, pstm);
		}	
		
		return categorias;
	}
	
	
	public Categoria selectById(Long id){
		
		Categoria categoria = new Categoria();
		String sql = "SELECT * FROM TB_CATEGORIA WHERE ID_CATEGORIA = ?";
		
		try {
			
			pstm = con.prepareStatement(sql);
			pstm.setLong(1, id);
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()){
				
				categoria.setId(rs.getLong("id_categoria"));
				categoria.setDescricao(rs.getString("descricao"));

			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			ConnectionFactory.closeConnection(con, pstm);
		}	
				
		return categoria;
	}

}
