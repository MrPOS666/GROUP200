package interface_adapter.result;

import interface_adapter.ViewModel;
import interface_adapter.change_password.LoggedInState;
import interface_adapter.search.SearchState;

import java.util.List;

public class ResultState {
    private String recipe;
    private List<String> ingredients;
    private String result;

    public ResultState(String recipe, List<String> ingredients, String result) {}

}
