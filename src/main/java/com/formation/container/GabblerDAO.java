package com.formation.container;

import com.formation.management.DAO;

import javax.enterprise.context.ApplicationScoped;
import javax.annotation.PostConstruct; //Java EE 6 dependency
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

@Named("gabdao")
@ApplicationScoped
public class GabblerDAO implements DAO<Gabbler> {
    private static List<Gabbler> listGabbler;
    //private static Gabbler newGabbler;
    private static int id=0;

    public GabblerDAO() {
        if(listGabbler ==null) listGabbler = new ArrayList<Gabbler>();
    }



    @Override
    public void add(Gabbler newGabbler) {
        newGabbler.setDate(new java.util.Date());
        newGabbler.setId(id);
        newGabbler.setDeletable(true);
        newGabbler.setLiked(false);
        listGabbler.add(newGabbler);
    }

    @Override
    public void remove(Gabbler gabblerToRemove) {
        listGabbler.remove(gabblerToRemove);
    }

    @Override
    public Gabbler showbyId(int id) {
        return listGabbler.get(id);
    }

    @Override
    public List<Gabbler> showAll() {
        /*for (Gabbler gab : listGabbler) {
            gab.getMessage();
        }*/
        return listGabbler;
    }

    @Override
    public void edit(Gabbler gabbler) {
        Gabbler gabblerToEdit = listGabbler.get(gabbler.getId());
        String messageURL;
        String href;
        if(!gabblerToEdit.getHashtags().isEmpty()){
            for (String hashtag : gabblerToEdit.getHashtags()){
                messageURL =  "https://twitter.com/search?q=%23/"+hashtag.substring(1);
                href = "<a href=\""+messageURL+"\">"+hashtag+"</a>";
                gabblerToEdit.getMessage().replace(hashtag, Matcher.quoteReplacement(href));
                //La méthode ne remplace pas les liens
                //gabblerToEdit.getMessage().replaceAll(hashtag, "MAMASFARTBOX");
            }
            listGabbler.set(gabblerToEdit.getId(),gabblerToEdit);
        }

    }

    @PostConstruct
    private void init(){
        listGabbler = new ArrayList<Gabbler>();
    }



}
