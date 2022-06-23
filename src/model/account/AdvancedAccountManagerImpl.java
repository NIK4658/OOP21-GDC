package model.account;

/**
 * Classe principale AVANZATA gestione account.
 */
public class AdvancedAccountManagerImpl extends SimpleAccountManagerImpl implements AccountManager {
    
    @Override
    public boolean register(final String usr, final String psw, final String age) {
        if (!(usr.length() == 0 || psw.length() == 0 || age.length() == 0)) {
            if (checkExisting(usr)) {
                return false;
            }   
            if (!checkValidField(usr)) {
                return false;
            }
            if (!checkValidField(psw)) {
                return false;
            }
            if (!(age.chars().allMatch(Character::isDigit))) {
                return false;
            }
            if (Integer.parseInt(age) < 18) { 
                return false;
            }
            if (age.length() > 2) { 
                return false;
            }
            return super.register(usr, psw, age);
        } else {
            return false;
        }
    }
    
    @Override
    public boolean logger(final String usr, final String psw) {
        if (super.logger(usr, psw)) {
            final String password = ManagerUtility.getField(Fields.PASSWORD, usr);
            if (usr.equals(getUsername()) && psw.equals(password)) {
                return true;
            } else {
                resetUsername();
                return false;
            }
        } else {
            return false;
        }    
    }
    
    @Override
    public boolean changeUsr(final String usrnew) {
        if (checkValidField(usrnew)) {
            return super.changeUsr(usrnew);
        } else {
            return false;
        }
    }
    
    @Override
    public boolean changePass(final String psw) {
        if (checkValidField(psw)) {
            return super.changePass(psw);
        } else {
            return false;
        }
    }  
    
    private boolean checkValidField(final String field) {
        return field.length() <= 20;
    }
}
