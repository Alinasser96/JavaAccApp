/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Roots;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author aly hamalawey
 */
public class Roots extends Application {
    double sum=0;
    
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        Button btn2 = new Button();
        Button btn3 = new Button();
        Button btn4 = new Button();
        Button btn5 = new Button();
        TextField namef = new TextField();
        TextField names = new TextField();
        TextField phonef  = new TextField();
        TextField emailf = new TextField();
        TextField noo3f = new TextField();
        TextField fe2af = new TextField();
        TextField numf  = new TextField();
        
        Label namel = new Label("اسم العميل ");
        Label namels = new Label("بحث باسم العميل ");
        Label phonel = new Label("هاتف");
        Label emaill = new Label("ايميل");
        Label datel = new Label("تاريخ الفاتورة");
        Label knuml = new Label("دفتر رقم");
        Label fnuml = new Label("فاتورة رقم");
        Label facnamel = new Label("العميل");
        
        //فاتوره
        
                   
                     
                     
                     
        
        
        
       
        
        btn.setText("إضافة");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                String name= namef.getText();
                String phone= phonef.getText();
                String email= emailf.getText();
                
                Connection conn;
                try {
                    conn = getmysqlconnection();
                    insertClient(conn,name,phone,email);
                    JOptionPane.showMessageDialog(null, "تمت الاضافة");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "حدث خطأ ");
                }
                namef.setText("");
                phonef.setText("");
                emailf.setText("");
                
                
                
            }
            
        });
        
        
        
        
       btn2.setText("بحث");
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                 Connection conn = null;
                 Statement st = null;
                 ResultSet rs = null;
                 String s ; 
                 String name= names.getText();
                 try {
                     conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/rootsAcc","root","");
                     st=conn.createStatement();
                     s="select * from data WHERE client LIKE '%"+name+"%'";
                     rs = st.executeQuery(s);
                     ResultSetMetaData rsmt = rs.getMetaData();
                     int c = rsmt.getColumnCount();
                     Vector coulmn = new Vector(c);
                     
                     for (int i =1 ; i<=c ;i++){
                     coulmn.add(rsmt.getColumnName(i));
                     
                     }
                     Vector row = new Vector();
                     Vector data = new Vector();
                     
                     
                     while(rs.next()){
                     row = new Vector(c);
                     for (int i =1 ; i<=c ;i++){
                     row.add(rs.getString(i));
                     
                     }
                     data.add(row);
                     
                     
                     }
                     
                     
                     
                     
                     JFrame frame = new JFrame();
                     frame.setSize(500,120);
                     frame.setLocationRelativeTo(null);
                     
                     JPanel panel = new JPanel();
                     JTable table = new JTable(data,coulmn);
                     int k = table.getSelectedRow();
                     
                    
                     JScrollPane jsp = new JScrollPane(table);
                     panel.setLayout(new BorderLayout());
                     panel.add(jsp,BorderLayout.CENTER);
                     frame.setContentPane(panel);
                     frame.setVisible(true);
                } catch (Exception e) {
                     JOptionPane.showMessageDialog(null, "error");
                }finally{
                     
                     try {
                         conn.close();
                         rs.close();
                         st.close();
                     } catch (Exception e) {
                          JOptionPane.showMessageDialog(null, "error close");
                     }
            
            }
            }
        });
        
        
        
        btn3.setText("+");
        btn3.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                sum=0;
                JFrame frame = new JFrame();
                     frame.setSize(500,120);
                     frame.setLocationRelativeTo(null);
                     
                     JPanel panel = new JPanel();
                     JTable table = new JTable();
                     Object[] columns = {"سعر"," عدد"," فئة","نوع"};
                     DefaultTableModel model = new DefaultTableModel();
                     model.setColumnIdentifiers(columns);
                     table.setModel(model);
                     JTextField textId = new JTextField();
                     JTextField textFname = new JTextField();
                     JTextField textLname = new JTextField();
                     JTextField textAge = new JTextField();
                     JTextField facnamef = new JTextField();
                     JTextField datef = new JTextField();
                     JTextField knumf = new JTextField();
                     JTextField fnumf = new JTextField();
                     
                     textId.setBounds(20, 220, 100, 25);
                     textFname.setBounds(20, 250, 100, 25);
                     textLname.setBounds(20, 280, 100, 25);
                     textAge.setBounds(20, 310, 100, 25);
                     facnamef.setBounds(500, 220, 100, 25);
                     datef.setBounds(500, 250, 100, 25);
                     knumf.setBounds(500, 280, 100, 25);
                     fnumf.setBounds(500, 310, 100, 25);

                     JButton btnAdd = new JButton("Add");
                     JButton btnAddsql = new JButton("Addsql");
                     btnAdd.setBounds(150, 220, 100, 25);
                     btnAddsql.setBounds(630, 220, 100, 25);
                     JScrollPane pane = new JScrollPane(table);
                     pane.setBounds(0, 0, 880, 200);
        
                     frame.setLayout(null);
        
                     frame.add(pane);
                     frame.add(textId);
                     frame.add(textFname);
                     frame.add(textLname);
                     frame.add(textAge);
                     frame.add(facnamef);
                     frame.add(datef);
                     frame.add(knumf);
                     frame.add(fnumf);
                     frame.add(btnAddsql);
                     frame.add(btnAdd);
                     Object[] row = new Object[4];
                   
                     
                     
                // button add row
        btnAdd.addActionListener(new ActionListener(){
            
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e ) {
                double price =Double.parseDouble(textFname.getText())*Double.parseDouble(textLname.getText());
                row[0] = price;
                 row[1] = textLname.getText();
                 row[2] = textFname.getText();
                row[3] = textId.getText();
                
                sum=sum+price;
                
                textAge.setText(""+sum+"");
                
               
                
           
                // add row to the model
                model.addRow(row);
                
            }
        });
        btnAddsql.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e ) {
             
                
                String facname= facnamef.getText();
                String date= datef.getText();
                String knum= knumf.getText();
                String fnum= fnumf.getText();
                
                Connection conn;
                try {
                    conn = getmysqlconnection();
                    insertData(conn,facname,date,knum,fnum,sum,"counter 9*9","","");
                    JOptionPane.showMessageDialog(null, "تمت الاضافة");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "حدث خطأ ");
                }
                facnamef.setText("");
                datef.setText("");
                knumf.setText("");
                fnumf.setText("");
                
                
               
                
            }
        });
        
                       frame.setSize(900,400);
        frame.setLocationRelativeTo(null);
        
        frame.setVisible(true);
             
            }
        });
        
        
        
        GridPane root = new GridPane();
        root.add(namef ,0,0);
        root.add(names ,2,0);
        root.add(phonef,0,1);
        root.add(emailf,0,2);
        root.add(namel, 1, 0);
        root.add(namels, 3, 0);
        root.add(phonel,1,1);
        root.add(emaill,1,2);
        root.add(btn,2,2);
        root.add(btn2,4,2);
        root.add(btn3,5,2);
        root.setAlignment(Pos.CENTER);
        root.setHgap(20);
        root.setVgap(20);
        root.setPadding(new Insets(25,25,25,25));
        
        Scene scene = new Scene(root, 600, 600);
       
        
        
        primaryStage.setTitle("RootsAcc");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    
    
    
     public static ResultSet searchclient(Connection conn,String name)throws Exception{
       
    return getresultset(conn,"select * from client where name like '%"+name+"%'");
    }
     public static ResultSet searchfat(Connection conn,String name)throws Exception{
       
    return getresultset(conn,"select * from data where clint like '%"+name+"%'");
    }

    public static ResultSet getresultset (Connection conn ,String sql)throws Exception{
    Class.forName("com.mysql.jdbc.Driver");
    Statement  stm = conn.createStatement(java.sql.ResultSet.CONCUR_READ_ONLY,java.sql.ResultSet.TYPE_FORWARD_ONLY);
    return stm.executeQuery(sql);
    
    }


    public static void insertClient(Connection conn ,String name,String phone , String email) throws Exception{


      PreparedStatement pst = conn.prepareStatement("insert into client (name,phone,email) values (?,?,?)");
      pst.setString(1, name);
      pst.setString(2,phone);
      pst.setString(3,email);
      pst.execute();
      pst.close();


    }
    public static void insertData(Connection conn ,String facname,String date,String knum , String fnum,double price,String things,String ta,String tb) throws Exception{


      PreparedStatement pst = conn.prepareStatement("insert into data (client,date,knum,fnum,price,things,typea,typeb) values (?,?,?,?,?,?,?,?)");
      pst.setString(1, facname);
      pst.setString(2,date);
      pst.setString(3,knum);
      pst.setString(4,fnum);
      pst.setDouble(5,price);
      pst.setString(6,things);
      pst.setString(7,ta);
      pst.setString(8,tb);
      pst.execute();
      pst.close();


    }



    public static Connection getmysqlconnection() throws Exception{
    Class.forName("com.mysql.jdbc.Driver");
    return DriverManager.getConnection("jdbc:mysql://127.0.0.1/rootsAcc","root","");
    }
    
    
}
