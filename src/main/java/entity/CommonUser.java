package entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple implementation of the User interface.
 */
public class CommonUser implements User {

    private final String name;
    private final String password;
    private final List<Cocktail> myFavourite;

    private final HashMap<String, Integer> myInterests;

    public CommonUser(String name, String password) {
        this.name = name;
        this.password = password;
        this.myFavourite = new ArrayList<Cocktail>();
        this.myInterests = new HashMap<String, Integer>();
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

    @Override
    public HashMap<String, Integer> getMyInterests() {
        return myInterests;
    }
}
