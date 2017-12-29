/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exam3;

/**
 *
 * @author petter
 */
public class BankCustomer {
    
    private String pnr;
    private String name;

    public BankCustomer() {
    }

    public BankCustomer(String pnr) {
        this.pnr = pnr;
    }

    public String getName() {
        return name;
    }

    public void setPnr(String pnr) {
        this.pnr = pnr;
    }

    public String getPnr() {
        return pnr;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    
    
}
