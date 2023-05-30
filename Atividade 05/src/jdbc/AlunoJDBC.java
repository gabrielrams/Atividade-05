package jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import entities.Aluno;

public class AlunoJDBC {
	
	 Connection con;
	 String sql;
	 PreparedStatement pst;

	
	public void salvar(Aluno a) throws IOException {
		
		try {
			Connection con = db.getConexao();
			System.out.println("Conexão realizada com sucesso !");
			
			sql = "INSERT INTO aluno (id, nome, sexo, dt_nasc) VALUES (?, ?,  ?, ?)";
			
			pst = con.prepareStatement(sql);
			pst.setInt(1, a.getId());
			pst.setString(2, a.getNome());
			pst.setString(3, a.getSexo());
			
			Date dataSql = new Date(a.getDt_nasc().getTime());
			pst.setDate(4, dataSql);
			
			pst.executeUpdate();
			System.out.println("\nCadastro do aluno realizado com sucesso!");
			
			pst.close();
		}
		catch (SQLException e) {
			
			System.out.println(e);
		}
		
	}
	
	public List<Aluno> listar() throws IOException {
		
		List<Aluno> alunos = new ArrayList<Aluno>();
		try {
			Connection con = db.getConexao();
			System.out.println("Conexão realizada com sucesso !");
			
			sql = "SELECT * FROM aluno";
			ResultSet rs;
				
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			
			if(rs == null) {
				return alunos;
			}else {
				while(rs.next()) {
					Aluno aluno = new Aluno();
					aluno.setId(rs.getInt("id"));
					aluno.setNome(rs.getString("nome"));
					aluno.setSexo(rs.getString("sexo"));
					aluno.setDt_nasc(rs.getDate("dt_nasc"));
					
					alunos.add(aluno);
					
				}
				
				rs.close();
				pst.close();
			}
			
		}catch(SQLException e) {
			System.out.println(e);
		}
		return alunos;

	}
	
	public void apagar(int id) throws IOException {
		
		try {
			Connection con = db.getConexao();
			System.out.println("Conexão realizada com sucesso !");
			
			sql = "DELETE FROM aluno WHERE id=?";
			pst = con.prepareStatement(sql);
			
			pst.setInt(1, id);
			pst.executeUpdate();
			
			System.out.println("\nExclusão do aluno realizado com sucesso!");
			
			pst.close();
			
		}catch(SQLException e) {
			System.out.println(e);
		}
		
	}
	
	public void alterar(int id, String nome) throws IOException {
		
		try {
			
			Connection con = db.getConexao();
			System.out.println("Conexão realizada com sucesso!");
			
			sql = "UPDATE aluno SET nome=? WHERE id=? ";
			pst = con.prepareStatement(sql);
			
			pst.setString(1, nome);
			pst.setInt(2, id);
			
			pst.executeUpdate();
			System.out.println("\nAtualização realizada com sucesso!");
			pst.close();
			
		}catch(SQLException e) {
			System.out.println(e);
		}
		
	}
}

