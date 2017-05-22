package com.warehouse.client.utils;

import com.warehouse.client.present.Present;
import org.gwtbootstrap3.client.ui.*;

/**
 * Created by Дима on 26.04.2017.
 *
 */

public class DialogBuilder<T extends Present & Dialog>
{
   private T present;
   private Modal dialog;
   private String title, message;
   private Button positive, neutral, negative;

   public DialogBuilder setPresent(T present) {this.present = present; return this;}
   public DialogBuilder setTitle(String title){this.title = title; return this;}
   public DialogBuilder setMessage(String message){this.message = message; return this;}

   public DialogBuilder addPositiveButton(String caption, boolean inspect)
   {
       positive = new Button(caption);
       positive.setId("positiveButton");
       if (inspect)  present.widgets.put(positive.getId(), positive);
       positive.addClickHandler(clickEvent -> present.onPositive(dialog, positive));
       return this;
   }

   public DialogBuilder addNeutralButton(String caption, boolean inspect)
   {
       neutral = new Button(caption);
       neutral.setId("neutralButton");
       if (inspect) present.widgets.put(neutral.getId(), neutral);
       neutral.addClickHandler(clickEvent -> present.onNeutral(dialog, neutral));
       return this;
   }

   public DialogBuilder addNegativeButton(String caption, boolean inspect)
   {
       negative = new Button(caption);
       negative.setId("negativeButton");
       if(inspect) present.widgets.put(negative.getId(), negative);
       negative.addClickHandler(clickEvent -> present.onNegative(dialog, negative));
       return this;
   }

   public Modal build()
   {
       dialog = new Modal();
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
