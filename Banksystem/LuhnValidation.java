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
public class LuhnValidation {
    
    
   public boolean luhn(String pnr){
    int extraChars = pnr.length() - 10;
    if (extraChars < 0) {
      throw new IllegalArgumentException("Personnumret måste innehålla minst 10 tecken!");
    }
    pnr = pnr.substring(extraChars, 10 + extraChars);
    int sum = 0;
    for (int i = 0; i < pnr.length(); i++){
      char tmp = pnr.charAt(i);
      int num = tmp - '0';
      int product;
      if (i % 2 != 0){
        product = num * 1;
      }
      else{
        product = num * 2;
      }
      if (product > 9)
        product -= 9;
      sum+= product;              
    }
    return (sum % 10 == 0);
  }

 
    
}
