package com.sw.api.note;


import com.sw.domain.entity.note.Note;
import com.sw.domain.facade.note.NoteFacade;
import com.sw.domain.util.OnException;
import com.sw.domain.util.PostVo;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/note")
public class NoteResource {

    private NoteFacade userFacade;

    @Inject
    public NoteResource(NoteFacade userFacade) {
        this.userFacade = userFacade;
    }


    @POST
    @Path("add")
    @OnException("addNoteFailed")
    @Produces(MediaType.APPLICATION_JSON)
    public Note add(Note note,@Context HttpServletRequest request) {
        note.setIp(getIpAddr(request));
        return userFacade.addNote(note);
    }

    @POST
    @Path("edit")
    @OnException("editNoteFailed")
    @Produces(MediaType.APPLICATION_JSON)
    public Note edit(Note note, @Context HttpServletRequest request) {
        String ip = getIpAddr(request);
        if (ip != null && !ip.equals("103.254.113.195")&& !ip.equals("1.180.206.205")) {
            if (note.getIp() != null && !ip.equals(note.getIp())) {
                return null;
            }
        }
        note.setIp(ip);
        return userFacade.editNote(note);
    }

    @GET
    @Path("get")
    @OnException("getNoteFailed")
    @Produces(MediaType.APPLICATION_JSON)
    public Note get(@QueryParam("id") Integer id) {
        return userFacade.getNote(id);
    }


    @GET
    @OnException("getAllPostFailed")
    @Produces(MediaType.APPLICATION_JSON)
    public List<PostVo> get() {
        return userFacade.getAllPost();
    }

    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
