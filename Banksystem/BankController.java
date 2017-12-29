/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Uppgift3.newpackage;
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
    
    public void addAccount(BankAccount account){
        accountList.add(account);
    }
    
    public void regAccount(String owner, int account, int amount){
        a = new BankAccount();
        a.setOwner(owner);
        a.setAccount(account);
        a.setBalance(amount);
        
        addAccount(a);
    }
    
    public void addCustomer(BankCustomer customer){
        customerList.add(customer);
    }
    
    public void regCustomer(String pnr, String name){
        c = new BankCustomer();
        c.setPnr(pnr);
        c.setName(name);
        
        addCustomer(c);
        
    }
    
    public void printAllCustomer() {
        System.out.println("----Bankkunder----");
        int i = 0;
        for (BankCustomer bc : customerList) {
            System.out.println("("+i+") "+bc.getPnr()+ ":" + bc.getName());
            i++;
        }
    }
    
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
        
        // sätt startvärde till något generellt, alltså inte 999...
        int idx = 0;
        boolean success = false;
        
        for (BankAccount ac : accountList){
            if (ac.getAccount() == account){
                // hitta index för det konto som skall raderas
                idx = accountList.indexOf(ac);
                success = true;
           }  
        }
        
        // om vi lyckats hitta ett index, radera detta konto
        // notera att detta måste ske utanför for loopen ovan
        if(success){
            accountList.remove(idx);
        }
    }
    
    public void printCustomerAccount(String pnr) {
        System.out.println("Konton Saldo");
        for (BankAccount ac : accountList) {
            if (ac.getOwner().contains(pnr)){
              System.out.println(ac.getAccount()+"      "+ac.getBalance());
               }
        }
    }
    
    public BankCustomer findCustomerbyIndex(int id) {
    return customerList.get(id);

    }
            
}