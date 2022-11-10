package group11.EventFiesta;

public interface ILogin {

    public Object login(String username, String pwd);

    public Boolean logout(Object object);

    //validate the security question and answer and give option to reset the password after validation
    public Boolean resetPassword(String emailId, String newPassword);


}
