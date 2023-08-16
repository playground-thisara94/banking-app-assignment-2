import java.util.Arrays;
import java.util.Scanner;

public class SmartBankingApp2 {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        
        final String CLEAR = "\033[H\033[2J";
        final String COLOR_BLUE_BOLD = "\033[34;1m";
        final String COLOR_RED_BOLD = "\033[31;1m";
        final String COLOR_GREEN_BOLD = "\033[32;1m";
        final String RESET = "\033[0m";

        final String DASHBOARD = "Welcome to Smart Banking App";
        final String OPEN_ACCOUNT = "Open New Account";
        final String DEPOSIT_MONEY = "Deposit Money";
        final String WITHDRAW_MONEY = "Withdraw Money";
        final String TRANSFER_MONEY = "Transfer Money";
        final String ACCOUNT_BALANCE = "Check Account Balance";
        final String DROP_ACCOUNT = "Drop Existing Account";
        
        final String ERROR_MSG = String.format("\t%s%s%s\n",COLOR_RED_BOLD,"%s",RESET);
        final String SUCCESS_MSG = String.format("\t%s%s%s\n", COLOR_GREEN_BOLD,"%s",RESET);

        String[][] accounts = new String[0][];

        String screen = DASHBOARD;

        outerloop:
        do {
            final String APP_TITLE = String.format("%s%s%s", COLOR_BLUE_BOLD,screen,RESET);

            System.out.println(CLEAR);
            System.out.println("\t"+APP_TITLE+"\n");

            switch(screen) {
                case DASHBOARD:
                    System.out.println("\t[1]. Open New Account");
                    System.out.println("\t[2]. Deposit Money");
                    System.out.println("\t[3]. Withdraw Money");
                    System.out.println("\t[4]. Transfer Money");
                    System.out.println("\t[5]. Check Account Balance");
                    System.out.println("\t[6]. Drop Existing Account");
                    System.out.println("\t[7]. Exit");   
                    System.out.println();
                    System.out.print("\tEnter an option to continue: ");
                    int option = SCANNER.nextInt();
                    SCANNER.nextLine();

                    switch (option) {
                        case 1: screen = OPEN_ACCOUNT; break;
                        case 2: screen = DEPOSIT_MONEY; break;
                        case 3: screen = WITHDRAW_MONEY; break;
                        case 4: screen = TRANSFER_MONEY; break;
                        case 5: screen = ACCOUNT_BALANCE; break;
                        case 6: screen = DROP_ACCOUNT; break;
                        case 7: System.out.println(CLEAR); System.exit(0);
                        default: continue;
                    }
                    break;

                case OPEN_ACCOUNT:
                    String name;
                    double initialDeposit;
                    boolean valid;

                    int id = accounts.length+1;
                    String accountID = String.format("SDB-%05d",id);
                    System.out.printf("\tID : %s\n",accountID);

                    do{
                        valid = true;
                        System.out.print("\tName: ");
                        name = SCANNER.nextLine().strip();
                        if (name.isBlank()){
                            System.out.printf(ERROR_MSG,"Name can't be empty");
                            valid = false;
                            continue;
                        }
                        for (int i = 0; i < name.length(); i++) {
                            if(!(Character.isLetter(name.charAt(i)) || Character.isSpaceChar(name.charAt(i)))) {
                                System.out.printf(ERROR_MSG,"Invalid Name");
                                valid = false;
                                break;
                            }
                        }
                    } while (!valid);

                    do{
                        valid = true;
                        System.out.print("\tInitial Deposit: ");
                        initialDeposit = SCANNER.nextDouble();
                        SCANNER.nextLine();
                        if (initialDeposit < 5000.00){
                            System.out.printf(ERROR_MSG,"Insufficient Amount");
                            valid = false;
                            continue;
                        }
                        
                    } while (!valid);
                    
                    String[][] newAccount = new String[accounts.length+1][3]; 
                    

                    for (int i = 0; i < accounts.length; i++) {
                        newAccount[i] = accounts[i];
                    }
                    newAccount[newAccount.length-1][0] = accountID;
                    newAccount[newAccount.length-1][1] = name;
                    newAccount[newAccount.length-1][2] = initialDeposit+"";

                    accounts = newAccount;

                    System.out.println();
                    System.out.printf(SUCCESS_MSG,String.format("SDB-%05d: %s has been created successfully.",id,name));
                    System.out.print("\tDo you want to add another account (Y/n)?");
                    if (SCANNER.nextLine().strip().toUpperCase().equals("Y")) continue;
                    screen = DASHBOARD;
                    break;
                
                    

            }
            
        } while (true);


    }
}