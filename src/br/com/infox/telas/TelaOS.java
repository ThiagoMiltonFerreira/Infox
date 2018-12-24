/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infox.telas;

import java.sql.*; //import o sql
import br.com.infox.dal.ModuloConexao; //importa a conexao
import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;  // import para carregar o infromaçao para o grid

/**
 *
 * @author ART
 */
public class TelaOS extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form TelaOS
     */
    public TelaOS() {
        initComponents();
        conexao = ModuloConexao.conector();
        
    }

    private void pesquisar_cliente() {
        String sql = "select idcli as Id, nomecli as Nome, fonecli as Fone  from tbclientes where nomecli like ?"; //select somente por determinadas letras
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtCliPesquisar.getText() + "%");
            rs = pst.executeQuery();
            tblClientes.setModel(DbUtils.resultSetToTableModel(rs));
          
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void setarCampos() {
        int setar = tblClientes.getSelectedRow();
        txtCliId.setText(tblClientes.getModel().getValueAt(setar, 0).toString());

    }

    private void adicionarOs() {
        String sql = "insert into tbos(idcli,equipamento,defeito,servico,tecnico,valor,tipo,situacao)\n"
                + "values(?,?,?,?,?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql); //prepara conexao
            pst.setString(1, txtCliId.getText());
            pst.setString(2, txtOsEquip.getText());
            pst.setString(3, txtOsDef.getText());
            pst.setString(4, txtOsServ.getText());
            pst.setString(5, txtOsTec.getText());  // Atribui o valor do txt para onde tem interogaçao
            pst.setString(6, txtOsValor.getText().replace(",", ".")); // .replace(",", ".") substitui a virgula pelo ponto
            pst.setString(7, cboOsSit.getSelectedItem().toString());
            if(tbtOrc.isSelected())
                
            {
                pst.setString(8, tbtOrc.getText().toString());
            }
            else if(rbtOs.isSelected())
            {
                pst.setString(8, rbtOs.getText().toString());
            }
            else{
                 JOptionPane.showMessageDialog(null, "Informe se orçamento ou ordem de serviço.","Verifique campo",JOptionPane.ERROR_MESSAGE);
            }
            // pst.setString(8, buttonGroup1.)
            //validacao de campos obrigatorios
            

            if (txtOsEquip.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha o campo Equipamento!");

            } else if (txtOsDef.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha o campo Defeito!");
            } 
            if (txtCliId.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Pesquise o Cliente no campo acima!");
            }
            {

                //confirma a insercao dos dados na tabela
                int adicionado = pst.executeUpdate(); //executa query de atualizacao
                //se o valor for maior q zero pq cadastrou
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "OS cadastrada com sucesso!");
                    txtOsDef.setText(null);
                    txtOsServ.setText(null);
                    txtOsTec.setText(null);
                    txtOsEquip.setText(null);
                    txtOsValor.setText(null);

                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro cadastro Ordem de serviço!!" + e);
        }

    }
    private void alterar_os(){
        String sql = "update tbos set equipamento=?,defeito=?,servico=?,tecnico=?,valor=?,tipo =?,situacao=? where os=?";
         try {
            pst = conexao.prepareStatement(sql); //prepara conexao para executar o sql
            
            pst.setString(1, txtOsEquip.getText());
            pst.setString(2, txtOsDef.getText());
            pst.setString(3, txtOsServ.getText());
            pst.setString(4, txtOsTec.getText());  // Atribui o valor do txt para onde tem interogaçao
            pst.setString(5, txtOsValor.getText().replace(",", "."));
            if(tbtOrc.isSelected())
            {
                pst.setString(6, tbtOrc.getText().toString());
            }
            else
            {
                pst.setString(6, rbtOs.getText().toString());
            }
            pst.setString(7, cboOsSit.getSelectedItem().toString());
            pst.setString(8, txtOs.getText());
           
  
            //validacao de campos obrigatorios
            
            if (txtOsEquip.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha o campo Equipamento!");

            } else if (txtOsDef.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha o campo Defeito!");
            } else if (txtOsServ.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha o campo Serviço!");
            } else if (txtOsTec.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha o campo Tecnico!");
            }
            if (txtCliId.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Pesquise o Cliente no campo acima!");
            }
            {

                //confirma a insercao dos dados na tabela
                int adicionado = pst.executeUpdate(); //executa query de atualizacao
                //se o valor for maior q zero pq cadastrou
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "OS Alterada com sucesso!");
                    txtOs.setText(null);
                    txtData.setText(null);
                    txtCliId.setText(null);
                    txtOsEquip.setText(null);
                    txtOsDef.setText(null);
                    txtOsServ.setText(null);
                    txtOsTec.setText(null);
                    txtOsValor.setText(null);
                    
                   // habilitar os objetos
                   
                    btnOsAdicionar.setEnabled(true);
                    txtCliPesquisar.setEnabled(true);
                    tblClientes.setVisible(true); // ativa a tabela como visivel
                    
                    

                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao auterar Ordem de serviço!!" + e);
        }

        
    }
    
    
    // metodo para excluir uma OS
    private void excluir_os(){
        int confirma =  JOptionPane.showConfirmDialog(null,"Tem Certeza que deseja excluir esta OS?", "Atencão!",JOptionPane.YES_NO_OPTION);
        if(confirma==JOptionPane.YES_OPTION)
        {
           String sql = "delete from tbos where os = ?";
            try {
                 pst = conexao.prepareStatement(sql); //prepara conexao para executar o sql
                 pst.setString(1, txtOs.getText());
                 int apagado = pst.executeUpdate();
                 if(apagado>0){
                     JOptionPane.showMessageDialog(null, "OS excluida com sucesso!");
                    txtOs.setText(null);
                    txtData.setText(null);
                    txtCliId.setText(null);
                    txtOsEquip.setText(null);
                    txtOsDef.setText(null);
                    txtOsServ.setText(null);
                    txtOsTec.setText(null);
                    txtOsValor.setText(null);
                    
                   // habilitar os objetos
                   
                    btnOsAdicionar.setEnabled(true);
                    txtCliPesquisar.setEnabled(true);
                    tblClientes.setVisible(true); // ativa a tabela como visivel
                     
                 }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }
    
    private void pesquisar_os(){
        // a linha abaixo cria uma caixa de entrada JOpame
        String num_os=JOptionPane.showInputDialog("Numero da OS");
        String sql="select * from tbos where os=" + num_os;
        try {
            pst=conexao.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){ // caso tenha um rgistro
                txtOs.setText(rs.getString(1));
                txtData.setText(rs.getString(2));
                // setando os radio butons
                String rbtTipo =  rs.getString(10);                
     
               
                if(rbtTipo.equals("OS")){
                    rbtOs.setSelected(true); // seleciona o radio button
                   
                }else{
                    tbtOrc.setSelected(true);
                  
                }
                cboOsSit.setSelectedItem(rs.getString(9));
                txtOsEquip.setText(rs.getString(3));
                txtOsDef.setText(rs.getString(4));
                txtOsServ.setText(rs.getString(5));
                txtOsTec.setText(rs.getString(6));
                txtOsValor.setText(rs.getString(7));
                txtCliId.setText(rs.getString(8));
                
                // evitando problemas
                //ao pesquisar nao posso adicionar mais abilitar o botao de adicionar
                btnOsAdicionar.setEnabled(false);
                txtCliPesquisar.setEnabled(false);
                tblClientes.setVisible(false); // desativa edicao da tabela
   
            }else
            {
                JOptionPane.showMessageDialog(null,"Os Nao cadastrada!");
            }
        } catch (com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException e) {   
            //com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException --> tira o exception e coloca o erro 
            // do sql ao colocar por exeplo uma letra no campo que e numero, nesse caso esta tratando a excessao especifica
            
            JOptionPane.showMessageDialog(null, "OS Invalida!");
            //System.out.println(e);
        } catch (Exception e2){ // trata outras excessoes nao tratadas
            JOptionPane.showMessageDialog(null, e2);
        }
           
        
        
    }
    public void imprimirOs() {
     

        
         java.io.FileWriter arquivo = null;
             
          String orcamento;                
                String osNumero ="OS: "+txtOs.getText().toString()+"   -   "+txtData.getText().toString();
                if(tbtOrc.isSelected()){
                    orcamento ="Tipo: "+tbtOrc.getText().toString(); 
                }else{
                    orcamento ="Tipo: "+rbtOs.getText().toString();
                }  
                String data ="Data: "+txtData.getText().toString();
                String cbOs ="Situação: "+cboOsSit.getSelectedItem().toString();
                String equipamento ="Equipamento: "+txtOsEquip.getText().toString();
                String defeito ="Defeito: "+txtOsDef.getText().toString();
		String servico ="Serviço: "+txtOsServ.getText().toString();
                String tecnico ="Tecnico: "+txtOsTec.getText().toString();
                String valor ="Valor: "+txtOsValor.getText().toString();
               
         
         
         
         
         
                try {
			arquivo = new java.io.FileWriter(new File("C:\\RELATORIO OS.txt"));
                        BufferedWriter bufferedWriter = new BufferedWriter(arquivo);
                        
                        arquivo.write(osNumero+"\r\n");
                        arquivo.write(orcamento+"\r\n");
                        arquivo.write(data+"\r\n");
			arquivo.write(cbOs+"\r\n");
                        arquivo.write(equipamento+"\r\n");
                        arquivo.write(defeito+"\r\n");
                        arquivo.write(servico+"\r\n");
                        arquivo.write(tecnico+"\r\n");
                        arquivo.write(valor+"\r\n");
                        // bufferedWriter.newLine();
                      
			arquivo.close();
                        
            
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
     
                Desktop desktop = Desktop.getDesktop();
                File arquivoImprimir = new File("C:\\RELATORIO OS.txt");
                try {
                    desktop.print(arquivoImprimir);
                } catch (IOException ex)
                {
                    Logger.getLogger(teste_impressao.class.getName()).log(Level.SEVERE, null, ex);
                }
        
         

     
               /**
                //Gerar o TXT com todos os campos
                String orcamento;                
                String osNumero ="OS: "+txtOs.getText().toString()+"   -   "+txtData.getText().toString();
                if(tbtOrc.isSelected()){
                    orcamento ="Tipo: "+tbtOrc.getText().toString(); 
                }else{
                    orcamento ="Tipo: "+rbtOs.getText().toString();
                }  
                String data ="Data: "+txtData.getText().toString();
                String cbOs ="Situação: "+cboOsSit.getSelectedItem().toString();
                String equipamento ="Equipamento: "+txtOsEquip.getText().toString();
                String defeito ="Defeito: "+txtOsDef.getText().toString();
		String servico ="Serviço: "+txtOsServ.getText().toString();
                String tecnico ="Tecnico: "+txtOsTec.getText().toString();
                String valor ="Valor: "+txtOsValor.getText().toString();
                java.io.FileWriter arquivo = null;
	
                try {
			arquivo = new java.io.FileWriter(new File("C:\\Users\\ART\\Desktop\\RELATORIO OS.txt"));
                        BufferedWriter bufferedWriter = new BufferedWriter(arquivo);
                      
                        arquivo.write(osNumero+"\r\n");
                        arquivo.write(orcamento+"\r\n");
                        arquivo.write(data+"\r\n");
			arquivo.write(cbOs+"\r\n");
                        arquivo.write(equipamento+"\r\n");
                        arquivo.write(defeito+"\r\n");
                        arquivo.write(servico+"\r\n");
                        arquivo.write(tecnico+"\r\n");
                        arquivo.write(valor+"\r\n");
                        // bufferedWriter.newLine();
                      
			arquivo.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
  
                // IMPRIME O TXT        
                Desktop desktop = Desktop.getDesktop();
                File arquivoImprimir = new File("C:\\Users\\ART\\Desktop\\RELATORIO OS.txt");
                try {
                    desktop.print(arquivoImprimir);
                } catch (IOException ex)
                {
                    Logger.getLogger(teste_impressao.class.getName()).log(Level.SEVERE, null, ex);
                }
      
}
**/
               
     
    }
    


    /**
    * 
    * 
    * 
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtOs = new javax.swing.JTextField();
        txtData = new javax.swing.JTextField();
        rbtOs = new javax.swing.JRadioButton();
        tbtOrc = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        cboOsSit = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        txtCliPesquisar = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtCliId = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        txtOsEquip = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtOsDef = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtOsServ = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtOsTec = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtOsValor = new javax.swing.JTextField();
        btnOsAdicionar = new javax.swing.JButton();
        btnOsPesquisar = new javax.swing.JButton();
        btnOsAlterar = new javax.swing.JButton();
        btnOsExcluir = new javax.swing.JButton();
        btnOsImprimir = new javax.swing.JButton();
        btnImprimirTeste = new javax.swing.JButton();

        jButton1.setText("jButton1");

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("OS");
        setMinimumSize(new java.awt.Dimension(950, 618));
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(950, 618));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("N OS");

        jLabel2.setText("Data");

        txtOs.setEditable(false);

        txtData.setEditable(false);

        buttonGroup1.add(rbtOs);
        rbtOs.setText("OS");

        buttonGroup1.add(tbtOrc);
        tbtOrc.setText("Orçamento");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(tbtOrc)
                        .addGap(22, 22, 22)
                        .addComponent(rbtOs))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtOs, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtOs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtOs)
                    .addComponent(tbtOrc))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setText("Situação");

        cboOsSit.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Entrega OK", "Orçamento REPROVADO", "Aguardando peças", "Abandonado Pelo Cliente", "Na bancada", "Retornou" }));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Cliente"));

        txtCliPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCliPesquisarKeyReleased(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/read.png"))); // NOI18N

        jLabel5.setText("*Id");

        txtCliId.setEditable(false);

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Id", "Nome", "Fone"
            }
        ));
        tblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblClientes);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 566, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtCliPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCliId, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(txtCliPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCliId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel6.setText("* Equipamento");

        jLabel7.setText("*Defeito");

        jLabel8.setText("Serviço");

        jLabel9.setText("Tecnico");

        jLabel10.setText("Valor Total ");

        txtOsValor.setText("0");

        btnOsAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/create.png"))); // NOI18N
        btnOsAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOsAdicionarActionPerformed(evt);
            }
        });

        btnOsPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/read.png"))); // NOI18N
        btnOsPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOsPesquisarActionPerformed(evt);
            }
        });

        btnOsAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/update.png"))); // NOI18N
        btnOsAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOsAlterarActionPerformed(evt);
            }
        });

        btnOsExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/delete.png"))); // NOI18N
        btnOsExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOsExcluirActionPerformed(evt);
            }
        });

        btnOsImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/imprimir.png"))); // NOI18N
        btnOsImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOsImprimirActionPerformed(evt);
            }
        });

        btnImprimirTeste.setText("Setar formulario com dados do banco");
        btnImprimirTeste.setEnabled(false);
        btnImprimirTeste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirTesteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtOsServ, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 725, Short.MAX_VALUE)
                                    .addComponent(txtOsDef, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtOsEquip, javax.swing.GroupLayout.Alignment.LEADING))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(btnOsAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnOsPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 127, Short.MAX_VALUE)
                                        .addComponent(btnOsAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnOsExcluir)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnOsImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtOsTec, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(44, 44, 44)
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtOsValor)
                                        .addGap(69, 69, 69)))
                                .addGap(183, 183, 183))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(cboOsSit, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)))
                        .addGap(24, 24, 24)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnImprimirTeste))))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnOsAdicionar, btnOsAlterar, btnOsExcluir, btnOsImprimir, btnOsPesquisar});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboOsSit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))))
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtOsEquip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtOsDef, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtOsServ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtOsTec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(txtOsValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(65, 65, 65)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnOsPesquisar, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnOsExcluir)
                    .addComponent(btnOsImprimir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnOsAdicionar)
                        .addComponent(btnOsAlterar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(btnImprimirTeste))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnOsAdicionar, btnOsAlterar, btnOsExcluir, btnOsImprimir, btnOsPesquisar});

        setBounds(0, 0, 950, 618);
    }// </editor-fold>//GEN-END:initComponents

    private void tblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientesMouseClicked
       //Chamando o metodo setar campos
       setarCampos();
    }//GEN-LAST:event_tblClientesMouseClicked

    private void btnOsAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOsAdicionarActionPerformed
         //chama o metodo pesquisar os
       adicionarOs();
    }//GEN-LAST:event_btnOsAdicionarActionPerformed

    private void btnOsPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOsPesquisarActionPerformed
        //chama o metodo pesquisar os
       pesquisar_os();
    }//GEN-LAST:event_btnOsPesquisarActionPerformed

    private void btnOsAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOsAlterarActionPerformed

           // Chama metodo Pesquisar
        alterar_os();
    }//GEN-LAST:event_btnOsAlterarActionPerformed

    private void btnOsExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOsExcluirActionPerformed
       // chamando metodo para excluir os
        excluir_os();
    }//GEN-LAST:event_btnOsExcluirActionPerformed

    private void btnOsImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOsImprimirActionPerformed
               // Chama Botao Imprimir
        imprimirOs();
    }//GEN-LAST:event_btnOsImprimirActionPerformed

    private void btnImprimirTesteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirTesteActionPerformed
          // Seta a tabela com todos campos da base de dados    
        String sql="select * from tbos";
        try {
            pst=conexao.prepareStatement(sql);
            rs=pst.executeQuery();
     
            
             tblClientes.setModel(DbUtils.resultSetToTableModel(rs));
           
            
           /** if(rs.next()){ // caso tenha um rgistro
                txtOs.setText(rs.getString(1));
                txtData.setText(rs.getString(2));
                // setando os radio butons
                String rbtTipo =  rs.getString(10);                
     
                lblTest.setText(rbtTipo);
                if(rbtTipo.equals("OS")){
                    rbtOs.setSelected(true); // seleciona o radio button
                   
                }else{
                    tbtOrc.setSelected(true);
                  
                }
                cboOsSit.setSelectedItem(rs.getString(9));
                txtOsEquip.setText(rs.getString(3));
                txtOsDef.setText(rs.getString(4));
                txtOsServ.setText(rs.getString(5));
                txtOsTec.setText(rs.getString(6));
                txtOsValor.setText(rs.getString(7));
                txtCliId.setText(rs.getString(8));
                
                // evitando problemas
                //ao pesquisar nao posso adicionar mais abilitar o botao de adicionar
                btnOsAdicionar.setEnabled(false);
                txtCliPesquisar.setEnabled(false);
                tblClientes.setVisible(false); // desativa edicao da tabela
   
            }else
            {
                JOptionPane.showMessageDialog(null,"Os Nao cadastrada!");
            }
            * **/
           
        } catch (com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException e) {   
            //com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException --> tira o exception e coloca o erro 
            // do sql ao colocar por exeplo uma letra no campo que e numero, nesse caso esta tratando a excessao especifica
            
            JOptionPane.showMessageDialog(null, "OS Invalida!");
            //System.out.println(e);
        } catch (Exception e2){ // trata outras excessoes nao tratadas
            JOptionPane.showMessageDialog(null, e2);
        }
        
        
    }//GEN-LAST:event_btnImprimirTesteActionPerformed

    private void txtCliPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCliPesquisarKeyReleased
            // Pesquisar cliente a medida por digitando 
        pesquisar_cliente();
    }//GEN-LAST:event_txtCliPesquisarKeyReleased
    /**
     * *
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnImprimirTeste;
    private javax.swing.JButton btnOsAdicionar;
    private javax.swing.JButton btnOsAlterar;
    private javax.swing.JButton btnOsExcluir;
    private javax.swing.JButton btnOsImprimir;
    private javax.swing.JButton btnOsPesquisar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboOsSit;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rbtOs;
    private javax.swing.JTable tblClientes;
    private javax.swing.JRadioButton tbtOrc;
    private javax.swing.JTextField txtCliId;
    private javax.swing.JTextField txtCliPesquisar;
    private javax.swing.JTextField txtData;
    private javax.swing.JTextField txtOs;
    private javax.swing.JTextField txtOsDef;
    private javax.swing.JTextField txtOsEquip;
    private javax.swing.JTextField txtOsServ;
    private javax.swing.JTextField txtOsTec;
    private javax.swing.JTextField txtOsValor;
    // End of variables declaration//GEN-END:variables

   
}
