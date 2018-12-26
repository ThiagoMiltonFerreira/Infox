/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infox.telas;

import ValidaCPFeCNPJ.CNPJ;
import ValidaCPFeCNPJ.CPF;
import java.awt.Color;
import javax.swing.JOptionPane;
import java.sql.*;
import br.com.infox.dal.ModuloConexao; //importa a conexao
import static br.com.infox.telas.Principal.c_documento;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;
import webServiceViaCep.WebServiceCep;

/**
 *
 * @author Adm
 */
public class telaEmpresaInicial extends javax.swing.JFrame {
   
    PreparedStatement pst = null; // smp colocar na tela que ira usar o bacco
    java.sql.Connection conexao = null;// smp colocar na tela que ira usar o bacco
    ResultSet rs = null; //smp colocar na tela que ira usar o bacco EXIBE O RESULTADO DA CONXECAO COM BANCO
    boolean contrato=false;
    String empresa = "";
    /**
     * Creates new form telaEmpresaInicial
     */
    public telaEmpresaInicial() {
        initComponents();
        conexao=ModuloConexao.conector(); // inicia conexao ao abrir o frm
        MaskFormatter maskCnpj = null;  
        try {
            maskCnpj = new MaskFormatter("##.###.###/####-##");
        } catch (ParseException ex) {
            Logger.getLogger(telaEmpresaInicial.class.getName()).log(Level.SEVERE, null, ex);
        }
        maskCnpj.install(lblCnpj); 
        
        lblEmail.setEnabled(false);
        lblResponsavel.setEnabled(false);
        lbltelefone.setEnabled(false);
        lblCep.setEnabled(false);
        lblEndereco.setEnabled(false);
    
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblEmpresa = new javax.swing.JTextField();
        lblEndereco = new javax.swing.JTextField();
        lblEmail = new javax.swing.JTextField();
        lblResponsavel = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        rbtLicontrato = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        btnValidarCNPJ = new javax.swing.JButton();
        lblCnpj = new javax.swing.JFormattedTextField();
        lblVerificaCnpj = new javax.swing.JLabel();
        lbltelefone = new javax.swing.JFormattedTextField();
        jLabel11 = new javax.swing.JLabel();
        btnBuscarCep = new javax.swing.JButton();
        lblCep = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Bem Vindo!");

        jLabel1.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        jLabel1.setText("Bem vindo ao Infox Controle de ordem de serviços.");
        jLabel1.setMaximumSize(new java.awt.Dimension(300, 14));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/icone hello.png"))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Cadastro De ultilização");

        jLabel4.setText("Empresa:  ");

        jLabel5.setText("Endereço: ");

        jLabel6.setText("CNPJ: ");

        jLabel7.setText("E-mail: ");

        jLabel8.setText("Responsavel: ");

        jLabel9.setText("Telefone de contato: ");

        jLabel10.setText("Contrato: ");

        lblEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lblEmpresaActionPerformed(evt);
            }
        });

        lblEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lblEmailActionPerformed(evt);
            }
        });

        lblResponsavel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lblResponsavelActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("Concessão da Licença:\n2.1 - A LICENCIANTE, neste ato e pela melhor forma de direito, outorga ao LICENCIADO uma licença de\nuso, em caráter não exclusivo e intransferível, para utilização do software.\n2.2 - A licença concedida nos termos deste instrumento é válida somente no Brasil. O LICENCIADO não\nremeterá, transferirá ou exportará de outra forma o produto para fora do território sem a anuência\nprévia por escrito da LICENCIANTE e o pagamento pelo LICENCIADO das eventuais taxas adicionais aos\níndices em vigor da LICENCIANTE.\n2.3 - APLICA-SE A ESTE CONTRATO O DISPOSTO NAS LEIS 9.609/98 (PROTEÇÃO DA PROPRIEDADE\nINTELECTUAL DO SOFTWARE) E 9.610/98 (PROTEÇÃO DOS DIREITOS AUTORAIS);");
        jScrollPane2.setViewportView(jTextArea1);

        rbtLicontrato.setText("Li e concordo com termos acima citados.");
        rbtLicontrato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtLicontratoActionPerformed(evt);
            }
        });

        jButton1.setText("Confirmar ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnValidarCNPJ.setText("Validar");
        btnValidarCNPJ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnValidarCNPJActionPerformed(evt);
            }
        });

        lbltelefone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lbltelefoneActionPerformed(evt);
            }
        });

        jLabel11.setText("CEP:");

        btnBuscarCep.setText("Buscar");
        btnBuscarCep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarCepActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(jLabel3)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(rbtLicontrato)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(458, 458, 458)
                                .addComponent(lblVerificaCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel11))
                                        .addGap(5, 5, 5)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(lblCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(btnValidarCNPJ))
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(lblCep)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(btnBuscarCep)))
                                            .addComponent(lblEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel5)))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblEmail)
                                            .addComponent(lblEndereco)
                                            .addComponent(lblResponsavel)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbltelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblVerificaCnpj))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)))
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(lblEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6))
                            .addComponent(btnValidarCNPJ))
                        .addGap(0, 13, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuscarCep)
                    .addComponent(jLabel11)
                    .addComponent(lblCep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblResponsavel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(lbltelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbtLicontrato)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    public void entrarTelaPrincipal(){
          
    TelaPrincipal principal = new TelaPrincipal(); //instacia a nova tela a ser aberta
    principal.setVisible(true); //abre nova tela
    TelaPrincipal.relatorioGeral.setEnabled(true);
    TelaPrincipal.MenCadUsu.setEnabled(true);
    TelaPrincipal.lblUsuario.setForeground(Color.red);
    this.dispose(); // fecha tela antiga

    
    }
    public void salvarEmpresaAoEntrar(){
        String sql="insert into tbempresas(nomeemp,endemp,cnpj,email,responsavel,tel,contrato)\n" +
        "values(?,?,?,?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
         
            pst.setString(1, lblEmpresa.getText());
            pst.setString(2, lblEndereco.getText());
            pst.setString(3, lblCnpj.getText());
            pst.setString(4, lblEmail.getText()); 
            pst.setString(5, lblResponsavel.getText());
            pst.setString(6, lbltelefone.getText());
            pst.setBoolean(7, contrato);
            
         
            int adicionado = pst.executeUpdate(); //executa query de atualizacao
            
            //se o valor for maior q zero pq cadastrou
            if (adicionado>0)
            {
               entrarTelaPrincipal();
             
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Erro ao cadastrar nova empresa!"+ e);
        }
        
    }
    public void bucaCep(String cep){
        
        WebServiceCep webServiceCep = WebServiceCep.searchCep(cep);
        if(webServiceCep.wasSuccessful())
        {
            /**
            System.out.println("Rua : "+webServiceCep.getLogradouro()); 
            System.out.println("Bairro : "+webServiceCep.getLogradouro());
            System.out.println("Cidade : "+webServiceCep.getCidade());
            System.out.println("Estado : "+webServiceCep.getUf());
            */
            lblEndereco.setText(webServiceCep.getLogradouro()+" "+webServiceCep.getCidade()+" / "+webServiceCep.getBairro()+" - "+webServiceCep.getUf());
            
            
        }
        else
        {
          JOptionPane.showMessageDialog(null,"Cep Incorreto.");
        }
    }
    private void lblEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblEmpresaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblEmpresaActionPerformed

    private void rbtLicontratoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtLicontratoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbtLicontratoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
       
       if(!rbtLicontrato.isSelected() || lblEmpresa.getText().equals("") || lblEndereco.getText().equals("") || lblCnpj.getText().equals("") || lblEmail.getText().equals("") || lblResponsavel.getText().equals("") || lbltelefone.getText().equals("") || lblVerificaCnpj.getText().equals(""))
       {

            JOptionPane.showMessageDialog(null,"Campo em branco / ou CNPJ nao Validado.Favor verificar!");
            
       }
       else
       {
           //JOptionPane.showMessageDialog(null,rbtLicontrato.isSelected());
           salvarEmpresaAoEntrar();
           
       }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnValidarCNPJActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnValidarCNPJActionPerformed
         
        String cnpj = lblCnpj.getText();
        String documento = lblCnpj.getText();
        telaEmpresaInicial telaEmpresa = new telaEmpresaInicial();
        CNPJ pj = new CNPJ(documento);
            if(pj.isCNPJ())
            {
                lblCnpj.setText(pj.getCNPJ(true));
                lblVerificaCnpj.setText("Valido!");
                lblEmail.setEnabled(true);
                lblResponsavel.setEnabled(true);
                lbltelefone.setEnabled(true);
                lblCep.setEnabled(true);
                lblEndereco.setEnabled(true);
                /**
                MaskFormatter maskTel = null;  
           
                try {
                        maskTel = new MaskFormatter("(##)#-####-####");
                    
                    } catch (ParseException ex) {
                        Logger.getLogger(telaEmpresaInicial.class.getName()).log(Level.SEVERE, null, ex);
                    }
                maskTel.install(lbltelefone); 
              **/
                 MaskFormatter maskCep = null;  
           
                try {
                        maskCep = new MaskFormatter("#####-###");
                    
                    } catch (ParseException ex) {
                        Logger.getLogger(telaEmpresaInicial.class.getName()).log(Level.SEVERE, null, ex);
                    }
                maskCep.install((JFormattedTextField) lblCep); 
            }else
            {

                JOptionPane.showMessageDialog(rootPane,"CNPJ inválido!");
            }
    }//GEN-LAST:event_btnValidarCNPJActionPerformed

    private void lblResponsavelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblResponsavelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblResponsavelActionPerformed

    private void lblEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblEmailActionPerformed

    private void lbltelefoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lbltelefoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lbltelefoneActionPerformed

    private void btnBuscarCepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarCepActionPerformed
        // Envia o numero do cep para a classe de busca que envia para o web service do busca cep
        bucaCep(lblCep.getText().toString());
         MaskFormatter maskTel = null;  
           
        try {
                maskTel = new MaskFormatter("(##)#-####-####");
                    
            } catch (ParseException ex) {
                        Logger.getLogger(telaEmpresaInicial.class.getName()).log(Level.SEVERE, null, ex);
            }
            maskTel.install(lbltelefone); 
       
    }//GEN-LAST:event_btnBuscarCepActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(telaEmpresaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(telaEmpresaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(telaEmpresaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(telaEmpresaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new telaEmpresaInicial().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarCep;
    private javax.swing.JButton btnValidarCNPJ;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JFormattedTextField lblCep;
    public javax.swing.JFormattedTextField lblCnpj;
    public javax.swing.JTextField lblEmail;
    public javax.swing.JTextField lblEmpresa;
    private javax.swing.JTextField lblEndereco;
    public javax.swing.JTextField lblResponsavel;
    private javax.swing.JLabel lblVerificaCnpj;
    private javax.swing.JFormattedTextField lbltelefone;
    private javax.swing.JCheckBox rbtLicontrato;
    // End of variables declaration//GEN-END:variables
}
