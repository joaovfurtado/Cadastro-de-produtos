/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sermed.modelDao;

import br.com.sermed.conecta.ConnectionFactory;
import br.com.sermed.model.Produto;
import br.com.sermed.model.Users;
import br.com.sermed.propridedades.Propriedades;
import br.com.sermed.view.TelaEstoque;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author joão.furtado
 */
public class ProdutosDao implements ActionListener {

    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("derby_PU");

    private List<Produto> listaP;
    private Connection con = null;
    private TelaEstoque form;

    public ProdutosDao(TelaEstoque form) {
        this.form = form;
        con = ConnectionFactory.getConection();
        Listar();
    }

    public ProdutosDao() {
        con = ConnectionFactory.getConection();
    }

    public boolean save() {
        String sql = "INSERT INTO TB_PRODUTOS (CODIGO,NOME,QUANTIDADE,PRECO) VALUES (?,?,?,?)";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(form.getTxtCodigo().getText()));
            stmt.setString(2, form.getTxtNome().getText());
            stmt.setInt(3, Integer.parseInt(form.getTxtQuantidade().getText()));
            stmt.setDouble(4, Double.parseDouble(form.getTxtPreco().getText()));
            stmt.execute();
            stmt.close();
            con.commit();
            Listar();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro" + ex);
            return false;
        }
    }

    public boolean saveOrUpdate() {
        try {
            EntityManager em = factory.createEntityManager();
            em.getTransaction().begin();
            Produto produto = getProduto();
            if (produto.getCodigo() == null) {
                em.persist(produto);
            } else {
                em.merge(produto);
            }

            em.getTransaction().commit();
            em.close();
            return true;
        } catch (Exception e) {
        }
        return false;
    }
    public Produto getProduto(){
        return new Produto(Integer.parseInt(form.getTxtCodigo().getText()),
                    form.getTxtNome().getText(),
                    Integer.parseInt(form.getTxtQuantidade().getText()),
                    Double.parseDouble(form.getTxtPreco().getText()));
    }

    public Produto findByCodigo(int codigo) {
        String sql = "SELECT* FROM TB_PRODUTOS WHERE CODIGO = ? ORDER BY CODIGO";
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setObject(1, codigo);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Produto produto = new Produto();
                produto.setCodigo(rs.getInt(1));
                produto.setNome(rs.getString(2));
                produto.setQuantidade(rs.getInt(3));
                produto.setPreco(rs.getDouble(4));
                return produto;
            }
        } catch (Exception e) {

        }
        return null;
    }

    public Produto findByCodigo2(int codigo) {
        try {
            EntityManager em = factory.createEntityManager();
            em.getTransaction().begin();
            Query query = em.createNamedQuery("produtos.findByCodigo", Produto.class);
            query.setParameter("codigo", codigo);
            Produto produto = (Produto) query.getSingleResult();
            em.getTransaction().commit();
            em.close();
            return produto;
        } catch (Exception e) {
        }
        return null;
    }

    public void deletarv2() {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.remove(getProduto());
        em.getTransaction().commit();
        em.close();
    }

    public List<Produto> Listar2() {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        TypedQuery<Produto> query = em.createNamedQuery("produto.findAll", Produto.class);
        List<Produto> lista = query.getResultList();
        em.getTransaction().commit();
        em.close();
        return lista;

    }

    public void Listar() {
        listaP = new ArrayList();
        String sql = "SELECT* FROM TB_PRODUTOS ORDER BY CODIGO";
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            DefaultTableModel model = (DefaultTableModel) form.getTbTabelaEstoque().getModel();
            while (rs.next()) {
                Produto produto = new Produto();
                produto.setCodigo(rs.getInt(1));
                produto.setNome(rs.getString(2));
                produto.setQuantidade(rs.getInt(3));
                produto.setPreco(rs.getDouble(4));
                listaP.add(produto);

            }
            try {
                for (int r = 0; r < listaP.size() - 1; r++) {
                    model.removeRow(0);
                }
                model.fireTableDataChanged();
            } catch (Exception e) {
            }
            for (int r = 0; r < listaP.size(); r++) {
                model.addRow(new Object[4]);
                for (int c = 0; c < 4; c++) {
                    switch (c) {
                        case 0:
                            model.setValueAt(listaP.get(r).getCodigo(), r, c);
                            break;
                        case 1:
                            model.setValueAt(listaP.get(r).getNome(), r, c);
                            break;
                        case 2:
                            model.setValueAt(listaP.get(r).getQuantidade(), r, c);
                            break;
                        case 3:
                            model.setValueAt(listaP.get(r).getPreco(), r, c);
                            break;
                    }
                }
            }
        } catch (SQLException ex) {
            System.err.println("Erro" + ex);
        }

    }

    public void closeConnection() {
        ConnectionFactory.closeConnection(con);
    }

    public boolean update() {
        String sql = "UPDATE TB_PRODUTOS SET NOME = ?,QUANTIDADE = ?,PRECO = ? WHERE CODIGO = ?";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, form.getTxtNome().getText());
            stmt.setInt(2, Integer.parseInt(form.getTxtQuantidade().getText()));
            stmt.setDouble(3, Double.parseDouble(form.getTxtPreco().getText()));
            stmt.setInt(4, Integer.parseInt(form.getTxtCodigo().getText()));
            stmt.executeUpdate();
            stmt.close();
            con.commit();
            Listar();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro ao atualizar" + ex);
            return false;
        }
    }

    public boolean delete() {
        String sql = "DELETE FROM TB_PRODUTOS WHERE CODIGO = ?";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(form.getTxtCodigo().getText()));
            stmt.executeUpdate();
            stmt.close();
            con.commit();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro ao atualizar" + ex);
            return false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "save":
            case "alterar":
                saveOrUpdate();
                break;
            case "delete":
                deletarv2();
                break;
            case "listar":
                Listar2();
                break;
        }
    }

}
