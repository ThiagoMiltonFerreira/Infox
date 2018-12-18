/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infox.telas;
import java.sql.*;
import br.com.infox.dal.ModuloConexao; //importa a conexao
import javax.swing.JOptionPane;
//A linha a baixo importa recursos da biblioteca  rs2xml.jar que trabalha com o gridview
import net.proteanit.sql.DbUtils;

public class TelaCliente extends javax.swing.JInternalFrame {
    PreparedStatement pst = null; // smp colocar na tela que ira usar o bacco
    java.sql.Connection conexao = null;// smp colocar na tela que ira usar o bacco
    ResultSet rs = null; //smp colocar na tela que ira usar o bacco EXIBE O RESULTADO DA CONXECAO COM BANCO
    
   
    public TelaCliente() {
        initComponents();
        conexao=ModuloConexao.conector(); // inicia conexao ao abrir o frm
    }
    
    //metodo para adicionar clietes
    private void adicionarCliente(){
         String sql="insert into tbclientes(nomecli,endcli,fonecli,emailcli)\n" +
         "values(?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql); //prepara conexao
            pst.setString(1, txtCliNome.getText());
            pst.setString(2, txtCliEndereco.getText());
            pst.setString(3, txtCliFone.getText());
            pst.setString(4, txtCliEmail.getText());  // Atribui o valor do txt para onde tem interogaçao
            
             //validacao de campos obrigatorios
            if (txtCliNome.getText().isEmpty()) {
                 JOptionPane.showMessageDialog(null, "Preencha o campo Nome!");
               
            } else if(txtCliFone.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha o campo Fone!");
            }else
            {
            
            //confirma a insercao dos dados na tabela
            int adicionado = pst.executeUpdate(); //executa query de atualizacao
            //se o valor for maior q zero pq cadastrou
            if (adicionado>0){
            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
             txtCliNome.setText(null);
             txtCliEndereco.setText(null);
             txtCliFone.setText(null);
             txtCliEmail.setText(null);
   
            }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Erro cadastro Cliente!!"+ e);
        }
                
         
    }     

    //metodo para pesquisar por cliente pelo nome, o SELECT * FROM dbinfox.tbClientes where nomecli like 'thi%'; conforme digita ele ja pesquisa
    private void pesquisarClientePorIniciais(){
        String sql = "select * from tbclientes where nomecli like ?"; // este sql pesquisa de acordo com o que for escrito dps do LIKE usado para pesquisar pelas letras
        try {
            pst = conexao.prepareStatement(sql); //variavel da conxao
            //passando o conteudo da caixa de pesquisa para a interogaçao..
            //atençao ao % q e contnuaçao da string sql
            pst.setString(1, txtClipesquisar.getText()+"%" ); //passo o valor do campo de texto o interroga la do sql..% faz parete da sql, entao para colocar no interoga junto precisa                      concatenar
            rs=pst.executeQuery(); // executa o sql
            
            // a linha abaixo usa a biblioteca rs2xml.jar   joga os dados para o resultset    
            tblClientes.setModel(DbUtils.resultSetToTableModel(rs));  // seta a tabela clientes com o resultado do rs, sendo que DbUtils.resultSetToTableModel vem da biblioteca rs2xml que foi               importada previamente no site https://sourceforge.net/projects/finalangelsanddemons/
            // agora devemos criar um evento que pega em tempo real conteudo no campo de texto
            
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Erro ao Pesquisar Cliente!!"+ e);
        }
    }
    
    //metodo para ao clicar no campo do formulario ele ja preencher os campos
    public void setarCampos(){
        int setar = tblClientes.getSelectedRow(); //pega a linha que esta selecionada na tabela
        txtCliId.setText(tblClientes.getModel().getValueAt(setar, 0).toString());
        txtCliNome.setText(tblClientes.getModel().getValueAt(setar, 1).toString());  // getValueAt(setar, 1) linha que esta selecionada(pega atraves do tblClientes.getSelectedRow(); que esta            atribuido a variavel ) coluna 1 
        txtCliEndereco.setText(tblClientes.getModel().getValueAt(setar, 2).toString()); // getValueAt(setar, 2) linha que esta selecionada(pega atraves do tblClientes.getSelectedRow(); que esta            atribuido a variavel ) coluna 1 
        txtCliFone.setText(tblClientes.getModel().getValueAt(setar, 3).toString()); // getValueAt(setar, 3) linha que esta selecionada(pega atraves do tblClientes.getSelectedRow(); que esta            atribuido a variavel ) coluna 1 
        txtCliEmail.setText(tblClientes.getModel().getValueAt(setar, 4).toString()); // getValueAt(setar, 4) linha que esta selecionada(pega atraves do tblClientes.getSelectedRow(); que esta            atribuido a variavel ) coluna 1 
        //A linha abaixo desabilita o campo adicionar caso o usuario  tente adicionar um cadastro ja esxistente
        btnAdicionar.setEnabled(false);
    
    
    }
    // altera cliente
    private void alterarCliente(){
          String sql="update tbclientes set nomecli = ? ,endcli = ?,fonecli = ?,emailcli = ? where idcli = ?";
        try {
            pst = conexao.prepareStatement(sql); //prepara conexao
           
            pst.setString(1, txtCliNome.getText());
            pst.setString(2, txtCliEndereco.getText());
            pst.setString(3, txtCliFone.getText());
            pst.setString(4, txtCliEmail.getText()); // Atribui o valor do txt para onde tem interogaçao
            pst.setString(5, txtCliId.getText());
            if(txtCliNome.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha o campo Nome!");
            }else if(txtCliEndereco.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha o Endereco!");
            }else if(txtCliFone.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha o campo Fone!");
            }else if(txtCliEmail.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha o campo Email!");
            }
            else
            {
            
            //confirma a insercao dos dados na tabela
            int adicionado = pst.executeUpdate(); //executa query de atualizacao                
            //se o valor for maior q zero pq cadastrou
            if (adicionado>0){ // se foi com sucesso retorna 1
            JOptionPane.showMessageDialog(null, "Dados do cliente Alterado com sucesso!");
             btnAdicionar.setEnabled(true); //Ativa o botao adicionar
             txtCliNome.setText(null);
             txtCliEndereco.setText(null);
             txtCliFone.setText(null);
             txtCliEmail.setText(null);
          
          
            }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Erro ao Alterar dados "+ e);
        }     
    }
    
    
  // esta linha deleta o cliente
    private void deletarCliente(){
            int confirma=JOptionPane.showConfirmDialog(null,"Tem certeza deseja remover este cliente ?","Atençao.",JOptionPane.YES_NO_OPTION);
        
        if(confirma==JOptionPane.YES_OPTION){
            String sql="delete from  tbclientes where idcli=?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtCliId.getText()); // atribui o valor para o campo cliente id do sql
                int apagado = pst.executeUpdate(); // retorna 0 ou 1
                if(apagado>0){ // se foi com sucesso retorna 1
                    JOptionPane.showConfirmDialog(null,"Cliente Removido com sucesso..","Atençao.",JOptionPane.OK_CANCEL_OPTION);
                    btnAdicionar.setEnabled(true); //Ativa o botao adicionar
                    txtCliNome.setText(null);
                    txtCliEndereco.setText(null);
                    txtCliFone.setText(null);
                    txtCliEmail.setText(null);
                }
            } catch (Exception e) {
                JOptionPane.showConfirmDialog(null,"Erro ao remover!!"+e,"Atençao.",JOptionPane.ERROR_MESSAGE);
            }
            }
        
    }

    
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtCliEndereco = new javax.swing.JTextField();
        txtCliNome = new javax.swing.JTextField();
        txtCliFone = new javax.swing.JTextField();
        txtCliEmail = new javax.swing.JTextField();
        btnRemover = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnAdicionar = new javax.swing.JButton();
        txtClipesquisar = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        txtCliId = new javax.swing.JTextField();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Clientes");
        setPreferredSize(new java.awt.Dimension(950, 618));

        jLabel1.setText("*Campos obrigatorios");

        jLabel2.setText("* Nome");

        jLabel3.setText("Endereço");

        jLabel4.setText("*Telefone");

        jLabel5.setText("Email");

        btnRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/delete.png"))); // NOI18N
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });

        btnAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/update.png"))); // NOI18N
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        btnAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/create.png"))); // NOI18N
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        txtClipesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtClipesquisarActionPerformed(evt);
            }
        });
        txtClipesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtClipesquisarKeyReleased(evt);
            }
        });

        jLabel6.setIcon(new javax.swing.ImageIcon("C:\\Users\\ART\\Desktop\\Projeto java netbens\\Imagens\\Icones\\pesquisar.png")); // NOI18N

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id", "Nome", "Endereço", "Telefone", "Email"
            }
        ));
        tblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblClientes);

        jLabel7.setText("Id Cliente");

        txtCliId.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel7)
                                        .addComponent(jLabel2)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(14, 14, 14)
                                                .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(103, 103, 103)
                                                .addComponent(btnAlterar))
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(txtCliEmail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                                                .addComponent(txtCliFone, javax.swing.GroupLayout.Alignment.LEADING)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnRemover)
                                        .addGap(54, 54, 54))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(txtCliEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 541, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtCliNome, javax.swing.GroupLayout.PREFERRED_SIZE, 541, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtCliId, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel1))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtClipesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)))
                .addGap(369, 369, 369))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 878, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnAdicionar, btnAlterar, btnRemover});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(txtClipesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtCliId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtCliNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtCliEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtCliFone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtCliEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRemover)
                    .addComponent(btnAlterar, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnAdicionar))
                .addGap(29, 29, 29))
        );

        setBounds(0, 0, 950, 618);
    }// </editor-fold>//GEN-END:initComponents

    private void txtClipesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtClipesquisarActionPerformed
    
    }//GEN-LAST:event_txtClipesquisarActionPerformed

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        //chama metodo que adiciona cliente 
        adicionarCliente();
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        // CHama metodo alterar
        alterarCliente();
    }//GEN-LAST:event_btnAlterarActionPerformed
        
    // o envento a baixo e do tipo Key > KeyReleased ou seja "enquanto for digitado" vai digitando e ja vai pesquisando
    private void txtClipesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClipesquisarKeyReleased
        // chama o metodo pesquisar cliente
        pesquisarClientePorIniciais();
    }//GEN-LAST:event_txtClipesquisarKeyReleased

    //evento da tabela ao clicar setar os campos  (clicando com mouse) Mouse>MouseClicked;
    private void tblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientesMouseClicked
        setarCampos();
        
    }//GEN-LAST:event_tblClientesMouseClicked

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        // Chama o metodo Deletar cliente
        deletarCliente();
    }//GEN-LAST:event_btnRemoverActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnRemover;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTextField txtCliEmail;
    private javax.swing.JTextField txtCliEndereco;
    private javax.swing.JTextField txtCliFone;
    private javax.swing.JTextField txtCliId;
    private javax.swing.JTextField txtCliNome;
    private javax.swing.JTextField txtClipesquisar;
    // End of variables declaration//GEN-END:variables
}
