import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class MainFrame extends JFrame
{
Container c;
JButton btnAddSol,btnViewSol,btnUpdateSol,btnDeleteSol;


MainFrame()
{
c=getContentPane();
c.setLayout(new FlowLayout());


btnAddSol=new JButton("Add");
btnViewSol=new JButton("View");
btnUpdateSol=new JButton("Update");
btnDeleteSol=new JButton("Delete");

c.add(btnAddSol);
c.add(btnViewSol);
c.add(btnUpdateSol);
c.add(btnDeleteSol);


btnAddSol.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae)
{
AddFrame a=new AddFrame();
dispose();
}
});

btnViewSol.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae)
{
ViewFrame a=new ViewFrame();
dispose();
}
});

btnUpdateSol.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae)
{
UpdateFrame a = new UpdateFrame();
//to make UpdateFrame visible and MainFrame go away we use
dispose();
}
});


btnDeleteSol.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae)
{
DeleteFrame a = new DeleteFrame();
//to make DeleteFrame visible and MainFrame go away we use
dispose();
}
});




setTitle("Military System");
setSize(500,150);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}//end of constructor
public static void main(String args[])
{
MainFrame m=new MainFrame();
}//end of main
}//end of class



class DatabaseHandler
{
static Connection con;

static void getCon()
{
try
{ 
DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
con=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","system","abc123");
}// end of try
catch(SQLException e)
{
System.out.println(e);
}//end of catch
}//end of getCon


public void addSoldier(int id,String name)
{
getCon();


try
{
String sql="insert into soldier values(?,?)";
PreparedStatement pst=con.prepareStatement(sql);
pst.setInt(1,id);
pst.setString(2,name);
int r=pst.executeUpdate();
JOptionPane.showMessageDialog(new JDialog(),r+"records inserted");
}//end of try
catch(SQLException e)
{
JOptionPane.showMessageDialog(new JDialog(),"insert issue");
}//end of catch
}//end of addEmployee



public String viewSoldier()
{
StringBuffer sb=new StringBuffer();
getCon();

try
{
String sql="select * from soldier";
Statement stmt=con.createStatement();
ResultSet rs=stmt.executeQuery(sql);
while(rs.next())
{
int r=rs.getInt(1);
String n=rs.getString(2);
sb.append("Id:"+r+"Name:"+n+ "\n");
}
}
catch(SQLException e)
{
JOptionPane.showMessageDialog(new JDialog(),"some issue");
}
return sb.toString();
}//end of viewEmployee()



public void updateSoldier(int id, String name)
{
getCon();
try{
String sql = "update soldier set name = (?) where id =(?)";
PreparedStatement pst = con.prepareStatement(sql);
pst.setInt(2,id);
pst.setString(1,name);
int r = pst.executeUpdate();
JOptionPane.showMessageDialog(new JDialog(), r+" record(s) were updated!");
}
catch(SQLException e)
{
JOptionPane.showMessageDialog(new JDialog(),"Error occured while updating : "+e);
}
}//end of update function


public void deleteSoldier(int id)
{
getCon();
try{
String sql = "delete from soldier where id = (?)";
PreparedStatement pst = con.prepareStatement(sql);
pst.setInt(1,id);
int r = pst.executeUpdate();
JOptionPane.showMessageDialog(new JDialog(), r +" record(s) were deleted!");
}
catch(SQLException e)
{
JOptionPane.showMessageDialog(new JDialog(),"Error occured while deleting : "+e);
}
}//end of delete function

}//end of DBHandler class




























