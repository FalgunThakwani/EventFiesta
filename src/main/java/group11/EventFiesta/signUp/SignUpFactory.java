package group11.EventFiesta.signUp;

import group11.EventFiesta.DBConnection.IDBPersistence;

public class SignUpFactory implements ISignUpFactory {

    @Override
    public ISignup createUserSignUp(IDBPersistence dbPersistence) {
        return new UserSignUp(dbPersistence);
    }

    @Override
    public ISignup createOrganizerSignUp(IDBPersistence dbPersistence) {
        return new OrganizerSignUp(dbPersistence);
    }

}
