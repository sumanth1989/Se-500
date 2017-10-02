import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main {
	
	private static int progflag = 0;
	static String fileName = "auth.txt";
	static String usr = null;
	static String pass = null;

	public static void main(String[] args) throws IOException {
		JFrame obj = new JFrame();
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName, true));
		BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
		String init = JOptionPane.showInputDialog(null, "Are you a new user? \n 1 - Yes \n 2 - No");
		int check = Integer.parseInt(init);
		if ( check == 1) {
			String credentials = JOptionPane.showInputDialog(null, "Please enter your user name \n");
			String password = JOptionPane.showInputDialog(null, "Please enter your password \n");
			bufferedWriter.newLine();
			bufferedWriter.write(credentials);
			bufferedWriter.newLine();
			bufferedWriter.write(password);
			bufferedWriter.flush();
			bufferedWriter.close();
		}
	
		String credentials = JOptionPane.showInputDialog(null, "Please enter your user name \n");
		String password = JOptionPane.showInputDialog(null, "Please enter your password \n");
		while((usr = bufferedReader.readLine()) != null) {
            if (usr.equals(credentials)) {
            	pass = bufferedReader.readLine();
            	if (pass.equals(password)) {
            		progflag = 1;
            	}
            }
        }
		
		bufferedReader.close();
        if (progflag == 1) {
		Gameplay gameplay = new Gameplay();
		obj.setBounds(10, 10, 1205, 700);
		obj.setBackground(Color.DARK_GRAY);
		obj.setResizable(false);
		obj.add(gameplay);
		obj.setVisible(true);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        }
        else {
        	JOptionPane.showMessageDialog(null, "Wrong credentials entered Bye Bye!");
        }
		
	}

}
