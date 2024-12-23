package view;

import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.ViewManagerModel;
import interface_adapter.homepage.HomepageViewModel;
import interface_adapter.detailPage.DetailPageController;
import interface_adapter.search.SearchController;
import interface_adapter.search.SearchState;
import interface_adapter.search.SearchViewModel;
import interface_adapter.search_by_ingredients.IngredientsController;

/**
 * View for search use cases.
 */
public class SearchView extends JPanel implements ActionListener, PropertyChangeListener {
    public static final int TEN = 10;
    public static final int CPWIDTH = 1200;
    public static final int CPHEIGHT = 1200;

    private final String viewName = "search";
    private final SearchViewModel searchViewModel;
    private final HomepageViewModel homepageViewModel;
    private final ViewManagerModel viewManagerModel;

    private final JTextField inputField = new JTextField(15);
    private final JPanel resultPanel = new JPanel();
    private final JLabel searchOutputField = new JLabel();

    private final JButton search;
    private final JButton enter;
    private final JButton toHomepage;
    private SearchController searchController;
    private IngredientsController ingredientsController;
    private DetailPageController detailPageController;

    public SearchView(SearchViewModel searchViewModel,
                      HomepageViewModel homepageViewModel,
                      ViewManagerModel viewManagerModel) {
        this.searchViewModel = searchViewModel;
        this.searchViewModel.addPropertyChangeListener(this);

        this.homepageViewModel = homepageViewModel;
        this.viewManagerModel = viewManagerModel;

        final JLabel title = new JLabel("Search Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel input = new LabelTextPanel(
                new JLabel("Search: "), inputField);

        final JPanel buttons = new JPanel();
        search = new JButton("search by name or id");
        enter = new JButton("search by ingredients");
        toHomepage = new JButton("back to homepage");
        buttons.add(search);
        buttons.add(enter);
        buttons.add(toHomepage);

        inputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final SearchState currentState = searchViewModel.getState();
                currentState.setInput(inputField.getText());
                searchViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });

        toHomepage.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(toHomepage)) {
                            viewManagerModel.setState(homepageViewModel.getViewName());
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );

        search.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(search)) {
                            final SearchState currentState = searchViewModel.getState();
                            try {
                                searchController.execute(
                                        currentState.getInput()
                                );
                            }
                            catch (IOException exception) {
                                throw new RuntimeException(exception);
                            }
                        }
                    }
                }
        );

        enter.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(enter)) {
                            final SearchState currentState = searchViewModel.getState();
                            ingredientsController.execute(
                                    List.of(currentState.getInput().split(","))
                            );
                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(input);
        this.add(buttons);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        resultPanel.removeAll();
        this.remove(resultPanel);
        searchOutputField.removeAll();
        this.remove(searchOutputField);
        final SearchState state = (SearchState) evt.getNewValue();
        setFields(state);
        if (state.getSearchError() != null) {
            searchOutputField.setText(state.getSearchError());
            searchOutputField.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.add(searchOutputField);
            revalidate();
            repaint();
            state.setSearchError(null);
        }
        else {
            searchresults(state);
            this.add(resultPanel);
            revalidate();
            repaint();
        }
    }

    private void setFields(SearchState state) {
        inputField.setText(state.getInput());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Click " + e.getActionCommand());
    }

    public String getViewName() {
        return viewName;
    }

    public void setSearchController(SearchController controller) {
        this.searchController = controller;
    }

    public void setIngredientsController(IngredientsController controller) {
        this.ingredientsController = controller;
    }

    public void setDetailPageController(DetailPageController detailPageController) {
        this.detailPageController = detailPageController;
    }

    /**
     * Helper Function to show the search results as panels.
     * @param state current Search State
     */
    public void searchresults(SearchState state) {
        final String username = state.getUsername();

        final List<String> nameList = state.getCocktailNamesList();
        final List<Integer> ids = state.getIdList();
        final List<String> instructions = state.getRecipeList();
        final List<Map<String, String>> ingredients = state.getIngredientsList();
        final List<String> photoLinks = state.getPhotoLinkList();
        final List<BufferedImage> images = state.getImages();

        for (int i = 0; i < nameList.size(); i++) {

            final String cocktailName = nameList.get(i);
            final Integer id = ids.get(i);
            final String instruction = instructions.get(i);
            final Map<String, String> ingredient = ingredients.get(i);
            final String photolink = photoLinks.get(i);
            final BufferedImage image = images.get(i);

            // Create a new JPanel for each cocktail
            final JPanel cocktailPanel = new JPanel();
            cocktailPanel.setLayout(new BoxLayout(cocktailPanel, BoxLayout.Y_AXIS));
            cocktailPanel.setBorder(BorderFactory.createEmptyBorder(TEN, TEN, TEN, TEN));

            // Set the background color for the cocktail panel to yellow
            cocktailPanel.setBackground(Color.YELLOW);
            // Create labels for cocktail details
            final JLabel nameLabel = new JLabel(cocktailName);
            final JLabel idlabel = new JLabel(id.toString());

            // Set color for labels
            nameLabel.setForeground(Color.DARK_GRAY);
            idlabel.setForeground(Color.DARK_GRAY);

            // Add the cocktail image if available
            final JLabel imageLabel = new JLabel();
            if (image != null) {
                // Create an ImageIcon from the BufferedImage
                final ImageIcon imageIcon = new ImageIcon(image);
                imageLabel.setIcon(imageIcon);
            }
            else {
                imageLabel.setText("Image not available");
            }

            // Add components to the cocktail panel
            cocktailPanel.add(nameLabel);
            cocktailPanel.add(idlabel);
            cocktailPanel.add(imageLabel);

            // Set a fixed size or preferred size for the cocktail panel (useful for UI consistency)
            cocktailPanel.setPreferredSize(new Dimension(CPWIDTH, CPHEIGHT));

            // Add the cocktail panel to the main result panel
            resultPanel.add(cocktailPanel);
            List<Object> info = ingredientsController.getInfo(id);

            final JButton detailsButton = new JButton("Details");
            detailsButton.addActionListener(
                    new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            if (evt.getSource().equals(detailsButton)) {
                                detailPageController.execute(username,
                                                            cocktailName,
                                                            id,
                                                            (String) info.get(3),
                                                            photolink,
                                                            (Map<String, String>) info.get(2),
                                                            image,
                                                            searchViewModel.getViewName());
                            }
                        }
                    }
            );

            resultPanel.add(detailsButton);

            // Add space between each cocktail panel
            resultPanel.add(Box.createVerticalStrut(TEN));
        }

        // After all panels are added, update the layout to reflect changes
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));
        resultPanel.revalidate();
        resultPanel.repaint();
    }
}
