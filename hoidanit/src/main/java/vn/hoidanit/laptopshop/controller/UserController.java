package vn.hoidanit.laptopshop.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.repository.UserRepository;
import vn.hoidanit.laptopshop.service.UserService;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String getHomePage(Model model) {

        // Lấy danh sách email duy@gmail.com của tất cả người dùng,hứng kết quả từ
        // userService
        List<User> arrUsers = this.userService.getAllUserByEmail("duy@gmail.com");
        System.out.println(arrUsers);

        model.addAttribute("eric", "test");
        model.addAttribute("hoidanit", "from controller with model");
        return "hello";
    }
    //Danh sách User
    @RequestMapping("/admin/user")
    public String getUserPage(Model model) {
        List<User> users = this.userService.getAllUser(); // Lấy dữ liệu
        model.addAttribute("users1", users);// truyền data qua view
        return "admin/user/table-user";
    }
    //View
    @RequestMapping("/admin/user/{id}") // Lấy id
    public String getUserDetailPage(Model model, @PathVariable long id) { // truyền biến động "id"
        User user = this.userService.getUserById(id); // lấy dữ lieu
        model.addAttribute("user", user); // ném qua view
        model.addAttribute("id", id);
        return "admin/user/show";
    }

    // Update
    @RequestMapping("/admin/user/update/{id}") // GET
    public String getUpdateUserPage(Model model,@PathVariable long id) {
        User currentUser = this.userService.getUserById(id);
        model.addAttribute("newUser", currentUser); // truyền biến currentUser để lấy data hiển thị lên form update
        return "admin/user/update";
    }
    //Xử lý action cho nút Update
    @PostMapping("/admin/user/update") // dùng PostMapping để không cần khia báo method = RequestMethod.POST
    public String getUpdateUser(Model model,@ModelAttribute("newUser") User hoidanit) { // lấy người dùng qua View
        User currentUser = this.userService.getUserById(hoidanit.getId()); // lấy dữ liệu từ hoidanit bên trên
        if (currentUser !=null) {
            currentUser.setAddress(hoidanit.getAddress());
            currentUser.setFullName(hoidanit.getFullName());
            currentUser.setPhone(hoidanit.getPhone());
            // Lưu những thay đổi trên vào database
            this.userService.handleSaveUser(currentUser);
        }
        return "redirect:/admin/user";
    }

    // Delete
    @GetMapping("/admin/user/delete/{id}")
    public String getDeleteUserPage(Model model,@PathVariable long id) {
        model.addAttribute("id", id);
        // User user = new User();
        // user.setId(id);
        model.addAttribute("newUser",new User() );
        return "admin/user/delete";
    }

    // Xử lý Confirm Delete
    @PostMapping("/admin/user/delete")
    public String postDeleteUser(Model model,@ModelAttribute("newUser")User eric) { // Lấy Id
        this.userService.deleteAUser(eric.getId()); // Gọi hàm xoá ngườu dùng bên UserService
        return "redirect:/admin/user";
    }
    
    // Create
    @RequestMapping("/admin/user/create") // GET
    public String getCreateUserPage(Model model) {
        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }
    // Quay lại Table User khi tạo xong User mới
    @RequestMapping(value = "/admin/user/create", method = RequestMethod.POST) // POST
    public String createUserPage(Model model, @ModelAttribute("newUser") User hoidanit) {
        this.userService.handleSaveUser(hoidanit);
        return "redirect:/admin/user"; // redirect : "chuyển hướng" link tới /admin/user để chạy khối code
                                       // @RequestMapping("/admin/user")
    }

}

// @RestController
// public class UserController {

// // DI : dependency ịnection
// private UserService userService;

// public UserController(UserService userService) {
// this.userService = userService;
// }

// @GetMapping("/")
// public String getHomePage() {
// return this.userService.handleHello();
// }
// }
