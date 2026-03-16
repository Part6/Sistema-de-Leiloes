/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public ProdutosDAO(){
        conectaDAO conect = new conectaDAO();
        conn = conect.connectDB();
    }
    
    public int cadastrarProduto (ProdutosDTO produto){
       
       int status;
        try {
            prep = conn.prepareStatement("INSERT INTO `produtos` (`nome`, `valor`, `status`) VALUES(?,?,?)");
           
            //prep.setInt(1,produto.getId());
            prep.setString(1,produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());
            status = prep.executeUpdate();
            return status; // 1
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return ex.getErrorCode(); // qualquer outro
        }   
    }
    
             
        
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        String sql = "SELECT * FROM `produtos`";             
              try {
                    PreparedStatement stmt = this.conn.prepareStatement(sql);
                    //stmt.setString(1,"%" + categorianome + "%");
                    
                    ResultSet rs = stmt.executeQuery();   
                    
                    ArrayList<ProdutosDTO> lista = new ArrayList<>();
                    while (rs.next()) {
                         ProdutosDTO produto = new ProdutosDTO();
                         
                        
                        produto.setId(rs.getInt("id"));
                        produto.setNome(rs.getString("nome"));
                        produto.setValor(rs.getInt("valor"));
                        produto.setStatus(rs.getString("status"));
                        
                        lista.add(produto);    
                    }
                    return lista;
                    
                } catch (Exception e) {
                    System.out.println("Erro na lista: " + e.getMessage());
                    return null;
                }
    }
    
    
    
        
}

