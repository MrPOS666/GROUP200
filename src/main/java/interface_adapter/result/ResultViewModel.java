package interface_adapter.result;

import interface_adapter.ViewModel;
import interface_adapter.change_password.ResultState;
import interface_adapter.search.ResultState;

public class ResultViewModel extends ViewModel<ResultState> {

    public ResultViewModel(String viewName) {
        super("result");
        setState(new ResultState());
    }

}
