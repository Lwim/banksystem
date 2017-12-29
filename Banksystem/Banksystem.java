/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exam3;

import java.util.Scanner;

/**
 *
 * @author petter
 */
public class Banksystem {

    static BankController bc = new BankController();
    static int accountNumber = 1000; //löpnummer för konton

    public static void main(String[] args) {
        loadTestData(); //laddar testdata
        //Anropar meny
        showMeny();
    }
// huvudmeny

    public static void showMeny() {
        Scanner input = new Scanner(System.in);
        System.out.println("\n 1. Meny \n 2. Hantera kunder \n 3. Hantera konton \n 4. Avsluta");
        while (true) {
            int menyVal = input.nextInt();
            switch (menyVal) {
                case 1: {
                    showMeny();
                    break;
                }
                case 2: {
                    showCustomerMeny();
                    break;
                }
                case 3: {
                    showAccountMeny();
                    break;
                }
                case 4: {
                    System.exit(0);
                    break;
                }
                default: {
                    System.out.println("Ogiltigt menyval");
                    break;
                }
            }
        }
    }
//Meny för att hantera kunder

    public static void showCustomerMeny() {
        Scanner input = new Scanner(System.in);
        System.out.println("\n 1. Huvudmeny \n 2. Skapa kund \n 3. Visa kunder \n 4. Sök kund \n 5. Ta bort kund och konton");
        while (true) {
            int menyVal = input.nextInt();
            switch (menyVal) {
                case 1: {
                    showMeny();
                    break;
                }
                case 2: {
                    addCustomer();
                    break;
                }
                case 3: {
                    showCustomer();
                    break;
                }
                case 4: {
                    findCustomer();
                    break;
                }
                case 5: {
                    delCustomer();
                    break;
                }

                default: {
                    System.out.println("Ogiltigt menyval");
                    break;
                }
            }
        }
    }

    // Meny för att hantera konton
    public static void showAccountMeny() {
        Scanner input = new Scanner(System.in);
        System.out.println("\n 1. Huvudmeny \n 2. Lägg till konto \n 3. Visa alla konton \n 4. Visa kunds konton \n 5. Gör överföring \n 6. Gör insättning \n 7. Ta bort konto");
        while (true) {
            int menyVal = input.nextInt();
            switch (menyVal) {
                case 1: {
                    showMeny();
                    break;
                }
                case 2: {
                    addAccount();
                    break;
                }
                case 3: {
                    printAccount();
                    break;
                }
                case 4: {
                    printCustomerAccount();
                    break;
                }
                case 5: {
                    makeTransfer();
                    break;
                }
                case 6: {
                    makeDeposit();
                    break;
                }                
                case 7: {
                    delAccount();
                    break;
                }
                default: {
                    System.out.println("Ogiltigt menyval");
                    break;
                }
            }
        }
    }

    //metod för att lägga till kund
    public static void addCustomer() {
 
        Scanner input = new Scanner(System.in);
        System.out.println("Ange personnummer 10 siffror");
        String pnr = input.nextLine();
        
        boolean exist = bc.customerExist(pnr);
        
        if (exist) {
            System.out.println("Kunden finns redan.");
            showCustomerMeny();
        }        
        
        //Kontrollerar personnummers längd
        if (pnr.length() != 10) {
            System.out.println("Ogiltigt format på personnumret");
            addCustomer();
        }
        //Kontrollerar att personnumret har giltig kontrollsiffra
        LuhnValidation l = new LuhnValidation();
        boolean validPnr = l.luhn(pnr);

        if (!validPnr) {
            System.out.println("Ogiltigt personnummer");
            addCustomer();
        }

        System.out.println("Ange kundens namn");
        String name = input.nextLine();

        bc.regCustomer(pnr, name);
        System.out.println("Kund tillagd");
        showMeny();

    }
    //Ta bort kund samt alla konton för kunden
    public static void delCustomer(){
        Scanner input = new Scanner(System.in);
        System.out.println("Ange kundens personnummer 10 siffror");
        String owner = input.nextLine();
        boolean exist = bc.customerExist(owner);
        if (!exist) {
            System.out.println("Kunden finns inte.");
            delCustomer();
        }
        bc.delCustomerAccounts(owner);
        bc.delCustomer(owner);
        showCustomerMeny();
    }

    //metod för att lägga till konto
    public static void addAccount() {
        Scanner input = new Scanner(System.in);
        System.out.println("Ange kundens personnummer 10 siffror");
        String owner = input.nextLine();
        boolean exist = bc.customerExist(owner);
        if (!exist) {
            System.out.println("Kunden finns inte.");
            addAccount();
        }

        int account = accountNumber;
        int balance = 0;

        bc.regAccount(owner, account, balance);
        System.out.println("Konto skapat med kontonr: " + accountNumber);
        accountNumber++;
        showMeny();
    }
// Skriv ut alla kunder
    public static void showCustomer() {
        bc.printAllCustomer();
        showMeny();

    }
//Ta bort konto
    public static void delAccount() {
        boolean exist;
        Scanner input = new Scanner(System.in);
        System.out.println("Ange kontonummer som ska tas bort");
        int account = input.nextInt();
        exist = bc.accountExist(account);
        if (!exist) {
            System.out.println("Kontot finns ej");
            delAccount();
        }
        bc.delAccount(account);
        System.out.println("Konto borttaget");
        showAccountMeny();

    }
//Skriv ut alla konton
    public static void printAccount() {
        bc.printAllAccount();
        showMeny();
    }
//Skriv ut konton för specifik kund
    public static void printCustomerAccount() {
        Scanner input = new Scanner(System.in);
        System.out.println("Ange personnummer för kunden vars saldon du vill visa");
        String search = input.nextLine();
        bc.printCustomerAccount(search);
        showMeny();
    }
//onödig?
    public static void findCustomer() {
        Scanner input = new Scanner(System.in);
        System.out.println("Ange personnummer för kunden du vill söka");
        String search = input.nextLine();
        System.out.println(bc.findCustomerbyPnr(search).getName());
        showMeny();
    }
//onödig?
    static void findByIndex() {
        Scanner input = new Scanner(System.in);
        System.out.println("Ange index");
        int idx = input.nextInt();
        System.out.println(bc.findCustomerbyIndex(idx).getName());
        showMeny();
    }
//Sätt in pengar på konto
    static void makeDeposit() {
        boolean exist;
        Scanner input = new Scanner(System.in);
        System.out.println("Ange kontonummer för insättning");
        int account = input.nextInt();
        exist = bc.accountExist(account);
        if (!exist) {
            System.out.println("Kontot finns ej");
            makeDeposit();
        }
        System.out.println("Ange belopp du vill sätta in på konto "+account);
        int amount = input.nextInt();
        bc.makeDeposit(account, amount);
        showAccountMeny();
   

    //Endast testdata
    static void loadTestData() {
        bc.regCustomer("8510102499", "Kalle Kula");
        bc.regCustomer("8311013671", "Bengt Sträng");
        bc.regCustomer("7905209563", "Johan Andersson");
        bc.regCustomer("9209158637", "Anna Persson");
        bc.regAccount("9209158637", accountNumber, 3500);
        accountNumber++;
        bc.regAccount("9209158637", accountNumber, 10000);
        accountNumber++;
        bc.regAccount("7905209563", accountNumber, 300);
        accountNumber++;
        bc.regAccount("7905209563", accountNumber, 1000);
        accountNumber++;
        bc.regAccount("8311013671", accountNumber, 2800);
        accountNumber++;
        bc.regAccount("8510102499", accountNumber, 18000);
        accountNumber++;
    }

}
