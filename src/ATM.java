
public class ATM {
	
	private Keypad keypad;
	private DepositSlot depositSlot;
	private BankDatabase bankDatabase;
	private CashDispenser cashDispenser;
	private Screen screen;
	private boolean userAuthenticated; 
	private int currentAccountNumber;
	
	
	private static final int Banlance_Inquiry = 1;
	private static final int Withdrawal = 2;
	private static final int Deposit = 3;
	private static final int Exit = 4;
	
	public ATM() {
		screen = new Screen();
		keypad = new Keypad();
		bankDatabase = new BankDatabase();
		cashDispenser = new CashDispenser();
		userAuthenticated = false;
		currentAccountNumber = 0;
		
	}
	
	
	public void start() {  //start ATM 
		while(true) {
			
			while(!userAuthenticated) {
				
				screen.displayMessage("\nWelcome!");
				authenticateUser();
		           }
			
			performTransactions();
			userAuthenticated = false;
			currentAccountNumber = 0;
			screen.displayMessage("\nThank you, Good bye ");
		} //end while
	} //end start

	private void performTransactions() {
		
		screen.displayMessage("1 - Balance Inquiry ");
		screen.displayMessage("2 - Cash Withdrawal ");
		screen.displayMessage("3 - Cash Deposit ");
		screen.displayMessage("4 - Exit ");
	}

	private void authenticateUser() {
		screen.displayMessage("\nEnter your account number");
		int accountNumber = keypad.getInput(); 
		screen.displayMessage("Enter your pin");
		int pin = keypad.getInput();
		
		userAuthenticated = bankDatabase.authenticateUser(accountNumber,pin);
		
		if(userAuthenticated) {
			currentAccountNumber = accountNumber;
		}
		else {
			screen.displayMessage("Incorrect Account number or pin ");
		}
	}

}
