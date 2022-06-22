package account;

/**
 * Classe principale AVANZATA gestione account.
 */
public class AdvancedAccountManagerImpl extends SimpleAccountManagerImpl implements AccountManager {
    
    @Override
    public boolean register(final String usr, final String psw, final String age) {
        if (!(usr.length() == 0 || psw.length() == 0 || age.length() == 0)) {
            if (checkExisting(usr)) {
                System.out.println("Account already existing");
                return false;
            }   
            if (!checkValidField(usr)) {
                System.out.println("Username too long");
                return false;
            }
            if (!checkValidField(psw)) {
                System.out.println("Password too long");
                return false;
            }
            if (!(age.chars().allMatch(Character::isDigit))) {
                System.out.println("Unable to complete registration, invalid age");
                return false;
            }
            if (Integer.parseInt(age) < 18) { 
                System.out.println("Unable to complete registration, "
                        + "you must be at least 18 years old to register on this site");
                return false;
            }
            if (age.length() > 2) { 
                System.out.println("Unable to complete registration, "
                        + "age invalid");
                return false;
            }
            if (super.register(usr, psw, age)) {
                System.out.println("Registration confirmed, file writing successful");
                return true;
            } else {
                System.out.println("Unable to complete registration, there was a problem writing the file");
                return false;
            }
        } else {
            System.out.println("Unable to complete registration, not all fields have been filled in");
            return false;
        }
    }
    
    @Override
    public boolean logger(final String usr, final String psw) {
        if (super.logger(usr, psw)) {
            //this.username = Utility.getField(Fields.USERNAME, usr);
            final String password = Utility.getField(Fields.PASSWORD, usr);
            final String balance = Utility.getField(Fields.BALANCE, usr);
            final String age = Utility.getField(Fields.AGE, usr);
            if (usr.equals(getUsername()) && psw.equals(password)) {
                System.out.println(("Welcome back: " + getUsername() + ". Your password is: " + password
                        + ", your balance is: " + balance + " and you're " + age + " years old"));
                return true;
            } else {
                System.out.println("Wrong password");
                resetUsername();
                return false;
            }
        } else {
            System.out.println("The account could not be found");
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
