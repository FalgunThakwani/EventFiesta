package group11.EventFiesta.signUp;

import group11.EventFiesta.db.IDBPersistence;

public interface ISignUpFactory {
    public ISignup createUserSignUp(IDBPersistence dbPersistence);

    public ISignup createOrganizerSignUp(IDBPersistence dbPersistence);
}
