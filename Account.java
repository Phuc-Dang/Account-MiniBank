import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import static java.lang.System.*;
import static java.lang.Integer.*;

//don't put panel, label, frame, bla bla into actionperformed,,,,, 

public class Account{
	JFrame frame = new JFrame("Mini Bank");
	JButton button = new JButton("Log out");
	JButton buttonN = new JButton("Press to see");
	JButton buttonC = new JButton("Continue");
	class ButtonLogOut implements ActionListener{
		public void actionPerformed(ActionEvent event){
			//after press log out
			mark = 2;
		}
	}
	public static void main(String[] args){
		Account a = new Account();
		while(true) {
			a.customerOption();
		}
	}

	public static final int NUMBER_ACCOUNT_MAX = 10;
	int numberOfAccount;//this variable store max index of customer's account system. Example: create 3 account => numberOfAccout is equal to 2
	int currentAccountIndex;
	int[] numberOfTransaction = new int[NUMBER_ACCOUNT_MAX];
	int[] numberOfReceiveMoney = new int[NUMBER_ACCOUNT_MAX];
	String[] idAccount = new String[NUMBER_ACCOUNT_MAX];
	String[] passwordAccount = new String[NUMBER_ACCOUNT_MAX];
	String[][] bankStatement = new String[NUMBER_ACCOUNT_MAX][NUMBER_ACCOUNT_MAX];
	String[][] receiveMoneyNotification = new String[NUMBER_ACCOUNT_MAX][NUMBER_ACCOUNT_MAX];
	int[] balance = new int[20];
	
	Account(){
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 300);
		frame.setVisible(true);
		numberOfAccount = -1;
		currentAccountIndex = 0;
		createAccount(new JPanel());
	}
	
	JTextField text1 = new JTextField("Enter money", 20);
	String choice1;
	int mark2;
	int money1;
	private void enterMoney(int m, int j, JPanel panel9) {
		mark = m;
		JLabel label1 = new JLabel("Enter money you want to transfer: ");
		panel9.add(label1);
		panel9.add(text1);
		frame.setVisible(true);
		mark2 = 1;
		while (mark2 == 1) {
			try{
				Thread.sleep(50);
				text1.addActionListener(new EnterMoney());
			}
			catch (Exception ex){
				ex.printStackTrace();
			}
		}
		mark2 = 1;
		while(mark2 == 1){
			if (money1 > balance[currentAccountIndex]) {
				label1 = new JLabel("----Debit amount exceeded account balance----");
				JLabel label2 = new JLabel("Enter again money you want to transfer: ");
				panel9.add(label1);
				panel9.add(label2);
				frame.setVisible(true);
				mark2 = 1;
				while (mark2 == 1) {
					try{
						Thread.sleep(50);
						text1.addActionListener(new EnterMoney());
					}
					catch (Exception ex){
						ex.printStackTrace();
					}
				}
				mark2 = 1;
			}
			else if (money1 <= 0) {
				label1 = new JLabel("----Amount money is not valid----");	
				JLabel label2 = new JLabel("Please enter again money you want to transfer: ");
				panel9.add(label1);
				panel9.add(label2);
				frame.setVisible(true);
				mark2 = 1;
				while (mark2 == 1) {
					try{
						Thread.sleep(50);
						text1.addActionListener(new EnterMoney());	
					}
					catch (Exception ex){
						ex.printStackTrace();
					}
				}
				mark2 = 1;
			}
			else {
				label1 = new JLabel("----Are you sure to transfer " + money1 + " to id: " + idAccount[j]);
				JLabel label2 = new JLabel("----Enter Y to do transaction or N to cancel transaction. Enter at here: ");
				panel9.add(label1);
				panel9.add(label2);
				panel9.add(text2);
				frame.setVisible(true);
				mark2 = 1;
				while (mark2 == 1) {
					try{
						Thread.sleep(50);
						text2.addActionListener(new Choice1());		
					}
					catch (Exception ex){
						ex.printStackTrace();
					}
				}
			}
		}
		if(choice1.equals("Y") || choice1.equals("y")) {
			balance[currentAccountIndex] -= money1;
			JLabel label2 = new JLabel("You have just transfer " + money1 + " to id account: " + idAccount[j] + ".");
			panel9.add(label2);
			displayCurrentBalance(panel9);
			frame.setVisible(true);
			balance[j] += money1;
	
			receiveMoneyNotification[j][numberOfReceiveMoney[j]] = "----Account " + idAccount[currentAccountIndex] + " had transfered " + money1 + " to your account----\n";
			numberOfReceiveMoney[j]++;
			bankStatement[currentAccountIndex][numberOfTransaction[currentAccountIndex]] = "Transaction " + numberOfTransaction[currentAccountIndex] + 1 + ": transfer " + money1 + " to account : " + idAccount[j];
			bankStatement[j][numberOfTransaction[j]] = "Transaction " + numberOfTransaction[j] + 1 + ": receive " + money1 + " from account : " + idAccount[currentAccountIndex];

			numberOfTransaction[currentAccountIndex]++;
			numberOfTransaction[j]++;
		}
		else if (choice1.equals("N") || choice1.equals("n")) {
			JLabel label2 = new JLabel("----Transaction is canceled----");
			panel9.add(label2);
			displayCurrentBalance(panel9);
			frame.setVisible(true);
		}
		else {
			JLabel label2 = new JLabel("----Your choice is invalid----");
			JLabel label3 = new JLabel("----End of transaction----");
			panel9.add(label2);
			panel9.add(label3);
			displayCurrentBalance(panel9);
			frame.setVisible(true);
		}
	}

	JTextField text2 = new JTextField("Enter choice", 20);
	class EnterMoney implements ActionListener{
		public void actionPerformed(ActionEvent event){
			//after enter money
			money1 = parseInt((String) (text1.getText()));
			mark2 = 2;
		}
	}

	class Choice1 implements ActionListener{
		public void actionPerformed(ActionEvent event){
			//after enter choice
			choice1 = text2.getText();
			mark2 = 2;
		}
	}

	void setCustomerAccount(String aId, String aPassword) {
		JPanel panel1 = new JPanel();
		while (true) {
			if (aId.length() >= 4) {
				idAccount[numberOfAccount] = "" + aId;
				break;
			}
			else {
				JLabel label1 = new JLabel("Id is not valid. Please enter again.\n");
				panel1.add(label1);
				frame.setContentPane(panel1);
				frame.setVisible(true);
				aId = createId();
			}
		}
	
		while (true) {
			if (aPassword.length() >= 4) {
				passwordAccount[numberOfAccount] = "" + aPassword;
				break;
			}
			else {
				JLabel label1 = new JLabel("Password is not valid. Please enter again.\n");
				panel1.add(label1);
				frame.setContentPane(panel1);
				frame.setVisible(true);
				aPassword = createPassword();
			}
		}
	}

	JTextField text3 = new JTextField("Enter id", 20);	
	String id1;
	int mark;
	String createId() {
		JPanel panel2 = new JPanel();
		mark = 1;
		text3.requestFocus();
		JLabel label1 = new JLabel("Enter your new ID with at least 4 characters: ");
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
		panel2.add(label1);
		panel2.add(text3);
		frame.setContentPane(new JPanel());
		frame.getContentPane().add(BorderLayout.NORTH, panel2);
		frame.setVisible(true);
		while (mark == 1) {	
			try{
				Thread.sleep(50);
				text3.addActionListener(new Id1());
			}
			catch(Exception ex){
				ex.printStackTrace();
			}	
		}
		for (int i = 0; i <= numberOfAccount; i++) {
			if (id1.equals(idAccount[i])) {
				i = -1;
				JLabel label2 = new JLabel("Id " + id1 + " is already exists.");
				panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
				panel2.add(label2);
				frame.setVisible(true);
				mark = 1;
				while (mark == 1) {	
					try{
						Thread.sleep(50);
						text3.addActionListener(new Id1());
					}
					catch(Exception ex){
						ex.printStackTrace();
					}	
				}
			}
		}
		mark = 1;
		frame.remove(panel2);
		return id1;
	}
	
	class Id1 implements ActionListener{
		//after enter id
		public void actionPerformed(ActionEvent event){
			id1 = text3.getText();
			mark = 2;
		}
	}
	
	String getId() {
		return idAccount[currentAccountIndex];
	}
	
	JTextField text4 = new JTextField("Enter password", 20);
	String password1 = "before";
	String createPassword() {
		JPanel panel3 = new JPanel();
		mark = 1;
		text4.requestFocus();
		JLabel label1 = new JLabel("Enter your new pasword with at least 4 characters: ");
		panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));
		panel3.add(label1);
		panel3.add(text4);
		frame.setContentPane(new JPanel());
		frame.getContentPane().add(BorderLayout.NORTH, panel3);
		frame.setVisible(true);
		while(mark == 1){
			try{
				Thread.sleep(50);
				text4.addActionListener(new Password1());
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
		}
		mark = 1;
		frame.remove(panel3);
		return password1;
	}
	class Password1 implements ActionListener{
		public void actionPerformed(ActionEvent event){
			//after enter password
			password1 = text4.getText();
			mark = 2;
		}
	}

	String getPassword(){
		return passwordAccount[currentAccountIndex];
	}

	void displayIdAndPassword(JPanel panel4){
		JLabel label1 = new JLabel("Your current account information is: ");		
		JLabel label2 = new JLabel("ID: " + getId());
		JLabel label3 = new JLabel("Password: " + getPassword());
		panel4.add(label1);
		panel4.add(label2);
		panel4.add(label3);
		//frame.setVisible(true);
	}
	
	void displayMessageToNewClient(JPanel panel4) {
		JLabel label1 = new JLabel("Welcome to our service where you can save very much money.");
		JLabel label2 = new JLabel("But first, depositing money into your bank account.");// to keep your balance account not blank.");
		JLabel label3 = new JLabel("Plese type number of money you want to deposit at here: ");
		panel4.add(label1);
		panel4.add(label2);
		panel4.add(label3);
		//frame.setVisible(true);
	}
	
	void setBalance(int aBalance, JPanel panel4) {	
		if (aBalance >= 0) {
			balance[numberOfAccount] = aBalance;
		}
		else {
			balance[numberOfAccount] = 0;
			JLabel label1 = new JLabel("Balance is not valid, setting balance to 0.");	
			panel4.add(label1);
			frame.setVisible(true);
		}
	}
	
	int getBalance() {
		return balance[currentAccountIndex];
	}

	JTextField text5 = new JTextField("Enter initial balance", 20);
	int initialBalance;
	int setInitialBalance(JPanel panel4) {
		mark = 1;
		text5.requestFocus();
		panel4.add(text5);
		frame.setVisible(true);
		while(mark == 1){
			try{
				Thread.sleep(50);
				text5.addActionListener(new InitialBalance());
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
		}
		mark = 1;
		text5.setText("Enter initial balance");
		return initialBalance;
	}
	class InitialBalance implements ActionListener{
		public void actionPerformed(ActionEvent event){
			//after enter initial balance
			initialBalance = parseInt(text5.getText());
			mark = 2;
		}
	}
	
	String choice2;
	void customerOption() {
		JPanel panel4 = new JPanel();
		JPanel panel5 = new JPanel();
		mark = 1;
		JLabel label1 = new JLabel("Choose option you want to conduct with your account:");
		JButton button1 = new JButton("Opt 1. Withdraw money.");
		JButton button2 = new JButton("Opt 2. Top up money.");
		JButton button3 = new JButton("Opt 3. Check balance account.");
		JButton button4 = new JButton("Opt 4. Transfer money to another account.");
		JButton button5 = new JButton("Opt 5. Watch bank statement.");
		JButton button6 = new JButton("Opt 6. Create New Account.");
		panel4.setLayout(new BoxLayout(panel4, BoxLayout.Y_AXIS));
		frame.setContentPane(new JPanel());
		frame.getContentPane().add(BorderLayout.NORTH, panel4);
		panel4.add(label1);
		panel4.add(button1);
		panel4.add(button2);
		panel4.add(button3);
		panel4.add(button4);
		panel4.add(button5);
		panel4.add(button6);
		frame.setVisible(true);
		while(mark == 1){
			try{
				Thread.sleep(50);
				button1.addActionListener(new Choice21());
				button2.addActionListener(new Choice22());
				button3.addActionListener(new Choice23());
				button4.addActionListener(new Choice24());
				button5.addActionListener(new Choice25());
				button6.addActionListener(new Choice26());
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
		}
		if(choice2 == "1") {
			logInAccount(panel5);
			withdrawMoney();
		}
		else if (choice2 == "2"){
			logInAccount(panel5);
			topUpMoney();
		}
		else if (choice2 == "3"){
			logInAccount(panel5);
			displayCurrentBalance(panel5);
		}	
		else if (choice2 == "4"){
			logInAccount(panel5);
			transferMoney();
		}
		else if (choice2 == "5"){
			logInAccount(panel5);
			displayBankStatement();
		}
		else if (choice2 == "6"){
			createAccount(panel5);
		}
	}
	
	class Choice21 implements ActionListener{
		public void actionPerformed(ActionEvent event){
			//after enter choice2
			choice2 = "1";
			mark = 2;
		}
	}
	class Choice22 implements ActionListener{
		public void actionPerformed(ActionEvent event){
			//after enter choice2
			choice2 = "2";
			mark = 2;
		}
	}
	class Choice23 implements ActionListener{
		public void actionPerformed(ActionEvent event){
			//after enter choice2
			choice2 = "3";
			mark = 2;
		}
	}
	class Choice24 implements ActionListener{
		public void actionPerformed(ActionEvent event){
			//after enter choice2
			choice2 = "4";
			mark = 2;
		}
	}
	class Choice25 implements ActionListener{
		public void actionPerformed(ActionEvent event){
			//after enter choice2
			choice2 = "5";
			mark = 2;
		}
	}
	class Choice26 implements ActionListener{
		public void actionPerformed(ActionEvent event){
			//after enter choice2
			choice2 = "6";
			mark = 2;
		}
	}

	JTextField text6 = new JTextField("Enter id", 20);
	JTextField text7 = new JTextField("Enter password", 20);
	String id2;
	String password2;
	void logInAccount(JPanel panel5) {
		mark = 1;
		text6.requestFocus();
		text7.requestFocus();
		JLabel label1 = new JLabel("----LOG IN----");
		JLabel label2 = new JLabel("Enter id: ");
		JLabel label3 = new JLabel("Enter password: ");
		JButton button1 = new JButton("LOG IN");
		frame.setContentPane(new JPanel());
		frame.getContentPane().add(BorderLayout.NORTH, panel5);
		panel5.setLayout(new BoxLayout(panel5, BoxLayout.Y_AXIS));
		panel5.add(label1);
		panel5.add(label2);
		panel5.add(text6);
		panel5.add(label3);
		panel5.add(text7);
		panel5.add(button1);
		frame.setVisible(true);
		while (mark == 1) {
			while(mark == 1){
				try{
					Thread.sleep(50);
					button1.addActionListener(new ButtonLogIn());
				}
				catch(Exception ex){
					ex.printStackTrace();
				}
			}
			mark = 1;
			for (int i = 0; i <= numberOfAccount; i++) {
				if (id2.equals(idAccount[i]) && password2.equals(passwordAccount[i])) {				
					currentAccountIndex = i;
					mark = 2;
					break;
				}
			}
			if (mark == 1) {
				label1 = new JLabel("----Id or password is incorrect----Enter again----");		
				panel5.add(label1);
				frame.setVisible(true);
			}
		}
		mark = 1;
		if(numberOfReceiveMoney[currentAccountIndex] > 0){
			panel5 = new JPanel();
			panel5.setLayout(new BoxLayout(panel5, BoxLayout.Y_AXIS));
			frame.setContentPane(new JPanel());
			frame.getContentPane().add(BorderLayout.NORTH, panel5);
			
			label1 = new JLabel("You have new notification.");
			panel5.add(label1);
			panel5.add(buttonN);
			panel5.setLayout(new BoxLayout(panel5, BoxLayout.X_AXIS));
			panel5.add(buttonS);
			panel5.setLayout(new BoxLayout(panel5, BoxLayout.Y_AXIS));
			frame.setVisible(true);
			mark = 1;
			while(mark == 1){
				try{
					Thread.sleep(50);
					buttonN.addActionListener(new ButtonNotify());
					buttonS.addActionListener(new ButtonSeeLater());
				}
				catch (Exception ex){
					ex.printStackTrace();
				}
			}
			if (mark == 2 && receiveMoneyNotification[currentAccountIndex][0] != "0") {
				for (int i = 0; i < numberOfReceiveMoney[currentAccountIndex]; i++) {
					label1 = new JLabel(receiveMoneyNotification[currentAccountIndex][i]);
					panel5.add(label1);
				}
				numberOfReceiveMoney[currentAccountIndex] = 0;
				receiveMoneyNotification[currentAccountIndex][0] = "0";
				panel5.add(buttonC);
				frame.setVisible(true);
				mark = 1;
				while(mark == 1){
					try{
						Thread.sleep(50);
						buttonC.addActionListener(new ButtonContinue());
					}
					catch (Exception ex){
						ex.printStackTrace();
					}
				}
			}
		}
	}
	JButton buttonS = new JButton("See later");

	class ButtonLogIn implements ActionListener{
		public void actionPerformed(ActionEvent event){
			//after press Log In
			id2 = "" + text6.getText();
			password2 = "" + text7.getText();
			mark = 2;
		}
	} 
	
	class ButtonNotify implements ActionListener{
		public void actionPerformed(ActionEvent event){
			//after press press to see
			mark = 2;
		}
	} 	
	
	class ButtonSeeLater implements ActionListener{
		public void actionPerformed(ActionEvent event){
			//after press see later
			mark = 3;
		}
	} 
	
	class ButtonContinue implements ActionListener{
		public void actionPerformed(ActionEvent event){
			//after press see later
			mark = 2;
		}
	} 
	
	JTextField text8 = new JTextField("Enter money", 20);
	int aMoneyWithdraw;
	void withdrawMoney() {
		JPanel panel6 = new JPanel();
		mark = 1;
		JLabel label1 = new JLabel("You want to withdraw money. Enter amount you want to withdraw at here: ");
		panel6.add(label1);
		panel6.add(text8);
		panel6.setLayout(new BoxLayout(panel6, BoxLayout.Y_AXIS));
		frame.setContentPane(new JPanel());
		frame.getContentPane().add(BorderLayout.NORTH, panel6);
		frame.setVisible(true);
		while(mark == 1){
			try{
				Thread.sleep(50);
				text8.addActionListener(new MoneyWithdraw());
			}
			catch (Exception ex){
				ex.printStackTrace();
			}
		}
		mark = 1;
		if (aMoneyWithdraw > balance[currentAccountIndex]) {
			JLabel label2 = new JLabel("----Debit amount exceeded account balance----");
			panel6.add(label2);
			displayCurrentBalance(panel6);
			frame.setVisible(true);
		}
		else {
			balance[currentAccountIndex] -= aMoneyWithdraw;
			JLabel label2 = new JLabel("You have just withdrawn " + aMoneyWithdraw + ".");
			panel6.add(label2);
			displayCurrentBalance(panel6);
			frame.setVisible(true);
			bankStatement[currentAccountIndex][ numberOfTransaction[currentAccountIndex] ] = "Transaction " + (numberOfTransaction[currentAccountIndex] + 1) + ": withdraw " + aMoneyWithdraw + " from account balance";
			numberOfTransaction[currentAccountIndex]++;
		}
	}
	class MoneyWithdraw implements ActionListener{
		public void actionPerformed(ActionEvent event){
			//after enter money
			aMoneyWithdraw = parseInt(text8.getText());
			mark = 2;
		}
	}

	JTextField text9 = new JTextField("Enter money", 20);
	int aMoneyTopUp;
	void topUpMoney() {
		JPanel panel7 = new JPanel();
		mark = 1;
		JLabel label1 = new JLabel("You want to deposit money into account.");
		JLabel label2 = new JLabel("Enter amount you want to deposit into your account at here: ");
		panel7.add(label1);
		panel7.add(label2);
		panel7.add(text9);
		panel7.setLayout(new BoxLayout(panel7, BoxLayout.Y_AXIS));
		frame.setContentPane(new JPanel());
		frame.getContentPane().add(BorderLayout.NORTH, panel7);
		frame.setVisible(true);
		while(mark == 1){
			try{
				Thread.sleep(50);
				text9.addActionListener(new MoneyTopUp());
			}
			catch (Exception ex){
				ex.printStackTrace();
			}
		}
		mark = 1;
		balance[currentAccountIndex] += aMoneyTopUp;

		JLabel label3 = new JLabel("You have just deposit " + aMoneyTopUp + " into your account");
		panel7.add(label3);
		displayCurrentBalance(panel7);
		frame.setVisible(true);
		bankStatement[currentAccountIndex][numberOfTransaction[currentAccountIndex]] = "Transaction " + (numberOfTransaction[currentAccountIndex] + 1) + ": top up " + aMoneyTopUp + " into account balance";
		numberOfTransaction[currentAccountIndex]++;
	}
	class MoneyTopUp implements ActionListener{
		public void actionPerformed(ActionEvent event){
			//after enter id
			aMoneyTopUp = parseInt(text9.getText());
			mark = 2;
		}
	}
	
	void displayCurrentBalance(JPanel panel5) {
		JLabel label1 = new JLabel("Your current balance is: " + getBalance() + ".");
		panel5.add(label1);
		panel5.add(button);
		frame.setVisible(true);
		mark = 1;
		while(mark == 1){
			try{
				Thread.sleep(50);
				button.addActionListener(new ButtonLogOut());
			}
			catch (Exception ex){
				ex.printStackTrace();
			}
		}
	}

	JTextField text10 = new JTextField("Enter account");
	String idTransfer;
	void transferMoney() {
		JPanel panel9 = new JPanel();
		JPanel panel10 = new JPanel();
		mark = 1;
		JLabel label1 = new JLabel("Enter account you want to transfer money: ");
		panel9.setLayout(new BoxLayout(panel9, BoxLayout.Y_AXIS));
		panel9.add(label1);
		panel9.add(text10);
		frame.setContentPane(new JPanel());
		frame.getContentPane().add(BorderLayout.NORTH, panel9);

		JLabel label2 = new JLabel("If you want to stop transaction. Just press Cancel");
		JButton button1 = new JButton("Cancel");
		panel9.add(label2);
		panel9.add(button1);
		frame.setVisible(true);
		while (mark == 1) {
			try{
				Thread.sleep(50);
				button1.addActionListener(new ButtonCancel());
				text10.addActionListener(new IdTransfer());
			}
			catch (Exception ex){
				ex.printStackTrace();
			}
		}
		panel9.setLayout(new BoxLayout(panel9, BoxLayout.Y_AXIS));
		while(mark == 2){
			int j;//j is represent for index of account want to transfer money
			for (j = 0; j <= numberOfAccount; j++) {
				if (idTransfer.equals(idAccount[currentAccountIndex])) {
					label1 = new JLabel("----Cannot transfer to your account yourself----");
					label2 = new JLabel("Please enter again account you want to transfer");
					panel9.add(label1);
					panel9.add(label2);
					frame.setVisible(true);
					mark = 1;
					while (mark == 1) {
						try{
							Thread.sleep(50);
							button1.addActionListener(new ButtonCancel());	
							text10.addActionListener(new IdTransfer());
						}
						catch (Exception ex){
							ex.printStackTrace();
						}
					}
					break;
				}
				else if(idTransfer.equals(idAccount[j])) {
					enterMoney(mark, j, panel9);
					mark = 1;
					break;
				}
				else if(j == numberOfAccount) {
					label1 = new JLabel("----Cannot find account you want to transfer----");
					panel9.add(label1);
					label2 = new JLabel("If you want to stop transaction. Just press Cancel");
					panel9.add(label2);
					button1.addActionListener(new ButtonCancel());
					JLabel label3 = new JLabel("Please enter again account you want to transfer");
					panel9.add(label3);
					frame.setVisible(true);
					mark = 1;
					while (mark == 1) {
						try{
							Thread.sleep(50);
							button1.addActionListener(new ButtonCancel());	
							text10.addActionListener(new IdTransfer());
						}
						catch (Exception ex){
							ex.printStackTrace();
						}
					}
				}
			}
		}
		if (mark == 3){
			label1 = new JLabel("\n----Stop doing transaction----");	
			panel10.add(label1);
			frame.setContentPane(panel10);
			frame.setVisible(true);
		}
	}

	class ButtonCancel implements ActionListener{
		public void actionPerformed(ActionEvent event){
			//after press cancel
			mark = 3;
		}
	}
	class IdTransfer implements ActionListener{
		public void actionPerformed(ActionEvent event){
			//after enter id
			idTransfer = text10.getText();
			mark = 2;
		}
	}
	
	void displayBankStatement() {
		JPanel panel11 = new JPanel();
		JLabel label1 = new JLabel("Bank statement: account " + idAccount[currentAccountIndex]);
		panel11.setLayout(new BoxLayout(panel11, BoxLayout.Y_AXIS));
		panel11.add(label1);
		frame.setContentPane(new Panel());
		frame.getContentPane().add(panel11);
		int i = currentAccountIndex;
		JLabel[] listLabel = new JLabel[numberOfTransaction[currentAccountIndex]];
		for (int j = 0; j < numberOfTransaction[currentAccountIndex]; j++) {
			listLabel[j] = new JLabel(bankStatement[i][j]);	
			panel11.add(listLabel[j]);
		}
		JLabel label2 = new JLabel("----END----");
		panel11.add(label2);
		displayCurrentBalance(panel11);
		frame.setVisible(true);
	}

	void createAccount(JPanel panel4) {
		numberOfAccount++;
		String id = createId();
		String password = createPassword();
		setCustomerAccount(id, password);
		JLabel label1 = new JLabel("----You have just created your account----");
		panel4.setLayout(new BoxLayout(panel4, BoxLayout.Y_AXIS));
		panel4.add(label1);
		frame.getContentPane().add(BorderLayout.CENTER, panel4);
		frame.setVisible(true);
		currentAccountIndex = numberOfAccount;
		receiveMoneyNotification[numberOfAccount][ numberOfReceiveMoney[numberOfAccount] ] = "0";
		displayIdAndPassword(panel4);
		displayMessageToNewClient(panel4);
		setBalance( setInitialBalance(panel4), panel4 );
		label1 = new JLabel("NOW you can deposit or withdraw money with your bank account.");
		panel4.add(label1);
		frame.setVisible(true);
	}
}
	