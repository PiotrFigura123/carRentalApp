package pl.piotrFigura.backendcarrental.springsecutiry;

import com.google.common.collect.Sets;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static pl.piotrFigura.backendcarrental.springsecutiry.ApplicationUserPermission.*;

@Getter
@RequiredArgsConstructor
public enum ApplicationUserRole {
    USER(Sets.newHashSet(GET_CAR)),
    ADMIN(Sets.newHashSet(ADD_CITY, ADD_MARK, ADD_CAR, GET_CAR)),
    BRANCH(Sets.newHashSet(ADD_CAR, GET_CAR));

    private final Set<ApplicationUserPermission> permissionSet;

    public Set<SimpleGrantedAuthority> getGrantedAuthoritySet(){
        Set<SimpleGrantedAuthority> permissions = getPermissionSet().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
