package com.vti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vti.entites.Account;

import java.util.List;


@Repository
public interface IAccountRepositoryV2 extends JpaRepository<Account, Integer>, JpaSpecificationExecutor<Account> {


//vd(bên AccountService có phương thức tìm account theo username thì ta chỉ cần .frindByUserName(truyền tham số vào),lưu ý userName là thuộc tính giống trong class Account.Và userName viết hoa chữ cái lối liền đầu tiên.
//@Transactional khi thực hiện lệnh update hoặc delete

    @Transactional
    Account deleteByUserName(String userName);


    Account getByIdAndUserName(int id, String userName);


    Account findByUserName(String userName);

    @Query(value = "SELECT * from testing_system_2.account WHERE Username = :Username ", nativeQuery = true)
    Account getAccountByUserName(@Param("Username") String userName);

    @Query(value = "SELECT username from testing_system_2.account", nativeQuery = true)
    List<String> getUsernameByAccount();
}
