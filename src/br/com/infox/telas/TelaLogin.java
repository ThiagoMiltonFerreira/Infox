package br.com.infox.telas;
//import java.sql.*; // Sempre que for usar banco de dados precisa importar estas sao as bibliotecas do sql

import br.com.infox.dal.ModuloConexao; // importei o modulo de conexao que esta dentro do pacote dal DAL
import java.awt.Color;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class TelaLogin extends javax.swing.JFrame {

    PreparedStatement pst = null; // smp colocar na tela que ira usar o bacco
    java.sql.Connection conexao = null;
    ResultSet rs = null; //smp colocar na tela que ira usar o bacco
    
    // Cria variavel conexao 
    public void logar() {
        String sql = "SELECT * from tbusuarios where login =? and senha =?";
        try {
            //as linhas abaixo prepara consulta ao banco em funçao do que foi dizitado nas caixas de texto
            // o interroga e substituido pelo conteudo das variaveis. entao ja busca o usuario respectivo ao campo de texto
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUsuario.getText()); //substitui a interogaçao do sql pelo txto
            pst.setString(2, txtSenha.getText()); //substitui a interogaçao do sql pelo txto
            //A linha abaixo executa a query(consulta o banco de dados)
            rs = pst.executeQuery();
            //Se existir usuario e senha correspondente
            if (rs.next()) {
                
                        String perfil = rs.getString(6); //Linha abaixo obtem o conteudo do campo perfil da tabela tbusuario de acordo com a posicao do campo no caso o 6 e do status do perfil
                        //System.out.println(perfil);
                        // A decisao abaixo trata o perfil do usuario destrava os botoes para o usaurio admin..
                if (perfil.equals("admin")) 
                {
                    
                        // JOptionPane.showMessageDialog(null, "CADASTRO DE EMPRESA");
                        // chama a tela de cadastro da empresa caso o usuario for ADM  
                        telaEmpresaInicial empresaInicial = new telaEmpresaInicial();
                        empresaInicial.setVisible(true);
                        this.dispose(); 

                }else
                {
              
                    // JOptionPane.showMessageDialog(null, "ELSE");
                    // abre a tela inicial travada alguns campos que esta configurado para estar travados para desterminados usuarios se nao for admin
                    TelaPrincipal principal = new TelaPrincipal(); //instacia a nova tela a ser aberta
                    principal.setVisible(true); //abre nova tela
                    TelaPrincipal.lblUsuario.setText(rs.getString(2)); // Pega o conteudo da coluna 2 do banco de dados(campo nome) e seta para o label.  
                    this.dispose(); // fecha tela antiga
                }

            } else {
                
                JOptionPane.showMessageDialog(null, "Usuario ou senha invalidos!");
               
            }
        } catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null, "erro:  " + e);
        }
    }

    public TelaLogin() 
    {
        initComponents();
        ModuloConexao chamaConexao = new ModuloConexao();
        conexao = ModuloConexao.conector();
       
        // A linha abaixo mostra o status da conxao se for diferente de nulo esta cocnetado
        //System.out.println(chamaConexao.conector());
        if (chamaConexao.conector() != null) {
            //Seta o labl com a imagem que esta dentro do pacote imagem
            lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/bdok.png")));

        } else {

            lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/bdError.png")));

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        btnLogin = new javax.swing.JButton();
        txtSenha = new javax.swing.JPasswordField();
        lblStatus = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("X System - Login");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLocationByPlatform(true);
        setResizable(false);

        jLabel1.setText("Usuário");

        jLabel2.setText("Senha");

        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/bdError.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLogin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(48, Short.MAX_VALUE)
                .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );

        setSize(new java.awt.Dimension(515, 260));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        // Chamando o metodo logar que chama e verifica se existe o usuario no banco
        logar();

    }//GEN-LAST:event_btnLoginActionPerformed

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
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaLogin().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JPasswordField txtSenha;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
