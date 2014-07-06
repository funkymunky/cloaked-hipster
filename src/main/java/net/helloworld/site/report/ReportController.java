package net.helloworld.site.report;

import net.helloworld.model.Student;
import net.helloworld.service.StudentService;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ReportController {

    @Autowired
    StudentService studentService;


    @RequestMapping(value = "/report/list", method = RequestMethod.GET)
    public String listAllStudents(Model model) {
        return "/reports/list";
    }

    @RequestMapping(value = "/report/allStudents", method = RequestMethod.GET)
    public String showAllStudents(Model model) {
        List<Student> students = studentService.getAllStudents();

        model.addAttribute("students", students);
        return "/reports/allStudents";
    }

    @RequestMapping(value="/report/html", method = RequestMethod.GET)
    public ModelAndView generateStudentReport(ModelAndView model) {

        Map<String,Object> parameterMap = new HashMap<>();

        List<Student> studentList = studentService.getAllStudents();
        JRDataSource JRdataSource = new JRBeanCollectionDataSource(studentList);
        parameterMap.put("dataSource", JRdataSource);

        //pdfReport bean has ben declared in the jasper-views.xml file
        model = new ModelAndView("htmlReport", parameterMap);
        return model;

    }

}
