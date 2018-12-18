/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infox.telas;

import br.com.infox.dal.ModuloConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author ART
 */
public class TelaOsRelatorio extends javax.swing.JInternalFrame {
        
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    /**
     * Creates new form TelaOsRelatorio
     */
    public TelaOsRelatorio() {
        initComponents();
         conexao = ModuloConexao.conector(); //chama a conexao smp colocar na tela que sera usado conexao
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        cboTipo = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblRel = new javax.swing.JTable();
        btnRelPesquisar = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 48)); // NOI18N
        jLabel1.setText("Relatório Geral.");
        jLabel1.setPreferredSize(new java.awt.Dimension(10, 14));

        cboTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ordem de serviço", "Clientes" }));

        jLabel2.setText("Tipo");

        tblRel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Codigo do Cliente", "Nome", "Endereço", "Telefone", "Email"
            }
        ));
        jScrollPane2.setViewportView(tblRel);

        btnRelPesquisar.setText("Pesquisar");
        btnRelPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRelPesquisarActionPerformed(evt);
            }
        });

        btnImprimir.setText("Imprimir");
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(234, 234, 234)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(btnRelPesquisar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnImprimir))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 868, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRelPesquisar)
                    .addComponent(btnImprimir))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );

        setBounds(0, 0, 950, 618);
    }// </editor-fold>//GEN-END:initComponents

    public void relatorio() throws SQLException{
       String nomeDaTabela="";
        if(cboTipo.getSelectedItem().equals("Ordem de serviço"))
        {
           nomeDaTabela="tbOs";
           System.out.println(nomeDaTabela);
           
        }else if(cboTipo.getSelectedItem().equals("Clientes")){
           nomeDaTabela="tbClientes";
           //System.out.println(nomeDaTabela);  
        }
              
         String sql = "select * from "+nomeDaTabela; // este sql faz o select da taela de acordo com que esta marcado no combobox
        
        try {
            pst = conexao.prepareStatement(sql); //variavel da conxao      
            rs=pst.executeQuery(); // executa o sql
            
            // a linha abaixo usa a biblioteca rs2xml.jar   joga os dados para o resultset    
            tblRel.setModel(DbUtils.resultSetToTableModel(rs));  // seta a tabela clientes com o resultado do rs, sendo que DbUtils.resultSetToTableModel vem da biblioteca rs2xml que foi               importada previamente no site https://sourceforge.net/projects/finalangelsanddemons/
            // agora devemos criar um evento que pega em tempo real conteudo no campo de texto
            
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Erro ao Pesquisar!!"+ e);
        }
      
           
     
            
      }
    
    
    public void ImprimeRelatorioGeral(){
        try {
            MessageFormat headerFormat = new MessageFormat("Relatorio Geral"); //cabeçalho
            MessageFormat footerFormat = new MessageFormat("Thiago Ferreira Tecnologia"); //rodape
            tblRel.print(JTable.PrintMode.NORMAL, headerFormat,footerFormat); //imprime JTABLE
            
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null,"Erro ao Imprimir ! "+ e);
        }
    }
        


    
    
    private void btnRelPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRelPesquisarActionPerformed
    
        try {
            relatorio();
        } catch (SQLException ex) {
            Logger.getLogger(TelaOsRelatorio.class.getName()).log(Level.SEVERE, null, ex);
        }

      
    
    }//GEN-LAST:event_btnRelPesquisarActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        //imprimir
        ImprimeRelatorioGeral();
    }//GEN-LAST:event_btnImprimirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnRelPesquisar;
    private javax.swing.JComboBox<String> cboTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblRel;
    // End of variables declaration//GEN-END:variables
}