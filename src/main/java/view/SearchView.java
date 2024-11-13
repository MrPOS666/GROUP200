package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.search.SearchController;
import interface_adapter.search.SearchState;
import interface_adapter.search.SearchViewModel;
import interface_adapter.signup.SignupController;

public class SearchView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "search";
    private final SearchViewModel searchViewModel;

    private final JTextField cocktailInputField = new JTextField(15);
    private final JTextField idInputField = new JTextField(15);
    private final JLabel searchErrorField = new JLabel();

    private final JButton search;
    private final JButton cancel;
    private SearchController searchController;

    public SearchView(SearchViewModel searchViewModel) {
        this.searchViewModel = searchViewModel;
        this.searchViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Search Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel cocktailName = new LabelTextPanel(
                new JLabel("Search By Cocktail Name: "), cocktailInputField);
        final LabelTextPanel cocktailID = new LabelTextPanel(
                new JLabel("Search By ID: "), idInputField);

        final JPanel buttons = new JPanel();
        search = new JButton("search");
        buttons.add(search);
        cancel = new JButton("cancel");
        buttons.add(cancel);

        cocktailInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final SearchState currentState = searchViewModel.getState();
                if (cocktailInputField.getText() != null) {
                    currentState.setSearchByName(true);
                    currentState.setCocktailName(cocktailInputField.getText());
                }
                if (idInputField.getText() != null) {
                    currentState.setSearchByID(true);
                    currentState.setId(idInputField.getText());
                }
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
                                    currentState.getCocktailName(),
                                    currentState.isSearchByName(),
                                    currentState.isSearchByID(),
                                    currentState.getInput()
                            );
                        }
                    }
                }
        );
        cancel.addActionListener(this);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(cocktailName);
        this.add(cocktailID);
        this.add(searchErrorField);
        this.add(buttons);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final SearchState state = (SearchState) evt.getNewValue();
        setFields(state);
        searchErrorField.setText(state.getSearchError());
    }

    private void setFields(SearchState state) {
        cocktailInputField.setText(state.getCocktailName());
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

}
