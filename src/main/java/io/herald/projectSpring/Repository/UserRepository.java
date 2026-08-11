package io.herald.projectSpring.Repository;

import io.herald.projectSpring.Model.UserTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserTable, Integer> {

    boolean existsByUsernameAndPassword(String un, String pwd);
    //existsBy function can be found already in our repo, but username and password cant be detected directly by existsBy function.
    //detected directly by existsBy function.
    //Hence, if our userTable has columns named "username" and "password" we can
    //suggest our repository to look for it, if the value exists or not.

    UserTable findByUsername(String username);

}
