package group11.EventFiesta;

public interface ISignup {

    /// Validate User by checking if the provided email exists in the DB.
    /// If return true then user has to use another email address.
    public boolean validateUser(String email);

    /// Store information in db object specific
    public void storeInfo(Object object);
}
