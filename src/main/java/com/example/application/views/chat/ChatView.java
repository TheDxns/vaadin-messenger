package com.example.application.views.chat;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.dom.Style;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@PageTitle("Chat")
@Route(value = "chat", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class ChatView extends VerticalLayout {

    private VerticalLayout chatLog;
    private TextArea messageContent;

    public ChatView() {
        createChatLayout();
    }

    private void createChatLayout() {
        setHeight("100%");
        final Div header = new Div();
        header.setText("Chatting with: Name Surname");
        chatLog = new VerticalLayout();
        Style style = chatLog.getElement().getStyle();
        style.set("overflow", "auto");
        style.set("flex", "1");
        style.set("transition", "background-color 100ms");
        messageContent = new TextArea();
        Button sendButton = new Button("Send");
        final HorizontalLayout footer = new HorizontalLayout();
        footer.setWidth("100%");
        footer.setPadding(true);
        footer.setFlexGrow(1, messageContent);
        footer.add(messageContent, sendButton);
        add(header, chatLog, footer);

        sendButton.addClickListener(e -> {
            if (!"".equals(messageContent.getValue())) {
                chatLog.add(composeMessage(messageContent.getValue()));
                messageContent.setValue("");
            } else {
                Notification.show("Empty message field");
            }
        });
    }

    private Label composeMessage(String messageText) {
        return new Label(getCurrentDateAndTime() + " " + getCurrentUserName() + ": " + messageText);
    }

    private String getCurrentUserName() {
        return "John Doe";
    }

    private String getCurrentDateAndTime() {
        final LocalDateTime currentDate = LocalDateTime.now();
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        return currentDate.format(formatter);
    }
}
