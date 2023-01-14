package com.vti.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.vti.services.AccountService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vti.entites.Account;
import com.vti.entites.ObjectErrorResponse;
import com.vti.entites.DTO.AccountDTO;
import com.vti.entites.Fillter.AccountFillter;
import com.vti.entites.Form.AccountCreateForm;
import com.vti.exception.CreateAccountException;
import com.vti.exception.UpdateAccountExeption;
import com.vti.services.IAccountService;

@RestController
@RequestMapping(value = "/v1/api")
@CrossOrigin(value = "*") // cho phép bên thứ 3 truy cập
@Validated
public class AccountController {

    @Autowired // hỗ trợ khởi tạo nhanh
    private IAccountService service;//vì đã đổi anotation của thằng này thành@Service nên nó sẽ cảnh báo,muốn hết thì gọi hẳn thằng AccountService ra vì giờ nó là 1 @Component

    @Autowired
    ModelMapper mapper;


    //Page-----------------------------------------------------------------------------------------------------------
    @GetMapping(value = "/accounts")
//    @PreAuthorize("hasAuthority('MANAGER')")
    public Page<AccountDTO> getAllAccounts(Pageable pagable, AccountFillter fillTer) {
        Page<Account> accountPage = service.getAllAccounts(pagable, fillTer);
// convert entites ---> DTO
        List<AccountDTO> dto = mapper.map(accountPage.getContent(), new TypeToken<List<AccountDTO>>() {
        }.getType());
        Page<AccountDTO> dtoPage = new PageImpl<>(dto, pagable, accountPage.getTotalElements());
        return dtoPage;

    }

    //GET BY ID-------------------------------------------------------------------------------------------------------------------------------------
    @GetMapping(value = "/accounts/{id}")
    @PreAuthorize("hasAuthority('MANAGER')")
    public Object getAccById(@PathVariable(value = "id") int id) {

        try {
            Account account = service.getAccById(id);
//sử dụng mapper
            AccountDTO accountDTO = mapper.map(account, AccountDTO.class);

            return new ResponseEntity<AccountDTO>(accountDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Tìm kiếm Account Thất Bại : " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    //CREATE-------------------------------------------------------------------------------------------------------------------------------------
    @PostMapping(value = "/create/accounts")
//    @PreAuthorize("hasAuthority('MANAGER')")
    public ResponseEntity<?> createAcc(@RequestBody @Valid AccountCreateForm form) {

        try {
            Account accountCreateAcc = service.createAcc(form);
            AccountDTO accountDTO = new AccountDTO(accountCreateAcc);
            return new ResponseEntity<AccountDTO>(accountDTO, HttpStatus.OK);

        } catch (CreateAccountException e) {
            return new ResponseEntity<Object>(e.getObjectError(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<Object>(new ObjectErrorResponse("Lỗi không xác định :" + e.getMessage(), 100),
                    HttpStatus.BAD_REQUEST);
        }

    }

    //UPDATE-------------------------------------------------------------
    @PutMapping(value = "/update/accounts/{id}")
//    @PreAuthorize("hasAuthority('MANAGER')")
    public ResponseEntity<?> updateAcc(@PathVariable(value = "id") int id, @RequestBody AccountCreateForm form) {
        System.out.println("hahahahaa");
        try {
            Account account = service.updateAcc(id, form);
            AccountDTO accountDTO = new AccountDTO(account);
            return new ResponseEntity<AccountDTO>(accountDTO, HttpStatus.OK);//tắt đi để xử lý cho fron khin sửa là load lại trang
        } catch (UpdateAccountExeption e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    //DELETE--------------------------------------------------------------------------------------------------------------------------------------
    @DeleteMapping(value = "/accounts/delete/{id}")
//    @PreAuthorize("hasAuthority('MANAGER')")//phân quyền đường dẫn cho Manager truy cập
    public ResponseEntity<?> deleteAccById(@PathVariable(value = "id") int id) {
        System.out.println("hahahahaa");
        try {
            service.deleteAccById(id);
            AccountDTO accountDTO = new AccountDTO();//tạo ra để trả ra giữ liệu cho fronend để nó load lại trang khi xóa
      //      return new ResponseEntity<String>("Delete Account Thành Công", HttpStatus.OK);//khi xóa sẽ chỉ in ra chuỗi string như vậy ở frontend xóa được và không trả ra cái gì nữa nên xóa thì được nhưng k load lại được trang:hướng giải quyết là cho trả về object
            return new ResponseEntity<AccountDTO>(accountDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Delete Account Thất Bại : " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

//-----------------------------------------------------------------------------------------------------------------------

    //DELETE BY NAME-----------------------------------------------------------------------------------------------------------------------
    @DeleteMapping(value = "/account/{name}")
    @PreAuthorize("hasAuthority('MANAGER')")
    public ResponseEntity<?> deleteAccByName(@PathVariable(value = "name") String userName) {
        try {
            service.deleteAccByName(userName);
            return new ResponseEntity<String>("Delete Account Thành Công", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Delete Account Thất Bại : " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //GET BY NAME---------------------------------------------------------------------------------------------------------------
    @GetMapping(value = "/account/{name}")
    public Object getAccByName(@RequestBody @PathVariable(value = "name") String userName) {

        /**
         * Cách chuyển đổi 1 đối tượng Account thành 1 đối tượng AccountDTO chuyển đổi
         */
        try {
            Account account = service.getAccByName(userName);

            AccountDTO accountDTO = new AccountDTO(account);

            return new ResponseEntity<AccountDTO>(accountDTO, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<String>("Tìm kiếm Account Thất Bại : " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }


}
