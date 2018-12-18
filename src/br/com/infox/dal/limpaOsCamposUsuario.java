/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infox.dal;

import br.com.infox.telas.TelaUsuario;

/**
 *
 * @author ART
 */
public class limpaOsCamposUsuario extends TelaUsuario{
   public void limpaTodosCamposDoUsuario(){
       txtUsuId.setText(null);
       txtUsuNome.setText(null);
       txtUsuFone.setText(null);
       txtxUsuLogin.setText(null);
       txtUsuSenha.setText(null);
   } 
}
