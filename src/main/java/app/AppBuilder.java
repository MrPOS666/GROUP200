package app;

import java.awt.CardLayout;

import javax.swing.*;

import data_access.DBUserDataAccessObject;
import data_access.DBUserDataAccessObject2;
import data_access.InMemoryUserDataAccessObject;
import data_access.SearchByNameOrIDAccessObject;
import entity.CommonCocktailFactory;
import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.change_password.ChangePasswordPresenter;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.delete.DeleteController;
import interface_adapter.delete.DeletePresenter;
import interface_adapter.homepage.HomepageController;
import interface_adapter.homepage.HomepagePresenter;
import interface_adapter.homepage.HomepageViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.logout.LogoutController;
import interface_adapter.logout.LogoutPresenter;
import interface_adapter.myFavourite.MyFavouriteViewModel;
import interface_adapter.search.SearchController;
import interface_adapter.search.SearchPresenter;
import interface_adapter.recommendation.RecommendationViewModel;
import interface_adapter.search.SearchViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import okhttp3.OkHttpClient;
import use_case.change_password.ChangePasswordInputBoundary;
import use_case.change_password.ChangePasswordInteractor;
import use_case.change_password.ChangePasswordOutputBoundary;
import use_case.delete_favorite.DeleteInputBoundary;
import use_case.delete_favorite.DeleteInteractor;
import use_case.delete_favorite.DeleteOutputBoundary;
import use_case.homepage.HomepageInputBoundary;
import use_case.homepage.HomepageInteractor;
import use_case.homepage.HomepageOutputBoundary;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.logout.LogoutInputBoundary;
import use_case.logout.LogoutInteractor;
import use_case.logout.LogoutOutputBoundary;
import use_case.search.SearchDataAccessInterface;
import use_case.search.SearchInputBoundary;
import use_case.search.SearchInteractor;
import use_case.search.SearchOutputBoundary;
import use_case.search_by_ingredients.IngredientsInputBoundary;
import use_case.search_by_ingredients.IngredientsInteractor;
import use_case.search_by_ingredients.IngredientsOutputBoundary;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import view.*;

/**
 * The AppBuilder class is responsible for putting together the pieces of
 * our CA architecture; piece by piece.
 * <p/>
 * This is done by adding each View and then adding related Use Cases.
 */
// Checkstyle note: you can ignore the "Class Data Abstraction Coupling"
//                  and the "Class Fan-Out Complexity" issues for this lab; we encourage
//                  your team to think about ways to refactor the code to resolve these
//                  if your team decides to work with this as your starter code
//                  for your final project this term.
public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    // thought question: is the hard dependency below a problem?
    private final UserFactory userFactory = new CommonUserFactory();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    // thought question: is the hard dependency below a problem?
    private final InMemoryUserDataAccessObject userDataAccessObject = new InMemoryUserDataAccessObject();
    private final DBUserDataAccessObject2 deleteDataAccessObject = new DBUserDataAccessObject2();
    private final SearchByNameOrIDAccessObject searchDataAccessObject = new SearchByNameOrIDAccessObject(new CommonCocktailFactory());

    private SignupView signupView;
    private SignupViewModel signupViewModel;
    private LoginViewModel loginViewModel;
    private LoggedInViewModel loggedInViewModel;
    private HomepageViewModel homepageViewModel;
    private MyFavouriteViewModel myFavouriteViewModel;
    private RecommendationViewModel recommendationViewModel;
    private LoggedInView loggedInView;
    private LoginView loginView;
    private HomepageView homepageView;
    private SearchViewModel searchViewModel;
    private SearchView searchView;
    private MyFavouriteView myFavouriteView;

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    /**
     * Adds the Signup View to the application.
     * @return this builder
     */
    public AppBuilder addSignupView() {
        signupViewModel = new SignupViewModel();
        signupView = new SignupView(signupViewModel);
        cardPanel.add(signupView, signupView.getViewName());
        return this;
    }

    /**
     * Adds the Login View to the application.
     * @return this builder
     */
    public AppBuilder addLoginView() {
        loginViewModel = new LoginViewModel();
        loginView = new LoginView(loginViewModel);
        cardPanel.add(loginView, loginView.getViewName());
        return this;
    }

    /**
     * Adds the LoggedIn View to the application.
     * @return this builder
     */
    public AppBuilder addLoggedInView() {
        loggedInViewModel = new LoggedInViewModel();
        loggedInView = new LoggedInView(loggedInViewModel);
        cardPanel.add(loggedInView, loggedInView.getViewName());
        return this;
    }

    /**
     * Adds the Homepage View to the application.
     * @return this builder
     */
    public AppBuilder addHomepageView() {
        homepageViewModel = new HomepageViewModel();
        // This is a temporary solution for recommendationViewModel and myFavouriteViewModel and searchViewModel
        homepageView = new HomepageView(homepageViewModel, loggedInViewModel);
        cardPanel.add(homepageView, homepageView.getViewName());
        return this;
    }

    /**
     * Adds the MyFavourite View to the application.
     * @return this builder
     */
    public AppBuilder addMyFavouriteView() {
        myFavouriteViewModel = new MyFavouriteViewModel("MyFavourite");
        myFavouriteView = new MyFavouriteView(myFavouriteViewModel);
        cardPanel.add(myFavouriteView, myFavouriteView.getViewName());
        return this;
    }

    /**
     * Adds the Signup Use Case to the application.
     * @return this builder
     */
    public AppBuilder addSignupUseCase() {
        final SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel,
                signupViewModel, loginViewModel);
        final SignupInputBoundary userSignupInteractor = new SignupInteractor(
                userDataAccessObject, signupOutputBoundary, userFactory);

        final SignupController controller = new SignupController(userSignupInteractor);
        signupView.setSignupController(controller);
        return this;
    }

    /**
     * Adds the Login Use Case to the application.
     * @return this builder
     */
    public AppBuilder addLoginUseCase() {
        final LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel,
                homepageViewModel, loginViewModel);
        final LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject, loginOutputBoundary);

        final LoginController loginController = new LoginController(loginInteractor);
        loginView.setLoginController(loginController);
        return this;
    }

    /**
     * Adds the Change Password Use Case to the application.
     * @return this builder
     */
    public AppBuilder addChangePasswordUseCase() {
        final ChangePasswordOutputBoundary changePasswordOutputBoundary =
                new ChangePasswordPresenter(loggedInViewModel);

        final ChangePasswordInputBoundary changePasswordInteractor =
                new ChangePasswordInteractor(userDataAccessObject, changePasswordOutputBoundary, userFactory);

        final ChangePasswordController changePasswordController =
                new ChangePasswordController(changePasswordInteractor);
        loggedInView.setChangePasswordController(changePasswordController);
        return this;
    }

    /**
     * Adds the Logout Use Case to the application.
     * @return this builder
     */
    public AppBuilder addLogoutUseCase() {
        final LogoutOutputBoundary logoutOutputBoundary = new LogoutPresenter(viewManagerModel,
                loggedInViewModel, loginViewModel);

        final LogoutInputBoundary logoutInteractor =
                new LogoutInteractor(userDataAccessObject, logoutOutputBoundary);

        final LogoutController logoutController = new LogoutController(logoutInteractor);
        loggedInView.setLogoutController(logoutController);
        return this;
    }

    /**
     * Adds the Homepage Use Case to the application.
     * @return this builder
     */
    public AppBuilder addHomepageUseCase() {

        final HomepageOutputBoundary homepagePresenter = new HomepagePresenter(viewManagerModel,
                recommendationViewModel, myFavouriteViewModel, searchViewModel, loggedInViewModel);

        final HomepageInputBoundary homepageInteractor = new HomepageInteractor(homepagePresenter);
        final HomepageController homepageController = new HomepageController(homepageInteractor);
        homepageView.setHomepageController(homepageController);
        return this;
    }

    /**
     * Adds the Delete Use Case to the application.
     * @return this builder.
     */
    public AppBuilder addDeleteUseCase() {
        if (myFavouriteView == null || myFavouriteViewModel == null) {
            throw new IllegalStateException("MyFavouriteView and MyFavouriteViewModel must be initialized before adding Delete Use Case.");
        }

        final DeleteOutputBoundary deleteOutputBoundary = new DeletePresenter(myFavouriteViewModel, viewManagerModel, homepageViewModel);
        final DeleteInputBoundary deleteInteractor = new DeleteInteractor(deleteDataAccessObject, deleteOutputBoundary);

        final DeleteController deleteController = new DeleteController(deleteInteractor);
        myFavouriteView.setDeleteController(deleteController);
        return this;
    }

    /**
     * Creates the JFrame for the application and initially sets the SignupView to be displayed.
     * @return the application
     */
    public JFrame build() {
        final JFrame application = new JFrame("Login Example");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        final JScrollPane scrollPane = new JScrollPane(cardPanel);
        application.setContentPane(scrollPane);

        viewManagerModel.setState(signupView.getViewName());
        viewManagerModel.firePropertyChanged();

        return application;
    }

    /**
     * Adds the LoggedIn View to the application.
     * @return this builder
     */
    public AppBuilder addSearchView() {
        searchViewModel = new SearchViewModel("search");
        searchView = new SearchView(searchViewModel);
        cardPanel.add(searchView, searchView.getViewName());
        return this;
    }

    /**
     * Adds the Signup Use Case to the application.
     * @return this builder
     */
    public AppBuilder addSearchUseCase() {
        final SearchOutputBoundary searchOutputBoundary = new SearchPresenter(searchViewModel,
                viewManagerModel);
        final SearchInputBoundary searchInteractor = new SearchInteractor(searchDataAccessObject,
                searchOutputBoundary);

        final SearchController controller = new SearchController(searchInteractor);
        searchView.setSearchController(controller);
        return this;
    }

    /**
     * Creates the JFrame for the application and initially sets the SignupView to be displayed.
     * @return the application
     */
    public JFrame build_search() {
        final JFrame application = new JFrame("Search Example");
        application.add(cardPanel);
        application.setContentPane(cardPanel);
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        viewManagerModel.setState(searchView.getViewName());
        viewManagerModel.firePropertyChanged();

        return application;
    }

}
