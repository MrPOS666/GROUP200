package app;

import interface_adapter.search.SearchController;
import interface_adapter.search.SearchViewModel;
import use_case.search.SearchDataAccessInterface;
import use_case.search.SearchInputBoundary;
import use_case.search.SearchInteractor;
import use_case.search.SearchOutputBoundary;
import view.SearchView;

import javax.swing.*;
import java.awt.*;

/**
 * The Main class of our application.
 */
public class Main {
    /**
     * Builds and runs the CA architecture of the application.
     * @param args unused arguments
     */
    public static void main(String[] args) {
        //TODO
        //final SearchInputBoundary searchInteractor = new SearchInteractor(searchDataAccessInterface,
               //searchOutputBoundary);

        //SearchController searchController = new SearchController(searchInteractor);

        SearchViewModel searchViewModel = new SearchViewModel("search");
        SearchView searchView = new SearchView(searchViewModel);
        //searchView.setSearchController(searchController);
        JPanel panel = new JPanel();
        panel.add(searchView, searchView.getViewName());

        Frame frame = new JFrame("Simple Swing App");
        frame.setSize(600, 300);
        ((JFrame) frame).setContentPane(panel);
        ((JFrame) frame).setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

        final AppBuilder appBuilder = new AppBuilder();

        final JFrame application = appBuilder
                                            .addLoginView()
                                            .addSignupView()
                                            .addLoggedInView()
                                            .addHomepageView()
                                            .addSignupUseCase()
                                            .addLoginUseCase()
                                            .addChangePasswordUseCase()
                                            .addLogoutUseCase()
                                            .build();

        //application.pack();
        //application.setVisible(true);
    }
}
