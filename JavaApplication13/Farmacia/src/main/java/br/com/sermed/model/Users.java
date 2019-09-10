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
import javax.persistence.NamedNativeQueries;
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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_USUARIOS")
@Indexes({
    @Index(name = "IDX_TBUSUARIOS_USUARIOS_SENHA", columnNames = {"USUARIOS", "SENHA"})
})
@NamedQueries({
    @NamedQuery(name = "users.findByLogin", query = "FROM Users AS us WHERE us.login = :login AND us.password = :password")
})
public class Users implements Serializable {

    private static final long serialVersionUID = -1742365351012379868L;

    @Id
    @Column(name = "USUARIOS", length = 40)
    private String login;
    @Column(name = "SENHA", length = 40)
    private String password;
    @Column(name = "PERM", length = 1)
    private String perm;

}
