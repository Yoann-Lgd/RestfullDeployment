package fr.yl.restfulldeployment.security;

import fr.yl.restfulldeployment.dao.DAOFactory;
import fr.yl.restfulldeployment.work.Compte;
import org.glassfish.soteria.identitystores.hash.Pbkdf2PasswordHashImpl;

public class AccountVerification {

    public static boolean login(User user) {
        Compte temp = DAOFactory.getCompteDAO().getByEmail(user.getLogin());
        if (temp != null) {
            Pbkdf2PasswordHashImpl pbkdf2PasswordHash = new Pbkdf2PasswordHashImpl();
            return pbkdf2PasswordHash.verify(user.getPassword().toCharArray(), temp.getPassword());
        }
        return false;
    }
}
