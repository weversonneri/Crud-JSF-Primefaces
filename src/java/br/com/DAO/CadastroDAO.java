package br.com.DAO;

import br.com.conexao.ConectarBD;
import br.com.modelo.Cadastro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CadastroDAO {

    PreparedStatement ps;
    Connection con;
    String sql;

    public boolean salvar(Cadastro jg) {
        con = ConectarBD.abrirConexao();
        sql = "INSERT INTO cadastro (id, titulo, genero, plataforma, desenvolvedor, distribuidora, lancamento) VALUES (?, ?, ?, ?, ?, ?, ?) ";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, jg.getId());
            ps.setString(2, jg.getTitulo());
            ps.setString(3, jg.getGenero());
            ps.setString(4, jg.getPlataforma());
            ps.setString(5, jg.getDesenvolvedor());
            ps.setString(6, jg.getDistribuidora());
            ps.setString(7, jg.getLancamento());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CadastroDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean editar(Cadastro jg) {
        con = ConectarBD.abrirConexao();
        sql = "UPDATE cadastro SET titulo = ?, genero = ?, plataforma = ?, desenvolvedor = ?, distribuidora = ?, lancamento = ? WHERE id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, jg.getTitulo());
            ps.setString(2, jg.getGenero());
            ps.setString(3, jg.getPlataforma());
            ps.setString(4, jg.getDesenvolvedor());
            ps.setString(5, jg.getDistribuidora());
            ps.setString(6, jg.getLancamento());
            ps.setString(7, jg.getId());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CadastroDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public void deletar(Cadastro jg) {
        con = ConectarBD.abrirConexao();
        sql = "DELETE FROM cadastro WHERE id = ? ";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, jg.getId());
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(CadastroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Cadastro selecionar(String id) {
        try {
            con = ConectarBD.abrirConexao();
            sql = "SELECT * FROM cadastro WHERE id = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            Cadastro jg = new Cadastro();
            if (rs.next()) {
                jg.setId(rs.getString("id"));
                jg.setTitulo(rs.getString("titulo"));
                jg.setGenero(rs.getString("genero"));
                jg.setPlataforma(rs.getString("plataforma"));
                jg.setDesenvolvedor(rs.getString("desenvolvedor"));
                jg.setDistribuidora(rs.getString("distribuidora"));
                jg.setLancamento(rs.getString("lancamento"));
            }
            return jg;
        } catch (SQLException ex) {
            Logger.getLogger(CadastroDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
       /* public Cadastro selecionarTodos() {
        try {
            List<Cadastro> cadastros = new ArrayList<>();
            con = ConectarBD.abrirConexao();
            sql = "SELECT * FROM evento";
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            Cadastro jg = new Cadastro();
            if (rs.next()) {
                jg.setTitulo(rs.getString("titulo"));
                jg.setGenero(rs.getString("genero"));
                jg.setDesenvolvedor(rs.getString("desenvolvedor"));
                jg.setDistribuidora(rs.getString("distribuidora"));
                jg.setLancamento(rs.getString("lancamento"));
                cadastros.add(jg);
            }
            return (Cadastro) cadastros;
        } catch (SQLException ex) {
            Logger.getLogger(CadastroDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }*/
    

    public List<Cadastro> getListaJG() {
        ArrayList<Cadastro> listaJG = new ArrayList<>();
        con = ConectarBD.abrirConexao();
        sql = "SELECT  titulo, genero, desenvolvedor, distribuidora, lancamento FROM cadastro";
        try {
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Cadastro jg = new Cadastro();
                jg.setTitulo(rs.getString("titulo"));
                jg.setGenero(rs.getString("genero"));
                jg.setDesenvolvedor(rs.getString("desenvolvedor"));
                jg.setDistribuidora(rs.getString("distribuidora"));
                jg.setLancamento(rs.getString("lancamento"));
                listaJG.add(jg);
            }
            return listaJG;
        } catch (SQLException ex) {
            Logger.getLogger(CadastroDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
