package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import interface_adapter.ViewManagerModel;
import interface_adapter.detailPage.DetailPageController;
import interface_adapter.myFavourite.MyFavouriteViewModel;
import interface_adapter.recommendation.RecommendationViewModel;
import interface_adapter.search.SearchViewModel;

/**
 * Showing detail of a cocktail.
 */
public class DetailView extends JPanel implements ActionListener {

    private final ViewManagerModel viewManagerModel;
    private final MyFavouriteViewModel MFviewModel;
    private final SearchViewModel searchViewModel;
    private final RecommendationViewModel recViewModel;

    private final DetailPageController detailPageController;

    private final JButton addMyFavourite;
    private final JButton backButton;

    public DetailView(ViewManagerModel viewManagerModel,
                      MyFavouriteViewModel myFavouriteViewModel,
                      SearchViewModel searchViewModel,
                      RecommendationViewModel recViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.MFviewModel = myFavouriteViewModel;
        this.searchViewModel = searchViewModel;
        this.recViewModel = recViewModel;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        /*
        Put the previous page parameter in the interactor
         */
        final JPanel buttons = new JPanel();
        addMyFavourite = new JButton("Add My Favourite");
        buttons.add(addMyFavourite);
        backButton = new JButton("Back");
        buttons.add(backButton);

        addMyFavourite.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        if (event.getSource().equals(addMyFavourite)) {
                            detailPageController.addMyFavourite();
                        }
                    }
                }
        );

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void setDetailPageController (DetailPageController detailPageController) {
        this.detailPageController = detailPageController;
    }
}
