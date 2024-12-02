package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import interface_adapter.recommended.RecommendedController;
import interface_adapter.recommended.RecommendedState;
import interface_adapter.recommended.RecommendedViewModel;
import interface_adapter.search_by_ingredients.IngredientsController;

import java.util.List;
import java.util.Map;

public class RecommendedView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "recommended";
    private final RecommendedViewModel recommendedViewModel;

    private final JPanel resultPanel = new JPanel();
    private final JLabel searchOutputField = new JLabel();

    private final JButton refresh;

    private RecommendedController recommendedController;
    private IngredientsController ingredientsController;

    public RecommendedView(RecommendedViewModel recommendedViewModel) {
        this.recommendedViewModel = recommendedViewModel;
        this.recommendedViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Recommended Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel buttons = new JPanel();
        refresh = new JButton("refresh recommendations");
        buttons.add(refresh);

        // Add Action Listeners Go Here






        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(buttons);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        resultPanel.removeAll();
        searchOutputField.removeAll();
        final RecommendedState state = (RecommendedState) evt.getNewValue();
        if (state.getRecommendedError() != null) {
            searchOutputField.setText(state.getRecommendedError());
            this.add(recommendedOutputField);
            revalidate();
            repaint();
            state.setRecommendedError(null);
        } else {
            recommendedResults(state);
            this.add(resultPanel);
            revalidate();
            repaint();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Click " + e.getActionCommand());
    }

    public String getViewName() {
        return viewName;
    }

    public void setRecommendedController(RecommendedController controller) {
        this.recommendedController = controller;
    }

    public void setIngredientsController(IngredientsController controller) {
        this.ingredientsController = controller;
    }

    public void recommendedResults(RecommendedState state) {
        final List<String> nameList = state.getCocktailNamesList();
        final List<Map<String, String>> ingredientsList = state.getIngredientsList();
        final List<Integer> ID = state.getIdList();
        final List<String> recipeList = state.getRecipeList();
        final List<String> photoLinkList = state.getPhotoLinkList();

        for (int i = 0; i < nameList.size(); i++) {
            final String name = nameList.get(i);
            final int id = ID.get(i);
            final String recipe = recipeList.get(i);
            final String photoLink = photoLinkList.get(i);
            final String ingredients = state.getIngredientsToString(ingredientsList.get(i));

            // Create a new JPanel for each cocktail
            final JPanel cocktailPanel = new JPanel();
            cocktailPanel.setLayout(new BoxLayout(cocktailPanel, BoxLayout.Y_AXIS));  // Vertical layout for better organization
            cocktailPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding to the panel

            // Set the background color for the cocktail panel to yellow
            cocktailPanel.setBackground(Color.YELLOW); // Yellow background for the cocktail panel

            // Create labels for cocktail details
            final JLabel nameLabel = new JLabel(name);
            final JLabel IDLabel = new JLabel("ID: " + id);
            final JLabel photoLinkLabel = new JLabel(photoLink);

            // Set color for labels
            nameLabel.setForeground(Color.DARK_GRAY);   // Dark gray for name label
            IDLabel.setForeground(Color.DARK_GRAY);     // Dark gray for ID label
            photoLinkLabel.setForeground(Color.DARK_GRAY); // Dark gray for photo link label

            // Create JLabel for photo link or image
            final JLabel photoLabel = new JLabel();
            final ImageIcon photoIcon = new ImageIcon(photoLink);
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
