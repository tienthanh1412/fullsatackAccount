package com.vti.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.vti.entites.Account;
import com.vti.entites.Fillter.AccountFillter;
import com.vti.entites.Form.AccountCreateForm;
import com.vti.exception.CreateAccountException;
import com.vti.exception.UpdateAccountExeption;
import org.springframework.stereotype.Service;

//extends UserDetailsService(thằng này có sẵn ở spring) để có thể làm đăng nhập phân quyền
@Service
public interface IAccountService extends UserDetailsService {




	public Account getAccById(int id) throws Exception;


	public Account updateAcc(int id, AccountCreateForm form) throws UpdateAccountExeption   ;

	public Account deleteAccById(int id) throws Exception;

	public Account createAcc(AccountCreateForm form) throws CreateAccountException;

	public Account getAccByName(String userName);

	public Account deleteAccByName(String userName);

	public Page<Account> getAllAccounts(Pageable pagable,AccountFillter fillTer);


	public Account getAccountByUserName(String userName);

	public UserDetails loadUserByUsername(String userName);


}
