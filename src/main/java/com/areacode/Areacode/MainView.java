package com.areacode.Areacode;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("")
public class MainView extends VerticalLayout {
    public MainView() {
        VerticalLayout todosList = new VerticalLayout();
        TextField taskField = new TextField();
        Button lookUp = new Button("Look Up");
        Text output = new Text("Output");
        lookUp.addClickListener(click -> {
            String enteredAreacode = taskField.getValue();
            try {
                String result = Areacode.getAreaCodeFromCSV(enteredAreacode);
                System.out.println(result);
                output.setText(result);
                taskField.setValue("");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        });
        lookUp.addClickShortcut(Key.ENTER);

        add(
                new H1("Area Code Searcher"),
                todosList,
                new HorizontalLayout(
                        taskField,
                        lookUp
                ),
                output
        );
    }


}
