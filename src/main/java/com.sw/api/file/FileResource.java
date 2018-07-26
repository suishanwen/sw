package com.sw.api.file;

import com.sw.domain.facade.file.FileFacade;
import com.sw.domain.util.OnException;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/file")
public class FileResource {
	private FileFacade fileFacade;

	@Inject
	public FileResource(FileFacade fileFacade) {
		this.fileFacade = fileFacade;
	}

	@POST
	@Path("upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@OnException("uploadFileFailed")
	public Response upload(@QueryParam("type") int type, FormDataMultiPart multiPart) throws Exception {
		return Response.ok(fileFacade.uploadFile(multiPart, type)).build();
	}
}
