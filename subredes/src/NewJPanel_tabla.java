
import java.awt.Color;
import javax.sound.sampled.DataLine;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author flyingcodd
 */
public class NewJPanel_tabla extends javax.swing.JPanel{

    /**
     * Creates new form NewJPanel_tabla
     */
    String byte1,byte2,byte3,byte4,nro_subred_input;
    public NewJPanel_tabla(String byte1,String byte2,String byte3,String byte4,String nro_subred_input) {
        this.byte1=byte1;
        this.byte2=byte2;
        this.byte3=byte3;
        this.byte4=byte4;
        this.nro_subred_input=nro_subred_input;
        initComponents();
        
        MostrarTabla();
        
        this.jTable1.setDefaultRenderer(Object.class, new MiRender());
    }
    private void MostrarTabla(){
        String nombreColumnas[]={"Nro","ID de red","Host Configurables","Broadcast"};
        
        
        jTable1.setModel(new DefaultTableModel(LlenarTabla(), nombreColumnas));
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(40);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(150);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(310);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(150);
        
        //JOptionPane.showMessageDialog(jPanel1,"w");
    }
    
    private String[][] LlenarTabla(){
        String data[][]={};
        int saltos=1;int nro_subredes=1,nro_bits=1;
        
        if(Integer.parseInt(byte1)>=192&&Integer.parseInt(byte1)<224){
            if(Integer.parseInt(nro_subred_input)>62){
                JOptionPane.showMessageDialog(this, "nuero de sub red exedido, solo menores o iguales a 62", "ERROR", JOptionPane.ERROR_MESSAGE);               
            }
            else{
                jLabel_Titulo.setText(jLabel_Titulo.getText()+"\"C\"");
                int C[]={0,2,6,14,30,62,126};
                for(int i=0;i<7;i++){
                    if(C[i]>=Integer.parseInt(nro_subred_input)){
                        nro_subredes=C[i];
                        nro_bits=i+1;
                        break;
                    }
                }   
                saltos=256/(nro_subredes+2);
                data= new String[nro_subredes+2][4];
                for(int i=0; i<nro_subredes+2; i++){
                    data[i][0]=""+(i+1);
                    data[i][1]=byte1+"."+byte2+"."+byte3+"."+(i*saltos);
                    data[i][2]=byte1+"."+byte2+"."+byte3+"."+(i*saltos+1)+" - "+byte1+"."+byte2+"."+byte3+"."+((i+1)*saltos-2);
                    data[i][3]=byte1+"."+byte2+"."+byte3+"."+((i+1)*saltos-1);
            
                }
                String binario="";
                for(int i=0;i<8;i++){
                    if(i<nro_bits){
                        binario+="1";
                    }
                    else{
                        binario+="0";
                    }
                }
                jLabel_ipsconf.setText(""+(Math.pow(2, 8-nro_bits)-2));
                jLabel_mascarasubred.setText("/"+(24+nro_bits+" o "+"255.255.255."+ConvertidorBinario(binario)));
            }
        }
        else if(Integer.parseInt(byte1)>=128&&Integer.parseInt(byte1)<=191){
            if(Integer.parseInt(nro_subred_input)>126){
                JOptionPane.showMessageDialog(this, "nuero de sub red exedido, solo menores o iguales a 126", "ERROR", JOptionPane.ERROR_MESSAGE);               
            }
            else{
                jLabel_Titulo.setText(jLabel_Titulo.getText()+"\"B\"");
                int B[]={0,2,6,14,30,62,126,254,510,1022,2046,4094,8190,16382};
                for(int i=0;i<14;i++){
                    if(B[i]>=Integer.parseInt(nro_subred_input)){
                        nro_subredes=B[i];
                        nro_bits=i+1;
                        break;
                    }
                }
                saltos=256/(nro_subredes+2);
                data= new String[nro_subredes+2][4];
                for(int i=0; i<nro_subredes+2; i++){
                    data[i][0]=""+(i+1);
                    data[i][1]=byte1+"."+byte2+"."+(i*saltos)+".0";
                    data[i][2]=byte1+"."+byte2+"."+(i*saltos)+".1 - "+byte1+"."+byte2+"."+((i+1)*saltos-1)+".254";
                    data[i][3]=byte1+"."+byte2+"."+((i+1)*saltos-1)+".255";
                }
            
                String binario="";
                for(int i=0;i<8;i++){
                    if(i<nro_bits){
                        binario+="1";
                    }
                    else{
                        binario+="0";
                    }
                    
                }
                jLabel_ipsconf.setText(""+(Math.pow(2, 16-nro_bits)-2));
                jLabel_mascarasubred.setText("/"+(16+nro_bits+" o "+"255.255."+ConvertidorBinario(binario))+".0");
            }
        }
        else if(Integer.parseInt(byte1)>0&&Integer.parseInt(byte1)<=127){
            if(Integer.parseInt(nro_subred_input)>126){
                JOptionPane.showMessageDialog(this, "nuero de sub red exedido, solo menores o iguales a 126", "ERROR", JOptionPane.ERROR_MESSAGE);               
            }
            else{
                jLabel_Titulo.setText(jLabel_Titulo.getText()+"\"A\"");
                int A[]={0,2,6,14,30,62,126,254,510,1022,2046,4094,8190,16382};
                for(int i=0;i<14;i++){
                    if(A[i]>=Integer.parseInt(nro_subred_input)){
                        nro_subredes=A[i];
                        nro_bits=i+1;
                        break;
                    }
                }
                saltos=256/(nro_subredes+2);
                data= new String[nro_subredes+2][4];
                for(int i=0; i<nro_subredes+2; i++){
                    data[i][0]=""+(i+1);
                    data[i][1]=byte1+"."+(i*saltos)+".0.0";
                    data[i][2]=byte1+"."+(i*saltos)+".0.1 - "+byte1+"."+((i+1)*saltos-1)+".255.254";
                    data[i][3]=byte1+"."+((i+1)*saltos-1)+".255.255";
                }
                    String binario="";
                for(int i=0;i<8;i++){
                    if(i<nro_bits){
                        binario+="1";
                    }
                    else{
                        binario+="0";
                    }
                }
                jLabel_ipsconf.setText(""+(Math.pow(2, 24-nro_bits)-2));
                jLabel_mascarasubred.setText("/"+(8+nro_bits+" o "+"255."+ConvertidorBinario(binario))+".0.0");
            }
        }
        else{
            jLabel_Titulo.setText("No seas pendejo esa IP no existe");
            JOptionPane.showMessageDialog(this, "ss","s",JOptionPane.ERROR_MESSAGE);
        }
        
        /*for(int i=0;i<8;i++){
            if(i<nro_bits){
                binario+="1";
            }
            else{
                binario+="0";
            }
        }
        jLabel_ipsconf.setText(""+(Math.pow(2, 8-nro_bits)-2));
        jLabel_mascarasubred.setText("/"+(24+nro_bits+" o "+"255.255.255."+ConvertidorBinario(binario)));*/
        jLabel2.setText(jLabel2.getText()+": "+nro_subredes);
        jLabel_bitsprestados.setText(nro_bits+"");
        jLabel_NroSaltos.setText(saltos+"");
        return data;
    }
    private int ConvertidorBinario(String binario){
        int exponente = 0;
        int decimal = 0; //será el equivalente en base decimal
        int digito;
        int numero= Integer.parseInt(binario);
        while (numero != 0) {
                  //se toma la última cifra
                  digito = numero % 10;
                  //se multiplica por la potencia de 2 correspondiente y se suma al número                          
                  decimal = decimal + digito * (int) Math.pow(2, exponente);
                  //se aumenta el exponente
                  exponente++;
                  //se quita la última cifra para repetir el proceso
                  numero = numero / 10;
        }
        return decimal;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel_Titulo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel_ipsconf = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel_mascarasubred = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel_bitsprestados = new javax.swing.JLabel();
        jLabel_NroSaltos = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(800, 460));

        jPanel1.setBackground(new java.awt.Color(102, 102, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel_Titulo.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel_Titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Titulo.setText("MI SUB RED ");
        jPanel1.add(jLabel_Titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 20, 790, -1));

        jTable1.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nro", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(5);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(200);
        }

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, 600, 280));

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel2.setForeground(java.awt.Color.green);
        jLabel2.setText("Disponibles");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, -1));

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel3.setForeground(java.awt.Color.red);
        jLabel3.setText("No disponibles");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 100, -1));

        jLabel4.setBackground(java.awt.Color.red);
        jLabel4.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Nro de IP's config por cada sub red:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 390, 230, 50));

        jLabel_ipsconf.setBackground(java.awt.Color.green);
        jLabel_ipsconf.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel_ipsconf.setText("S");
        jPanel1.add(jLabel_ipsconf, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 400, 90, 30));

        jLabel5.setBackground(java.awt.Color.red);
        jLabel5.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Mascara de subred:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 350, 230, 50));

        jLabel_mascarasubred.setBackground(java.awt.Color.green);
        jLabel_mascarasubred.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel_mascarasubred.setText("S");
        jPanel1.add(jLabel_mascarasubred, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 360, 210, 30));

        jLabel6.setBackground(java.awt.Color.red);
        jLabel6.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Nro de bits prestados:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 390, 150, 50));

        jLabel_bitsprestados.setBackground(java.awt.Color.green);
        jLabel_bitsprestados.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel_bitsprestados.setText("S");
        jPanel1.add(jLabel_bitsprestados, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 400, 40, 30));

        jLabel_NroSaltos.setBackground(java.awt.Color.green);
        jLabel_NroSaltos.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel_NroSaltos.setText("S");
        jPanel1.add(jLabel_NroSaltos, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 360, 40, 30));

        jLabel7.setBackground(java.awt.Color.red);
        jLabel7.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Nro de saltos:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 350, 150, 50));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel_NroSaltos;
    private javax.swing.JLabel jLabel_Titulo;
    private javax.swing.JLabel jLabel_bitsprestados;
    private javax.swing.JLabel jLabel_ipsconf;
    private javax.swing.JLabel jLabel_mascarasubred;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
