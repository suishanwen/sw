package com.sw.api.file;

import com.sw.domain.facade.file.FileFacade;
import com.sw.domain.util.OnException;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
    public Response upload(FormDataMultiPart multiPart) {
        fileFacade.uploadFile(multiPart);
        return Response.ok().build();
    }
}
