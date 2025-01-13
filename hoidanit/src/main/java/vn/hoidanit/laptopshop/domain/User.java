package vn.hoidanit.laptopshop.domain;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity // Anotation để tạo table User
@Table(name = "users")
public class User {

    
    @Id // Set biến id là khoá
    @GeneratedValue(strategy = GenerationType.IDENTITY) // cho Id tự tăng
    private long id;

    // Validate Email
    @NotNull
    @Email(message = "Email không hợp lệ", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;

    // Validate Password
    @NotNull
    @Size(min = 2, message = "Password phải có tối thiểu 2 ký tự")
    //@StrongPassword(message = "Pass word phải có 8 ký tự")
    private String password;

    // Validate FullName
    @NotNull
    @Size(min = 3, message = "Fullname phải có tối thiểu 3 ký tự")
    private String fullName;

    // Validate Address
    //@NotNull
    //@Size(min = 5, message = "Address phải có tối thiểu 5 ký tự")
    private String address;

    // Validate Phone
    // @NotNull
    // @Pattern(regexp = "^0\\d{9}$", message = "Phone phải có 10 chữ số và bắt đầu bằng 0")
    private String phone;

    private String avatar;

    // User many -> to one -> role
    @ManyToOne
    @JoinColumn(name = "role_id") // JoinColumn để Psring không tạo bảng giữa 2 khoá này.name="role_id" để nó liên kết đến Id bên Role.java
    private Role role;

    // User - one => many Order -----------------------------------------------------
    @OneToMany(mappedBy = "user")
    List<Order> orders;
    //--------------------------------------------------------------------------------//

    @OneToOne(mappedBy = "user")
    private Cart cart;
    

    public long getId() {
        return id;
    }
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }
    public List<Order> getOrders() {
        return orders;
    }
    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getAvatar() {
        return avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public Cart getCart() {
        return cart;
    }
    public void setCart(Cart cart) {
        this.cart = cart;
    }
    @Override
    public String toString() {
        return "User [id=" + id + ", email=" + email + ", password=" + password + ", fullName=" + fullName
                + ", address=" + address + ", phone=" + phone + ", avatar=" + avatar + "]";
    }
    
   

    
}
