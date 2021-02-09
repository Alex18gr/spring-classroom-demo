package io.alexc.classroomdemo.controller;

import io.alexc.classroomdemo.dto.ClassroomDto;
import io.alexc.classroomdemo.entity.Classroom;
import io.alexc.classroomdemo.entity.Student;
import io.alexc.classroomdemo.error.ClassroomNotFoundException;
import io.alexc.classroomdemo.error.StudentNotFoundException;
import io.alexc.classroomdemo.jasperreports.SimpleReportExporter;
import io.alexc.classroomdemo.jasperreports.SimpleReportFiller;
import io.alexc.classroomdemo.service.ClassroomService;
import io.alexc.classroomdemo.service.ClassroomServiceImpl;
import io.alexc.classroomdemo.service.StudentService;
import io.alexc.classroomdemo.service.StudentServiceImpl;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.fill.ReportFiller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.awt.print.Pageable;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("classrooms")
// For fixing the CORS issues due to different domain with the front-end
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4201"}, maxAge = 3600)
public class ClassroomEntityController {

    private final ClassroomService classroomService;

    private final StudentService studentService;

    private final SimpleReportFiller reportFiller;

    private final SimpleReportExporter reportExporter;

    public ClassroomEntityController(ClassroomService classroomService, StudentService studentService, SimpleReportFiller reportFiller, SimpleReportExporter reportExporter) {
        this.classroomService = classroomService;
        this.studentService = studentService;
        this.reportFiller = reportFiller;
        this.reportExporter = reportExporter;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<ClassroomDto> getClassrooms() {
        return classroomService.findAllClassrooms();
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Classroom postClassroom(
            @RequestBody Classroom classroom
    ) {
        return this.classroomService.saveClassroom(classroom);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Classroom getClassroom(@PathVariable Integer id) {
        return this.classroomService.findById(id)
                .orElseThrow(() -> new ClassroomNotFoundException(id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Classroom putClassroom(
            @RequestBody Classroom classroom,
            @PathVariable Integer id) {
        return classroomService.findById(id)
                .map(c -> {
                    c.setName(classroom.getName());
                    c.setStudents(classroom.getStudents());
                    return this.classroomService.saveClassroom(classroom);
                })
                .orElseThrow(() -> new ClassroomNotFoundException(id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteClassroom(
            @PathVariable Integer id
    ) {
        this.classroomService.deleteClassroomById(id);
    }

    @RequestMapping(value = "/{classroomId}/students", method = RequestMethod.GET)
    public Collection<Student> getClassroomStudents(@PathVariable Integer classroomId) {
        Classroom classroom = this.classroomService.findById(classroomId)
                .orElseThrow(() -> new ClassroomNotFoundException(classroomId));
        return classroom.getStudents();
    }

    @RequestMapping(value = "/{classroomId}/students", method = RequestMethod.POST)
    public Student postClassroomStudent(
            @PathVariable Integer classroomId,
            @RequestBody Student student
    ) {
        Classroom classroom = this.classroomService.findById(classroomId)
                .orElseThrow(() -> new ClassroomNotFoundException(classroomId));
        student.setClassroom(classroom);
        return this.studentService.save(student);
    }
    @RequestMapping(value = "/{classroomId}/students/{studentId}", method = RequestMethod.GET)
    public Student getClassroomStudentById(
            @PathVariable Integer classroomId,
            @PathVariable Integer studentId
    ) {
        return this.studentService.findStudentByIdAndClassroomId(classroomId, studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId, classroomId));
    }
    @RequestMapping(value = "/{classroomId}/students/{studentId}", method = RequestMethod.PUT)
    public Student putClassroomStudent(
            @PathVariable Integer classroomId,
            @PathVariable Integer studentId,
            @RequestBody Student student
    ) {
        Classroom classroom = this.classroomService.findById(classroomId)
                .orElseThrow(() -> new ClassroomNotFoundException(classroomId));
        student.setClassroom(classroom);
        return this.studentService.findStudentById(studentId)
                .map(s -> {
                    s.setClassroom(classroom);
                    s.setBirthDate(student.getBirthDate());
                    s.setFirstName(student.getFirstName());
                    s.setLastName(student.getLastName());
                    s.setGrade(student.getGrade());
                    return this.studentService.save(s);
                })
                .orElseThrow(() -> new StudentNotFoundException(studentId, classroomId));
    }
    @RequestMapping(value = "/{classroomId}/students/{studentId}", method = RequestMethod.DELETE)
    public void deleteClassroomStudent(@PathVariable Integer classroomId, @PathVariable Integer studentId) {
        this.studentService.deleteStudent(this.studentService.findStudentByIdAndClassroomId(classroomId, studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId, classroomId)));
    }

    @RequestMapping(value = "/{classroomId}/students/report", method = RequestMethod.GET)
    public void getClassroomStudentsReport(
            HttpServletResponse response,
            @PathVariable Integer classroomId,
            @RequestParam(required = false) String exportType
    ) {

        Classroom classroom = this.classroomService.findById(classroomId)
                .orElseThrow(() -> new ClassroomNotFoundException(classroomId));

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(classroom.getStudents());



        if (exportType == null || exportType.equals("pdf")) {
            try {
                reportFiller.setReportFileName("studentsReport");

                reportFiller.setDataSource(dataSource);

                reportFiller.prepareReport();

                reportExporter.setJasperPrint(reportFiller.getJasperPrint());

                response.setContentType("application/pdf");
                reportExporter.exportToPdf("temp1", "Alexc", response.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (exportType.equals("xlsx")) {
            try {
                reportFiller.setReportFileName("studentsExcelReport");

                reportFiller.setDataSource(dataSource);

                reportFiller.prepareReport();

                reportExporter.setJasperPrint(reportFiller.getJasperPrint());

                response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                reportExporter.exportToXlsx("temp1", "Grades", response.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }



    }

}
