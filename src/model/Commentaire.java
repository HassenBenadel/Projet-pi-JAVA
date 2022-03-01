/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author El Ghali Omar
 */
public class Commentaire {
    private int id;
    private int userId;
    private String commentateur;
    private String contenu;

    public Commentaire() {
    }

    public Commentaire(int id, int userId, String commentateur, String contenu) {
        this.id = id;
        this.userId = userId;
        this.commentateur = commentateur;
        this.contenu = contenu;
    }
    
    public Commentaire(int userId, String commentateur, String contenu) {
        this.userId = userId;
        this.commentateur = commentateur;
        this.contenu = contenu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCommentateur() {
        return commentateur;
    }

    public void setCommentateur(String commentateur) {
        this.commentateur = commentateur;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }
}
