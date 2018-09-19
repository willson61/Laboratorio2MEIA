/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.pkg2.mie.pkg2018;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.security.MessageDigest;
import java.util.ArrayList;

/**
 *
 * @author Sthephan
 */
public class MainView extends javax.swing.JFrame {

    /**
     * Creates new form MainView
     */
    public MainView() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtContraseña = new javax.swing.JTextField();
        btnValidar = new javax.swing.JButton();
        labelInfo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnValidar.setText("Validar");
        btnValidar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnValidarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelInfo)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(btnValidar)))
                .addContainerGap(94, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnValidar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelInfo)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    File archivo1 = new File("C:\\MEIA\\puntuacion.txt");
    File archivo2 = new File("C:\\MEIA\\resultado.txt");
    File usuario = new File("C:\\MEIA\\usuario.txt");
    static ArrayList<Integer> valores = new ArrayList<Integer>();
    static ArrayList<Integer> criterio = new ArrayList<Integer>();
    String strError;
    
    public void leerArchivo(){
        FileReader puntuacion;
        FileReader resultado;
            try{
                puntuacion = new FileReader(archivo1);
                BufferedReader lectura = new BufferedReader(puntuacion);
                String linea;
                try{
                    linea = lectura.readLine();
                    while(linea != null){
                        valores.add(Integer.parseInt(linea));
                        linea = lectura.readLine();
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
                puntuacion.close();
                lectura.close();
            }catch(Exception e){
                e.printStackTrace();
            }
            
            try{
                resultado = new FileReader(archivo2);
                BufferedReader lectura2 = new BufferedReader(resultado);
                String linea2;
                String[] split = null;
                try{
                    linea2 = lectura2.readLine();
                    while(linea2 != null){
                        if(!"".equals(linea2)){
                            split = linea2.split(",");
                        }
                        criterio.add(Integer.parseInt(split[0]));
                        criterio.add(Integer.parseInt(split[1]));
                        linea2 = lectura2.readLine();
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
                lectura2.close();
                resultado.close();
            }catch(Exception e){
                e.printStackTrace();
            }
            
    }
    
    public String calcularSeguridad(String contraseña){
        int puntos = 0;
        String mensaje = "";
        if(contraseña.length() < valores.get(0)){
            mensaje = ("La contraseña debe ser mayor a "+valores.get(0).toString() + " caracteres \n porfavor ingrese nuevamente una contraseña");
        }
        else{
            puntos += valores.get(1)*contraseña.length();
            puntos += valores.get(2)*calcularMayus(contraseña);
            puntos += valores.get(3)*calcularLetras(contraseña);
            puntos += valores.get(4)*calcularNumeros(contraseña);
            puntos += (calcularSimbolos(contraseña)*(contraseña.length()+valores.get(5)));
            if(calcularLetras(contraseña) == contraseña.length()){
                puntos = puntos - valores.get(6);
            }
            if(calcularNumeros(contraseña) == contraseña.length()){
                puntos = puntos - valores.get(7);
            }
            if((puntos >= criterio.get(0)) && (puntos <= criterio.get(1))){
            mensaje = "Contraseña Insegura";
            }
            if((puntos >= criterio.get(2)) && (puntos <= criterio.get(3))){
                mensaje = "Contraseña poco Segura";
            }
            if((puntos >= criterio.get(4)) && (puntos <= criterio.get(5))){
                mensaje = "Contraseña Segura";
            }
            if((puntos >= criterio.get(6)) && (puntos <= criterio.get(7))){
                mensaje = "Contraseña muy Segura";
            }
        }
        return mensaje;
    }
    
    public int calcularMayus(String contraseña){
        int cont = 0;
        for(int i = 0; i < contraseña.length(); i++){
            if(Character.isUpperCase(contraseña.charAt(i))){
                cont++;
            }             
        }
        return cont;
    }
    public int calcularLetras(String contraseña){
        int cont = 0;
        for(int i = 0; i < contraseña.length(); i++){
            if(Character.isLetter(contraseña.charAt(i))){
                cont++;
            }             
        }
        return cont;
    }
    public int calcularNumeros(String contraseña){
        int cont = 0;
        for(int i = 0; i < contraseña.length(); i++){
            if(Character.isDigit(contraseña.charAt(i))){
                cont++;
            }             
        }
        return cont;
    }
    public int calcularSimbolos(String contraseña){
        int cont = 0;
        for(int i = 0; i < contraseña.length(); i++){
            if((Character.valueOf(contraseña.charAt(i)).equals('/')) || (Character.valueOf(contraseña.charAt(i)).equals('¿')) || (Character.valueOf(contraseña.charAt(i)).equals('?')) || (Character.valueOf(contraseña.charAt(i)).equals('%')) || (Character.valueOf(contraseña.charAt(i)).equals('$')) || (Character.valueOf(contraseña.charAt(i)).equals('#'))){
                cont++;
            }             
        }
        return cont;
    }
    
    private void btnValidarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnValidarActionPerformed
        leerArchivo();
        if(txtContraseña.getText().equals("")){
            labelInfo.setText("Debe ingresar una contraseña para continuar");
        }
        else{
            String seguridad = calcularSeguridad(txtContraseña.getText());
            String contraseña = txtContraseña.getText();
            labelInfo.setText(seguridad);
            if((seguridad.equals("Contraseña Segura")) || (seguridad.equals("Contraseña muy Segura"))){
                try{
                    FileWriter Escribir = new FileWriter(usuario, true);
                    BufferedWriter bw = new BufferedWriter(Escribir);
                    FileOutputStream fos = new FileOutputStream(usuario);
                    MessageDigest md = MessageDigest.getInstance("MD5");
                    byte[] messageDigest = md.digest(contraseña.getBytes());
                    if(usuario.exists()){
                        fos.write('\n');
                        fos.write(messageDigest);
                    }
                    else{
                        fos.write(messageDigest);
                    }
                    fos.flush();
                    fos.close();
                    bw.close();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        
    }//GEN-LAST:event_btnValidarActionPerformed

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
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnValidar;
    private javax.swing.JLabel labelInfo;
    private javax.swing.JTextField txtContraseña;
    // End of variables declaration//GEN-END:variables
}
