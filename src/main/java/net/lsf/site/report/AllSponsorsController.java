package net.lsf.site.report;

import net.lsf.data.SponsorDTO;
import net.lsf.service.ReportService;
import net.lsf.service.SponsorService;
import net.lsf.service.WriterService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@Controller
public class AllSponsorsController {

    @Autowired
    WriterService writerService;

    @Autowired
    ReportService reportService;

    @Autowired
    SponsorService sponsorService;

    final static Logger LOG = Logger.getLogger("AllSponsorsController.class");

    private static final String[] ALL_SPONSORS_HEADER_ROW = {"Sponsor id", "Sponsor name", "Email", "Phone 1", "Phone 2", "Currently sponsored student(s)"};

    @RequestMapping(value = "/report/allSponsors", method = RequestMethod.GET)
    public String showALlSponsors(Model model) throws ReportException {
        try {
            List<SponsorDTO> allSponsors = reportService.getAllSponsors();

            model.addAttribute("sponsors", allSponsors);
            model.addAttribute("activeTab", "reportS");
            return "/reports/allSponsors";
        } catch (Exception e) {
            model.addAttribute("error", new ReportException());
            LOG.error("Something bad:", e);
        }
        return "/reports/list";
    }

    @RequestMapping(value="/report/allSponsors/downloadCsv")
    public void downloadCsvForAllSponsors(HttpServletResponse response) throws IOException, ReportException {
        List<SponsorDTO> sponsors = reportService.getAllSponsors();

        writerService.writeCsvFile("allSponsors.csv", ALL_SPONSORS_HEADER_ROW, sponsors, response);
    }
}