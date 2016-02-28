package net.lsf.site.report;

import net.lsf.AgentType;
import net.lsf.InstitutionType;
import net.lsf.model.Student;
import net.lsf.service.ReportService;
import net.lsf.service.WriterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class StudentsByAgentController {

    @Autowired
    ReportService reportService;

    @Autowired
    WriterService writerService;

    @RequestMapping(value="/report/agent", method = RequestMethod.GET)
    public String showAllStudentsByAgent(Model model) {
        model.addAttribute("agentTypeValues", AgentType.values());
        model.addAttribute("activeTab", "reportAgent");
        return "/reports/showByAgent";
    }

    @RequestMapping(value="/report/agent/{agentName}", method = RequestMethod.GET)
    public String showAllStudentsForAgent(@PathVariable String agentName, Model model) throws ReportException {
        AgentType agentType = AgentType.valueOf(agentName);
        List<Student> students = reportService.getStudentsByAgentType(agentType);
        model.addAttribute("agentTypeValues", AgentType.values());
        model.addAttribute("students", students);
        model.addAttribute("activeTab", "reportAgent");
        model.addAttribute("selectedAgent", agentName);
        return "/reports/showByAgent";
    }

    @RequestMapping(value="/report/agent/school/{agentName}", method = RequestMethod.GET)
    public String showAllSchoolStudentsForAgent(@PathVariable String agentName, Model model) throws ReportException {
        AgentType agentType = AgentType.valueOf(agentName);
        List<Student> students = reportService.getStudentsByAgentType(agentType, InstitutionType.School);
        model.addAttribute("agentTypeValues", AgentType.values());
        model.addAttribute("students", students);
        model.addAttribute("activeTab", "reportAgent");
        model.addAttribute("selectedAgent", agentName);
        model.addAttribute("activeFilter", "school");
        return "/reports/showByAgent";
    }

    @RequestMapping(value="/report/agent/university/{agentName}", method = RequestMethod.GET)
    public String showAllUniversityStudentsForAgent(@PathVariable String agentName, Model model) throws ReportException {
        AgentType agentType = AgentType.valueOf(agentName);
        List<Student> students = reportService.getStudentsByAgentType(agentType, InstitutionType.University);
        model.addAttribute("agentTypeValues", AgentType.values());
        model.addAttribute("students", students);
        model.addAttribute("activeTab", "reportAgent");
        model.addAttribute("selectedAgent", agentName);
        model.addAttribute("activeFilter", "uni");
        return "/reports/showByAgent";
    }

    @RequestMapping(value="/report/agent/downloadCsv/{agentName}")
    public void downloadCsvForStudentsByAgent(@PathVariable String agentName, HttpServletResponse response) throws IOException, ReportException {
        AgentType agentType = AgentType.valueOf(agentName);
        List<Student> listOfStudents = reportService.getStudentsByAgentType(agentType);
        writerService.writeCsvFile("studentsByAgent.csv", ReportController.HEADER_ROW, listOfStudents, response, false, false);
    }

}
