package com.warehouse.client.utils;

import com.google.gwt.event.dom.client.ClickHandler;
import com.warehouse.client.present.Present;
import org.gwtbootstrap3.client.ui.*;

/**
 * Created by Дима on 26.04.2017.
 *
 */

public class DialogBuilder
{
   private Present present;
   private String title, message;
   private Button positive, neutral, negative;

   public DialogBuilder setPresent(Present present) {this.present = present; return this;}
   public DialogBuilder setTitle(String title){this.title = title; return this;}
   public DialogBuilder setMessage(String message){this.message = message; return this;}
   public DialogBuilder addPositiveButton(String caption, ClickHandler handler)
   {
       positive = new Button(caption);
       positive.addClickHandler(handler);
       return this;
   }

   public DialogBuilder addNeutralButton(String caption, ClickHandler handler)
   {
       neutral = new Button(caption);
       neutral.addClickHandler(handler);
       return this;
   }

   public DialogBuilder addNegativeButton(String caption, ClickHandler handler)
   {
       negative = new Button(caption);
       negative.addClickHandler(handler);
       return this;
   }

   public Modal build()
   {
       Modal dialog = new Modal();
       dialog.setClosable(true);
       dialog.setFade(true);

       ModalBody body = new ModalBody();
       ModalFooter footer = new ModalFooter();

       dialog.setTitle(title);

       if(present == null)
       {
           body.add(new Label(message));
       } else {
           body.add(present);
       }

       if(positive != null) footer.add(positive);
       if(neutral != null) footer.add(neutral);
       if(negative != null) footer.add(negative);

       dialog.add(body);
       dialog.add(footer);
       return dialog;
   }
}
