package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.myFavourite.MyFavouriteViewModel;
import interface_adapter.myFavourite.DeleteController;
import interface_adapter.myFavourite.MyFavouriteState;
import interface_adapter.myFavourite.MyFavouriteViewModel;

/**
 * View for Delete use case.
 */
public class DeleteView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName1 = "select";
    private final String viewName2 = "delete";
    private final MyFavouriteViewModel deleteViewModel;

    private final JTextField deleteInputField = new JTextField(15);
    private final JLabel deleteErrorField = new JLabel();

    private final JTextField selectInputField = new JTextField(15);
    private final JLabel selectErrorField = new JLabel();

    private final JButton delete;
    private final JButton select;
    private final JButton cancel;
    private final DeleteController deleteController;

    public DeleteView(MyFavouriteViewModel deleteViewModel) {

        this.deleteViewModel = deleteViewModel;
        this.deleteViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Delete my favourite");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel selectInfo = new LabelTextPanel(
                new JLabel("Select"), selectInputField);
        final LabelTextPanel deleteInfo = new LabelTextPanel(
                new JLabel("Delete"), deleteInputField);

        final JPanel buttons = new JPanel();
        select = new JButton("search");
        buttons.add(select);
        delete = new JButton("delete");
        buttons.add(delete);
        cancel = new JButton("cancel");
        buttons.add(cancel);

        delete.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(delete)) {
                            final MyFavouriteState myFavouriteState = deleteViewModel.getState();

                            deleteController.execute(
                                    myFavouriteState.getSelectCocktail()
                            );
                        }
                    }
                }
        );

        select.addActionListener(
                new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(select)) {
                final MyFavouriteState myFavouriteState = deleteViewModel.getState();}


                }
                }
    }
        )

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



    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    /**
     * This method gets called when a bound property is changed.
     *
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
