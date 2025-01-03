package vn.hoidanit.laptopshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Dùng Query để lấy dữ liệu (dùng JpaRepositiry)

    public List<User> getAllUser(){
        return this.userRepository.findAll();
    }
    // Tìm người dùng có email
    public List<User> getAllUserByEmail(String email){
        return this.userRepository.findByEmail(email);
    }



    public User handleSaveUser(User user){
        User eric = this.userRepository.save(user);
        System.out.println(eric);
        return eric;
    }

    public User getUserById(long id) {
       return this.userRepository.findById(id);
    }
    //funtion xoá người dùng từ bên Userrepository
    public void deleteAUser(long id) {
        this.userRepository.deleteById(id);
     }
}
