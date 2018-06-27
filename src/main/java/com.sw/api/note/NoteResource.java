package com.sw.api.note;


import com.sw.base.exception.BusinessException;
import com.sw.domain.entity.note.Note;
import com.sw.domain.facade.note.NoteFacade;
import com.sw.domain.util.OnException;
import com.sw.domain.vo.EnquiryVO;
import com.sw.domain.vo.PostVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/note")
public class NoteResource {
	private static Logger logger = LoggerFactory.getLogger(NoteResource.class);

	private NoteFacade noteFacade;

	@Inject
	public NoteResource(NoteFacade noteFacade) {
		this.noteFacade = noteFacade;
	}

	@GET
	@OnException("getAllPostFailed")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PostVo> get() {
		return noteFacade.getAllPost();
	}

	@POST
	@Path("add")
	@OnException("addNoteFailed")
	@Produces(MediaType.APPLICATION_JSON)
	public Note add(Note note, @Context HttpServletRequest request) {
		note.setIp(getIpAddr(request));
		return noteFacade.addNote(note);
	}

	@POST
	@Path("edit")
	@OnException("editNoteFailed")
	@Produces(MediaType.APPLICATION_JSON)
	public Note edit(Note note, @Context HttpServletRequest request) {
		String ip = getIpAddr(request);
		logger.info(String.join(" ", "ip:", ip, "to edit note:", note.getId().toString()));
		if (ip == null || !ip.equals("198.181.57.231")) {
			throw new BusinessException("当前IP没有编辑权限！");
		}
		note.setIp(ip);
		return noteFacade.editNote(note);
	}

	@GET
	@Path("get")
	@OnException("getNoteFailed")
	@Produces(MediaType.APPLICATION_JSON)
	public Note get(@QueryParam("id") Integer id) {
		return noteFacade.getNote(id);
	}

	@GET
	@Path("recommend")
	@OnException("getRecommendNoteFailed")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PostVo> getRecommend() {
		return noteFacade.getRecommend();
	}

	@POST
	@Path("enquiry")
	@OnException("sendEnquiryFailed")
	@Produces(MediaType.APPLICATION_JSON)
	public Response sendEnquiry(EnquiryVO enquiryVO) {
		noteFacade.sendEnquiry(enquiryVO);
		return Response.ok().build();
	}

	private static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		logger.info("Proxy-Client-IP:" + request.getHeader("Proxy-Client-IP"));
		logger.info("WL-Proxy-Client-IP:" + request.getHeader("WL-Proxy-Client-IP"));
		logger.info("HTTP_CLIENT_IP:" + request.getHeader("HTTP_CLIENT_IP"));
		logger.info("HTTP_X_FORWARDED_FOR:" + request.getHeader("HTTP_X_FORWARDED_FOR"));
		logger.info("X_FORWARDED_FOR:" + request.getHeader("X_FORWARDED_FOR"));
		logger.info("getRemoteAddr:" + request.getRemoteAddr());
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
			ip = request.getHeader("X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}
