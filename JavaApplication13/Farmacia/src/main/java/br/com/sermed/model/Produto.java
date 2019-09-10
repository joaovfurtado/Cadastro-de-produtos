/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sermed.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.persistence.annotations.Index;
import org.eclipse.persistence.annotations.Indexes;

/**
 *
 * @author joão.furtado
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_PRODUTOS")
@Indexes({
    @Index(name = "IDX_TBPRODUTOS_CODIGO_NOME_QUANTIDADE_PRECO", columnNames = {"CODIGO","NOME","QUANTIDADE","PRECO"})
})
//@SequenceGenerator(name = "SEQ_TB_PRODUTOS", initialValue = 1, allocationSize = 1)
@NamedQueries({
    @NamedQuery(name = "produtos.findAll", query = "FROM Produto AS pr"),
    @NamedQuery(name = "produtos.findByCodigo", query ="FROM Produto AS pr WHERE pr.codigo = :codigo ORDER BY pr.codigo ASC")
})
public class Produto implements Serializable {

    private static final long serialVersionUID = -252060865345739821L;
    
    @Id
    //@GeneratedValue(generator = "SEQ_TB_PRODUTOS", strategy = GenerationType.SEQUENCE)
    private Integer codigo;
    @Column(name = "NOME",length = 50)
    private String nome;
    @Column(name = "QUANTIDADE")
    private Integer quantidade;
    @Column(name = "PRECO", scale = 12,precision = 3)
    private Double preco;


   
    
    
    
}
