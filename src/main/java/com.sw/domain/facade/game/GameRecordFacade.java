package com.sw.domain.facade.game;

import com.google.inject.persist.Transactional;
import com.sw.domain.entity.game.GameRecord;
import com.sw.domain.facade.BaseFacade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class GameRecordFacade extends BaseFacade {

    @Transactional
    public GameRecord addGameRecord(GameRecord gameRecord) {
        gameRecord.setTime(new Date());
        try{
            entityManager.persist(gameRecord);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return gameRecord;
    }


    public List<GameRecord> getGameRecord() {
        List<GameRecord> gameRecordList0=entityManager.createQuery("select gr from GameRecord gr order by gr.score desc").getResultList();
        List<GameRecord> gameRecordList=new ArrayList<GameRecord>();
        for(Integer i=0;i<gameRecordList0.size();i++){
            gameRecordList.add(gameRecordList0.get(i));
            if(i==9){
                break;
            }
        }
        return gameRecordList;
    }
}
