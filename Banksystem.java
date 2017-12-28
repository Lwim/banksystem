/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Uppgift3.newpackage; 
import java.util.Scanner;

/**
 *
 * @author petter
 */
public class Banksystem {

    static BankController bc = new BankController();
    static int accountNumber = 1000; //löpnummer för konton

    public static void main(String[] args) {
        loadTestData();
        //Anropar meny
        showMeny();
    }

    public static void showMeny() {
        Scanner input = new Scanner(System.in);
        System.out.println("\n 1. Meny \n 2. Skapa kund \n 3. Visa kunder \n 4. Sök kund \n 5. Lägg till konto \n 6. Visa alla konton \n 7. Visa kunds konton \n 8. Gör överföring \n 9. Ta bort konto \n 10. Avsluta");
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
                    addAccount();
                    break;
                }
                case 6: {
                    printAccount();
                    break;
                }
                case 7: {
                    printCustomerAccount();
                    break;
                }
                case 8: {
                    makeTransfer();
                    break;
                }
                case 9: {
                    delAccount();
                    break;
                }
                case 10: {
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

        //metod för att lägga till kund
    public static void addCustomer() {
        
        Scanner input = new Scanner(System.in);
        System.out.println("Ange personnummer 10 siffror");
        String pnr = input.nextLine();
       
        //Kontrollera längd av personnummer
        if (pnr.length() != 10) {
            System.out.println("Ogiltigt format på personnumret");
            addCustomer();
        }
        //Kontrollera att personnummer inte redan finns i kundlistan
        if(bc.customerExist(pnr)){
            System.out.println("Kund med id: " + pnr + " existerar redan");
            showMeny();
        }
        
        LuhnValidation l = new LuhnValidation();
        boolean validPnr = l.luhn(pnr);
        
        if (!validPnr){
            System.out.println("Ogiltigt personnummer");
            addCustomer();
        }
        
        System.out.println("Ange kundens namn");
        String name = input.nextLine();

        bc.regCustomer(pnr, name);
        System.out.println("Kund tillagd");
        showMeny();

    }

        //metod för att lägga till konto
    public static void addAccount() {
        Scanner input = new Scanner(System.in);
        System.out.println("Ange kundens personnummer 10 siffror");
        String owner = input.nextLine();
        boolean exist = bc.customerExist(owner);
        if (!exist){
            System.out.println("Kunden finns inte.");
            addAccount();
        }
        
        int account = accountNumber;
        int balance = 0;

        bc.regAccount(owner, account, balance);
        System.out.println("Konto skapat med kontonr: "+ accountNumber);
        accountNumber++;
        showMeny();
    }

    public static void showCustomer() {
        bc.printAllCustomer();
        showMeny();

    }
    
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
        
    }

    public static void printAccount() {
        bc.printAllAccount();
        showMeny();
    }

    public static void printCustomerAccount() {
        Scanner input = new Scanner(System.in);
        System.out.println("Ange personnummer för kunden vars saldon du vill visa");
        String search = input.nextLine();
        bc.printCustomerAccount(search);
//        System.out.println("Konton        Saldo");
//        System.out.println(bc.printCustomerAccount(search).getOwner()+"  "+bc.printCustomerAccount(search).getBalance());
        showMeny();
    }

    public static void findCustomer() {
        Scanner input = new Scanner(System.in);
        System.out.println("Ange personnummer för kunden du vill söka");
        String search = input.nextLine();
        System.out.println(bc.findCustomerbyPnr(search).getName());
        showMeny();
    }

    static void findByIndex() {
        Scanner input = new Scanner(System.in);
        System.out.println("Ange index");
        int idx = input.nextInt();
        System.out.println(bc.findCustomerbyIndex(idx).getName());
        showMeny();
    }
    
    static void makeTransfer() {
        boolean transfer;
        boolean exist;
        Scanner input = new Scanner(System.in);
        System.out.println("Från vilket konto ska överföringen ske?");
        int from = input.nextInt();
        exist = bc.accountExist(from);
        if (!exist) {
            System.out.println("Kontot finns ej");
            makeTransfer();
        }
        
        System.out.println("Till vilket konto ska pengarna överföras till?");
        int to = input.nextInt();
        
        exist = bc.accountExist(to);
        if (!exist) {
            System.out.println("Kontot finns ej");
            makeTransfer();
        }
        
        System.out.println("Ange summa för överföring");
        int amount = input.nextInt();
        
        transfer = bc.balanceOk(from, amount); //kollar att kontot har täckning.
        
        if (transfer) {
            bc.balanceTransfer(from, to, amount);
            System.out.println("Överföring klar");
            showMeny();
        }
        else {
            System.out.println("Beloppet är större än saldot på kontot.");
            showMeny();
        }
    }

    //Endast testdata
    static void loadTestData() {
        bc.regCustomer("8912277897", "Kalle Kula");
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
        bc.regAccount("8912277897", accountNumber, 18000);
        accountNumber++;
    }

}
