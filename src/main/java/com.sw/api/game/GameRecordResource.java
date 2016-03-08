package com.sw.api.game;

import com.sw.domain.entity.game.GameRecord;
import com.sw.domain.facade.game.GameRecordFacade;
import com.sw.domain.util.OnException;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;



@Path("/game")
public class GameRecordResource {

    private GameRecordFacade gameRecordFacade;

    @Inject
    public GameRecordResource(GameRecordFacade gameRecordFacade) {
        this.gameRecordFacade = gameRecordFacade;
    }


    @POST
    @Path("add")
    @OnException("addGameRecordFailed")
    @Produces(MediaType.APPLICATION_JSON)
    public GameRecord add(GameRecord gameRecord) {
        return gameRecordFacade.addGameRecord(gameRecord);
    }

    @GET
    @Path("get")
    @OnException("getGameRecordFailed")
    @Produces(MediaType.APPLICATION_JSON)
    public List<GameRecord> get() {
        return gameRecordFacade.getGameRecord();
    }


}
