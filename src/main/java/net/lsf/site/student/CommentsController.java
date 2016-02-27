package net.lsf.site.student;

import net.lsf.AgentType;
import net.lsf.BankInstitution;
import net.lsf.InstitutionType;
import net.lsf.SponsorshipType;
import net.lsf.model.Comments;
import net.lsf.model.Student;
import net.lsf.service.CommentsService;
import net.lsf.service.SponsorService;
import net.lsf.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommentsController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private SponsorService sponsorService;

    @Autowired
    private CommentsService commentsService;

    @RequestMapping(value="/student/comments/addOrUpdate", method = RequestMethod.POST)
    public String submitForm(@ModelAttribute CommentsCommand comments,
                             @RequestParam(value="studentid") String studentId,
                             BindingResult bindingResult,
                             Model model) {
        String message;
        int id = Integer.parseInt(studentId);
        Student student = studentService.getStudent(id);

        Comments currentComments = student.getComments();
        Comments modifiedComments = comments.getComments();

        if (currentComments != modifiedComments && currentComments != null) {
            commentsService.updateComments(modifiedComments, currentComments.getId());
            message = "Successfully updated comment details";
        } else {
            commentsService.addComments(modifiedComments);
            studentService.updateCommentsForStudent(id, modifiedComments);
            message = "Successfully added commnets to student";
        }

        model.addAttribute("activeTab", "comments");
        model.addAttribute("student", studentService.getStudent(id));
        model.addAttribute("institutionTypeValues", InstitutionType.values());
        model.addAttribute("sponsorshipTypeValues", SponsorshipType.values());
        model.addAttribute("bankValues", BankInstitution.values());
        model.addAttribute("agentTypeValues", AgentType.values());
        model.addAttribute("listOfSponsors", sponsorService.getAllSponsors());
        model.addAttribute("maxUploadSize", studentService.getMaxUploadFileSize());
        model.addAttribute("message", message);
        model.addAttribute("updateMode", true);

        return "/student/addOrUpdate";
    }

    private static final class CommentsCommand {
        private Comments comments;

        public Comments getComments() {
            return comments;
        }

        public void setComments(Comments comments) {
            this.comments = comments;
        }
    }
}
