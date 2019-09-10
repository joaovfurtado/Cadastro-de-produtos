/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sermed.modelDao;

import br.com.sermed.model.Caixa;
import br.com.sermed.model.Users;
import br.com.sermed.view.TelaCaixa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author joão.furtado
 */
public class CaixaDao {
     private EntityManagerFactory factory = Persistence.createEntityManagerFactory("derby_PU");
     
      public boolean saveOrUpdate(Caixa caixa) {
        try {
            EntityManager em = factory.createEntityManager();
            em.getTransaction().begin();
            if(caixa.getNum_venda()==null){
                em.persist(caixa);
            }else{
                em.merge(caixa);
            }
            
            em.getTransaction().commit();
            em.close();
            return true;
        } catch (Exception e) {
        }
        return false;
    }
    
       
}
