/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import model.Client;

/**
 *
 * @author hasse
 */
public interface IClientService {

    public void afficherClient();

    public void ajouterClient(Client cl);

    public void modifierClient(Client cl, String attribute, String newValue);

    public void supprimerClient(Client cl);

}
