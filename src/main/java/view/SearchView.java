package view;

import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.search.SearchController;
import interface_adapter.search.SearchState;
import interface_adapter.search.SearchViewModel;
import interface_adapter.search_by_ingredients.IngredientsController;
import interface_adapter.search_by_ingredients.IngredientsState;
import interface_adapter.search_by_ingredients.IngredientsViewModel;

import java.util.List;
import java.util.Map;


public class SearchView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "search";
    private final SearchViewModel searchViewModel;
    private final IngredientsViewModel ingredientsViewModel;

    private final JTextField inputField = new JTextField(15);
    private final JPanel resultPanel = new JPanel();
    private final JLabel searchOutputField = new JLabel();

    private final JButton search;
    private final JButton enter;
    private SearchController searchController;
    private IngredientsController ingredientsController;

    public SearchView(SearchViewModel searchViewModel, IngredientsViewModel ingredientsViewModel) {
        this.searchViewModel = searchViewModel;
        this.ingredientsViewModel = ingredientsViewModel;
        this.searchViewModel.addPropertyChangeListener(this);
        this.ingredientsViewModel.addPropertyChangeListener(this);

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
                            searchController.execute(
                                    currentState.getInput()
                            );
                        }
                        if (evt.getSource().equals(enter)) {
                            final IngredientsState currentState = ingredientsViewModel.getState();
                            ingredientsController.execute(
                                    currentState.getInput()
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

    public void searchresults(SearchState state) {

        final List<String> nameList = state.getCocktailNamesList();
        final List<Map<String, String>> ingredientsList = state.getIngredientsList();
        final List<Integer> ID = state.getIdList();
        final List<String> recipeList = state.getRecipeList();
        final List<String> photoLinkList = state.getPhotoLinkList();

        for (int i = 0; i < nameList.size(); i++) {
            String name = nameList.get(i);
            int id = ID.get(i);
            String recipe = recipeList.get(i);
            String photoLink = photoLinkList.get(i);
            String ingredients = state.getIngredientsToString(ingredientsList.get(i));

            // Create a new JPanel for each cocktail
            JPanel cocktailPanel = new JPanel();
            cocktailPanel.setLayout(new BoxLayout(cocktailPanel, BoxLayout.Y_AXIS));  // Vertical layout for better organization
            cocktailPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding to the panel

            // Set the background color for the cocktail panel to yellow
            cocktailPanel.setBackground(Color.YELLOW); // Yellow background for the cocktail panel

            // Create labels for cocktail details
            JLabel nameLabel = new JLabel(name);
            JLabel IDLabel = new JLabel("ID: " + id);
            JLabel photoLinkLabel = new JLabel(photoLink);

            // Set color for labels
            nameLabel.setForeground(Color.DARK_GRAY);   // Dark gray for name label
            IDLabel.setForeground(Color.DARK_GRAY);     // Dark gray for ID label
            photoLinkLabel.setForeground(Color.DARK_GRAY); // Dark gray for photo link label

            // Create JLabel for photo link or image
            JLabel photoLabel = new JLabel();
            ImageIcon photoIcon = new ImageIcon(photoLink);
            photoLabel.setIcon(photoIcon);  // Display the image

            // Optionally, set a background color for the photo label (for a border around the photo)
            photoLabel.setBackground(Color.LIGHT_GRAY); // Set background color for image label
            photoLabel.setOpaque(true); // Make sure background color is visible

            // Add components to the cocktail panel
            cocktailPanel.add(nameLabel);
            cocktailPanel.add(IDLabel);
            cocktailPanel.add(photoLinkLabel);
            cocktailPanel.add(photoLabel);

            // Set a fixed size or preferred size for the cocktail panel (useful for UI consistency)
            cocktailPanel.setPreferredSize(new Dimension(200, 200));

            // Add the cocktail panel to the main result panel
            resultPanel.add(cocktailPanel);

            // Add space between each cocktail panel
            resultPanel.add(Box.createVerticalStrut(10)); // Add space between cocktails
        }

        // After all panels are added, update the layout to reflect changes
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));
        resultPanel.revalidate();
        resultPanel.repaint();
    }
}
