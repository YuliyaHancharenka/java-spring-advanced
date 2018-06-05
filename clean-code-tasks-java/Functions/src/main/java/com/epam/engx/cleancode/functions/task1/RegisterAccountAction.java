package com.epam.engx.cleancode.functions.task1;

import com.epam.engx.cleancode.functions.task1.thirdpartyjar.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.epam.engx.cleancode.functions.task1.thirdpartyjar.CheckStatus.OK;

public class RegisterAccountAction {

    private PasswordChecker passwordChecker;
    private AccountManager accountManager;

    public void register(Account account) {
        validateName(account);
        validatePassword(account);

        account.setCreatedDate(new Date());
        List<Address> addresses = getAddresses(account);
        account.setAddresses(addresses);
        accountManager.createNewAccount(account);
    }

    private List<Address> getAddresses(Account account) {
        List<Address> addresses = new ArrayList<>();
        addresses.add(account.getHomeAddress());
        addresses.add(account.getWorkAddress());
        addresses.add(account.getAdditionalAddress());
        return addresses;
    }

    private void validateName(Account account) {
        if (account.getName().length() <= 5){
            throw new WrongAccountNameException();
        }
    }

    private void validatePassword(Account account) {
        String password = account.getPassword();
        if (password.length() <= 8) {
            if (passwordChecker.validate(password) != OK) {
                throw new WrongPasswordException();
            }
        }
    }


    public void setAccountManager(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    public void setPasswordChecker(PasswordChecker passwordChecker) {
        this.passwordChecker = passwordChecker;
    }

}
