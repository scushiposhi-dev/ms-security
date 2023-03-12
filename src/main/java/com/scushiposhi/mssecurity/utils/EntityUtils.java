package com.scushiposhi.mssecurity.utils;

public class EntityUtils {
    public enum WineTypeEnum {
        WHITE,RED,ROSE,SPARKLING;
    }
    public enum RoleEnum{
        ROLE_ADMIN,
        ROLE_DEV,
        ROLE_DEVOPS,
        ROLE_TESTER,
        ROLE_CUSTOMER;
    }
    public enum WineAuthoritiesEnum{
        WINE_READ,
        WINE_CREATE,
        WINE_UPDATE,
        WINE_DELETE,
        USER_PAGE_READ,
        ADMIN_PAGE_READ;
    }
}
