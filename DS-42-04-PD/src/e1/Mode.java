package e1;

import e1.Termostato;

public interface Mode {

   String stringTemperatura(Termostato t);
   String screenInfo(Termostato t);

   void apagar(Termostato t);
   void programar(Termostato t, double limit);
   void encendida(Termostato t);
   void termporizador(Termostato t, int time);



}
