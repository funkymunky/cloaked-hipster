package net.helloworld.site.student;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;

@Controller
@RequestMapping(value = "/uploadfile")
public class UploadFileController {
    private String uploadFolderPath = "../../../images/";

    public String getUploadFolderPath() {
        return uploadFolderPath;
    }

    public void setUploadFolderPath(String uploadFolderPath) {
        this.uploadFolderPath = uploadFolderPath;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getUploadForm(Model model) {
        model.addAttribute(new UploadItem());
        return "/student/addOrUpdate";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String create(@RequestParam CommonsMultipartFile[] fileUpload,
                         Model model, BindingResult result) throws Exception {
        if (fileUpload != null && fileUpload.length > 0) {
            for (CommonsMultipartFile aFile : fileUpload){

                System.out.println("Saving file: " + aFile.getOriginalFilename());

                if (!aFile.getOriginalFilename().equals("")) {
                    aFile.transferTo(new File(uploadFolderPath + aFile.getOriginalFilename()));
                }
            }
        }

        return "/student/addOrUpdate";
    }

    public class UploadItem {
        private String filename;
        private CommonsMultipartFile fileData;

        public String getFilename() {
            return filename;
        }

        public void setFilename(String filename) {
            this.filename = filename;
        }

        public CommonsMultipartFile getFileData() {
            return fileData;
        }

        public void setFileData(CommonsMultipartFile fileData) {
            this.fileData = fileData;
        }
    }

}
