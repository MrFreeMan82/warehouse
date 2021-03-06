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
   private String title;
   private Button positive, neutral, negative;
   private boolean locked;
   private RequestCallBack callBack;

   public DialogBuilder setPresent(T present) {this.present = present; return this;}
   public DialogBuilder setTitle(String title){this.title = title; return this;}
   public DialogBuilder setLocked(){this.locked = true; return this;}
   public DialogBuilder setCallback(RequestCallBack callback){this.callBack = callback; return this;}

   public DialogBuilder addPositiveButton(String caption)
   {
       positive = new Button(caption);
       positive.setId("positiveButton");
       positive.addClickHandler(clickEvent -> present.onPositive(dialog, callBack) );
       return this;
   }

   public DialogBuilder addNeutralButton(String caption)
   {
       neutral = new Button(caption);
       neutral.setId("neutralButton");
       neutral.addClickHandler(clickEvent -> present.onNeutral(dialog));
       return this;
   }

   public DialogBuilder addNegativeButton(String caption)
   {
       negative = new Button(caption);
       negative.setId("negativeButton");
       negative.addClickHandler(clickEvent -> present.onNegative(dialog));
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
           body.add(new Label("ServerException"));
       } else {
           body.add(present);
       }
       if(callBack == null) setCallback(dto -> {});

       if(locked) present.setReadOnly();
       if(positive != null) footer.add(positive);
       if(neutral != null) footer.add(neutral);
       if(negative != null) footer.add(negative);

       dialog.add(body);
       dialog.add(footer);
       return dialog;
   }
}
