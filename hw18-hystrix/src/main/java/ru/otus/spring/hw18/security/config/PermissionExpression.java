package ru.otus.spring.hw18.security.config;

public class PermissionExpression {

    public static final String USER_OR_MANAGER ="hasRole('" + RoleNames.USER + "') or hasRole('" + RoleNames.MANAGER + "')";
    public static final String MANAGER = "hasRole('" + RoleNames.MANAGER + "')";

}
