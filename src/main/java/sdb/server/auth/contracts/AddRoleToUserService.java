package sdb.server.auth.contracts;

public interface AddRoleToUserService {

    void addRoleToUser(String username, String roleName);

}
