package repository;

import entity.Wallet;

import java.util.HashMap;
import java.util.Map;

public class WalletRepository {

    private final Map<String, Wallet> walletMap;

    public WalletRepository() {
        this.walletMap = new HashMap<>();
    }

    public boolean withdraw(String username, double amount){

        Wallet userWallet = walletMap.get(username);

        if(userWallet.getBalance()-amount < 0) return false;

        double updatedBalance = userWallet.getBalance()-amount;
        userWallet.setBalance(updatedBalance);

        return true;
    }

    public void fund(String username, double amount){

        Wallet userWallet = walletMap.get(username);

        userWallet.setBalance(userWallet.getBalance()+amount);
    }

    public double getBalance(String username){
        return walletMap.get(username).getBalance();
    }

    public void assignWalletToUser(String username){
        walletMap.put(username, new Wallet());
    }

    public Wallet getWallet(String username){
        return walletMap.get(username);
    }
}
