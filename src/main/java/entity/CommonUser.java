package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple implementation of the User interface.
 */
public class CommonUser implements User {

    private final String name;
    private final String password;
    private final List<Cocktail> myFavourite;

    public CommonUser(String name, String password) {
        this.name = name;
        this.password = password;
        this.myFavourite = new ArrayList<Cocktail>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public List<Cocktail> getMyFavourite() {
        return myFavourite;
    }

}
