package library.management.books.Service;
import library.management.books.Dto.UserDto;
import library.management.books.Entity.Role;
import library.management.books.Entity.UserEntity;
import library.management.books.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public List<UserDto> createUser(List<UserDto> dtoList){
        return dtoList.stream().map(dto->{

            UserEntity user = new UserEntity();
            user.setName(dto.getName());
            user.setEmail(dto.getEmail());

            user.setRole(Role.valueOf(dto.getRole().toUpperCase()));
            user.setPassword(dto.getPassword());

            UserEntity saved = userRepo.save(user);

            UserDto response=new UserDto();
            response.setId(saved.getId());
            response.setName(saved.getName());
            response.setEmail(saved.getEmail());
            response.setRole(saved.getRole().name());
            response.setPassword(saved.getPassword());

            return response;
        }).toList();
    }
    public List<UserDto>getAll(){
        List<UserEntity>users=userRepo.findAll();

        return users.stream().map(user -> {
            UserDto dto=new UserDto();
            dto.setId(user.getId());
            dto.setName(user.getName());
            dto.setEmail(user.getEmail());
            dto.setRole(user.getEmail());
            dto.setPassword(user.getPassword());
            return dto;
        }).toList();

        }

        public UserDto getByUserId(Long id){
        UserEntity userEntity=userRepo.findById(id)
                .orElseThrow(()-> new RuntimeException("User id not found"));

        UserDto dto=new UserDto();
        dto.setId(userEntity.getId());
        dto.setName(userEntity.getName());
        dto.setEmail(userEntity.getEmail());
        dto.setRole(userEntity.getEmail());
        dto.setPassword(userEntity.getPassword());
        return dto;
        }

    public UserDto updateUser(Long id,UserDto userDto){
        UserEntity userEntity=userRepo.findById(id)
                .orElseThrow(()->new RuntimeException("User Id not found"));

        userEntity.setName(userDto.getName());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setRole(Role.valueOf(userDto.getRole().toUpperCase()));
        userEntity.setPassword(userDto.getPassword());

        UserEntity updateUser=userRepo.save(userEntity);

        UserDto dto=new UserDto();
        dto.setId(updateUser.getId());
        dto.setName(updateUser.getName());
        dto.setEmail(updateUser.getEmail());
        dto.setRole(updateUser.getRole().name());
        dto.setPassword(updateUser.getPassword());

        return dto;
    }
    public void deleteUser(Long id){
        UserEntity userEntity=userRepo.findById(id)
                .orElseThrow(()->new RuntimeException("User id not found"));
        userRepo.delete(userEntity);
    }
}
