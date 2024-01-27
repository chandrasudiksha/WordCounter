import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import static javax.swing.JOptionPane.showMessageDialog;
class WordCounter implements ActionListener
{
	JFrame f=new JFrame();
	JLabel l1,l2,l3,l4;
	JTextArea ta;
	JButton submit, clear;
	JTextField tf;
	WordCounter()
	{
		ta = new JTextArea("");
		submit = new JButton("SUBMIT");
		clear = new JButton("CLEAR");
		l1=new JLabel ("Enter file name:");
		l4=new JLabel ("(OR)");
		tf=new JTextField();
		l3 = new JLabel("Enter Your string Here : ");
		l2 = new JLabel("");
		Font txtFont = new Font(Font.SERIF, Font.PLAIN, 16);
		l1.setFont(txtFont);
		l4.setFont(txtFont);
		l3.setFont(txtFont);
		l2.setFont(txtFont);
		l1.setBounds(10, 25, 200, 20);
		tf.setBounds(150,25,200,20);
		l4.setBounds(150,55,200,30);
		l3.setBounds(10,80 ,200,30);
		ta.setBounds(18, 120, 450, 300);
		l2.setBounds(10, 425, 400, 30);
		submit.setBounds(100, 470, 100, 50);
		clear.setBounds(275, 470, 100, 50);
		ta.setLineWrap(true);
		ta.setWrapStyleWord(true);
		submit.addActionListener(this);
		clear.addActionListener(this);
		f.add(l1);
		f.add(tf);
		f.add(l4);
		f.add(l3);
		f.add(ta);
		f.add(l2);
		f.add(submit);
		f.add(clear);
		f.setSize(500, 570);
		f.setResizable(false);
		f.setLayout(null);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getSource()==clear)
		{
			ta.setText("");
			tf.setText("");
			l4.setText("(OR)");
			l3.setText("Enter Your string Here : ");
			l2.setText("");
		}
		else
		{
			if(ta.getText().equals("") && tf.getText().equals(""))
				showMessageDialog(null,"Enter string or select a file");
			else if(!ta.getText().equals("") && !tf.getText().equals(""))
				showMessageDialog(null,"Choose only one option\nIf you select file to count clear the textarea\nOr if you want to give text clear file name.");
			else if(ta.getText().equals("") && !tf.getText().equals(""))
			{
				String line;
				int count=0;
				try
				{
					FileReader file=new FileReader(tf.getText());
					BufferedReader br=new BufferedReader(file);
					while((line=br.readLine())!=null)
					{
						String words[]=line.split("[\\p{Punct}\\s+]");
						ta.append(line+"\n");
						count=count+words.length;
					}
					l4.setText("");
					l2.setText("No of Words : " + count);
					l3.setText("Content in the text file");
					br.close();
				}
				catch (FileNotFoundException e)
				{
					showMessageDialog(null,"File doesn't exist");
				}
				catch(IOException io)
				{
					System.out.println("IO");
				}
			}
			else
			{
				String str[] = ta.getText().split("\n");
				int count=0;
				for(int i=0;i<str.length;i++)
				{
					String words[]=str[i].split("[\\p{Punct}\\s+]");
					count=count+words.length;
				}
				l4.setText("(OR)");
				l3.setText("Enter Your string Here : ");
				l2.setText("No of Words : " + count);
			}
		}
	}
	public static void main(String ar[])
	{
		WordCounter wc=new WordCounter();
	}
}
