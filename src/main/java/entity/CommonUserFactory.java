package entity;

import java.util.List;

/**
 * Factory for creating CommonUser objects.
 */
public class CommonUserFactory implements UserFactory {

    @Override
    public User create(String name, String password) {
        return new CommonUser(name, password);
    }

    @Override
    public User create(String name, String password, List<Cocktail> MyFavourite) {
        return new CommonUser(name, password, MyFavourite);
    }
}