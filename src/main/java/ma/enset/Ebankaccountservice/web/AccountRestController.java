package ma.enset.Ebankaccountservice.web;

import ma.enset.Ebankaccountservice.dto.BankAccountRequestDTO;
import ma.enset.Ebankaccountservice.dto.BankAccountResponseDTO;
import ma.enset.Ebankaccountservice.entities.BankAccount;
import ma.enset.Ebankaccountservice.mappers.AccountMapper;
import ma.enset.Ebankaccountservice.repositories.BankAccountRepository;
import ma.enset.Ebankaccountservice.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountRestController {
    private AccountService accountService;
    private AccountMapper accountMapper;
    private BankAccountRepository bankAccountRepository;

    public AccountRestController(AccountService accountService, AccountMapper accountMapper, BankAccountRepository bankAccountRepository) {
        this.accountService = accountService;
        this.accountMapper = accountMapper;
        this.bankAccountRepository = bankAccountRepository;
    }

    @GetMapping("/bankAccounts")
    public List<BankAccount> bankAccounts(){
        return bankAccountRepository.findAll();
    }

    @GetMapping("/bankAccounts/{id}")
    public BankAccount bankAccount(@PathVariable String id){
        return bankAccountRepository.findById(id).orElseThrow(()->new RuntimeException(String.format("Account  %s not found ",id)));
    }
    @PostMapping("/bankAccounts")
    public BankAccountResponseDTO save(@RequestBody BankAccountRequestDTO requestDTO){

        return accountService.addAccount(requestDTO);
    }

    @PutMapping("/bankAccounts/{id}")
    public  BankAccount update(@PathVariable String id,@RequestBody BankAccount bankAccount){
        BankAccount account=bankAccountRepository.findById(id).orElseThrow();
        if(bankAccount.getBalance()!= null)
        account.setBalance(bankAccount.getBalance());
        if(bankAccount.getCreateDate()!= null)
            account.setCreateDate(new Date());
        if(bankAccount.getType()!= null)
            account.setType(bankAccount.getType());
        if(bankAccount.getCurrency()!= null)
            account.setCurrency(bankAccount.getCurrency());
        return bankAccountRepository.save(account);
    }
    @DeleteMapping("/bankAccounts/{id}")
    public void  deleteAccount(@PathVariable String id){
        bankAccountRepository.deleteById(id);
    }
}
