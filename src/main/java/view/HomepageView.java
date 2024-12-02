package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.delete.DeleteController;
import interface_adapter.homepage.HomepageController;
import interface_adapter.homepage.HomepageState;
import interface_adapter.homepage.HomepageViewModel;
import interface_adapter.logout.LogoutController;

/**
 * The view for when the user is logged into the program.
 */
public class HomepageView extends JPanel implements ActionListener {
    private final String viewName = "homepage";

    private final HomepageViewModel homepageViewModel;

    private final JButton toSearch;
    private final JButton toMyFavourites;
    private final JButton toRecommends;
    private final JButton toChangePassword;
    private final JButton logout;
    private LogoutController logoutController;
    private HomepageController homepageController;
    private DeleteController deleteController;

    public HomepageView(HomepageViewModel homepageViewModel,
                        LoggedInViewModel loggedInViewModel) {
        this.homepageViewModel = homepageViewModel;

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
                            homepageController.switchToSearchView(homepageViewModel.getState().getUsername());
                        }
                    }
                });

        toMyFavourites.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(toMyFavourites)) {
                            deleteController.execute(new ArrayList<Integer>(),
                                    homepageViewModel.getState().getUsername());
                        }
                    }
                });

        toRecommends.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(toRecommends)) {
                            homepageController.switchToRecommendationView(homepageViewModel.getState().getUsername());
                        }
                    }
                });

        toChangePassword.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(toChangePassword)) {
                            homepageController.switchToChangePasswordView(homepageViewModel.getState().getUsername());
                        }
                    }
                });

        logout.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(logout)) {
                        // 1. get the state out of the loggedInViewModel. It contains the username.
                        // 2. Execute the logout Controller.
                        final HomepageState state = homepageViewModel.getState();
                        this.logoutController.execute(state.getUsername());
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

    public void setHomepageController(HomepageController homepageController) {
        this.homepageController = homepageController;
    }

    public void setDeleteController(DeleteController deleteController) {
        this.deleteController = deleteController;
    }
}
