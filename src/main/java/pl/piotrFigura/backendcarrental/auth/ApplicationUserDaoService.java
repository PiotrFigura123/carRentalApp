package pl.piotrFigura.backendcarrental.auth;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

import static pl.piotrFigura.backendcarrental.springsecutiry.ApplicationUserRole.*;

@Repository("users")
@RequiredArgsConstructor
public class ApplicationUserDaoService implements ApplicationUserDao {
    private final PasswordEncoder passwordEncoder;
    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUsers()
                .stream()
                .filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }

    private List<ApplicationUser> getApplicationUsers(){
        List<ApplicationUser> applicationUsers = Lists.newArrayList(
             new ApplicationUser("anna",
                     passwordEncoder.encode("password"),
                     USER.getGrantedAuthoritySet(),
                     true,
                     true,
                     true,
                     true
                     ),
                new ApplicationUser("linda",
                        passwordEncoder.encode("password"),
                        ADMIN.getGrantedAuthoritySet(),
                        true,
                        true,
                        true,
                        true
                ),
                new ApplicationUser("tom",
                        passwordEncoder.encode("password"),
                        BRANCH.getGrantedAuthoritySet(),
                        true,
                        true,
                        true,
                        true
                )
        );
        return applicationUsers;
    }
}
