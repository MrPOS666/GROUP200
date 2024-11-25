package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.LoggedInState;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.homepage.HomepageViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.logout.LogoutController;
import interface_adapter.myFavourite.MyFavouriteViewModel;
import interface_adapter.recommendation.RecommendationViewModel;
import interface_adapter.search.SearchViewModel;

/**
 * The view for when the user is logged into the program.
 */
public class HomepageView extends JPanel implements ActionListener {
    private final String viewName = "homepage";

    private final HomepageViewModel homepageViewModel;
    private final ViewManagerModel viewManagerModel;
    private final LoginViewModel loginViewModel;
    private final LoggedInViewModel loggedInViewModel;
    private final SearchViewModel searchViewModel;
    private final RecommendationViewModel recommendationViewModel;
    private final MyFavouriteViewModel myFavouriteViewModel;

    private final JButton toSearch;
    private final JButton toMyFavourites;
    private final JButton toRecommends;
    private final JButton toChangePassword;
    private final JButton logout;
    private LogoutController logoutController;

    public HomepageView(HomepageViewModel homepageViewModel,
                        ViewManagerModel viewManagerModel,
                        LoginViewModel loginViewModel,
                        LoggedInViewModel loggedInViewModel,
                        SearchViewModel searchViewModel,
                        RecommendationViewModel recommendationViewModel,
                        MyFavouriteViewModel myFavouriteViewModel) {
        this.homepageViewModel = homepageViewModel;
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
        this.loggedInViewModel = loggedInViewModel;
        this.searchViewModel = searchViewModel;
        this.recommendationViewModel = recommendationViewModel;
        this.myFavouriteViewModel = myFavouriteViewModel;

        final JLabel title = new JLabel("Homepage");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel buttons = new JPanel();
        toSearch = new JButton("Search");
        buttons.add(toSearch);
        toMyFavourites = new JButton("My Favourites");
        buttons.add(toMyFavourites);
        toRecommends = new JButton("Recommends");
        buttons.add(toRecommends);
        toChangePassword = new JButton("Change Password");
        buttons.add(toChangePassword);
        logout = new JButton("Logout");
        buttons.add(logout);

        toSearch.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(toSearch)) {
                            viewManagerModel.setState(searchViewModel.getViewName());
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                });

        toMyFavourites.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(toMyFavourites)) {
                            viewManagerModel.setState(myFavouriteViewModel.getViewName());
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                });

        toRecommends.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(toRecommends)) {
                            viewManagerModel.setState(recommendationViewModel.getViewName());
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                });

        toChangePassword.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(toChangePassword)) {
                            viewManagerModel.setState(loggedInViewModel.getViewName());
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                });

        logout.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(logout)) {
                        // 1. get the state out of the loggedInViewModel. It contains the username.
                        // 2. Execute the logout Controller.
                        final LoggedInState state = loggedInViewModel.getState();
                        logoutController.execute(state.getUsername());
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void setLogoutController(LogoutController logoutController) {
        this.logoutController = logoutController;
    }

    public String getViewName() {
        return viewName;
    }
}