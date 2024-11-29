package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;

import javax.swing.*;

import interface_adapter.detailPage.DetailPageController;
import interface_adapter.detailPage.DetailPageState;
import interface_adapter.detailPage.DetailPageViewModel;

/**
 * Showing detail of a cocktail.
 */
public class DetailView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "detailpage";

    private final DetailPageViewModel detailPageViewModel;

    private final JPanel detailPanel = new JPanel();
    private final JLabel detailOutputField = new JLabel();

    private DetailPageController detailPageController;

    private final JButton addMyFavourite;
    private final JButton backButton;

    public DetailView(DetailPageViewModel detailPageViewModel) {
        this.detailPageViewModel = detailPageViewModel;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        /*
        Put the previous page parameter in the interactor
         */
        final JPanel buttons = new JPanel();
        addMyFavourite = new JButton("Add My Favourite");
        buttons.add(addMyFavourite);
        backButton = new JButton("Back");
        buttons.add(backButton);

        addMyFavourite.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        if (event.getSource().equals(addMyFavourite)) {
                            final DetailPageState currentState = detailPageViewModel.getState();
                            detailPageController.addMyFavourite(currentState.getUsername(),
                                    currentState.getCocktailname(),
                                    currentState.getCocktailiD(),
                                    currentState.getInstruction(),
                                    currentState.getPhotolink(),
                                    currentState.getIngredients(),
                                    currentState.getImage());
                        }
                    }
                }
        );

        backButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        if (event.getSource().equals(backButton)) {
                            final DetailPageState currentState = detailPageViewModel.getState();
                            detailPageController.returnOrigin(currentState.getPreviousViewName());
                        }
                    }
                }
        );

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void setDetailPageController(DetailPageController detailPageController) {
        this.detailPageController = detailPageController;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        detailPanel.removeAll();
        this.remove(detailPanel);
        detailOutputField.removeAll();
        this.remove(detailOutputField);
        final DetailPageState currentState = (DetailPageState) evt.getNewValue();
        if (currentState.getDetailPageError() != null) {
            detailOutputField.setText(currentState.getDetailPageError());
            this.add(detailOutputField);
            revalidate();
            repaint();
            currentState.setDetailPageError(null);
        }
        else {
            detailPanel.setLayout(new BoxLayout(detailPanel, BoxLayout.Y_AXIS));
            detailPanel.setBackground(Color.DARK_GRAY);

            // Add cocktail details
            final JLabel nameLabel = new JLabel("Name: " + currentState.getCocktailname());
            nameLabel.setForeground(Color.WHITE);
            detailPanel.add(nameLabel);

            final JLabel idLabel = new JLabel("ID: " + currentState.getCocktailiD());
            idLabel.setForeground(Color.WHITE);
            detailPanel.add(idLabel);

            final JLabel instructionLabel = new JLabel("Instructions: " + currentState.getInstruction());
            instructionLabel.setForeground(Color.WHITE);
            detailPanel.add(instructionLabel);

            // Display the actual image
            final BufferedImage image = currentState.getImage();
            if (image != null) {
                final ImageIcon imageIcon = new ImageIcon(image);
                final JLabel imageLabel = new JLabel(imageIcon);
                detailPanel.add(imageLabel);
            }
            else {
                final JLabel noImageLabel = new JLabel("No image available.");
                noImageLabel.setForeground(Color.WHITE);

                // Add ingredients to the panel
                detailPanel.add(new JLabel("Ingredients:"));
                for (Map.Entry<String, String> entry : currentState.getIngredients().entrySet()) {
                    final JLabel ingredientLabel = new JLabel(entry.getKey() + ": " + entry.getValue());
                    ingredientLabel.setForeground(Color.WHITE);
                    detailPanel.add(ingredientLabel);
                }

                this.add(detailPanel);
                revalidate();
                repaint();
            }
        }
    }

    public String getViewName() {
        return viewName;
    }
}
