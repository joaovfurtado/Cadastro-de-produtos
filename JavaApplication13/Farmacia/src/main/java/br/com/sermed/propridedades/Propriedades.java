/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sermed.propridedades;

import br.com.sermed.model.Users;

/**
 *
 * @author joão.furtado
 */
public class Propriedades {

    private static Users user;


    public static Users getUser() {
        return user;
    }

    public static void setUser(Users username) {
        Propriedades.user = username;
    }

}
