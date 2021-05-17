package main;

public class UserHolder
{

    private User user;

    private final static UserHolder INSTANCE = new UserHolder();

    private UserHolder() {}

    public static UserHolder getInstance()
    {
        return INSTANCE;
    }

    public User getUser() {
        return user;
    }
}