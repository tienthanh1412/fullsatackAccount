package com.vti.entites.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vti.entites.Account;
import com.vti.entites.Department;

import com.vti.entites.Position;
import com.vti.entites.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

//class chuyển đổi và trả dữ liệu lên phía client
//Nhằm mục đích không trả về object Account nữa mà cho nó trả về object AccountDTO giữ lại các phương thức get set 1 list không bị lặp đi lặp lại dẫn đến lỗi của bên class Account
//object AccountDTO sẽ nhận dữ liệu từ object Account và trả dữ liệu về object AccountDTO
@Data
@NoArgsConstructor
public class AccountDTO {
    //các thuộc tính mà ta muốn trả về và lợi ích đó là có thể thay đổi các  key(VD:trong objiect Account có đối tượng tên:Department thì ở đây ta đổi thên nó là dpName )
// viết đúng tên thuộc tính của class mà muốn lấy(khi sử dụng Mapper)
//	@JsonProperty(value = "account_Id") dùng để đổi tên key của đối tượng trên API
    private int id;
    //	@JsonProperty(value = "ulatroi")
    private String userName;
    private String fullName;
    private String email;
    //	(vd class Account có departments và bên phía class Department có thuộc tính name thì ta nối như bên dưới)viết hoa nửa tiếp theo của thuộc tính
    @JsonProperty(value = "dpName")
    private String departmentsName;
    @JsonProperty(value = "posName")
    private String positionPositionName;

    private String role;


    public AccountDTO(Account account) { // để lấy thông tin từ đối tượng account mà mình cần
        this.id = account.getId();
        this.userName = account.getUserName();
        this.fullName = account.getFullName();
        this.email = account.getEmail();
        this.departmentsName = account.getDepartments() != null ? account.getDepartments().getName() : "null";
        this.positionPositionName = account.getPosition() != null ? account.getPosition().getPositionName().name()
                : "null";
        this.role = account.getRole() != null ? account.getRole().name() : "null";
    }


}
