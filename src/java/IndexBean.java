
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.imageio.ImageIO;
import javax.servlet.http.Part;
import org.apache.commons.codec.binary.Base64;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author janes.thomas
 */
@ManagedBean(name = "index")
@RequestScoped

public class IndexBean {

    private String submitter_name;
    private String submitter_email;

    private Part file;

    public String upload() throws IOException {

        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();

        File dir = new File("/uploaded_images/");
        if (!dir.exists()) {
            try{
                dir.mkdir();
            } catch(SecurityException se){
                se.getStackTrace();
            }
        }
        
        Files.copy(file.getInputStream(), new File(dir.getPath(), dateFormat.format(date) + "_" + getSubmitter_name() + "_" + file.getSubmittedFileName()).toPath());

//        BufferedImage image = getFile().
//         InputStream inputStream = getFile().getInputStream();        
//        FileOutputStream outputStream = new FileOutputStream(getFilename(getFile()));
//         
//        byte[] buffer = new byte[4096];        
//        int bytesRead = 0;
//        while(true) {                        
//            bytesRead = inputStream.read(buffer);
//            if(bytesRead > 0) {
//                outputStream.write(buffer, 0, bytesRead);
//            }else {
//                break;
//            }                       
//        }
//        outputStream.close();
//        inputStream.close();
        return "success";
    }

    private static String getFilename(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }
        return null;
    }

    /**
     * @return the submitter_name
     */
    public String getSubmitter_name() {
        return submitter_name;
    }

    /**
     * @param submitter_name the submitter_name to set
     */
    public void setSubmitter_name(String submitter_name) {
        this.submitter_name = submitter_name;
    }

    /**
     * @return the submitter_email
     */
    public String getSubmitter_email() {
        return submitter_email;
    }

    /**
     * @param submitter_email the submitter_email to set
     */
    public void setSubmitter_email(String submitter_email) {
        this.submitter_email = submitter_email;
    }

    /**
     * @return the file
     */
    public Part getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(Part file) {
        this.file = file;
    }
}
