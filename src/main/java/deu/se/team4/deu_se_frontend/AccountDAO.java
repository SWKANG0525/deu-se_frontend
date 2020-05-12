/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deu.se.team4.deu_se_frontend;

/**
 *
 * @author 강호동
 */
public class AccountDAO {
    private String apiKey;
    private EnumAccount account_type;
    private static AccountDAO singleton_dao;  
    
    private AccountDAO() {

    }
    
    public static final AccountDAO getInstance() {
        if(singleton_dao == null)
            singleton_dao = new AccountDAO();
       
        return singleton_dao; 
    }
    
    public final void setApiKey(String apiKey) {
        this.apiKey = apiKey;  
    }
    
    public final void setAccountType(EnumAccount account_type) {
        this.account_type = account_type;
    }
    
    public final String getApiKey() {
        return this.apiKey;   
    }
    
    public final EnumAccount getAccountType() {
        return this.account_type;
    }
    
    
}
