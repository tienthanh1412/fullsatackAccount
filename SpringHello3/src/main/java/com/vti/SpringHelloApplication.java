package com.vti;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//trong java khi chạy 1 project thì phải định nghĩa 1 hàm main() để nó khởi chạy đầu tiên và Spring boot cũng vậy @SpringBootApplication chỉ cho Spring Boot biết nơi nó khởi chạy lần đầu, để nó cài đặt mọi thứ.
//Cách thực hiện là thêm annotation @SpringBootApplication trên class chính và gọi SpringApplication.run(App.class, args); để chạy project.
@SpringBootApplication
public class SpringHelloApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringHelloApplication.class, args);
//Nhớ để thư mục này ra ngoài to nhất để nó scan tất cả các package bên trong	
//Chạy API có thể sảy ra nhiều lỗi Vd:trong file pom.xml chưa import đủ thư viện,click chuột lên Project ở thanh menu trên cùng và Clean
// xong chọn mục SpringHello to nhất kéo xuống run và chọn maven install để load lại thư viện 
//nếu không được thử đổi cổng localhost : vào file application.properties viết :server.port = 8090
//Resful API là các API chạy trên giao thức là http,https dùng để trao dổi dữ liệu có các loại GET(lấy dữ liệu về),POST(đẩy dữ liệu lên,tạo mới dữ liệu),PUT(thay đổi dữ liệu),DELETE(Xóa)
//JSON tổ chức dữ liệu theo dạng key value mỗi object sẽ nằm trong cặp ngoặc nhọn {} và 1 mảng thì được nằm trong ngoặc vuông []
//XML theo dạng các cặp thẻ,trong các cặp thẻ sẽ mang giá trị
	}
}
