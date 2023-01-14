package com.vti.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.vti.entites.DTO.AccountDTO;
import com.vti.entites.Role;
import com.vti.repository.IDepartmentRepositoryV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vti.entites.Account;
import com.vti.entites.Department;
import com.vti.entites.Position;
import com.vti.entites.Fillter.AccountFillter;
import com.vti.entites.Form.AccountCreateForm;
import com.vti.entites.Specification.AccountSpecification;
import com.vti.exception.CreateAccountException;
import com.vti.exception.UpdateAccountExeption;
import com.vti.repository.IAccountRepositoryV2;
import com.vti.repository.IDepartmentRepository;
import com.vti.repository.IPositionRepository;

@Component
@Transactional

public class AccountService implements IAccountService {


    @Autowired
    private IPositionRepository pRepository;

    @Autowired
    private IDepartmentRepository dpRepository;

    private IDepartmentRepositoryV2 departmentRepositoryV2;

    @Autowired
    private IAccountRepositoryV2 repositoryV2;// sử dụng JPA


    //GETBYID-----------------------------------------------------------------------------------------------------------------------
    public Account getAccById(int id) throws Exception {
        return repositoryV2.getById(id);

    }

    //UPDATE-----------------------------------------------------------------------------------------------------------------------
    public Account updateAcc(int id, AccountCreateForm form) throws UpdateAccountExeption {

        Account account = repositoryV2.getById(id);
        if (account == null) {
            throw new UpdateAccountExeption("lỗi update Tài Khoản: Sai Id");
        }
        Department department = dpRepository.getDepartmentById(form.getDpId());
        if (department == null) {
            throw new UpdateAccountExeption("lỗi update Tài Khoản : Sai DepartmentId");
        }
        Position position = pRepository.getPositionById(form.getPosId());
        if (position == null) {
            throw new UpdateAccountExeption("lỗi update Tài Khoản : Sai PositionId");
        }
        // Kiểm tra định dạng email
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        if (form.getEmail() != null && !form.getEmail().matches(regexPattern)) {
            throw new UpdateAccountExeption("lỗi update Tài Khoản: Sai định dạng Email");
        }
//gán dữ liệu của Account vào form
        if (form.getUserName() != null) {
            account.setUserName(form.getUserName());
        }
        if (form.getUserName() != null) {
            account.setFullName(form.getFullName());
        }
        if (form.getEmail() != null) {
            account.setEmail(form.getEmail());
        }
        if (form.getPassword() != null){
            account.setPassword(form.getPassword());
        }
        account.setRole(Role.valueOf(form.getRole()));

        account.setDepartments(department);

        account.setPosition(position);



        return repositoryV2.save(account);// sử dụng JPA

    }

    //DELETE-----------------------------------------------------------------------------------------------------------------------
    public Account deleteAccById(int id) throws Exception {
        if (id < 0) {
            throw new Exception("ID Không Hợp lệ");
        }

        Account account = repositoryV2.getById(id);
        repositoryV2.deleteById(id);
        return account;

    }


    //CREATE-----------------------------------------------------------------------------------------------------------------------
    @Override
    public Account createAcc(AccountCreateForm form) throws CreateAccountException {

        Department department = dpRepository.getDepartmentById(form.getDpId());
        if (department == null) {
            throw new CreateAccountException("lỗi tạo Tài Khoản : Sai PositionId", 402);
        }
        Position position = pRepository.getPositionById(form.getPosId());
        if (position == null) {
            throw new CreateAccountException("lỗi tạo Tài Khoản : Sai PositionId", 402);
        }
        // Kiểm tra định dạng email
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        if (!form.getEmail().matches(regexPattern)) {
            throw new CreateAccountException("lỗi tạo Tài Khoản: Sai định dạng Email", 403);
        }
        Account account = new Account();
        List<String> listAccount = repositoryV2.getUsernameByAccount();
        if(!listAccount.contains(form.getUserName())){
            account.setUserName(form.getUserName());
        }else {throw new CreateAccountException("lỗi tạo Tài Khoản: username đã tồn tại", 403) ;}

        account.setFullName(form.getFullName());
        account.setPassword(form.getPassword());
        account.setEmail(form.getEmail());
        account.setDepartments(department);
        account.setRole(Role.valueOf(form.getRole()));
        account.setPosition(position);

        return repositoryV2.save(account);

    }
//DELETE BY NAME-------------------------------------------------------------------------------------------------------------------------------

    @Override
    public Account deleteAccByName(String userName) {
        Account account = repositoryV2.getAccountByUserName(userName);
        repositoryV2.deleteByUserName(userName);
        return account;

    }

//Find By Name------------------------------------------------------------------------------------------------------------------------------

    public Account getAccByName(String userName) {

        return repositoryV2.findByUserName(userName);
    }

    public Account getAccountByUserName(String userName) {
        return repositoryV2.getAccountByUserName(userName);
    }

    //VD tìm Account theo id và name = JPA--------------------------------------------------------------------------------------------
    public Account findAccByIdAndUserName(int id, String userName) {
        return repositoryV2.getByIdAndUserName(id, userName);
    }

    @Override
    public Page<Account> getAllAccounts(Pageable pagable, AccountFillter fillTer) {
        Specification<Account> where = AccountSpecification.buidWhere(fillTer);
        return repositoryV2.findAll(where, pagable);
    }

    @Override
    //load username,và pass của account này thông qua userName để class WebSocurityConfiguration làm việc tiếp
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //load được thông tin của thằng này
        Account account = repositoryV2.findByUserName(userName);
        if (account == null) {
            throw new UsernameNotFoundException(userName);
        }
        //trả ra thông tin của account và pass vẫn mã hóa,như vậy spring đã có được thông tin của thằng này
        return new User(account.getUserName(),
                        account.getPassword(),
                        AuthorityUtils.createAuthorityList(account.getRole().name()));
    }



}
