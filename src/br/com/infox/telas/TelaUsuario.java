
package br.com.infox.telas;
import java.sql.*; //importa ligacao com banco de dados
import br.com.infox.dal.ModuloConexao; //importa a conexao
import br.com.infox.dal.limpaOsCamposUsuario;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;
/**
 *
 * @author ART
 */
public class TelaUsuario extends javax.swing.JInternalFrame {

    /**
     * Creates new form TelaUsuario
     */
    //limpaOsCamposUsuario limpaTudo = new limpaOsCamposUsuario();
    PreparedStatement pst = null; // smp colocar na tela que ira usar o bacco
    java.sql.Connection conexao = null;// smp colocar na tela que ira usar o bacco
    ResultSet rs = null; //smp colocar na tela que ira usar o bacco EXIBE O RESULTADO DA CONXECAO COM BANCO

    public TelaUsuario() { //CONSTRUTOR DA TELA
        initComponents();
        conexao = ModuloConexao.conector(); //chama a conexao smp colocar na tela que sera usado conexao

    
    }
    
    private void consultarUsuario(){
        String sql = "select * from tbusuarios where usuario=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUsuNome.getText()); //joga o valor para o interoga do selet
            rs = pst.executeQuery(); // executa a query resultset
            if (rs.next()) {
                txtUsuId.setText(rs.getString(1));
                txtUsuNome.setText(rs.getString(2)); //o RS pega o valor do banco
                txtUsuFone.setText(rs.getString(3));
                txtxUsuLogin.setText(rs.getString(4));
                txtUsuSenha.setText(rs.getString(5));
                cboUsuPerfil.setSelectedItem(rs.getString(6)); // adicionando ao comboBox
                
                
            }
            else {
                //Exibe a mesg e limpa os campos
                JOptionPane.showMessageDialog(null, "Nome inexistente..".toUpperCase());           
                txtUsuFone.setText(null);
                txtxUsuLogin.setText(null);
                txtUsuSenha.setText(null);
          
                
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void adicionarUsuario(){
         String sql="insert into tbusuarios(usuario,fone,login,senha,perfil)\n" +
         "values(?, ?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql); //prepara conexao
            //pst.setString(1, txtUsuId.getText());
            pst.setString(1, txtUsuNome.getText().toUpperCase());
            pst.setString(2, txtUsuFone.getText());
            pst.setString(3, txtxUsuLogin.getText().toUpperCase());
            pst.setString(4, txtUsuSenha.getText()); // Atribui o valor do txt para onde tem interogaçao
            pst.setString(5, cboUsuPerfil.getSelectedItem().toString()); // buscando valor do combobox PRECISA CONVERTER
           //validacao de campos obrigatorios
           if(txtUsuNome.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha o campo Nome!");
            }else if(txtxUsuLogin.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha o campo Login!");
            }else if(txtUsuSenha.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha o campo Senha!");
            }
            else
            {
            
            //confirma a insercao dos dados na tabela
            int adicionado = pst.executeUpdate(); //executa query de atualizacao
            //se o valor for maior q zero pq cadastrou
            if (adicionado>0){
            JOptionPane.showMessageDialog(null, "Usuario cadastrado com sucesso!");
             txtUsuId.setText(null);
             txtUsuNome.setText(null);
             txtUsuFone.setText(null);
             txtxUsuLogin.setText(null);
             txtUsuSenha.setText(null);
            
            }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Erro cadastro usuario"+ e);
        }
                
         
    }
    private void alterarUsuario(){
          String sql="update tbusuarios set usuario=?,fone=?,login=?,senha=?,perfil=? where iduser=?";
        try {
            pst = conexao.prepareStatement(sql); //prepara conexao
           
            pst.setString(1, txtUsuNome.getText().toUpperCase());
            pst.setString(2, txtUsuFone.getText());
            pst.setString(3, txtxUsuLogin.getText().toUpperCase());
            pst.setString(4, txtUsuSenha.getText()); // Atribui o valor do txt para onde tem interogaçao
            pst.setString(5, cboUsuPerfil.getSelectedItem().toString()); // buscando valor do combobox PRECISA CONVERTER
            pst.setString(6, txtUsuId.getText());
         if (txtUsuId.getText().isEmpty()) { // se for vazio
                 JOptionPane.showMessageDialog(null, "Preencha o campo ID!");
               
            } else if(txtUsuNome.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha o campo Nome!");
            }else if(txtxUsuLogin.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha o campo Login!");
            }else if(txtUsuSenha.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha o campo Senha!");
            }
            else
            {
            
            //confirma a insercao dos dados na tabela
            int adicionado = pst.executeUpdate(); //executa query de atualizacao                
            //se o valor for maior q zero pq cadastrou
            if (adicionado>0){ // se foi com sucesso retorna 1
            JOptionPane.showMessageDialog(null, "Dados do usuario Alterado com sucesso!");
             txtUsuId.setText(null);
             txtUsuNome.setText(null);
             txtUsuFone.setText(null);
             txtxUsuLogin.setText(null);
             txtUsuSenha.setText(null);
          
            }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Erro ao Alterar dados "+ e);
        }       
    }
    //metodo resposavel por remover usuario
    private void excluirUsuario(){
        //confirma remocao
        int confirma=JOptionPane.showConfirmDialog(null,"Tem certeza deseja remover este usuario?","Atençao.",JOptionPane.YES_NO_OPTION);
        
        if(confirma==JOptionPane.YES_OPTION){
            String sql="delete from tbusuarios where iduser=?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtUsuId.getText()); // atribui o valor para o campo id do sql
                int apagado = pst.executeUpdate(); // retorna 0 ou 1
                if(apagado>0){ // se foi com sucesso retorna 1
                    JOptionPane.showConfirmDialog(null,"Removido com sucesso..","Atençao.",JOptionPane.OK_CANCEL_OPTION);
                    txtUsuId.setText(null);
                    txtUsuNome.setText(null);
                    txtUsuFone.setText(null);
                    txtxUsuLogin.setText(null);
                    txtUsuSenha.setText(null);
                }
            } catch (Exception e) {
                JOptionPane.showConfirmDialog(null,"Erro ao remover!!"+e,"Atençao.",JOptionPane.ERROR_MESSAGE);
            }
            }
        
       
    }
    
    private void limpar(){
        txtUsuFone.setText(null);
        txtxUsuLogin.setText(null);
        txtUsuSenha.setText(null);
    }
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtUsuId = new javax.swing.JTextField();
        txtUsuNome = new javax.swing.JTextField();
        txtxUsuLogin = new javax.swing.JTextField();
        txtUsuSenha = new javax.swing.JTextField();
        cboUsuPerfil = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txtUsuFone = new javax.swing.JTextField();
        btnUsuCreate = new javax.swing.JButton();
        btnUsuRead = new javax.swing.JButton();
        btnUsuUpdate = new javax.swing.JButton();
        btnUsuDelete = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Usuarios");
        setPreferredSize(new java.awt.Dimension(1014, 618));

        jLabel1.setText("id");

        jLabel2.setText("*Nome");

        jLabel3.setText("*Login");

        jLabel4.setText("*Senha");

        jLabel5.setText("*Perfil");

        txtUsuId.setEnabled(false);

        cboUsuPerfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "admin", "user" }));

        jLabel6.setText("Fone");

        btnUsuCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/create.png"))); // NOI18N
        btnUsuCreate.setToolTipText("Adicionar");
        btnUsuCreate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuCreate.setPreferredSize(new java.awt.Dimension(80, 80));
        btnUsuCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuCreateActionPerformed(evt);
            }
        });

        btnUsuRead.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/read.png"))); // NOI18N
        btnUsuRead.setToolTipText("Consultar");
        btnUsuRead.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuRead.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuReadActionPerformed(evt);
            }
        });

        btnUsuUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/update.png"))); // NOI18N
        btnUsuUpdate.setToolTipText("Atualizar");
        btnUsuUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuUpdateActionPerformed(evt);
            }
        });

        btnUsuDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/delete.png"))); // NOI18N
        btnUsuDelete.setToolTipText("Apagar");
        btnUsuDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuDeleteActionPerformed(evt);
            }
        });

        jLabel7.setText("*Campos Obrigatorios");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(124, 124, 124)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtUsuNome, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtUsuId, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(93, 93, 93)
                                .addComponent(jLabel7))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtUsuFone, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addComponent(txtUsuSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(44, 44, 44)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtxUsuLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(18, 18, 18)
                                        .addComponent(cboUsuPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(btnUsuCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnUsuRead)
                        .addGap(18, 18, 18)
                        .addComponent(btnUsuUpdate)
                        .addGap(18, 18, 18)
                        .addComponent(btnUsuDelete)))
                .addContainerGap(334, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnUsuCreate, btnUsuDelete, btnUsuRead, btnUsuUpdate});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtUsuId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtUsuNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtxUsuLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6)
                    .addComponent(txtUsuFone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtUsuSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboUsuPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnUsuCreate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnUsuRead, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnUsuUpdate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnUsuDelete, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(188, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnUsuCreate, btnUsuDelete, btnUsuRead, btnUsuUpdate});

        setBounds(0, 0, 950, 618);
    }// </editor-fold>//GEN-END:initComponents

    private void btnUsuReadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuReadActionPerformed
        // Chamando o metodo consultar
        consultarUsuario();
    }//GEN-LAST:event_btnUsuReadActionPerformed

    private void btnUsuCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuCreateActionPerformed
        // Chama o metodo adicionar
        adicionarUsuario();
        
    }//GEN-LAST:event_btnUsuCreateActionPerformed

    private void btnUsuUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuUpdateActionPerformed
        // Chama matodo alterar
        alterarUsuario();
    }//GEN-LAST:event_btnUsuUpdateActionPerformed

    private void btnUsuDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuDeleteActionPerformed
        // Chama metodo remover
        excluirUsuario();
        
    }//GEN-LAST:event_btnUsuDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnUsuCreate;
    private javax.swing.JButton btnUsuDelete;
    private javax.swing.JButton btnUsuRead;
    private javax.swing.JButton btnUsuUpdate;
    public javax.swing.JComboBox<String> cboUsuPerfil;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JSeparator jSeparator1;
    public javax.swing.JTextField txtUsuFone;
    public javax.swing.JTextField txtUsuId;
    public javax.swing.JTextField txtUsuNome;
    public javax.swing.JTextField txtUsuSenha;
    public javax.swing.JTextField txtxUsuLogin;
    // End of variables declaration//GEN-END:variables
}
