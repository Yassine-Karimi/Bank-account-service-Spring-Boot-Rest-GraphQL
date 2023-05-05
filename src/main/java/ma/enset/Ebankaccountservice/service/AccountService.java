package ma.enset.Ebankaccountservice.service;

import ma.enset.Ebankaccountservice.dto.BankAccountRequestDTO;
import ma.enset.Ebankaccountservice.dto.BankAccountResponseDTO;

public interface AccountService {
    BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO);
}
