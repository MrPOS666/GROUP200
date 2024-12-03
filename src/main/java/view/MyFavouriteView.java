package view;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;

import javax.swing.*;

import interface_adapter.delete.DeleteController;
import interface_adapter.detailPage.DetailPageController;
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
    private final JButton back;

    private final JPanel resultPanel = new JPanel();
    private final List<JCheckBox> cocktailCheckboxes = new ArrayList<>();
    private final JLabel deleteOutputField = new JLabel();

    private DeleteController deleteController;
    private DetailPageController detailPageController;

    private List<Integer> selectedCocktailIds = new ArrayList<>();

    public MyFavouriteView(MyFavouriteViewModel myFavouriteViewModel) {

        this.myFavouriteViewModel = myFavouriteViewModel;
        this.myFavouriteViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Delete my favourite");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel buttons = new JPanel();
        select = new JButton("select");
        buttons.add(select);
        select.setEnabled(true);
        delete = new JButton("delete");
        delete.setEnabled(false);
        buttons.add(delete);
        cancel = new JButton("cancel");
        cancel.setEnabled(false);
        buttons.add(cancel);
        back = new JButton("back");
        back.setEnabled(true);
        buttons.add(back);

        // Result Panel
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));

        displayCocktails();

        select.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(select)) {
                    System.out.println("Select button clicked");
                    delete.setEnabled(true);
                    back.setEnabled(true);
                    select.setEnabled(false);
                    cancel.setEnabled(true);
                    updateCocktailCheckboxes(myFavouriteViewModel.getState().getIdList());

                }
            }
        });

        delete.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(delete)) {
                            updateCocktailCheckboxes(myFavouriteViewModel.getState().getIdList());
                            System.out.println("Delete button clicked");
                            final List<Integer> selectedCocktails = getSelectedCocktails();
                            final String username = myFavouriteViewModel.getState().getUsername();

                            if (selectedCocktails == null || selectedCocktails.isEmpty()) {
                                System.out.println("The cocktails list is empty.");
                                deleteOutputField.setText("Cocktails list is empty.");
                            }
                            else {
                                deleteController.execute(selectedCocktails, username);
                                displayCocktails();
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

        back.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource() == back) {
                            deleteController.switchToHomepageView();
                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(buttons);
        this.add(deleteOutputField);
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

        final List<String> names = myFavouriteViewModel.getState().getCocktailNamesList();
        final List<Integer> ids = myFavouriteViewModel.getState().getIdList();
        final List<String> instructions = myFavouriteViewModel.getState().getInstructionList();
        final List<Map<String,String>> ingredients = myFavouriteViewModel.getState().getIngredientsList();
        final List<String> photos = myFavouriteViewModel.getState().getPhotoLinkList();
        final List<BufferedImage> images = myFavouriteViewModel.getState().getImageList();


        for (Integer cocktail : cocktails) {
            final JCheckBox checkbox = new JCheckBox(String.valueOf(cocktail));

            Integer cocktailIndex = ids.indexOf(cocktail);
            final String name = names.get(cocktailIndex);
            final String photo = photos.get(cocktailIndex);
            final String instruction = instructions.get(cocktailIndex);
            final Integer id = ids.get(cocktailIndex);
            final Map<String, String> ingredient = ingredients.get(cocktailIndex);
            final BufferedImage image = images.get(cocktailIndex);

            // Create a panel for each cocktail
            final JPanel cocktailPanel = new JPanel();
            cocktailPanel.setLayout(new BoxLayout(cocktailPanel, BoxLayout.Y_AXIS));
            cocktailPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            // Set the background color for the cocktail panel to yellow
            cocktailPanel.setBackground(Color.YELLOW); // Yellow background for the cocktail panel

            // Add cocktail details
            final JLabel nameLabel = new JLabel(name);

            cocktailPanel.add(nameLabel);

            resultPanel.add(cocktailPanel);
            resultPanel.add(Box.createVerticalStrut(10));

            // Set color for labels
            nameLabel.setForeground(Color.DARK_GRAY);   // Dark gray for name label

            // Add the cocktail image if available
            final JLabel imageLabel = new JLabel();
            if (images != null && cocktailIndex < images.size() && images.get(cocktailIndex) != null) {
                // Create an ImageIcon from the BufferedImage
                final ImageIcon imageIcon = new ImageIcon(images.get(cocktailIndex));
                imageLabel.setIcon(imageIcon); // Set the image in the label
            }
            else {
                imageLabel.setText("Image not available"); // Fallback text
            }

            // Optionally, set a background color for the photo label (for a border around the photo

            // Add components to the cocktail panel
            cocktailPanel.add(nameLabel);

            cocktailPanel.add(imageLabel);

            cocktailPanel.add(checkbox);

            // add checkbox to checkbox list
            cocktailCheckboxes.add(checkbox);

            // Set a fixed size or preferred size for the cocktail panel (useful for UI consistency)
            cocktailPanel.setPreferredSize(new Dimension(1200, 1200));

            // Add space between each cocktail panel
            resultPanel.add(Box.createVerticalStrut(10));
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
        for (JCheckBox checkbox : cocktailCheckboxes) {
            if (checkbox.isSelected()) {
                final int cocktailId = Integer.parseInt(checkbox.getText());
                selectedCocktailIds.add(cocktailId);
            }
        }
        return selectedCocktailIds;
    }

    public void setDetailPageController(final DetailPageController detailPageController) {
        this.detailPageController = detailPageController;
    }

    /**
     * Displays cocktails without checkboxes.
     */
    private void displayCocktails() {
        resultPanel.removeAll();
        final String username = myFavouriteViewModel.getState().getUsername();

        final List<String> names = myFavouriteViewModel.getState().getCocktailNamesList();
        final List<Integer> ids = myFavouriteViewModel.getState().getIdList();
        final List<String> instructions = myFavouriteViewModel.getState().getInstructionList();
        final List<Map<String,String>> ingredients = myFavouriteViewModel.getState().getIngredientsList();
        final List<String> photos = myFavouriteViewModel.getState().getPhotoLinkList();
        final List<BufferedImage> images = myFavouriteViewModel.getState().getImageList();

        for (int i = 0; i < names.size(); i++) {
            final String name = names.get(i);
            final String photo = photos.get(i);
            final String instruction = instructions.get(i);
            final Integer id = ids.get(i);
            final Map<String, String> ingredient = ingredients.get(i);
            final BufferedImage image = images.get(i);

            // Create a panel for each cocktail
            final JPanel cocktailPanel = new JPanel();
            cocktailPanel.setLayout(new BoxLayout(cocktailPanel, BoxLayout.Y_AXIS));
            cocktailPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            // Set the background color for the cocktail panel to yellow
            cocktailPanel.setBackground(Color.YELLOW); // Yellow background for the cocktail panel

            // Add cocktail details
            final JLabel nameLabel = new JLabel(name);

            cocktailPanel.add(nameLabel);

            resultPanel.add(cocktailPanel);
            resultPanel.add(Box.createVerticalStrut(10));

            // Set color for labels
            nameLabel.setForeground(Color.DARK_GRAY);   // Dark gray for name label

            // Add the cocktail image if available
            final JLabel imageLabel = new JLabel();
            if (images != null && i < images.size() && images.get(i) != null) {
                // Create an ImageIcon from the BufferedImage
                final ImageIcon imageIcon = new ImageIcon(images.get(i));
                imageLabel.setIcon(imageIcon); // Set the image in the label
            }
            else {
                imageLabel.setText("Image not available"); // Fallback text
            }

            // Optionally, set a background color for the photo label (for a border around the photo

            // Add components to the cocktail panel
            cocktailPanel.add(nameLabel);

            cocktailPanel.add(imageLabel);

            // Set a fixed size or preferred size for the cocktail panel (useful for UI consistency)
            cocktailPanel.setPreferredSize(new Dimension(1200, 1200));

            // Add space between each cocktail panel
            resultPanel.add(Box.createVerticalStrut(10));

            JButton detailsButton = new JButton("Details");
            detailsButton.addActionListener(
                    new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            if (evt.getSource().equals(detailsButton)) {
                                detailPageController.execute(username,
                                        name,
                                        id,
                                        instruction,
                                        photo,
                                        ingredient,
                                        image,
                                        myFavouriteViewModel.getViewName());
                            }
                        }
                    }
            );

            resultPanel.add(detailsButton);

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
        back.setEnabled(true);

        selectedCocktailIds.clear();
        cocktailCheckboxes.clear();
        displayCocktails();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        resultPanel.removeAll();

        final MyFavouriteState myFavouriteState = (MyFavouriteState) evt.getNewValue();
        if (myFavouriteState != null) {
            displayCocktails();
            revalidate();
            repaint();
        }
        else {
            deleteOutputField.setText("No cocktails are in my favourite.");
            revalidate();
            repaint();
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
