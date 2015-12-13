package br.com.ifce.jwallet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.ifce.jwallet.exception.DaoException;
import br.com.ifce.jwallet.model.GrupoUsuario;
import br.com.ifce.jwallet.model.Usuario;

public class GrupoUsuarioDao {

	private Connection con;
	private PreparedStatement pstm;
	
	
	public GrupoUsuarioDao(){
		try {
			this.con = ConnectionFactory.getConnection();
		
		} catch (DaoException e) {
			e.printStackTrace();
		}
	}
	
	
	public void insert(GrupoUsuario grupoUsuario, Usuario usuario){
		
		String sql = "INSERT INTO TB_GRUPO_USUARIO (ID_GRUPO_USUARIO, NOME_GRUPO) VALUES (?,?)";
		
		String sql_insert_usuario = "INSERT INTO TB_REL_GRUPOS_USUARIOS (ID_GRUPOS_USUARIOS, ID_GRUPO_USUARIO, ID_USUARIO, USUARIO_APELIDO)"
				+ "VALUES (NEXTVAL('seq_rel_grupos_usuarios'), ?, ?, ? )";
		
		try {
			
			con.setAutoCommit(false);

			// buscando id
			Long idGrupo;
			pstm = con.prepareStatement("select NEXTVAL('seq_grupo_usuario')");
			ResultSet result = pstm.executeQuery();
			result.next();
			idGrupo = result.getLong(1);
			
			
			// inserir grupo
			pstm = con.prepareStatement(sql);
			pstm.setLong(1, idGrupo);
			pstm.setString(2, grupoUsuario.getNome());
			pstm.execute();
			
			
			// inserir usuario
			pstm = con.prepareStatement(sql_insert_usuario);
			pstm.setLong(1, idGrupo);
			pstm.setLong(2, usuario.getId());
			pstm.setString(3, usuario.getApelido());
			pstm.execute();
			
			con.commit();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao inserir grupo de usuario no banco de dados.");
		} finally {
			ConnectionFactory.closeConnection(con, pstm);
		}
		
	}
	
	public void update(GrupoUsuario grupoUsuario){
		
		String sql = "UPDATE TB_GRUPO_USUARIO SET NOME_GRUPO = ? WHERE ID_GRUPO_USUARIO = ?";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, grupoUsuario.getNome());
			pstm.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao atualizar grupo de usuario no banco de dados.");
		} finally {
			ConnectionFactory.closeConnection(con, pstm);
		}
		
	}
	
	public void delete(GrupoUsuario grupoUsuario){
		
		String sql = "DELETE FROM TB_GRUPO_USUARIO WHERE ID_GRUPO_USUARIO = ?";
		try {
			
			pstm = con.prepareStatement(sql);
			pstm.setLong(1, grupoUsuario.getId());
			pstm.execute();

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao excluir grupo de usuarios no banco de dados.");
		} finally {
			ConnectionFactory.closeConnection(con, pstm);
		}
	}
	
	public List<GrupoUsuario> selectAll(){
		
		List<GrupoUsuario> gruposUsuario = new ArrayList<GrupoUsuario>();
		String sql = "SELECT * FROM TB_GRUPO_USUARIO ORDER BY NOME_GRUPO";
		
		
		try {
			
			pstm = con.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()){
				
				GrupoUsuario grupoUsuario = new GrupoUsuario();
				grupoUsuario.setId(rs.getLong(1));
				grupoUsuario.setNome(rs.getString(2));
				
				gruposUsuario.add(grupoUsuario);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			ConnectionFactory.closeConnection(con, pstm);
		}
		
		return gruposUsuario;
	}

	
	public void insertUsuarioNoGrupo(Usuario usuario){
		
		String sql = "INSERT INTO TB_REL_GRUPOS_USUARIOS (ID_GRUPOS_USUARIOS, ID_GRUPO_USUARIO, ID_USUARIO, USUARIO_APELIDO)"
				+ "VALUES (NEXTVAL('seq_rel_grupos_usuarios'), ?, ?, ? )";
		
		try {
			
			pstm = con.prepareStatement(sql);
			pstm.setLong(1, usuario.getIdGrupoUsuario());
			pstm.setLong(2, usuario.getId());
			pstm.setString(3, usuario.getApelido());
			pstm.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao inserir usuario no grupo de usuario no banco de dados.");
		} finally {
			ConnectionFactory.closeConnection(con, pstm);
		}		
	}

	public List<Usuario> selectPorGrupoUsuario(GrupoUsuario grupoUsuario){
		
		List<Usuario> grupoUsuarios = new ArrayList<Usuario>();
		String sql = "SELECT U.ID_USUARIO, U.NOME_USUARIO, U.USERNAME, U.E_MAIL, R.USUARIO_APELIDO, U.AVATAR"
				+ "     FROM TB_USUARIO U"
				+ "  INNER JOIN TB_REL_GRUPOS_USUARIOS R"
				+ "    ON (U.ID_USUARIO = R.ID_USUARIO)"
				+ "  WHERE R.ID_GRUPO_USUARIO = ?";
		
		try {
			
			pstm = con.prepareStatement(sql);
			
			pstm.setLong(1, grupoUsuario.getId());
			ResultSet rs = pstm.executeQuery();
			while (rs.next()){
				
				Usuario usuario = new Usuario();
				usuario.setId(rs.getLong(1));
				usuario.setNomeDoUsuario(rs.getString(2));
				usuario.setUserName(rs.getString(3));
				usuario.setEmail(rs.getString(4));
				usuario.setApelido(rs.getString(5));
				usuario.setAvatar(rs.getString(6));
				
				grupoUsuarios.add(usuario);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			ConnectionFactory.closeConnection(con, pstm);
		}
		
		return grupoUsuarios;
	}	

	public void deleteUsuarioDoGrupo(Usuario usuario){
		
		String sql = "DELETE FROM TB_REL_GRUPOS_USUARIOS "
				+ " WHERE ID_GRUPO_USUARIO = ? AND ID_USUARIO = ?";
		
		try {
			
			pstm = con.prepareStatement(sql);
			pstm.setLong(1, usuario.getIdGrupoUsuario());
			pstm.setLong(2, usuario.getId());
			pstm.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao excluir usuario do grupo de usuario no banco de dados.");
		} finally {
			ConnectionFactory.closeConnection(con, pstm);
		}		
	}
	
	public List<GrupoUsuario> selectGruposDoUsuario(Usuario usuario){
		
		List<GrupoUsuario> gruposUsuario = new ArrayList<GrupoUsuario>();
		String sql = "SELECT G.*"
				+ "     FROM TB_GRUPO_USUARIO G"
				+ "  INNER JOIN TB_REL_GRUPOS_USUARIOS R"
				+ "    ON (G.ID_GRUPO_USUARIO = R.ID_GRUPO_USUARIO)"
				+ "  WHERE R.ID_USUARIO = ?";
		
		
		try {
			
			pstm = con.prepareStatement(sql);
			pstm.setLong(1, usuario.getId());
			ResultSet rs = pstm.executeQuery();
			while (rs.next()){
				
				GrupoUsuario grupoUsuario = new GrupoUsuario();
				grupoUsuario.setId(rs.getLong(1));
				grupoUsuario.setNome(rs.getString(2));
				
				gruposUsuario.add(grupoUsuario);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			ConnectionFactory.closeConnection(con, pstm);
		}
		
		return gruposUsuario;
	}
	
}

