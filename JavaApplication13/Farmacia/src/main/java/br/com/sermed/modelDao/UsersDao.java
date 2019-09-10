/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sermed.modelDao;

import br.com.sermed.conecta.ConnectionFactory;
import br.com.sermed.model.Users;
import br.com.sermed.propridedades.Propriedades;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author joão.furtado
 */
public class UsersDao {

    private Connection con = ConnectionFactory.getConection();
    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("derby_PU");

    public boolean validaLogin(String login, String password) {
        // String sql = "SELECT* FROM TB_USUARIOS WHERE LOGIN = ? AND PASSWORD =?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean checar = false;

        try {
            stmt = con.prepareStatement("SELECT* FROM TB_USUARIOS WHERE USUARIOS = ? AND SENHA = ?");
            stmt.setString(1, login);
            stmt.setString(2, password);
            rs = stmt.executeQuery();
            if (rs.next()) {

                checar = true;
                Users u = new Users();
                u.setLogin(rs.getString(2));
                u.setPerm(rs.getString(3));

                Propriedades.setUser(u);

            }

        } catch (SQLException ex) {
            System.err.println("ERRO" + ex);
        }
        return checar;
    }

    public static void main(String[] args) {
        UsersDao u = new UsersDao();
        u.save(new Users("JOAO", "123","c"));
    }

    public boolean validaLogin2(String login, String password) {
        try {
            EntityManager em = factory.createEntityManager();
            em.getTransaction().begin();
            Query query = em.createNamedQuery("users.findByLogin",Users.class);
            query.setParameter("login", login);
            query.setParameter("password", password);
            Users user = (Users) query.getSingleResult();
            Propriedades.setUser(user);
            em.getTransaction().commit();
            em.close();
            return true;

        } catch (Exception e) {
        }
        return false;
    }

     public boolean save(Users user) {
        try {
            EntityManager em = factory.createEntityManager();
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
            em.close();
            return true;
        } catch (Exception e) {
        }
        return false;
    }

}
