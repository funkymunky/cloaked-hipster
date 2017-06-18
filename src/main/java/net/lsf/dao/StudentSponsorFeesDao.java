package net.lsf.dao;

import net.lsf.data.StudentFeeDTO;

import java.text.ParseException;

public interface StudentSponsorFeesDao {

    StudentFeeDTO getStudentSponsorFeeInformation(int studentId);

    void updateFeesForStudentAndSponsor(StudentFeeDTO studentSponsorFee, int studentId) throws ParseException;
}
