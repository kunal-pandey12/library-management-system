package library.management.books.Controller;

import library.management.books.Dto.UserDto;
import library.management.books.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/User")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public List<UserDto> createUser(@RequestBody List<UserDto> userDto){
        return userService.createUser(userDto);
    }
    @GetMapping("/getAll")
    public List<UserDto>getAllUser(){
        return userService.getAll();
    }
    @GetMapping("/{id}")
    public UserDto getById(@PathVariable Long id){
        return userService.getByUserId(id);
    }

    @PutMapping("/{id}")
    public UserDto getById(@PathVariable Long id, @RequestBody UserDto userDto ){
        return userService.updateUser(id, userDto);
    }
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return "Delete successfully user id ";
    }
}
