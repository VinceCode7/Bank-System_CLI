
//import the required packages
import java.util.Scanner; 
import java.util.ArrayList;
import java.util.HashMap;
import java.time.LocalDateTime;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class BankSystem {
	//variable to used for transaction history
 String operationDone;
 String ksh;
 Double amount;
 String from;
 String bank;
 Integer bankcode;
 String on;
 String datetime;
 
 //method constructor
 	public BankSystem(String a,String b,Double c,String d,String e,Integer f,String g,String h) {
 		operationDone=a;
 		ksh=b;
 		amount=c;
 		from=d;
 		bank=e;
 		bankcode=f;
 		on=g;
 		datetime=h;
 		
 	}
		//static variables
 	static Double bankAmount=2000.0;//Appears after you deposit money through bank or agents
 	static HashMap<String,String> email= new HashMap<String,String>();//Representing a database
 	static ArrayList<String> firstDisplay= new ArrayList<String>(); //Representing a database
 	static HashMap<String,String> accounts= new HashMap<String,String>();//Representing a database
 	static HashMap<String,Integer> bankATM=new HashMap<String,Integer>();//Representing a database
 	static ArrayList<BankSystem> history=new ArrayList<BankSystem>();//Representing a database
 	static HashMap<Long,String> phoneNumbers=new HashMap<Long,String>();
 	static HashMap<Integer,String> tillNumbers= new HashMap<Integer,String>();
 	static HashMap<Integer,String> accountNumbers=new HashMap<Integer,String>();
 	static ArrayList<String> menu= new ArrayList<String>();
 	
 	static	boolean upper;
	static 	boolean lower;
	static	boolean integer;
	static	boolean special;
	static	boolean length;
	static	boolean upper2;
	static 	boolean lower2;
	static	boolean integer2;
	static	boolean special2;
	static	boolean length2;
	
 	static Double withdrawamount;
	static	Integer userBankCode;
	static Integer pin;
	static String userEmail; //for creating new account
	static String emailUser; //used in the forgotPass method
 	
 	//start method
 	static void  start() {
 		int userInputNumber=0;
 		
 		System.out.println(accounts);
 		firstDisplay.add("1. Login");
 		firstDisplay.add("2. Signup");
 		firstDisplay.add("3. Forgot Password");
 		firstDisplay.add("4. Exit");
 		
 		for(String i:firstDisplay) {
 			System.out.println(i);
 		if(i.equals("4. Exit")) {
 			break;
 		}
 		}
 		
 		System.out.println("Enter the number of the operation you want to perform");
 		
 		Scanner scan=new Scanner(System.in);
 		String userInput= scan.nextLine();
 		if (inputChecker(userInput)) {
 		userInputNumber=Integer.parseInt(userInput);	
 		}else {
 			System.out.println("INVALID INPUT");
 			start();
 		}
 		
 		switch(userInputNumber) {
 		case 1:
 			login();
 			break;
 		case 2:
 			signup();
 			break;
 		case 3:
 			forgotPassword();
 			break;
 		case 4:
 			exit();
 			break;
 		default:
 			System.out.println("wrong input kindly choose again");
 			start();
 			
 		}
 		scan.close();
 	}
 	
 	//login method
 	static void login() {
 		System.out.println(accounts);
 		System.out.println("Enter username or 00=BACK");
 		Scanner scan = new Scanner(System.in);
 		String userinput1 = scan.nextLine();
 		
 		if(inputChecker(userinput1)&&Integer.parseInt(userinput1)==00) {
 		start();
 		}else {
 			System.out.println("Enter password");
 		}
 		String userinput2=scan.nextLine();
 		if(inputChecker(userinput2)&&Integer.parseInt(userinput2)==00) {
 			start();
 		}else {
 		check(userinput1,userinput2);
 		}
 		scan.close();
 	}
 	
 	//The check method in the login method
 	static void check(String n,String m) {
 		for(String i:accounts.keySet()) {
 			if(i.equals(n)) {
 				if(m.equals(accounts.get(i))) {
 					System.out.println("LOGIN SUCCESFULL");
 					menuDisplay();
 				}
 			}	
 		}
 				System.out.println("INVALID ACCOUT \n Either wrong pin or wrong username ");
 				login();
 	}
 	
 	// sign up method 
 	static void signup() {
 		System.out.println("Enter your email............enter 00 to go back");
 		Scanner scan = new Scanner(System.in);
 		userEmail =scan.nextLine();
 		if(inputChecker(userEmail)&&Integer.parseInt(userEmail)==00) {
 			start();	
 		}
 		userCreator();
 		scan.close();
 	}
 	
 	//The userName checker in sign up method
 	public static void userCreator() {
 		int pinTestNumber=0;
 		
 		Scanner scan = new Scanner(System.in);
 	
 		System.out.println("Enter our desired username");
 		String userName =scan.nextLine();
 		for(String i:accounts.keySet()) {
 			if(i.equals(userName)) {
 				System.out.println("That username is already owned \nplease enter another username ");
 				userCreator();
 			}else if(inputChecker(userName)) {
 				start();
 			}else {
 				break;
 			}
 			
 		}
 		
 		System.out.println("Enter your password");
 		String userPass1 =scan.nextLine();
 		strengthPass(userPass1);
 		System.out.println("confirm your password");
 		String userPass2=scan.nextLine();
 		if(userPass1.equals(userPass2)) {
 			accounts.put(userName,userPass1);
 			System.out.println("YOU HAVE SUCCESSFULLY CREATED YOUR PASSWORD");
 			System.out.println(accounts);
 		}else {
 			System.out.println("Password dont match");
 			userCreator();
 		}
 		
 		System.out.println("ENTER THE PIN YOU WILL BE USING FOR TRANSATION(FOUR DIGIT NUMBER ..ENTER 00 TO EXIT)");
		String	pinTest=scan.nextLine();
		if(inputChecker(pinTest)) {
			pinTestNumber=Integer.parseInt(pinTest);
		}else {
			System.out.println("!!!USE NUMBER ONLY!!!");
			userCreator();
		}
			
			if(pinTestNumber==00) {
				start();	
			}else if(Integer.toString(pinTestNumber).length()<4) {
				System.out.println("THE PIN IS LESS THAN 4 CHARACTER");
				userCreator();
			}else if(inputChecker(Integer.toString(pinTestNumber))) {
				pin=pinTestNumber;
				System.out.println("Account created succefully");
				accounts.put(userName,userPass1);
				email.put(userEmail,userName);
				start();
			}else {
				System.out.println("WRONG INPUT");
				userCreator();
			}
			System.out.println("test1");
 	}
 	
 	// The method to sense user input
 	private static boolean inputChecker(String n) {
 		try {
 			Integer.parseInt(n);
 			return true;
 		} catch (NumberFormatException e) {
 			return false;
 		}
 	}
 	
 	//The  password strength checker method for user creator
 	public static void strengthPass(String p) {
 		upper=false;
 		lower=false;
 		special=false;
 		integer=false;
 		
 		int strength = 0 ;
 		String problem ="";
 		if(p.length()>=7) {
 		strength+=20;
 		}else {
 			problem+="increase the length of your password to 7 characters";
 		}
 		for(int v=0;v<p.length();v++) {
 			char characters = p.charAt(v);
 			
 			if(Character.isUpperCase(characters)) {
 				upper=true;
 				
 			}else if(Character.isLowerCase(characters)) {
 				lower=true;	
 			}else if("!@#$%^&*()".contains(Character.toString(characters))) {
 				special=true;
 			}else if("1234567890".contains(Character.toString(characters))) {
 				integer=true;
 			}else {
 				System.out.println("test1");
 			}
 		}
 		System.out.println(upper+""+lower+""+special+""+integer); 
 		if(upper) {
 			strength+=20;
 		}else {
 			problem+="\nuse some uppercase letters";
 		}
		if(lower) {
			strength+=20;
		}else {
			problem+="\nuse some lower case letters";
		}
		if(special) {
			strength+=20;
		}else {
			problem+="\nuse some special characters";
		}
		if(integer) {
			strength+=20;
		}else {
			problem+="\nuse some integers";
		}
		
		switch(strength) {
		case 100:
			
			break;
			default:
				System.out.println("WEAK PASSWORD \nTRY TO "+problem);
				userCreator();
				break;
		}
 	}
 	
 	//The forgot method
 	public static void forgotPassword() {
 		int v=(int) (Math.random()*10);
 		int x=(int) (Math.random()*27);
 		int y =(int) (Math.random()*10);
 		int z =(int) (Math.random()*27);
 		char letters[]= {'A','G','H','I','B','C','D','E','F','J','K','L','M','N','U','V','W','X','Y','O','P','Q','R','S','T','Z'};
 		char letterss[]= {'g','h','i','j','k','l','a','b','c','d','e','f','m','u','v','w','x','y','n','o','p','q','r','s','t','z'};
 		String otp =""+v+letters[x]+y+letterss[z];
 		Scanner scan = new Scanner(System.in);
 		System.out.println("Enter your email address :");
 		emailUser =scan.nextLine();
 		if(inputChecker(emailUser)) {
 			start();
 		}
 		for(String i:email.keySet()){
 			if(i.equals(emailUser)) {
 				System.out.println("OTP send to your email: "+i); 
 				System.out.println(otp);
 				otpChecker(otp);
 			}
 		}
 		System.out.println("Email not found");
 			forgotPassword();
 	}
 	
 	//strengthPass2 for forgotPassword
 		public static void strengthPass2(String p) {
 		upper2=false;
 		lower2=false;
 		integer=true;
 		
 		
 		int strength = 0 ;
 		String problem ="";
 		if(p.length()>=7) {
 		strength+=20;
 		}else {
 			problem+="increase the length of your password to 7 characters";
 		}
 		for(int v=0;v<p.length();v++) {
 			char characters = p.charAt(v);
 			
 			if(Character.isUpperCase(characters)) {
 				upper2=true;
 				
 			}else if(Character.isLowerCase(characters)) {
 				lower2=true;	
 			}else if("!@#$%^&*()".contains(Character.toString(characters))) {
 				special2=true;
 			}else if("1234567890".contains(Character.toString(characters))) {
 				integer2=true;
 			}else {
 				System.out.println("test1");
 			}
 		}
 
 		if(upper2) {
 			strength+=20;
 		}else {
 			problem+="\nuse some uppercase letters";
 		}
		if(lower2) {
			strength+=20;
		}else {
			problem+="\nuse some lower case letters";
		}
		if(special2) {
			strength+=20;
		}else {
			problem+="\nuse some special characters";
		}
		if(integer2) {
			strength+=20;
		}else {
			problem+="\nuse some integers";
		}
		
		switch(strength) {
		case 100:
			
			break;
			default:
				System.out.println("WEAK PASSWORD \nTRY TO "+problem);
				otpPassSet();
				break;
		}
 	}
 	
 	//otpChecker method in the forgotPassword method
 	public static void otpChecker(String o) {
 		Scanner scan =new Scanner(System.in);
 		System.out.println("ENTER THE OTP");
 		String userOtp=scan.nextLine();
 		if(inputChecker(userOtp)&&Integer.parseInt(userOtp)==00) {
 			forgotPassword();
 			} else if (o.equals(userOtp)) {
 			otpPassSet();
 		}else {
 		System.out.println("WRONG OTP ");
 		otpChecker(o);
 		}
 	}
 	 
 	//otpChecker method in the otpChecker
 	public static void otpPassSet() {
 	 Scanner scan =new Scanner(System.in);
	 System.out.println("ENTER PASSWORD");
	 String pass1=scan.nextLine();
	
	 strengthPass2(pass1);
	 System.out.println("CONFIRM PASSWORD");
	 String pass2=scan.nextLine();
	 if(pass1.equals(pass2)) {
		
		 for(String i:email.keySet()) {
			 if(i.equals(emailUser)) {
				String passChange=email.get(i);
				for(String a:accounts.keySet()) {
					if(a.equals(passChange)) {
						accounts.put(passChange,pass1);
					}
				}
			 }
		 }
		 System.out.println("PASSWORD SET SUCCESSFULLY");
		 start();
	 }else {
		 System.out.println("PASSWORD DON'T MATCH");
		 otpPassSet();
	 }
   }
 	
 	//exit method 
 	public static void exit() {
 		Scanner scan = new Scanner(System.in);
 		System.out.println("YOU HAVE EXITED THE PROGRAM.\nENTER ANY NUMBER TO GET BACK");
 		String userinput=scan.nextLine();
 		if (inputChecker(userinput)) {
 			start();
 		}else {
 		System.out.println("YOU HAVE EXITED \n THANK YOU VERY MUCH ");
 		}
 	}
 
 	//END OF THE LOGIN AND SIGN UP METHODS 
 	//START OF MENU AND OPERATION METHODS IN THE SYSTEM
  
 	//menu Display method
 	public static void menuDisplay() {
 		int menuChooiceNumber=0;
 		
 	menu.add("1. WITHDRAW");
 	menu.add("2. SEND MONEY");
 	menu.add("3. CHECK MY ACCOUNT");
 	menu.add("4. LOANS");
 	menu.add("5. SAVINGS");
 	menu.add("6. LOGOUT");
 	
 	System.out.println("WHAT WOULD YOU LIKE TO DO");
 	for(String operations:menu) {
 		System.out.println(operations);
 		if(operations.equals("6. LOGOUT")) {
 			break;
 		}
 	}
 	
 	System.out.println("ENTER THE NUMBER OF THE OPERATION YOU WISH TO PERFORM");
 	Scanner scan = new Scanner(System.in);
 	String menuChooice = scan.nextLine();
 	
 	if(inputChecker(menuChooice)) {
 		menuChooiceNumber=Integer.parseInt(menuChooice);
 	}else {
 		System.out.println("INVALID INPUT");
 		menuDisplay();
 	}
 	
 	switch(menuChooiceNumber) {
 	case 1:
 		withdraw();
 		break;
 	case 2:
 		sendMoney();
 		break;
 	case 3:
 		myAccount();
 		break;
 	case 4:
 		loans();
 		break;
 	case 5:
 		savings();
 		break;
 	case 6:
 		logout();
 		break;
 	case 00:
 		start();
 		break;
 	default:
 		System.out.println("Invalid chooice");
 		menuDisplay();
 		
 		}
 		
 	}
 	
 	//withdraw method in menu Display method
 	
 	public static void withdraw() {
 		boolean bankFound=false;
 		String bankFound2="";
 		 LocalDateTime dateTime=LocalDateTime.now();
		  String date =dateTime.toString();
 	System.out.println("ENTER THE BANK TO WITHDRAW FROM. /nEXAMPLE THIKABRANCH1..........EXIT=00");
 	Scanner scan = new Scanner(System.in);
 	String bank = scan.nextLine();
 	if(inputChecker(bank)) {
 		menuDisplay();
 	 
 	}
 	
 	for(String banks:bankATM.keySet()) {
 		System.out.println(banks);
 		Pattern userBank=Pattern.compile(bank ,Pattern.CASE_INSENSITIVE);
 		Matcher banksearch =userBank.matcher(banks);
 		boolean found =banksearch.find();
 		if(found) {
 			bankFound=true;
 			bankFound2=banks;
 		}	
 	}
 	
 	System.out.println(bankFound2);
 	System.out.println(bankATM.get(bankFound2));
 		if(bankFound) {
 			System.out.println("ENTER BANK CODE  ....TO EXIT ENTER 00");
 			userBankCode =scan.nextInt();
 			if(userBankCode==00) {
 				menuDisplay();
 			}else {
 				if(Integer.toString(userBankCode).equals(Integer.toString(bankATM.get(bankFound2)))) {
 					System.out.println("ENTER THE AMOUNT TO WITHDRAW");
 					withdrawamount=scan.nextDouble();
 					System.out.println("ENTER YOUR PIN");
 					Integer userPin=scan.nextInt();
 					if(Integer.toString(userPin).equals(Integer.toString(pin))){
 					
 					
 					}else if(Integer.toString(userPin).equals(Integer.toString(pin))!=true) {
 						System.out.println("SORRY WRONG PIN");
 						menuDisplay();
 					}else {
 						System.out.println("WRONG INPUT");
 						menuDisplay();
 					}
 				
 				}else {
 					System.out.println("BANK CODE DOES NOT MATCH WITH THE BANK");
 					System.out.println(userBankCode==bankATM.get(bankFound2));
 					withdraw();
 				}
 			}
 		}else {
 			System.out.println("THE BANK NAME IS NOT FOUND");
 			withdraw();
 	  }
 		
 	System.out.println("Please confirm the withdraw of "+withdrawamount+" "+"from "+bank+"\nEnter\n1.YES\n2. NO" );
 	 Integer confirm = scan.nextInt();
 	 if (confirm==1) {
 		 if(bankAmount>withdrawamount) {
 			 bankAmount-=withdrawamount;
 			 
 		 System.out.println("You have succefull withdrawn "+withdrawamount+" .KSH from "+bank+" on "+date+". Balance "+bankAmount);
 		 history.add(new BankSystem("withdraw "," Ksh",withdrawamount," from ",bank,userBankCode," on ",date));
 		 menuDisplay();
 		 }else {
 		 System.out.println("YOU have insufficient amount to complete the transaction "+"balance "+bankAmount);
 		 menuDisplay();
 		 }
 	 }else if (confirm==2) {
 		 System.out.println("You have cancelled the withdraw process of "+withdrawamount+" from "+bank+" at "+date);
 	 menuDisplay();
 	 } else {
 		 System.out.println("INVALID CHOOICE");
 		 menuDisplay();
 	 }
   }
 	
 	//send money method in menu Display method
 	public static void sendMoney() {
 	ArrayList<String>sendMoneyDisplay= new ArrayList<String>();
 	sendMoneyDisplay.add("1. Phone Number");
 	sendMoneyDisplay.add("2. Pay Bill");
 	sendMoneyDisplay.add("3. Buy Goods and Services");
 	 
 	for(String i:sendMoneyDisplay) {
 		System.out.println(i);
 		if(i.equals("3. Buy Goods and Services")) {
 			break;
 		}
 	}
 	System.out.println("ENTER THE NUMBER OF THE OPERATION TO PERFORM ....Enter 00 to go back");
 	Scanner scan = new Scanner(System.in);
 	int toWho =scan.nextInt();
 		switch(toWho) {
 		case 1:
 			phoneNumber();
 			break;
 		case 2:
 			payBill();
 			break;
 		case 3:
 			buyGoodAndServices();
 			break;
 		case 00:
 			menuDisplay();
 			break;
 			default:
 				System.out.println("INVALID CHOOISE");
 				sendMoney();
 		}
 	}
 	
 	//phone Number method in send Money method 
 	public static void phoneNumber() {
 		LocalDateTime dateTime=LocalDateTime.now();
 		String date=dateTime.toString();
 		double	sendMoney;
 		
 		Scanner scan = new Scanner(System.in);
 		System.out.println("Enter phone number");
 		int sendingPhoneNumber = scan.nextInt();
 		for(Long p:phoneNumbers.keySet()) {
 			if(sendingPhoneNumber==00) {
 				sendMoney();
 			}else if(sendingPhoneNumber==p) {
 				System.out.println("ENTER AMOUNT TO SEND ....00=back");
 			}else if(sendingPhoneNumber!=p) {
 			System.out.println("THE NUMBER IS NOT REGISTERED");
 			}else {
 				System.out.println("INVALID ENTRY");
 			}
 			
 			sendMoney=scan.nextInt();
 			if(inputChecker(Double.toString(sendMoney))==false){
 				System.out.println("!!! INVALID INPUT !!!");
 				phoneNumber();
 			}
 			
 			if(sendMoney==00) {
 				phoneNumber();
 			}else if(sendMoney>bankAmount) {
 				System.out.println("INSUFFICIENT AMOUNT IN YOUR ACCOUNT.Account balance :"+bankAmount+" Ksh .");
 				phoneNumber();
 			}else if(sendMoney<bankAmount) {
 			System.out.println("Enter your pin......00=back");
 			}else {
 				System.out.println("INVALID INPUT");
 				phoneNumber();
 			}
 			
 			int userPin = scan.nextInt();
 			if (userPin==00) {
 				sendMoney();
 			}else if (userPin==pin) {
 			System.out.println("CONFIRM THE TRASATION SENDING MONEY ");
 				
 			}else if (userPin!=pin) {
 				System.out.println("WRONG PIN");
 				phoneNumber();
 			}else {
 				System.out.println("INVALID INPUT");
 			}
 			
 			System.out.println("TO " +phoneNumbers.get(p)+" phone number "+sendingPhoneNumber);
 			System.out.println("ENTER \n1=YES \n2=NO");
 			int userSendConfirm=scan.nextInt();
 			if (userSendConfirm==1) {
 				bankAmount-=sendMoney;
 				System.out.println("You have send Ksh "+sendMoney+" to "+phoneNumbers.get(p)+" phone number "+sendingPhoneNumber+" on "+date+" balance "+bankAmount);
 				history.add(new BankSystem("SEND MONEY"," Ksh ",sendMoney," to ",phoneNumbers.get(p),sendingPhoneNumber," on ",date));
 				menuDisplay();
 			}else if (userSendConfirm==2) {
 				System.out.println("You have cancelled the transaction of sending money to" +phoneNumbers.get(p)+" phone number "+sendingPhoneNumber+"on"+date);
 			 phoneNumber();
 			}else {
 				System.out.println("INVALID INPUT");
 				sendMoney();
 			}
 		}
 	}
 	
 	//paybill method in the sendmoney method
 
 	public static void payBill() {
 		System.out.println("!!! UNDER MAINTAINCE !!!");
 		menuDisplay();
 	}
 	
 	//buyGoodAndServices method in the send money method
 	public static void buyGoodAndServices() {
 		LocalDateTime dateTime=LocalDateTime.now();
 		String date =dateTime.toString();
 		System.out.println("ENTER THE TILL NUMBER__________00=BACK");
 		Scanner scan = new Scanner(System.in);
 		int till= scan.nextInt();
 		for(Integer t:tillNumbers.keySet()) {
 			if(till==00) {
 				sendMoney();
 			}else if (till==t) {
 				System.out.println("ENTER AMOUNT..........00=BACK");
 			}else if(till!=t) {
 				System.out.println("THE TILL NUMBER IS NOT REGISTERED YET");
 				sendMoney();
 			}else {
 				System.out.println("INVALID INPUT");
 				sendMoney();
 			}
 			
 			double tillAmount=scan.nextDouble();
 			 if(tillAmount==00) {
 				 sendMoney();
 			 } else if (tillAmount!=00) {
 				 System.out.println("ENTER YOUR PIN NUMBER...........00=BACK");
 			 }else {
 				 System.out.println("INVALID INPUT");
 			 }
 			 
 			 int pinUser=scan.nextInt();
 			 if (pinUser==00) {
 				 sendMoney();
 			 }else if (pinUser==pin) {
 				 System.out.println("Confirm the transaction of sending " +tillAmount+" to "+tillNumbers.get(t)+" on " +date);
 			 }else if (pinUser!=pin) {
 				 System.out.println("WRONG PIN ");
 				 sendMoney();
 			 }else {
 				 System.out.println("INVALID INPUT");
 				 sendMoney();
 			 }
 			 
 			 System.out.println("ENTER \n 1=YES \n 2=NO");
 			 int paybillConfirm=scan.nextInt();
 			 if(inputChecker(Integer.toString(paybillConfirm))==false) {
 				 System.out.println("!!INVALID INPUT!!");
 				 menuDisplay();
 			 }
 			 if(paybillConfirm==1) {
 				 if(bankAmount>tillAmount) {
 				 bankAmount=-tillAmount;
 				 System.out.println("You have successfully send " +tillAmount+" Ksh. to "+tillNumbers.get(t)+" on "+date+" balance "+bankAmount);
 				history.add(new BankSystem("SEND MONEY"," Ksh ",tillAmount," to ",tillNumbers.get(t),till," on ",date));
 				menuDisplay();
 				 }else {
 					 System.out.println("YOU HAVE INSUFFICIENT AMOUNT IN YOUR ACCOUNT"+" balance "+bankAmount);
 				 }
 				
 			 }else if(paybillConfirm==2) {
 				 System.out.println("YOU HAVE CANCELLED THE TRANSATION OF SENDING "+tillAmount+" to "+tillNumbers.get(t)+" on " +date);
 				 menuDisplay();
 			 }else {
 				 System.out.println("INVALID INPUT");
 				 menuDisplay();
 			 }
 		
 		  }
 	  }
 	
 	//myAccount method in the menu Display method
 	public static void myAccount() {
 		Scanner scan = new Scanner(System.in);
 		ArrayList<String> myAccountDisplay = new ArrayList<String>();
 		myAccountDisplay.add("1. Check Balance");
 		myAccountDisplay.add("2. Change Pin");
 		myAccountDisplay.add("3. History");
 		
 		for(String m:myAccountDisplay) {
 			System.out.println(m);
 			if(m.equals("3. History")) {
 			break;
 			}
 		}
 		
 		System.out.println("ENTER THE NUMBER OF THE OPERATION YOU WANT TO PERFORM...\nTO EXIT  ENTER 00");
        int chooise =scan.nextInt();
        switch(chooise){
        case 1:
        	checkBalance();
        	break;
        case 2:
        	changePin();
        	break;
        case 3:
        	historyShow();
        	break;
        case 00:
        	menuDisplay();
        	break;
        default :
        		System.out.println();
        		myAccount();
        }
 	}
 	
 	
 	//The check balance methods in the myAccount method
 	public static void checkBalance() {
 		System.out.println("You balance is :"+" Ksh "+bankAmount);
 		myAccount();
 	}
 	
 	// change pin method in the myAccount method
 	public static void changePin() {
 		Scanner scan =new Scanner(System.in);
 		System.out.println("ENTER YOUR CURRENT PIN");
 	    int userCurrentPin =scan.nextInt();
 	    if (userCurrentPin==00) {
 	    	myAccount();
 	    }else if(inputChecker(Integer.toString(userCurrentPin))) {
 	    	if(userCurrentPin==pin) {
 	    	System.out.println("ENTER YOUR NEW PIN");
 	    	int newPass1=scan.nextInt();
 	    	System.out.println("CONFIRM NEW PIN");
 	    	int newPass2=scan.nextInt();
 	    		if(newPass1==newPass2) {
 	    			System.out.println("Pin changen Successfully");
 	    			pin=newPass1;
 	    			myAccount();
 	    		}else if(newPass1!=newPass2) {
 	    		  System.out.println("BOTH PIN DONT MATCH");
 	    		  changePin();
 	    		}else {
 	    			System.out.println("INVALID INPUT");
 	    			changePin();
 	    		}
 	    	
 	    	}else if(userCurrentPin!=pin) {
 	    	System.out.println("THATS NOT YOUR CURRENT PIN");
 	    	changePin();
 	    	} 
 		}else {
 			System.out.println("INVALID INPUT");
 			changePin();
 		}
 	}
 	
 	//History method in myAccount method
 	public static void historyShow() {
 	 int historySize=history.size();	
 	
 		System.out.println("showing history");
 		for(BankSystem h:history) {
 		System.out.println(h.operationDone+h.ksh+h.amount+h.from+h.bank+h.bankcode+h.on+h.datetime);
 			
 			//if(historySize==history.size()) {
 				//break;
 		//	}
 		}
 		System.out.println("showing history 2");
 		myAccount();
 	}
 	
 	//loans method in the menuDisplay method 
 	public static void loans() {
 		System.out.println("!!!!UNDER MAINTAINCE !!!");
 		menuDisplay();
 	}
 	
 	//saving method in the menuDisplay method 
 	public static void savings() {
 		System.out.println("!!!!UNDER MAINTAINCE !!!");
 		menuDisplay();
 	}
 	
 	//logout method in the menuDisplay
 	public static void logout() {
 		System.out.println("YOU HAVE SUCCEFULLY LOGED OUT");
 		start();
 	}
 	
 	
	public static void main(String[] args) {
		//ADD banKS Branches in the bankATM database
 		bankATM.put("THIKABRANCH",1234);
 		bankATM.put("MURANGA",5678);
 		
 		//bankATM.put("NAIROBI",9876);
 		bankATM.put("MOMBASSA",1212);
 		
 		//Adding Number in the phoneNumber database
 		phoneNumbers.put(712345678L, "NGUGI");
 		
 		//adding accounts in the accounts database
 	accounts.put("Vince", "Vincent#78");
 		 pin=1234;
 	//adding till  numbers in the tillNumbers database
 	tillNumbers.put(123456, "Karanja");
 		
 		start();
	}
}

