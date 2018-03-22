import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class DeleteFrame extends JFrame{
Container c;
JLabel lblId;
JTextField txtId;
JButton btnSave, btnBack;
JPanel p1, p2;

DeleteFrame(){
c = getContentPane();
p1 = new JPanel();
lblId = new JLabel("ID");
txtId = new JTextField(5);

p1.add(lblId);
p1.add(txtId);

c.add(p1);

p2 = new JPanel();
btnSave =  new JButton("Save");
btnBack = new JButton("Back");
p2.add(btnSave);
p2.add(btnBack);

c.add("South",p2);

btnSave.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae)
{
String i = txtId.getText();

if(i.length()==0)
{
JOptionPane.showMessageDialog(new JDialog(),"Id is Empty");
txtId.requestFocus();
return;
}

DatabaseHandler d = new DatabaseHandler();
d.deleteSoldier(Integer.parseInt(i));
txtId.setText("");
txtId.requestFocus();
}//end of actionPerformed
});



btnBack.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae)
{
MainFrame a = new MainFrame();
//to make MainFrame visible and UpdateFrame go away we use
dispose();
}
});


setTitle("Delete Record");
setSize(500,150);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//visbility to grt GUI dispalyed
setVisible(true);
//end of constructor
}
}
