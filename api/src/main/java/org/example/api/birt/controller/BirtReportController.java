package org.example.api.birt.controller;

import org.eclipse.birt.report.engine.api.EngineException;
import org.example.api.birt.domain.OutputType;
import org.example.api.birt.domain.Report;
import org.example.api.birt.service.BirtReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class BirtReportController {
    private final BirtReportService reportService;

    public BirtReportController(BirtReportService reportService) {
        this.reportService = reportService;
    }

    @RequestMapping(produces = "application/json", method = RequestMethod.GET, value = "/report")
    @ResponseBody
    public List<Report> listReports() {
        return reportService.getReports();
    }

    @RequestMapping(produces = "application/json", method = RequestMethod.GET, value = "/report/reload")
    @ResponseBody
    public ResponseEntity reloadReports() {
        try {
            reportService.loadReports();
        } catch (EngineException | IOException e) {
            return ResponseEntity.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/report/{name}")
    @ResponseBody
    public void generateFullReport(HttpServletResponse response, HttpServletRequest request,
                                   @PathVariable("name") String name, @RequestParam("output") String output) {
        OutputType format = OutputType.from(output);
        reportService.generateMainReport(name, format, response, request);
    }
}
