package br.com.ifce.jwallet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.ifce.jwallet.exception.DaoException;
import br.com.ifce.jwallet.model.Usuario;

public class UsuarioDao {
	
	private Connection con;
	private PreparedStatement pstm;
	
	public UsuarioDao(){
		
		try {
			this.con = ConnectionFactory.getConnection();
		
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
	}

	public void insert(Usuario usuario) {
		
		String sql = "INSERT INTO TB_USUARIO (ID_USUARIO,NOME_USUARIO,E_MAIL, USERNAME, SENHA, AVATAR) VALUES (NEXTVAL('seq_usuario'),?,?,?,?,?)";
		
		try {
			
			pstm = con.prepareStatement(sql);
			pstm.setString(1, usuario.getNomeDoUsuario());
			pstm.setString(2, usuario.getEmail());
			pstm.setString(3, usuario.getUserName());
			pstm.setString(4, usuario.getSenha());
			pstm.setString(5, usuario.getAvatar());
			pstm.execute();		
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			ConnectionFactory.closeConnection(con, pstm);
		}
	}
	
	public void update(Usuario usuario) {
		
		String sql = "UPDATE TB_USUARIO SET NOME_USUARIO = ?,E_MAIL = ?, USERNAME = ?, SENHA = ?  WHERE ID_USUARIO = ?";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, usuario.getNomeDoUsuario());
			pstm.setString(2, usuario.getEmail());
			pstm.setString(3, usuario.getUserName());
			pstm.setString(4, usuario.getSenha());
			pstm.setLong(5, usuario.getId());
			
			pstm.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao atualizar usuario no banco de dados.");
		} finally {
			ConnectionFactory.closeConnection(con, pstm);
		}
		
	}
	
	public void delete(Usuario usuario) {
		
		try {
			
			String sql = "DELETE FROM TB_USUARIO WHERE ID_USUARIO = ?";
			
			pstm = con.prepareStatement(sql);
			pstm.setLong(1, usuario.getId());
			pstm.execute();

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao excluir usuario no banco de dados.");
		} finally {
			ConnectionFactory.closeConnection(con, pstm);
		}
		
	}
	
	public List<Usuario> selectAll() {
		
		List<Usuario> usuarios = new ArrayList<Usuario>();
		String sql = "SELECT * FROM TB_USUARIO ORDER BY USERNAME";
		
		
		try {
			
			pstm = con.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()){
				
				Usuario usuario = new Usuario();
				usuario.setId(rs.getLong(1));
				usuario.setUserName(rs.getString(2));
				usuario.setSenha(rs.getString(3));
				
				usuarios.add(usuario);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			ConnectionFactory.closeConnection(con, pstm);
		}
		
		return usuarios;
		
	}
	
	public Usuario selectById(Long id) {

		Usuario usuario = null;
		String sql = "SELECT * FROM TB_USUARIO WHERE ID_USUARIO = ?";
		
		
		try {
			
			pstm = con.prepareStatement(sql);
			pstm.setLong(1, id);
			
			
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				
			usuario = new Usuario();
			usuario.setId(rs.getLong(1));
			usuario.setNomeDoUsuario(rs.getString(2));
			usuario.setEmail(rs.getString(3));
			usuario.setUserName(rs.getString(4));
			usuario.setSenha(rs.getString(5));
			  
			}
							
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Não foi possivel selecionar informações do banco de dados.");
		} finally {
			ConnectionFactory.closeConnection(con, pstm);
		}
		
		return usuario;
		
	}
	
	public Usuario selectByUserName(String userName) {

		Usuario usuario = null;
		String sql = "SELECT * FROM TB_USUARIO WHERE USERNAME = ?";
		
		
		try {
			
			pstm = con.prepareStatement(sql);
			pstm.setString(1, userName);
			
			
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				
				usuario = new Usuario();
				usuario.setId(rs.getLong(1));
				usuario.setNomeDoUsuario(rs.getString(2));
				usuario.setEmail(rs.getString(3));
				usuario.setUserName(rs.getString(4));
				usuario.setSenha(rs.getString(5));
			  
			}
							
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Não foi possivel selecionar informações do banco de dados.");
		} finally {
			ConnectionFactory.closeConnection(con, pstm);
		}
		
		return usuario;
		
	}	
	
	public Usuario selectByEmail(String email) {

		Usuario usuario = null;
		String sql = "SELECT * FROM TB_USUARIO WHERE E_MAIL = ?";
		
		
		try {
			
			pstm = con.prepareStatement(sql);
			pstm.setString(1, email);
			
			
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				
				usuario = new Usuario();
				usuario.setId(rs.getLong(1));
				usuario.setNomeDoUsuario(rs.getString(2));
				usuario.setEmail(rs.getString(3));
				usuario.setUserName(rs.getString(4));
				usuario.setSenha(rs.getString(5));
			  
			}
							
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Não foi possivel selecionar informações do banco de dados.");
		} finally {
			ConnectionFactory.closeConnection(con, pstm);
		}
		
		return usuario;
		
	}		

}
