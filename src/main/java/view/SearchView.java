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

import interface_adapter.detailPage.DetailPageController;
import interface_adapter.search.SearchController;
import interface_adapter.search.SearchState;
import interface_adapter.search.SearchViewModel;
import interface_adapter.search_by_ingredients.IngredientsController;

/**
 * View for search use cases.
 */
public class SearchView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "search";
    private final SearchViewModel searchViewModel;

    private final JTextField inputField = new JTextField(15);
    private final JPanel resultPanel = new JPanel();
    private final JLabel searchOutputField = new JLabel();

    private final JButton search;
    private final JButton enter;

    private SearchController searchController;
    private IngredientsController ingredientsController;

    private DetailPageController detailPageController;

    public SearchView(SearchViewModel searchViewModel) {
        this.searchViewModel = searchViewModel;
        this.searchViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Search Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel input = new LabelTextPanel(
                new JLabel("Search: "), inputField);

        final JPanel buttons = new JPanel();
        search = new JButton("search");
        enter = new JButton("enter ingredients");
        buttons.add(search);
        buttons.add(enter);

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
                            catch (IOException e) {
                                throw new RuntimeException(e);
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
            cocktailPanel.setLayout(new BoxLayout(cocktailPanel, BoxLayout.Y_AXIS));  // Vertical layout for better organization
            cocktailPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding to the panel

            // Set the background color for the cocktail panel to yellow
            cocktailPanel.setBackground(Color.YELLOW); // Yellow background for the cocktail panel

            // Create labels for cocktail details
            final JLabel nameLabel = new JLabel(cocktailName);
            final JLabel IDLabel = new JLabel(id.toString());

            // Set color for labels
            nameLabel.setForeground(Color.DARK_GRAY);   // Dark gray for name label
            IDLabel.setForeground(Color.DARK_GRAY);     // Dark gray for ID label

            // Add the cocktail image if available
            final JLabel imageLabel = new JLabel();
            if (image != null) {
                // Create an ImageIcon from the BufferedImage
                final ImageIcon imageIcon = new ImageIcon(image);
                imageLabel.setIcon(imageIcon); // Set the image in the label
            }
            else {
                imageLabel.setText("Image not available"); // Fallback text
            }

            // Add components to the cocktail panel
            cocktailPanel.add(nameLabel);
            cocktailPanel.add(IDLabel);
            cocktailPanel.add(imageLabel);

            // Set a fixed size or preferred size for the cocktail panel (useful for UI consistency)
            cocktailPanel.setPreferredSize(new Dimension(200, 200));

            // Add the cocktail panel to the main result panel
            resultPanel.add(cocktailPanel);

            JButton detailsButton = new JButton("Details");
            detailsButton.addActionListener(
                    new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            if (evt.getSource().equals(detailsButton)) {
                                detailPageController.execute(username,
                                        cocktailName,
                                        id,
                                        instruction,
                                        photolink,
                                        ingredient,
                                        image);
                            }
                        }
                    }
            );

            resultPanel.add(detailsButton);

            // Add space between each cocktail panel
            resultPanel.add(Box.createVerticalStrut(10)); // Add space between cocktails
        }

        // After all panels are added, update the layout to reflect changes
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));
        resultPanel.revalidate();
        resultPanel.repaint();
    }
}
