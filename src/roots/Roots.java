
package Roots;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author aly hamalawey
 */
public class Roots extends Application {
    double sum=0;
    double total=0;
    Connection conn2;
    
    
    @Override
    public void start(Stage primaryStage) {
        final ToggleGroup group = new ToggleGroup();
        Button btn = new Button();
        Button btn2 = new Button();
        Button btn3 = new Button();
        Button btn4 = new Button();
        Button btn5 = new Button();
        Button btn6 = new Button();
        
        ArrayList<String> bda3aarr=new ArrayList<String>();
        bda3aarr.add("aly");
        
        
        RadioButton rb1 = new RadioButton("عميل");
        rb1.setToggleGroup(group);
        rb1.setSelected(true);

        RadioButton rb2 = new RadioButton("تاريخ");
        rb2.setToggleGroup(group);

        RadioButton rb3 = new RadioButton("رقم فاتوره");
        rb3.setToggleGroup(group);
        
        RadioButton rb4 = new RadioButton("كشف حساب");
        

        rb1.setUserData("client");
        rb2.setUserData("date");
        rb3.setUserData("fnum");
        rb3.setUserData("prie");
        
        
    
        TextField namef = new TextField();
        TextField names = new TextField();
        TextField phonef  = new TextField();
        TextField emailf = new TextField();
        TextField noo3f = new TextField();
        
        
        Label namel = new Label("الاسم ");
        Label namels = new Label("بحث عن ");
        Label phonel = new Label("السعر");
        Label emaill = new Label("الكود");
        Label hsap = new Label("إجمالي");
        Label datel = new Label("تاريخ الفاتورة");
        Label knuml = new Label("دفتر رقم");
        Label fnuml = new Label("فاتورة رقم");
        Label facnamel = new Label("العميل");
        
        //فاتوره
        
                   
                     
                     
        
       try {
    FileOutputStream fos = new FileOutputStream("bda3a");
    ObjectOutputStream oos = new ObjectOutputStream(fos);
    oos.writeObject(bda3aarr);
    oos.close();
} catch(Exception e) {
    e.printStackTrace();
}  
        
        
       
        
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
                total=0;
                
                 String keyword="" ;
                 String name= names.getText();
                            if(rb1.isSelected()){
                   keyword= "client";
                    try {
                    Class.forName("com.mysql.jdbc.Driver");
                    conn2 =DriverManager.getConnection("jdbc:mysql://127.0.0.1/rootsAcc?useUnicode=yes&characterEncoding=UTF-8","root","");
                    //conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/rootsAcc?useUnicode=yes&characterEncoding=UTF-8","root","");
                    //String r="C:\\Users\\aly hamalawey\\Documents\\NetBeansProjects\\Roots\\src\\roots\\report1.jrxml";
                    JasperDesign jd = JRXmlLoader.load("C:\\Users\\aly hamalawey\\Documents\\NetBeansProjects\\Roots\\src\\roots\\report2.jrxml");
                    String sql = "SELECT\n" +
"     data.`date` AS data_date,\n" +
"     data.`fnum` AS data_fnum,\n" +
"     data.`price` AS data_price\n" +
"FROM\n" +
"     `data` data WHERE data.`"+keyword+"` LIKE '"+name+"'";
                    JRDesignQuery newquery = new JRDesignQuery();
                    newquery.setText(sql);
                    jd.setQuery(newquery);
                    HashMap param = new HashMap();
                    param.put("name",name);
                    JasperReport jr=JasperCompileManager.compileReport(jd);
                    JasperPrint jp= JasperFillManager.fillReport(jr,param,conn2);
                    JasperViewer.viewReport(jp,false);
                } catch (JRException ex) {
                    Logger.getLogger(Roots.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Roots.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(Roots.class.getName()).log(Level.SEVERE, null, ex);
                } 
                   }
                   if(rb2.isSelected()){
                   keyword= "date";
                    try {
                    Class.forName("com.mysql.jdbc.Driver");
                    conn2 =DriverManager.getConnection("jdbc:mysql://127.0.0.1/rootsAcc?useUnicode=yes&characterEncoding=UTF-8","root","");
                    //conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/rootsAcc?useUnicode=yes&characterEncoding=UTF-8","root","");
                    //String r="C:\\Users\\aly hamalawey\\Documents\\NetBeansProjects\\Roots\\src\\roots\\report1.jrxml";
                    JasperDesign jd = JRXmlLoader.load("C:\\Users\\aly hamalawey\\Documents\\NetBeansProjects\\Roots\\src\\roots\\report1.jrxml");
                    String sql = "SELECT\n" +
"     data.`client` AS data_client,\n" +
"     data.`price` AS data_price,\n" +
"     data.`date` AS data_date\n" +
"FROM\n" +
"     `data` data WHERE data.`"+keyword+"` LIKE '"+name+"'";
                    JRDesignQuery newquery = new JRDesignQuery();
                    newquery.setText(sql);
                    jd.setQuery(newquery);
                    JasperReport jr=JasperCompileManager.compileReport(jd);
                    JasperPrint jp= JasperFillManager.fillReport(jr,null,conn2);
                    JasperViewer.viewReport(jp,false);
                } catch (JRException ex) {
                    Logger.getLogger(Roots.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Roots.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(Roots.class.getName()).log(Level.SEVERE, null, ex);
                } 
                   }
                   if(rb3.isSelected()){
                   keyword= "fnum";
                    try {
                    Class.forName("com.mysql.jdbc.Driver");
                    conn2 =DriverManager.getConnection("jdbc:mysql://127.0.0.1/rootsAcc?useUnicode=yes&characterEncoding=UTF-8","root","");
                    //conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/rootsAcc?useUnicode=yes&characterEncoding=UTF-8","root","");
                    //String r="C:\\Users\\aly hamalawey\\Documents\\NetBeansProjects\\Roots\\src\\roots\\report1.jrxml";
                    JasperDesign jd = JRXmlLoader.load("C:\\Users\\aly hamalawey\\Documents\\NetBeansProjects\\Roots\\src\\roots\\report1.jrxml");
                    String sql = "SELECT\n" +
"     data.`client` AS data_client,\n" +
"     data.`price` AS data_price,\n" +
"     data.`date` AS data_date\n" +
"FROM\n" +
"     `data` data WHERE data.`"+keyword+"` LIKE '"+name+"'";
                    JRDesignQuery newquery = new JRDesignQuery();
                    newquery.setText(sql);
                    jd.setQuery(newquery);
                    JasperReport jr=JasperCompileManager.compileReport(jd);
                    JasperPrint jp= JasperFillManager.fillReport(jr,null,conn2);
                    JasperViewer.viewReport(jp,false);
                } catch (JRException ex) {
                    Logger.getLogger(Roots.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Roots.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(Roots.class.getName()).log(Level.SEVERE, null, ex);
                } 
                   }
                   if(rb4.isSelected()){
                   noo3f.setVisible(true);
                   }
                   else{
                   noo3f.setVisible(false);
                   }
                
                   
               
                  
              
                
                
            
        
              
            }
        });
        
        
        
        btn3.setText("فاتورة جديدة");
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

                     JButton btnAdd = new JButton("إضافة عنصر");
                     JButton btnAddsql = new JButton("إضافة الفاتورة");
                     JButton btnPrint = new JButton("'طباعة الفاتورة");
                     btnAdd.setBounds(150, 220, 100, 25);
                     btnAddsql.setBounds(630, 220, 100, 25);
                     btnPrint.setBounds(630, 250, 100, 25);
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
                     frame.add(btnPrint);
                     Object[] row = new Object[4];
                   
                     
                     
                // button add row
        btnAdd.addActionListener(new ActionListener(){
            
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e ) {
                Vector row2 = getrow(textId);
                double price =Double.parseDouble(textFname.getText())*Double.parseDouble(row2.get(2).toString());
                row[0] = price;
                 row[1] = row2.get(2);
                 row[2] = row2.get(1);
                row[3] = row2.get(3);
                
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
                    insertData(conn,facname,date,knum,fnum,sum);
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
        btnPrint.addActionListener(new ActionListener(){
            
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e ) {
                
                   String facname= facnamef.getText();
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    conn2 =DriverManager.getConnection("jdbc:mysql://127.0.0.1/rootsAcc?useUnicode=yes&characterEncoding=UTF-8","root","");
                    //conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/rootsAcc?useUnicode=yes&characterEncoding=UTF-8","root","");
                    //String r="C:\\Users\\aly hamalawey\\Documents\\NetBeansProjects\\Roots\\src\\roots\\report1.jrxml";
                    JasperDesign jd = JRXmlLoader.load("C:\\Users\\aly hamalawey\\Documents\\NetBeansProjects\\Roots\\src\\roots\\report1.jrxml");
                    String sql = "SELECT\n" +
"     data.`client` AS data_client,\n" +
"     data.`price` AS data_price,\n" +
"     data.`date` AS data_date\n" +
"FROM\n" +
"     `data` data WHERE data.`client` LIKE '"+facname+"'";
                    JRDesignQuery newquery = new JRDesignQuery();
                    newquery.setText(sql);
                    jd.setQuery(newquery);
                    JasperReport jr=JasperCompileManager.compileReport(jd);
                    JasperPrint jp= JasperFillManager.fillReport(jr,null,conn2);
                    JasperViewer.viewReport(jp);
                } catch (JRException ex) {
                    Logger.getLogger(Roots.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Roots.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(Roots.class.getName()).log(Level.SEVERE, null, ex);
                } 
                  
              
                
                
            }
        });
        
                       frame.setSize(900,400);
        frame.setLocationRelativeTo(null);
        
        frame.setVisible(true);
             
            }
        });
        
        btn4.setText("استلام نقدية");
        btn4.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
                JFrame frame = new JFrame();
                     frame.setSize(500,120);
                     frame.setLocationRelativeTo(null);
                     
                     
                     
                     
                    
                     JTextField facnamef = new JTextField();
                     JTextField datef = new JTextField();
                     JTextField knumf = new JTextField();
                     
                     
                     
                     facnamef.setBounds(500, 220, 100, 25);
                     datef.setBounds(500, 250, 100, 25);
                     knumf.setBounds(500, 280, 100, 25);
                     

                     
                     JButton btnAddsql = new JButton("إضافة");
                     
                     btnAddsql.setBounds(630, 220, 100, 25);
                     
                     
        
                     frame.setLayout(null);
        
                     
                     frame.add(facnamef);
                     frame.add(datef);
                     frame.add(knumf);
                     
                     frame.add(btnAddsql);
                     
                     
                   
                     
                     
                // button add row
  
        btnAddsql.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e ) {
             
                
                String facname= facnamef.getText();
                String date= datef.getText();
                String knum= knumf.getText();
                double xxx= Double.parseDouble(knum);
                
                
                Connection conn;
                try {
                    conn = getmysqlconnection();
                    insertData(conn,facname,date,knum,"",-1*xxx);
                    JOptionPane.showMessageDialog(null, "تمت الاضافة");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "حدث خطأ ");
                }
                facnamef.setText("");
                datef.setText("");
                knumf.setText("");
                
                
                
               
                
            }
        });
        
                       frame.setSize(900,400);
        frame.setLocationRelativeTo(null);
        
        frame.setVisible(true);
             
            }
        });
        
        
        
        GridPane root = new GridPane();
        root.add(rb1 ,4,0);
        root.add(rb2 ,5,0);
        root.add(rb3 ,6,0);
        root.add(rb4 ,6,1);
        root.add(namef ,0,0);
        root.add(names ,5,2);
        root.add(phonef,0,1);
        root.add(emailf,0,2);
        root.add(namel, 1, 0);
        root.add(namels, 6, 2);
        root.add(hsap, 6, 3);
        root.add(phonel,1,1);
        root.add(emaill,1,2);
        root.add(btn,0,4);
        root.add(btn2,4,2);
        root.add(btn3,3,4);
        root.add(btn4,4,4);
        root.add(noo3f,5,3);
        root.setAlignment(Pos.CENTER);
        root.setHgap(20);
        root.setVgap(20);
        root.setPadding(new Insets(25,25,25,25));
        
        Scene scene = new Scene(root, 800, 400);
       
        
        
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
    public static void insertData(Connection conn ,String facname,String date,String knum , String fnum,double price) throws Exception{


      PreparedStatement pst = conn.prepareStatement("insert into data (client,date,knum,fnum,price) values (?,?,?,?,?)");
      pst.setString(1, facname);
      pst.setString(2,date);
      pst.setString(3,knum);
      pst.setString(4,fnum);
      pst.setDouble(5,price);
     
      
      pst.execute();
      pst.close();


    }
    public static Connection getmysqlconnection() throws Exception{
    
    Class.forName("com.mysql.jdbc.Driver");
    return DriverManager.getConnection("jdbc:mysql://127.0.0.1/rootsAcc?useUnicode=yes&characterEncoding=UTF-8","root","");
    }
    
    public static Vector getrow(JTextField names){
    
                
                 Connection conn = null;
                 Statement st = null;
                 ResultSet rs = null;
                 String s ; 
                 
                 String name= names.getText();
                 Vector row = new Vector();
                            
                 try {
                     conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/rootsAcc?useUnicode=yes&characterEncoding=UTF-8","root","");
                     st=conn.createStatement();
                     s="select * from client WHERE email LIKE '%"+name+"%'";
                     rs = st.executeQuery(s);
                     ResultSetMetaData rsmt = rs.getMetaData();
                     int c = rsmt.getColumnCount();
                     
                     
                     
                     
                     
                     while(rs.next()){
                     row = new Vector(c);
                     for (int i =1 ; i<=c ;i++){
                     row.add(rs.getString(i));
                    
                     
                     }
                     
                     
                     
                     }   
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
                 return row;
    }
   
   
    
    
}
