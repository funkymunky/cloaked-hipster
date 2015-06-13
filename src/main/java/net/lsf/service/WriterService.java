package net.lsf.service;

import net.lsf.model.Student;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface WriterService {

    public void writeCsvFile(String filename, String[] headerRow, List<Student> content, HttpServletResponse response, boolean studentSpecifics, boolean addSponsorDetails) throws IOException;
}
