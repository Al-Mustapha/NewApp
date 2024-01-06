package com.example.NewApp.Security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.NewApp.Security.UserPermissions.*;

public enum UserRole {
    READER(Sets.newHashSet(READ_ARTICLE)),
    JOURNALIST(Sets.newHashSet(READ_ARTICLE, DELETE_ARTICLE, DELETE_USER, CREATE_USER, CREATE_ARTICLE));

    private final Set<UserPermissions> permissionsSet;

    UserRole(Set<UserPermissions> permissionsSet) {
        this.permissionsSet = permissionsSet;
    }

    public Set<UserPermissions> getPermissionsSet() {
        return permissionsSet;
    }

    public List<SimpleGrantedAuthority> getAuthorities(){
        var permissions = getPermissionsSet().stream()
                .map(permissionSet -> new SimpleGrantedAuthority(permissionSet.name()))
                .collect(Collectors.toList());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
