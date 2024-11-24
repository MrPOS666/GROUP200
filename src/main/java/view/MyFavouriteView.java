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
        this.selectViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Delete my favourite");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel buttons = new JPanel();
        select = new JButton("search");
        buttons.add(select);
        delete = new JButton("delete");
        delete.setEnabled(false);
        buttons.add(delete);
        cancel = new JButton("cancel");
        cancel.setEnabled(false);
        buttons.add(cancel);

        // Cocktail Panel
        cocktailPanel.setLayout(new BoxLayout(cocktailPanel, BoxLayout.Y_AXIS));
        cocktailPanel.setVisible(false);

        select.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(select)) {
                    System.out.println("Select button clicked");
                    cocktailPanel.setVisible(true);
                    delete.setEnabled(true);
                    cancel.setEnabled(true);
                    select.setEnabled(false);
                    updateCocktailCheckboxes(selectViewModel.getState().getCocktailList());
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
                            final String username = selectViewModel.getState().getUsername();

                            if (selectedCocktails.isEmpty()) {
                                System.out.println("The cocktails list is empty.");
                            }
                            else {
                                deleteController.execute(selectedCocktails, username);
                                resetView();
                            }
                        }
                    }
                });

        cancel.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource() == cancel) {
                            resetView();
                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(buttons);
        this.add(cocktailPanel);
    }

    /**
     * Dynamically updates the cocktail checkboxes based on the list of cocktails.
     *
     * @param cocktails List of cocktail names.
     */
    private void updateCocktailCheckboxes(List<Integer> cocktails) {
        cocktailPanel.removeAll();
        cocktailCheckboxes.clear();

        for (Integer cocktail : cocktails) {
            final JCheckBox checkbox = new JCheckBox(String.valueOf(cocktail));
            cocktailCheckboxes.add(checkbox);
            cocktailPanel.add(checkbox);
        }

        addCheckboxSelectionListener();

        cocktailPanel.revalidate(); // Refresh the panel
        cocktailPanel.repaint();
    }

    private void addCheckboxSelectionListener() {
        for (JCheckBox checkbox : cocktailCheckboxes) {
            checkbox.addActionListener(evt -> {
                final SelectState currentState = selectViewModel.getState();
                final List<Integer> selectedCocktails = getSelectedCocktails();
                currentState.setSelectedCocktails(selectedCocktails);
                selectViewModel.setState(currentState);
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

    /**
     * Resets the view to hide the checkboxes and disable delete and cancel buttons.
     */
    private void resetView() {
        cocktailPanel.setVisible(false); // Hide the checkbox panel
        delete.setEnabled(false); // Disable delete button
        cancel.setEnabled(false); // Disable cancel button
        select.setEnabled(true); // Enable select button
        cocktailCheckboxes.clear();
        cocktailPanel.removeAll();
        cocktailPanel.revalidate();
        cocktailPanel.repaint();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final SelectState selectState = (SelectState) evt.getNewValue();
        if (cocktailPanel.isVisible()) {
            updateCocktailCheckboxes(selectState.getCocktailList());
        }
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
