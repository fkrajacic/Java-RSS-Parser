/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dal.Sql;

/**
 *
 * @author Fran
 */
public interface IUserManagement {
       public boolean checkUsernameAndPassword(String username,String password);
       public boolean checkAdminLogin(String username,String password);
       public boolean register(String username,String password);
}
