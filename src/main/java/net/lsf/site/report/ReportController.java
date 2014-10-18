package net.lsf.site.report;

import net.lsf.AgentType;
import net.lsf.InstitutionType;
import net.lsf.SponsorshipType;
import net.lsf.model.Student;
import net.lsf.service.StudentService;
import net.lsf.service.WriterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Controller
public class ReportController {

    private static final String[] HEADER_ROW = {"Student id", "Student name", "Year of study", "Account name", "Bank", "Branch", "Account number", "Standing order number"};

    @Autowired
    StudentService studentService;

    @Autowired
    WriterService writerService;


    @RequestMapping(value = "/report/list", method = RequestMethod.GET)
    public String listAllStudents(Model model) {
        return "/reports/list";
    }

    @RequestMapping(value = "/report/currentlySponsored", method = RequestMethod.GET)
    public String showCurrentlySponsoredStudents(Model model) {
        List<Student> sponsored = getStudentsBySponsorshipType(SponsorshipType.CurrentlySponsored);
        model.addAttribute("students", sponsored);
        model.addAttribute("activeTab", "reportC");
        return "/reports/currentlySponsored";
    }

    @RequestMapping(value = "/report/currentlySponsored/school", method = RequestMethod.GET)
    public String showOnlySchoolStudents(Model model) {
        List<Student> students = studentService.getAllStudentsByInstitutionType(SponsorshipType.CurrentlySponsored, InstitutionType.School);

        model.addAttribute("students", students);
        model.addAttribute("activeTab", "reportC");
        return "/reports/currentlySponsored";
    }

    @RequestMapping(value = "/report/currentlySponsored/university", method = RequestMethod.GET)
    public String showOnlyUniversityStudents(Model model) {
        List<Student> students = studentService.getAllStudentsByInstitutionType(SponsorshipType.CurrentlySponsored, InstitutionType.University);

        model.addAttribute("students", students);
        model.addAttribute("activeTab", "reportC");
        return "/reports/currentlySponsored";
    }

    @RequestMapping(value="/report/currentlySponsored/bank/{bankName}", method = RequestMethod.GET)
    public String showOnlySponsoredStudentsWithBank(@PathVariable String bankName, Model model) {
        List<Student> students = studentService.getAllStudentsWithBank(SponsorshipType.CurrentlySponsored, bankName);
        model.addAttribute("students", students);
        model.addAttribute("activeTab", "reportC");
        return "/reports/currentlySponsored";
    }

    @RequestMapping(value="/report/currentlySponsored/downloadCsv")
    public void downloadCsvForCurrentlySponsoredStudents(HttpServletResponse response) throws IOException {
        List<Student> listOfStudents = getStudentsBySponsorshipType(SponsorshipType.CurrentlySponsored);
        writerService.writeCsvFile("currentlySponsoredStudents.csv", HEADER_ROW, listOfStudents, response);
    }

    @RequestMapping(value = "/report/awaitingSponsorship", method = RequestMethod.GET)
    public String showStudentsAwaitingSponsorship(Model model) {
        List<Student> awaitingSponsorship = getStudentsBySponsorshipType(SponsorshipType.AwaitingSponsorship);
        model.addAttribute("students", awaitingSponsorship);
        model.addAttribute("activeTab", "reportA");
        return "/reports/awaitingSponsorship";
    }

    @RequestMapping(value = "/report/awaitingSponsorship/school", method = RequestMethod.GET)
    public String showOnlySchoolStudentsAwaitingSponsorship(Model model) {
        List<Student> students = studentService.getAllStudentsByInstitutionType(SponsorshipType.AwaitingSponsorship, InstitutionType.School);
        model.addAttribute("students", students);
        model.addAttribute("activeTab", "reportA");
        return "/reports/awaitingSponsorship";
    }

    @RequestMapping(value = "/report/awaitingSponsorship/university", method = RequestMethod.GET)
    public String showOnlyUniveristyStudentsAwaitingSponsorship(Model model) {
        List<Student> students = studentService.getAllStudentsByInstitutionType(SponsorshipType.AwaitingSponsorship, InstitutionType.University);
        model.addAttribute("students", students);
        model.addAttribute("activeTab", "reportA");
        return "/reports/awaitingSponsorship";
    }

    @RequestMapping(value="/report/awaitingSponsorship/bank/{bankName}", method = RequestMethod.GET)
    public String showOnlyAwaitingStudentsWithBank(@PathVariable String bankName, Model model) {
        List<Student> students = studentService.getAllStudentsWithBank(SponsorshipType.AwaitingSponsorship, bankName);
        model.addAttribute("students", students);
        model.addAttribute("activeTab", "reportA");
        return "/reports/awaitingSponsorship";
    }

    @RequestMapping(value="/report/awaitingSponsorship/downloadCsv")
    public void downloadCsvForStudentsAwaitingSponsorship(HttpServletResponse response) throws IOException {
        List<Student> listOfStudents = getStudentsBySponsorshipType(SponsorshipType.AwaitingSponsorship);
        writerService.writeCsvFile("studentsAwaitingSponsorship.csv", HEADER_ROW, listOfStudents, response);
    }

    @RequestMapping(value = "/report/formerlySponsored", method = RequestMethod.GET)
    public String showStudentsFormerlySponsored(Model model) {
        List<Student> formerlySponsored = getStudentsBySponsorshipType(SponsorshipType.FormerlySponsored);
        model.addAttribute("students", formerlySponsored);
        model.addAttribute("activeTab", "reportF");
        return "/reports/formerlySponsored";
    }

    @RequestMapping(value = "/report/formerlySponsored/school", method = RequestMethod.GET)
    public String showOnlySchoolStudentsFormerlySponsored(Model model) {
        List<Student> students = studentService.getAllStudentsByInstitutionType(SponsorshipType.FormerlySponsored, InstitutionType.School);
        model.addAttribute("students", students);
        model.addAttribute("activeTab", "reportF");
        return "/reports/formerlySponsored";
    }

    @RequestMapping(value = "/report/formerlySponsored/university", method = RequestMethod.GET)
    public String showOnlyUniveristyStudentsFormerlySponsored(Model model) {
        List<Student> students = studentService.getAllStudentsByInstitutionType(SponsorshipType.FormerlySponsored, InstitutionType.University);
        model.addAttribute("students", students);
        model.addAttribute("activeTab", "reportF");
        return "/reports/formerlySponsored";
    }

    @RequestMapping(value="/report/formerlySponsored/bank/{bankName}", method = RequestMethod.GET)
    public String showOnlyFormerlySponsoredWithBank(@PathVariable String bankName, Model model) {
        List<Student> students = studentService.getAllStudentsWithBank(SponsorshipType.FormerlySponsored, bankName);
        model.addAttribute("students", students);
        model.addAttribute("activeTab", "reportF");
        return "/reports/formerlySponsored";
    }

    @RequestMapping(value="/report/formerlySponsored/downloadCsv")
    public void downloadCsvForStudentsFormerlySponsored(HttpServletResponse response) throws IOException {
        List<Student> listOfStudents = getStudentsBySponsorshipType(SponsorshipType.FormerlySponsored);
        writerService.writeCsvFile("studentsFormerlySponsored.csv", HEADER_ROW, listOfStudents, response);
    }

    @RequestMapping(value = "/report/applicationExpired", method = RequestMethod.GET)
    public String showStudentsWhereApplicationExpired(Model model) {
        List<Student> students = getStudentsBySponsorshipType(SponsorshipType.ApplicationExpired);
        model.addAttribute("students", students);
        model.addAttribute("activeTab", "reportAE");
        return "/reports/applicationExpired";
    }

    @RequestMapping(value = "/report/applicationExpired/school", method = RequestMethod.GET)
    public String showOnlySchoolStudentsWhereApplicationExpired(Model model) {
        List<Student> students = studentService.getAllStudentsByInstitutionType(SponsorshipType.ApplicationExpired, InstitutionType.School);
        model.addAttribute("students", students);
        model.addAttribute("activeTab", "reportAE");
        return "/reports/applicationExpired";
    }

    @RequestMapping(value = "/report/applicationExpired/university", method = RequestMethod.GET)
    public String showOnlyUniveristyStudentsWhereApplicationExpired(Model model) {
        List<Student> students = studentService.getAllStudentsByInstitutionType(SponsorshipType.ApplicationExpired, InstitutionType.University);
        model.addAttribute("students", students);
        model.addAttribute("activeTab", "reportAE");
        return "/reports/applicationExpired";
    }

    @RequestMapping(value="/report/applicationExpired/bank/{bankName}", method = RequestMethod.GET)
    public String showOnlyApplicationExpiredWithBank(@PathVariable String bankName, Model model) {
        List<Student> students = studentService.getAllStudentsWithBank(SponsorshipType.ApplicationExpired, bankName);
        model.addAttribute("students", students);
        model.addAttribute("activeTab", "reportAE");
        return "/reports/applicationExpired";
    }

    @RequestMapping(value="/report/applicationExpired/downloadCsv")
    public void downloadCsvForStudentsWhereApplicationExpired(HttpServletResponse response) throws IOException {
        List<Student> listOfStudents = getStudentsBySponsorshipType(SponsorshipType.ApplicationExpired);
        writerService.writeCsvFile("studentsApplicationExpired.csv", HEADER_ROW, listOfStudents, response);
    }

    @RequestMapping(value="/report/agent", method = RequestMethod.GET)
    public String showAllStudentsByAgent(Model model) {
        model.addAttribute("agentTypeValues", AgentType.values());
        model.addAttribute("activeTab", "reportAgent");
        return "/reports/showByAgent";
    }

    @RequestMapping(value="/report/agent/{agentName}", method = RequestMethod.GET)
    public String showAllStudentsForAgent(@PathVariable String agentName, Model model) {
        AgentType agentType = AgentType.valueOf(agentName);
        List<Student> students = getStudentsByAgentType(agentType);
        model.addAttribute("agentTypeValues", AgentType.values());
        model.addAttribute("students", students);
        model.addAttribute("activeTab", "reportAgent");
        model.addAttribute("selectedAgent", agentName);
        return "/reports/showByAgent";
    }

    @RequestMapping(value="/report/agent/school/{agentName}", method = RequestMethod.GET)
    public String showAllSchoolStudentsForAgent(@PathVariable String agentName, Model model) {
        AgentType agentType = AgentType.valueOf(agentName);
        List<Student> students = getStudentsByAgentType(agentType, InstitutionType.School);
        model.addAttribute("agentTypeValues", AgentType.values());
        model.addAttribute("students", students);
        model.addAttribute("activeTab", "reportAgent");
        model.addAttribute("selectedAgent", agentName);
        return "/reports/showByAgent";
    }

    @RequestMapping(value="/report/agent/university/{agentName}", method = RequestMethod.GET)
    public String showAllUniversityStudentsForAgent(@PathVariable String agentName, Model model) {
        AgentType agentType = AgentType.valueOf(agentName);
        List<Student> students = getStudentsByAgentType(agentType, InstitutionType.University);
        model.addAttribute("agentTypeValues", AgentType.values());
        model.addAttribute("students", students);
        model.addAttribute("activeTab", "reportAgent");
        model.addAttribute("selectedAgent", agentName);
        return "/reports/showByAgent";
    }

    @RequestMapping(value="/report/agent/downloadCsv/{agentName}")
    public void downloadCsvForStudentsByAgent(@PathVariable String agentName, HttpServletResponse response) throws IOException {
        AgentType agentType = AgentType.valueOf(agentName);
        List<Student> listOfStudents = getStudentsByAgentType(agentType);
        writerService.writeCsvFile("studentsByAgent.csv", HEADER_ROW, listOfStudents, response);
    }

    @RequestMapping(value = "/report/allStudents", method = RequestMethod.GET)
    public String showAllStudents(Model model) {
        List<Student> students = studentService.getAllStudents();

        model.addAttribute("students", students);
        return "/reports/allStudents";
    }

    @RequestMapping(value = "/report/allStudents/downloadCSV")
    public void downloadCSV(HttpServletResponse response) throws IOException {

        String [] header = { "First Name", "Last Name", "Date of Birth", "Institution Type" };

        List<Student> listStudents = studentService.getAllStudents();

        BufferedWriter writer = new BufferedWriter(response.getWriter());
        try {
            response.setHeader("Content-Disposition", "attachment; filename=\"mytextfile.csv\"");
            for (String h : header) {
                writer.write(h);
                writer.write(",");
            }
            writer.newLine();

            for (Student aStudent : listStudents) {
                writer.write(aStudent.getFirstName());
                writer.write(",");
                writer.write(aStudent.getLastName());
                writer.write(",");
                writer.write(aStudent.getDateOfBirth().toString());
                writer.write(",");
                writer.write(aStudent.getEducation().getInstitutionType());
                writer.newLine();
            }

        } catch (IOException ex) {
        } finally {
            writer.flush();
            writer.close();
        }

    }

    private List<Student> getStudentsBySponsorshipType(SponsorshipType sponsorshipType) {
        return studentService.getAllStudents().stream()
                .filter(student -> student.getSponsorship().getSponsorshipType().equals(sponsorshipType.getName()))
                .collect(Collectors.toList());
    }

    private List<Student> getStudentsByAgentType(AgentType agentType) {
        return studentService.getAllStudents().stream()
                .filter(student -> student.getEducation() != null &&
                        student.getEducation().getAgent() != null &&
                        student.getEducation().getAgent().equals(agentType.getName()))
                .collect(Collectors.toList());
    }

    private List<Student> getStudentsByAgentType(AgentType agentType, InstitutionType institutionType) {
        return studentService.getAllStudents().stream()
                .filter(student -> student.getEducation() != null &&
                        student.getEducation().getAgent() != null &&
                        student.getEducation().getAgent().equals(agentType.getName()) &&
                        student.getEducation().getInstitutionType().equals(institutionType.name()))
                .collect(Collectors.toList());
    }
}
