package com.example.application.views.main;

import com.example.application.ClojureAccess;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@PageTitle("Main")
@Route(value = "")
public class MainView extends VerticalLayout {

    private TextField name;
    private Button sayHello;

    @Autowired
    public MainView(ClojureAccess clojureAccess, LabelService labelService) {
        name = new TextField(new TextFieldLabelService().getLabel("name"));
        sayHello = new Button(labelService.getLabel("Say hello from Java"));
        sayHello.addClickListener(e -> {
            Notification.show("Hello " + name.getValue());
        });
        sayHello.addClickShortcut(Key.ENTER);

        setMargin(true);
        setHorizontalComponentAlignment(Alignment.END, name, sayHello);

        add(new RedTitle("Bonjour en rouge"),
                clojureAccess.createClojureTitle(),
                new VerticalLayout(name, sayHello));
    }

}
