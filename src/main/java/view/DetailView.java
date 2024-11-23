package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import interface_adapter.ViewManagerModel;
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
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
