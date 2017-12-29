/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exam3;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author petter
 */
public class BankController {
    
    ArrayList<BankCustomer> customerList = new ArrayList();
    ArrayList<BankAccount> accountList = new ArrayList();
    
    BankCustomer c;
    BankAccount a;
    
    //spara registrerat konto i ArrayList
    public void addAccount(BankAccount account){
        accountList.add(account);
    }
    //registrera konto
    public void regAccount(String owner, int account, int amount){
        a = new BankAccount();
        a.setOwner(owner);
        a.setAccount(account);
        a.setBalance(amount);
        
        addAccount(a);
    }
    //spara registrerad kund i ArrayList
    public void addCustomer(BankCustomer customer){
        customerList.add(customer);
    }
    //registrera kund
    public void regCustomer(String pnr, String name){
        c = new BankCustomer();
        c.setPnr(pnr);
        c.setName(name);
        
        addCustomer(c);
        
    }
    //skriv ut lista på alla kunder
    public void printAllCustomer() {
        System.out.println("----Bankkunder----");
        int i = 0;
        for (BankCustomer bc : customerList) {
            System.out.println("("+i+") "+bc.getPnr()+ ":" + bc.getName());
            i++;
        }
    }
    //Skriv ut lista på alla konton
    public void printAllAccount(){
        System.out.println("----Bankkonton----");
        int i = 0;
        for (BankAccount ac : accountList) {
            System.out.println("("+i+") "+ac.getOwner()+" : "+ac.getAccount()+" : "+ac.getBalance());
            i++;
        }
    }
    
    public BankCustomer findCustomerbyPnr(String pnr) {
       
        for (BankCustomer cu : customerList) {
            if (cu.getPnr().equals(pnr)){
               c = cu;
               return c;
               
               }
        }
        c = null;
        return c;
    }
    //överföring av pengar mellan konton
    public void balanceTransfer(int from, int to, int amount){
        for (BankAccount ac : accountList){
            if (ac.getAccount() == from){
                int balance = ac.getBalance();
                ac.setBalance(balance-amount);
            }
            if (ac.getAccount() == to){
                int balance = ac.getBalance();
                ac.setBalance(balance+amount);
            }
        }
    }
    
    public void makeDeposit(int account, int amount){
        if(amount <= 0){
            System.out.println("Summan måste vara större än 0");
        }
        else {
            for (BankAccount ac : accountList){
                if (ac.getAccount() == account){
                    int balance = ac.getBalance();
                    ac.setBalance(balance + amount);
                }
                
            }
            System.out.println(amount+"kr är insatt på konto "+account);
        }
    }
    
    //kontrollerar att summan inte blir negativ på kontot efteröverföring
    public boolean balanceOk(int account, int amount){
        int balance = 0;
        for (BankAccount ac : accountList){
           if (ac.getAccount() == account){
                balance = ac.getBalance(); 
            }
        }
        int result = balance - amount;
        return result >=0;
    }
    
    //kontrollerar att konto existerar
    public boolean accountExist(int account){
        boolean exist = false;
        for (BankAccount ac : accountList){
           if (ac.getAccount() == account){
               exist = true;
            }
        }
        return exist;
    }
    
    //kontrollerar att kund existerar
    public boolean customerExist(String pnr){
    boolean exist = false;
    for (BankCustomer cu : customerList) {
        if (cu.getPnr().equals(pnr)){
        exist = true;
        }
    }
        return exist;
    }
    

    
    //Ta bort konto
    public void delAccount(int account){
        int idx = -1;
        for (BankAccount ac : accountList){
            if (ac.getAccount() == account){
                idx = accountList.indexOf(ac);
            }
        }
        accountList.remove(idx);
    }
    //Ta bort kund
    public void delCustomer(String owner){
        int idx = -1;
        for (BankCustomer bc : customerList){
            if (bc.getPnr().equals(owner)) {
                idx = customerList.indexOf(bc);
            }
        }
        customerList.remove(idx);
        System.out.println("Kund borttagen");
    }
    
    //Ta bort alla konton för given kund
    public void delCustomerAccounts(String owner){
        //Spara alla index för accounts kopplade till kunden
        List<Integer> accounts = new ArrayList();
        for (BankAccount ac : accountList){
            if (ac.getOwner().contains(owner)){
                int idx = accountList.indexOf(ac);
                accounts.add(idx);
            }
        }
        //Tar bort konton i accountList med index i listan accounts
       for (int i : accounts){
           accountList.remove(i);
       }
        System.out.println("Konton borttagna");
    }
    
    // skriv ut alla konton för vald kund
    public void printCustomerAccount(String pnr) {
        System.out.println("Konton Saldo");
        for (BankAccount ac : accountList) {
            if (ac.getOwner().contains(pnr)){
              System.out.println(ac.getAccount()+"      "+ac.getBalance());
               }
        }
    }
    //överflödigt?
    public BankCustomer findCustomerbyIndex(int id) {
    return customerList.get(id);

    }
            
}
