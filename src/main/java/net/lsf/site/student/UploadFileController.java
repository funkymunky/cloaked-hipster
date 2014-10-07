package net.lsf.site.student;

import net.lsf.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

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
        String message;
        boolean warn = false;

        if (!isFileValid(originalFilename)) {
            model.addAttribute("student", studentService.getStudent(id));
            model.addAttribute("message", filenameExists(originalFilename) ? getStringForDuplicatFilename() : getStringForBadFileType());
            return "/student/addOrUpdate";
        }

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
            message = "Profile picture was successfully uploaded.";
        } catch (IllegalStateException e) {
            e.printStackTrace();
            message =  "File uploadfailed:" + originalFilename;
            warn = true;
        } catch (IOException e) {
            e.printStackTrace();
            message = "File upload failed - check configured temp folders.";
            warn = true;
        } catch (MaxUploadSizeExceededException e) {
            message = "File upload failed - file size is too large.";
            warn = true;
        }

        model.addAttribute("student", studentService.getStudent(id));
        model.addAttribute("message", message);
        model.addAttribute("warn", warn);
        return "/student/addOrUpdate";
    }

    private boolean isFileValid(String originalFilename) {
        if (filenameExists(originalFilename)) {
            return false;
        } else if (!filenameValid(originalFilename)) {
            return false;
        }
        return true;
    }

    private String getStringForBadFileType() {
        return "Please check the file you are uploading. Only image files will be accepted.";
    }

    private String getStringForDuplicatFilename() {
        return "Filename is not unique. Please change the filename of the file you are trying to upload and try again. ";
    }

    private boolean filenameValid(String filename) {
        return filename.contains(".jpg") || filename.contains(".png") || filename.contains(".jpeg");
    }

    private boolean filenameExists(String filename) {
        List<String> allProfilePics = studentService.getAllProfilePics();
        return allProfilePics.contains(filename);
    }

}
