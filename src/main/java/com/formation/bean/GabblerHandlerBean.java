package com.formation.bean;

import com.formation.container.Gabbler;
import com.formation.container.GabblerDAO;
import com.formation.management.DAO;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class GabblerHandlerBean implements Serializable {
    private String message;
    private int id;
    private Gabbler newGabbler;
    private List<Gabbler> listGabbler;
    private String messageToDisplay;
    private String tempSmiley;
    private boolean isValid;

    @Inject
    private DAO<Gabbler> myDAO;

    public GabblerHandlerBean() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Gabbler getNewGabbler() {
        return newGabbler;
    }

    public void setNewGabbler(Gabbler newGabbler) {
        this.newGabbler = newGabbler;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DAO<Gabbler> getMyDAO() {
        return myDAO;
    }

    public List<Gabbler> getListGabbler() {
        listGabbler = myDAO.showAll();
        return listGabbler;
    }

    public void setListGabbler(List<Gabbler> listGabbler) {
        this.listGabbler = listGabbler;
    }

    public void setMyDAO(DAO<Gabbler> myDAO) {
        this.myDAO = myDAO;
    }

    public String getMessageToDisplay() {
        return messageToDisplay;
    }

    public void setMessageToDisplay(String messageToDisplay) {
        this.messageToDisplay = messageToDisplay;
    }

    public String getTempSmiley() {
        return tempSmiley;
    }

    public void setTempSmiley(String tempSmiley) {
        this.tempSmiley = tempSmiley;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    //Handling of the data
    public String addGab(){
        messageToDisplay = null;
        if(message.length()>140 || message.isEmpty()){

            messageToDisplay = "Erreur: message vide ou trop long (>140 caractères)";
            isValid = false;
            //FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, messageToDisplay, null);
            clearMessage();
            //return "failure";
        }else{
            newGabbler =  new Gabbler();
            newGabbler.setMessage(message);
            detectHashtag(newGabbler);

            myDAO.add(newGabbler);
            myDAO.edit(newGabbler);
            id++;
            clearMessage();
            messageToDisplay = "Nouveau Gab posté!";
            isValid = true;
            //FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, messageToDisplay, null);

        }
        return null;
    }

    public void likeGab(Gabbler gabblerToLike){
        if(gabblerToLike.isLiked()){
            gabblerToLike.setLiked(false);
            tempSmiley = ";_;";
        }else{
        gabblerToLike.setLiked(true);
            tempSmiley = "=^_^=";
        }
    }

    public void clearMessage(){
        this.message = null;
    }



    public void detectHashtag(Gabbler newGabbler){

        if(newGabbler.getHashtags()==null){
            List<String>  hashtagList = new ArrayList<String>();
            newGabbler.setHashtags(hashtagList);
        }
        String message = newGabbler.getMessage();
        char findHash = '#';
        char findSpace = ' ';
        String hashtag = "";
        int j=0;
        while(j<message.length()) {
            char currentChar = message.charAt(j);
            if(currentChar==findHash) {
                while(currentChar!=findSpace) {
                    currentChar = message.charAt(j);
                    hashtag = hashtag + String.valueOf(currentChar);
                    j++;
                    if(j==message.length()) {
                        break;
                    }
                }
                newGabbler.getHashtags().add(hashtag.trim());
                hashtag = "";
            }else {
                j++;
            }
        }



    }





}
