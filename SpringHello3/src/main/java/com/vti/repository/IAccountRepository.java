package com.vti.repository;

import java.util.List;

import com.vti.entites.Account;
import com.vti.entites.Department;
import com.vti.entites.Position;
import com.vti.entites.Fillter.AccountFillter;
import com.vti.entites.Form.AccountCreateForm;

public interface IAccountRepository {

	public List<Account> getAllAccount();

	public Account getAccById(int id);

	public Account updateAcc(Account account);
	
	public Account deleteAccById(int id);

	public List<Account> getAllAccountFillter(int page, int size, AccountFillter fillTer);
	
	public List<Account> getAllAccountByDepartment(int id);
	
	public Account createAcc(Account account);

}
