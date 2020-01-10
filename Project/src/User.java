public class User {
    String account;
    String password;
    User(String acc,String pwd){
        this.account = acc.replace("\"","");
        this.password = pwd.replace("\"","");
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }
    public boolean identify(User user){
        String acc = user.getAccount().replace("\"","");
        String paw = user.getPassword().replace("\"","");
        if(paw.compareTo(this.password)==0 && acc.compareTo(this.account)==0) {
                    System.out.println("Log in!");
                    return true;
                }
                else {
                    System.out.println("Wrong Password");
                    return false;//wrong password/account
                }
    }
    public  boolean isIntact(){
        if(account.equals("") || password.equals(""))
            return false;
        else
            return true;
    }
}
