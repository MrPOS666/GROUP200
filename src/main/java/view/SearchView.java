package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.search.SearchController;
import interface_adapter.search.SearchState;
import interface_adapter.search.SearchViewModel;
import interface_adapter.signup.SignupController;

public class SearchView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "search";
    private final SearchViewModel searchViewModel;

    private final JTextField inputField = new JTextField(15);
    private final JLabel searchOutputField = new JLabel();

    private final JButton search;
    private SearchController searchController;

    public SearchView(SearchViewModel searchViewModel) {
        this.searchViewModel = searchViewModel;
        this.searchViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Search Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel input = new LabelTextPanel(
                new JLabel("Search: "), inputField);

        final JPanel buttons = new JPanel();
        search = new JButton("search");
        buttons.add(search);

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
                    }
                }
        );
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(input);
        this.add(buttons);
        this.add(searchOutputField);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final SearchState state = (SearchState) evt.getNewValue();
        setFields(state);
        if (state.getSearchError() != null) {
            searchOutputField.setText(state.getSearchError());
        }
        else {
            searchOutputField.setText("success");
            searchresults(state);
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
        JFrame frame = new JFrame("result");
        String name = state.getCocktailName();
        String ingredients = state.getIngredients();
        int ID = state.getId();
        String recipe = state.getRecipe();
        String photoLink = state.getPhotoLink();

        JLabel nameLabel = new JLabel("Cocktail Name: " + name);
        JLabel IDLabel = new JLabel("ID: " + ID);
        JLabel photoLinkLabel = new JLabel("Photolink: " + photoLink);

        JTextArea ingredientsArea = new JTextArea(5, 20); // 5 rows, 20 columns
        JTextArea recipeArea = new JTextArea(5, 20);      // 5 rows, 20 columns

        // Set the ingredients and recipe text (allow wrapping)
        ingredientsArea.setText(ingredients);
        recipeArea.setText(recipe);

        // Make the JTextAreas non-editable since they are just for display
        ingredientsArea.setEditable(false);
        recipeArea.setEditable(false);

        // Set line wrapping for JTextAreas
        ingredientsArea.setWrapStyleWord(true);
        ingredientsArea.setLineWrap(true);
        recipeArea.setWrapStyleWord(true);
        recipeArea.setLineWrap(true);

        // Create JLabel for photo link or image
        JLabel photoLabel = new JLabel();
        ImageIcon photoIcon = new ImageIcon(photoLink);
        photoLabel.setIcon(photoIcon);  // Display the image

        // Create a JPanel and set its layout (BoxLayout or GridLayout)
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));  // Vertical layout for better organization

        // Add components to the panel
        panel.add(nameLabel);
        panel.add(IDLabel);
        panel.add(photoLinkLabel);
        panel.add(new JScrollPane(ingredientsArea));  // Add JTextArea inside a JScrollPane
        panel.add(new JScrollPane(recipeArea));      // Add JTextArea inside a JScrollPane
        panel.add(photoLabel);

        // Set up the frame
        frame.setSize(500, 300);  // Adjust frame size if necessary
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
        /**
        JLabel ingredientsLabel = new JLabel();
        JLabel recipeLabel = new JLabel();
        JLabel photoLabel = new JLabel();

        nameLabel.setText(name);
        IDLabel.setText(String.valueOf(ID));
        ingredientsLabel.setText(ingredients);
        recipeLabel.setText(recipe);
        photoLabel.setText(photoLink);

        JPanel panel = new JPanel();
        panel.add(nameLabel);
        panel.add(IDLabel);
        panel.add(ingredientsLabel);
        panel.add(recipeLabel);
        panel.add(photoLabel);

        frame.setSize(600, 300);
        frame.setContentPane(panel);
        frame.pack();
        ((JFrame) frame).setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    */
}
