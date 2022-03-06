/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.Locale;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author hasse
 */
public class Countries {

    public ObservableList<String> getAllCountries() {
        ObservableList<String> pays = FXCollections.observableArrayList();
        String[] countries = Locale.getISOCountries();

        // Loop each country 
        for (int i = 0; i < countries.length; i++) {

            String country = countries[i];
            Locale locale = new Locale("en", country);

            // Get the country name by calling getDisplayCountry()
            String countryName = locale.getDisplayCountry();
            pays.add(countryName);
        }
        return pays;

    }

    

}
