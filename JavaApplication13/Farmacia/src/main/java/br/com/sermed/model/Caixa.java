/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sermed.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
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
@Table(name = "TB_VENDAS")
@Indexes({
    @Index(name = "IDX_TBVENDAS_DATAATUAL", columnNames = {"DATA_ATUAL"})
})
@SequenceGenerator(name = "SEQ_TB_VENDAS", initialValue = 1, allocationSize = 1)
@NamedQueries({
    @NamedQuery(name = "caixa.findAll", query = "FROM Caixa AS cx")
})

public class Caixa implements Serializable {

    private static final long serialVersionUID = -1385686212050416966L;

    @Id
    @GeneratedValue(generator = "SEQ_TB_VENDAS", strategy = GenerationType.SEQUENCE)
    private Long num_venda;
    @Column(name = "VALOR_TOTAL", scale = 12, precision = 3)
    private double totalSoma;
    @Column(name = "DATA_ATUAL", columnDefinition = "date")
    @Temporal(TemporalType.DATE)
    private Date dataAtual = new Date();
    @Transient
    private double valor;

}
