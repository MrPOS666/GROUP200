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

    private JLabel nameLabel;
    private JLabel idLabel;
    private JTextArea instructionTextArea;
    private JLabel imageLabel;
    private JPanel ingredientsPanel;

    public DetailView(DetailPageViewModel detailPageViewModel) {
        this.detailPageViewModel = detailPageViewModel;
        this.detailPageViewModel.addPropertyChangeListener(this);

        final JLabel titleLabel = new JLabel("Detail Page");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        final JPanel buttons = new JPanel();
        addMyFavourite = new JButton("Add My Favourite");
        backButton = new JButton("Back");
        buttons.add(addMyFavourite);
        buttons.add(backButton);

        nameLabel = new JLabel("Name: ");
        idLabel = new JLabel("ID: ");
        instructionTextArea = new JTextArea("Instructions: ");
        imageLabel = new JLabel(new ImageIcon());
        ingredientsPanel = new JPanel();

        nameLabel.setBackground(Color.GRAY);
        nameLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        idLabel.setBackground(Color.GRAY);
        idLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        idLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        instructionTextArea.setBackground(Color.GRAY);
        instructionTextArea.setLineWrap(true);
        instructionTextArea.setWrapStyleWord(true);
        instructionTextArea.setFont(new Font("Monospaced", Font.PLAIN, 15));
        instructionTextArea.setColumns(60);
        instructionTextArea.setEditable(false);
        instructionTextArea.setBackground(Color.GRAY);
        instructionTextArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        ingredientsPanel.setLayout(new BoxLayout(ingredientsPanel, BoxLayout.Y_AXIS));
        ingredientsPanel.setBackground(Color.GRAY);
        ingredientsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel ingredientLabel = new JLabel("Ingredients");
        ingredientLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        ingredientsPanel.add(ingredientLabel);

        detailPanel.add(nameLabel);
        detailPanel.add(idLabel);
        detailPanel.add(imageLabel);
        detailPanel.add(ingredientsPanel);
        detailPanel.add(instructionTextArea);

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

        this.add(titleLabel);
        detailPanel.setLayout(new BoxLayout(detailPanel, BoxLayout.Y_AXIS));
        detailPanel.setBackground(Color.GRAY);
        this.add(buttons);
        this.add(detailPanel);
        this.add(detailOutputField);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Clicked: " + e.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        detailOutputField.removeAll();
        this.remove(detailOutputField);
        final DetailPageState currentState = (DetailPageState) evt.getNewValue();
        if (!currentState.getDetailPageError().isEmpty()) {
            detailOutputField.setText(currentState.getDetailPageError());
            this.add(detailOutputField);
            revalidate();
            repaint();
            currentState.setDetailPageError(null);
        }
        else {
            detailPanel.setLayout(new BoxLayout(detailPanel, BoxLayout.Y_AXIS));
            detailPanel.setBackground(Color.GRAY);

            // Add cocktail details
            nameLabel.setText("Name: " + currentState.getCocktailname());

            idLabel.setText("ID: " + currentState.getCocktailiD());

            // Display the actual image
            final BufferedImage image = currentState.getImage();
            if (image != null) {
                imageLabel.setIcon(new ImageIcon(image));
            }
            else {
                imageLabel.setIcon(new ImageIcon());
            }

            instructionTextArea.setText("Instructions: " + currentState.getInstruction());

            // Add ingredients to the panel
            for (Map.Entry<String, String> entry : currentState.getIngredients().entrySet()) {
                final JLabel ingredientLabel = new JLabel(entry.getKey() + ": " + entry.getValue());
                ingredientLabel.setBackground(Color.GRAY);
                ingredientLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
                ingredientsPanel.add(ingredientLabel);
            }

            revalidate();
            repaint();
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setDetailPageController(DetailPageController detailPageController) {
        this.detailPageController = detailPageController;
    }
}
