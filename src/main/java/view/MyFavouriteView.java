package view;

import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

import interface_adapter.delete.DeleteController;
import interface_adapter.select.SelectState;
import interface_adapter.select.SelectViewModel;

/**
 * View for Delete use case.
 */
public class MyFavouriteView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "MyFavourite";
    private final SelectViewModel selectViewModel;

    private final JButton delete;
    private final JButton select;
    private final JButton cancel;

    private final JPanel cocktailPanel = new JPanel();
    private final List<JCheckBox> cocktailCheckboxes = new ArrayList<>();

    private DeleteController deleteController;

    public MyFavouriteView(SelectViewModel selectViewModel) {

        this.selectViewModel = selectViewModel;
        this.selectViewModel.addPropertyChangeListner(this);

        final JLabel title = new JLabel("Delete my favourite");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel buttons = new JPanel();
        select = new JButton("search");
        buttons.add(select);
        delete = new JButton("delete");
        buttons.add(delete);
        cancel = new JButton("cancel");
        buttons.add(cancel);

        // Cocktail Panel
        cocktailPanel.setLayout(new BoxLayout(cocktailPanel, BoxLayout.Y_AXIS));

        select.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(select)) {
                    System.out.println("Select button clicked");
                    final List<Integer> selectedCocktails = getSelectedCocktails();
                    final SelectState currentState = selectViewModel.getState();
                    currentState.setSelectedCocktails(selectedCocktails);
                    selectViewModel.setState(currentState);
                }
            }
        });

        delete.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(delete)) {
                            System.out.println("Delete button clicked");
                            final List<Integer> selectedCocktails = selectViewModel.getState().getSelectedCocktails();
                            if (selectedCocktails.isEmpty()) {
                                System.out.println("The cocktails list is empty.");
                            }
                            else {
                                deleteController.execute(selectedCocktails); // Pass selected cocktails to DeleteController
                            }
                        }
                    }
                });

        cancel.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource() == cancel) {
                            // Terminates the application
                            System.exit(0);
                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(buttons);
    }

    /**
     * Dynamically updates the cocktail checkboxes based on the list of cocktails.
     *
     * @param cocktails List of cocktail names.
     */
    private void updateCocktailCheckboxes(List<Integer> cocktails) {
        cocktailPanel.removeAll(); // Clear existing checkboxes
        cocktailCheckboxes.clear(); // Clear the list of checkboxes

        for (Integer cocktail : cocktails) {
            final JCheckBox checkbox = new JCheckBox(cocktail); // Create a checkbox for each cocktail
            cocktailCheckboxes.add(checkbox); // Add to the list of checkboxes
            cocktailPanel.add(checkbox); // Add to the panel
        }

        // Add listeners to the checkboxes
        addCheckboxSelectionListener();

        cocktailPanel.revalidate(); // Refresh the panel
        cocktailPanel.repaint();
    }

    private void addCheckboxSelectionListener() {
        for (JCheckBox checkbox : cocktailCheckboxes) {
            checkbox.addActionListener(evt -> {
                final SelectState currentState = selectViewModel.getState();
                final List<Integer> selectedCocktails = getSelectedCocktails(); // Get selected cocktails
                currentState.setSelectedCocktails(selectedCocktails); // Update the state with selected cocktails
                selectViewModel.setState(currentState); // Trigger state change
            });
        }
    }

    /**
     * Retrieves the list of selected cocktails from the checkboxes.
     *
     * @return List of selected cocktail names.
     */
    private List<Integer> getSelectedCocktails() {
        final List<Integer> selectedCocktails = new ArrayList<>();
        for (JCheckBox checkbox : cocktailCheckboxes) {
            if (checkbox.isSelected()) {
                final int cocktailId = Integer.parseInt(checkbox.getText());
                selectedCocktails.add(cocktailId);
            }
        }
        return selectedCocktails;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final SelectState selectState = (SelectState) evt.getNewValue();
        // Dynamically update the cocktail checkboxes based on the state
        updateCocktailCheckboxes(selectState.getCocktailList());
    }

    public String getViewName() {
        return viewName;
    }

    public void setDeleteController(DeleteController deleteController) {
        this.deleteController = deleteController;
    }

    /**
     * Invoked when an action occurs.
     *
     * @param evt the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }
}
