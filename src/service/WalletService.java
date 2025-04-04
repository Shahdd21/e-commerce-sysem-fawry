package service;

import entity.Wallet;
import repository.WalletRepository;

public class WalletService {

    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public boolean withdraw(String username, double amount){

        return walletRepository.withdraw(username, amount);
    }

    public void fund(String username, double amount){

        walletRepository.fund(username, amount);
    }

    public double getBalance(String username){
        return walletRepository.getBalance(username);
    }

    public void assignWalletToUser(String username){
        walletRepository.associateWalletToUser(username);
    }

    public Wallet getWallet(String username){
        return walletRepository.getWallet(username);
    }
}
