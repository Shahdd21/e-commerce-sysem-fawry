package service;

import entity.Wallet;
import exception.InsufficientBalanceException;
import repository.WalletRepository;

public class WalletService {

    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public boolean withdraw(String username, double amount){

        boolean isSuccessful =  walletRepository.withdraw(username, amount);

        if(!isSuccessful){
            throw new InsufficientBalanceException("Insufficient balance in your wallet.");
        }

        return true;
    }

    public void fund(String username, double amount){

        walletRepository.fund(username, amount);
    }

    public double getBalance(String username){
        return walletRepository.getBalance(username);
    }

    public void assignWalletToUser(String username){
        walletRepository.assignWalletToUser(username);
    }

    public Wallet getWallet(String username){
        return walletRepository.getWallet(username);
    }
}
