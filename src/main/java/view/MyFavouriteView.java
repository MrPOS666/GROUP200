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
import interface_adapter.myFavourite.MyFavouriteState;
import interface_adapter.myFavourite.MyFavouriteViewModel;

/**
 * View for Delete use case.
 */
public class MyFavouriteView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "MyFavourite";
    private final MyFavouriteViewModel myFavouriteViewModel;

    private final JButton delete;
    private final JButton select;
    private final JButton cancel;

    private final JPanel resultPanel = new JPanel();
    private final List<JCheckBox> cocktailCheckboxes = new ArrayList<>();

    private DeleteController deleteController;

    public MyFavouriteView(MyFavouriteViewModel myFavouriteViewModel) {

        this.myFavouriteViewModel = myFavouriteViewModel;
        this.myFavouriteViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Delete my favourite");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel buttons = new JPanel();
        select = new JButton("select");
        buttons.add(select);
        delete = new JButton("delete");
        delete.setEnabled(false);
        buttons.add(delete);
        cancel = new JButton("cancel");
        cancel.setEnabled(false);
        buttons.add(cancel);

        // Result Panel
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));

        select.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(select)) {
                    System.out.println("Select button clicked");
                    delete.setEnabled(true);
                    cancel.setEnabled(true);
                    select.setEnabled(false);
                    updateCocktailCheckboxes(myFavouriteViewModel.getState().getIdList());
                }
            }
        });

        delete.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(delete)) {
                            System.out.println("Delete button clicked");
                            final List<Integer> selectedCocktails = myFavouriteViewModel.getState().getSelectedCocktails();
                            final String username = myFavouriteViewModel.getState().getUsername();

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
        this.add(resultPanel);

        displayCocktails();
    }

    /**
     * Dynamically updates the cocktail checkboxes based on the list of cocktails.
     *
     * @param cocktails List of cocktail names.
     */
    private void updateCocktailCheckboxes(List<Integer> cocktails) {
        resultPanel.removeAll();
        cocktailCheckboxes.clear();

        for (Integer cocktail : cocktails) {
            final JCheckBox checkbox = new JCheckBox(String.valueOf(cocktail));
            cocktailCheckboxes.add(checkbox);
            resultPanel.add(checkbox);
        }

        addCheckboxSelectionListener();

        resultPanel.revalidate();
        resultPanel.repaint();
    }

    private void addCheckboxSelectionListener() {
        for (JCheckBox checkbox : cocktailCheckboxes) {
            checkbox.addActionListener(evt -> {
                final MyFavouriteState currentState = myFavouriteViewModel.getState();
                final List<Integer> selectedCocktails = getSelectedCocktails();
                currentState.setSelectedCocktails(selectedCocktails);
                myFavouriteViewModel.setState(currentState);
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
     * Displays cocktails without checkboxes.
     */
    private void displayCocktails() {
        resultPanel.removeAll();
        final List<String> names = myFavouriteViewModel.getState().getCocktailNamesList();
        final List<String> photos = myFavouriteViewModel.getState().getPhotoLinkList();

        for (int i = 0; i < names.size(); i++) {
            final String name = names.get(i);
            final String photo = photos.get(i);

            // Create a panel for each cocktail
            final JPanel cocktailPanel = new JPanel();
            cocktailPanel.setLayout(new BoxLayout(cocktailPanel, BoxLayout.Y_AXIS));
            cocktailPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            // Set the background color for the cocktail panel to yellow
            cocktailPanel.setBackground(Color.YELLOW); // Yellow background for the cocktail panel

            // Add cocktail details
            final JLabel nameLabel = new JLabel(name);
            final JLabel photoLabel = new JLabel(photo);

            cocktailPanel.add(nameLabel);
            cocktailPanel.add(photoLabel);

            resultPanel.add(cocktailPanel);
            resultPanel.add(Box.createVerticalStrut(10));

            // Set color for labels
            nameLabel.setForeground(Color.DARK_GRAY);   // Dark gray for name label
            photoLabel.setForeground(Color.DARK_GRAY); // Dark gray for photo link label

            // Create JLabel for photo link or image
            ImageIcon photoIcon = new ImageIcon(photo);
            photoLabel.setIcon(photoIcon);  // Display the image

            // Optionally, set a background color for the photo label (for a border around the photo)
            photoLabel.setBackground(Color.LIGHT_GRAY); // Set background color for image label
            photoLabel.setOpaque(true); // Make sure background color is visible

            // Add components to the cocktail panel
            cocktailPanel.add(nameLabel);
            cocktailPanel.add(photoLabel);

            // Set a fixed size or preferred size for the cocktail panel (useful for UI consistency)
            cocktailPanel.setPreferredSize(new Dimension(200, 200));

            // Add space between each cocktail panel
            resultPanel.add(Box.createVerticalStrut(10));
        }

        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));
        resultPanel.revalidate();
        resultPanel.repaint();
    }

    /**
     * Resets the view to hide the checkboxes and disable delete and cancel buttons.
     */
    private void resetView() {
        delete.setEnabled(false); // Disable delete button
        cancel.setEnabled(false); // Disable cancel button
        select.setEnabled(true); // Enable select button
        cocktailCheckboxes.clear();
        displayCocktails();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final MyFavouriteState myFavouriteState = (MyFavouriteState) evt.getNewValue();
        updateCocktailCheckboxes(myFavouriteState.getIdList());
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
