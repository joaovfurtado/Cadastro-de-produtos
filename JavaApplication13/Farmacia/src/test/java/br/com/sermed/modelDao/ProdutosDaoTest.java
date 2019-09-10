/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sermed.modelDao;

import br.com.sermed.model.Produto;
import org.junit.Test;

/**
 *
 * @author joão.furtado
 */
public class ProdutosDaoTest {
    
    public ProdutosDaoTest() {
    }

   /* @Test
    public void inserir() {
        Produto p1 = new Produto(1, "Dipirona", 50, 10);
        //Produto p2 = new Produto(2, "Paraceamol",5, 30);
        //Produto p3 = new Produto(3, "Lufital", 200, 100);
        ProdutosDao dao = new ProdutosDao();
        
        dao.save(p1);
        //dao.save(p2);
        //dao.save(p3);
        dao.closeConnection();
    }
    
   // @Test
    public void listar(){
        ProdutosDao dao = new ProdutosDao();
        for(Produto p: dao.findAll()){
            System.out.println("Codigo " + p.getCodigo());
            System.out.println("Nome " + p.getNome());
            System.out.println("Quantidade " + p.getQuantidade());
            System.out.println("Preco " + p.getPreco());
        }
    }
    //@Test
    public void alterar(){
        Produto p1 = new Produto(1, "Dorflex", 200, 10);
        ProdutosDao dao = new ProdutosDao();
        dao.update(p1);
        System.out.println("Atualizacao feita com sucesso");
        dao.closeConnection();
    }
    //@Test
    public void deletar(){
        Produto p1 = new Produto();
        p1.setCodigo(1);
        ProdutosDao dao = new ProdutosDao();
        dao.delete(p1);
    } */
    
}
