package net.helloworld.site.student;

import net.helloworld.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class UploadFileController {

      @Autowired
      private StudentService studentService;


    @RequestMapping(value = "/student/profile/uploadFile", method = RequestMethod.POST)
    public String uploadFileHandler(@RequestParam("studentid") String studentId,
                                    @RequestParam("file") MultipartFile file,
                                    Model model) {
        int id = Integer.parseInt(studentId);
        String originalFilename = file.getOriginalFilename();

        String dirPath = "/tmp/images";
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdir();
        }

        String filePath = dir.getAbsolutePath() + File.separator + originalFilename;

        File dest = new File(filePath);
        try {
            file.transferTo(dest);
            studentService.updateProfilePicForStudent(id, originalFilename);
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return "File uploaded failed:" + originalFilename;
        } catch (IOException e) {
            e.printStackTrace();
            return "File uploaded failed:" + originalFilename;
        }

        model.addAttribute("student", studentService.getStudent(id));
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
