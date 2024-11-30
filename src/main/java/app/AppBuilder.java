package app;

import java.awt.CardLayout;
import java.util.Map;

import javax.swing.*;

import data_access.InMemoryUserDataAccessObject;
import data_access.SearchByNameOrIDAccessObject;
import entity.*;
import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.change_password.ChangePasswordPresenter;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.homepage.HomepageViewModel;
import interface_adapter.myFavourite.MyFavouriteViewModel;
import interface_adapter.recommendation.RecommendationViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.logout.LogoutController;
import interface_adapter.logout.LogoutPresenter;
import interface_adapter.search.SearchController;
import interface_adapter.search.SearchPresenter;
import interface_adapter.search.SearchViewModel;
import interface_adapter.search_by_ingredients.IngredientsController;
import interface_adapter.search_by_ingredients.IngredientsPresenter;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import use_case.change_password.ChangePasswordInputBoundary;
import use_case.change_password.ChangePasswordInteractor;
import use_case.change_password.ChangePasswordOutputBoundary;
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
    private final SearchByNameOrIDAccessObject searchDataAccessObject = new SearchByNameOrIDAccessObject(new CommonCocktailFactory());

    private SignupView signupView;
    private SignupViewModel signupViewModel;
    private LoginViewModel loginViewModel;
    private LoggedInViewModel loggedInViewModel;
    private LoggedInView loggedInView;
    private LoginView loginView;

    private SearchViewModel searchViewModel = new SearchViewModel("search");
    private SearchView searchView;

    private HomepageViewModel homepageViewModel;
    private HomepageView homepageView;

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
        // searchViewModel = new SearchViewModel("search");
        searchView = new SearchView(searchViewModel, homepageViewModel, viewManagerModel);
        cardPanel.add(searchView, searchView.getViewName());

        return this;
    }

    /**
     * Adds the Search Use Case to the application.
     * @return this builder
     */
    public AppBuilder addSearchUseCase() {
        final SearchOutputBoundary searchOutputBoundary = new SearchPresenter(searchViewModel,
                viewManagerModel);
        final SearchInputBoundary searchInteractor = new SearchInteractor(searchDataAccessObject,
                searchOutputBoundary);

        final SearchController searchController = new SearchController(searchInteractor);
        searchView.setSearchController(searchController);
        return this;
    }

    /**
     * Adds the Ingredients Use Case to the application.
     * @return this builder
     */
    public AppBuilder addIngredientsUseCase() {
        final IngredientsOutputBoundary ingredientsOutputBoundary = new IngredientsPresenter(searchViewModel,
                viewManagerModel);
        final IngredientsInputBoundary ingredientsInteractor = new IngredientsInteractor(searchDataAccessObject,
                ingredientsOutputBoundary);

        final IngredientsController ingredientsController = new IngredientsController(ingredientsInteractor);
        searchView.setIngredientsController(ingredientsController);
        return this;
    }

    /**
     * Adds the Homepage View to the application.
     * @return this builder
     */
    public AppBuilder addHomepageView() {
        homepageViewModel = new HomepageViewModel();
        // This is a temporary solution for recommendationViewModel and myFavouriteViewModel and searchViewModel
        final RecommendationViewModel recommendationViewModel = new RecommendationViewModel();
        final MyFavouriteViewModel myFavouriteViewModel = new MyFavouriteViewModel();
        homepageView = new HomepageView(homepageViewModel,
                viewManagerModel,
                loginViewModel,
                loggedInViewModel,
                searchViewModel,
                recommendationViewModel,
                myFavouriteViewModel);
        cardPanel.add(homepageView, homepageView.getViewName());
        return this;
    }

    /**
     * Creates the JFrame for the application and initially sets the SearchView to be displayed.
     * @return the application
     */
    public JFrame build_search() {
        final JFrame application = new JFrame("Search");
        final JScrollPane scrollPane = new JScrollPane(cardPanel);
        application.add(scrollPane);
        application.setContentPane(scrollPane);
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        viewManagerModel.setState(searchView.getViewName());
        viewManagerModel.firePropertyChanged();

        return application;
    }

}
