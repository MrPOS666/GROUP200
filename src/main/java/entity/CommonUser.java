package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple implementation of the User interface.
 */
public class CommonUser implements User {

    private final String name;
    private final String password;
    private List<Cocktail> myFavourite = new ArrayList();

    public CommonUser(String name, String password) {
        this.name = name;
        this.password = password;
        this.myFavourite = new ArrayList<Cocktail>();
    }

    public CommonUser(String name, String password, List<Cocktail> myFavourite) {
        this.name = name;
        this.password = password;
        this.myFavourite = myFavourite;
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
