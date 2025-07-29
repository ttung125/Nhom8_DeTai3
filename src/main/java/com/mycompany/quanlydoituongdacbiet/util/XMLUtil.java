/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlydoituongdacbiet.util;

import com.mycompany.quanlydoituongdacbiet.entity.ExamBlockXML;
import com.mycompany.quanlydoituongdacbiet.entity.Subject;
import com.mycompany.quanlydoituongdacbiet.entity.SubjectXML;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class XMLUtil {
    // Khối trong Student
    public static ExamBlockXML readExamBlockFromFile(String filePath) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                return new ExamBlockXML(); // danh sách rỗng nếu chưa có
            }

            JAXBContext context = JAXBContext.newInstance(ExamBlockXML.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (ExamBlockXML) unmarshaller.unmarshal(file);

        } catch (JAXBException e) {
            e.printStackTrace();
            return new ExamBlockXML();
        }
    }
    // Môn trong Khối
    public static List<String> readSubjectNamesFromFile(String filePath) {
        try {
            JAXBContext context = JAXBContext.newInstance(SubjectXML.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            SubjectXML subjectXML = (SubjectXML) unmarshaller.unmarshal(new File(filePath));

            if (subjectXML.getSubjects() == null) return new ArrayList<>();

            return subjectXML.getSubjects()
                    .stream()
                    .map(Subject::getSubjectName)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}

