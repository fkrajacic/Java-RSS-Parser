/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

/**
 *
 * @author Fran
 */
public class TextFormator {
    
    public static String formatDescription(String desc)
    {
        String formattedDescriptionHtml=desc.replaceAll("(<.*?>)|(&.*?;)|([ ]{2,})", "");
        String formattedDescriptionNumbers[]=formattedDescriptionHtml.split(filterNumbers(formattedDescriptionHtml));
        
        return formattedDescriptionNumbers[0];
    }

    private static String filterNumbers(String formattedDescriptionHtml) {
        for(int i=12;i<24;i++)
        {
            String provjera=String.valueOf((i));
            if(formattedDescriptionHtml.contains(provjera+"."))
            {
                return provjera+".";
            }
              
        }
        return "--";
    }
    
    public static String formatTrajanje(String Trajanje)
    {
        return Trajanje+ " min";
    }
    
    
}
