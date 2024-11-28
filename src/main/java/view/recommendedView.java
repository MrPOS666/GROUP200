package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class recommendedView extends JPanel {

    private final String viewName = "recommended";
    private final JButton refreshButton;
    private final JButton backButton;
    private final JButton[] drinkButtons = new JButton[6];

    public recommendedView() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Title
        JLabel title = new JLabel("Recommended Drinks");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(title);

        // Panel for the horizontal buttons
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        // Create the 6 horizontal buttons
        for (int i = 0; i < 6; i++) {
            drinkButtons[i] = new JButton("Drink " + (i + 1));  // Placeholder text
            buttonsPanel.add(drinkButtons[i]);
        }
        this.add(buttonsPanel);

        // Refresh and Back buttons at the top
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        refreshButton = new JButton("Refresh");
        backButton = new JButton("Back");

        controlPanel.add(refreshButton);
        controlPanel.add(backButton);
        this.add(controlPanel);
    }

    public String getViewName() {
        return viewName;
    }

    public JButton getRefreshButton() {
        return refreshButton;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public JButton[] getDrinkButtons() {
        return drinkButtons;
    }

    // Method to update the text on the buttons (can be called later by the Presenter)
    public void updateDrinkButtons(String[] buttonLabels) {
        for (int i = 0; i < drinkButtons.length; i++) {
            if (i < buttonLabels.length) {
                drinkButtons[i].setText(buttonLabels[i]);
            } else {
                drinkButtons[i].setText("No recommendation");
            }
        }
    }

    // Method to set ActionListeners on the buttons (so they can be handled later)
    public void setRefreshButtonListener(ActionListener listener) {
        refreshButton.addActionListener(listener);
    }

    public void setBackButtonListener(ActionListener listener) {
        backButton.addActionListener(listener);
    }

    public void setDrinkButtonListener(ActionListener listener) {
        for (JButton drinkButton : drinkButtons) {
            drinkButton.addActionListener(listener);
        }
    }
}
