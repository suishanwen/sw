package com.sw.domain.facade.file;


import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class FileFacade {
    public static final String path = "/etc/nginx/html/file/";

    public void uploadFile(FormDataMultiPart multiPart) {
        FormDataBodyPart file = multiPart.getField("file");
        InputStream ins = file.getValueAs(InputStream.class);
        File f = new File(path + file.getName());
        try {
            OutputStream os = new FileOutputStream(f);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
