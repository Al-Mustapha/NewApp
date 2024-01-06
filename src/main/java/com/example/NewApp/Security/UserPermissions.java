package com.example.NewApp.Security;

public enum UserPermissions {

    CREATE_ARTICLE("article:add"),
    DELETE_ARTICLE("article:delete"),
    READ_ARTICLE("article:read"),
    DELETE_USER("user:delete"),
    CREATE_USER("user:create");

    private final String permissionSet;

    UserPermissions(String permissionSet) {
        this.permissionSet = permissionSet;
    }

    public String getPermissionSet(){
        return permissionSet;
    }
}
