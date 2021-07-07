package com.company;

import exception.InvalidDOBException;
import exception.InvalidFullNameException;
import exception.InvalidPhoneNumberException;
import student.GoodStudent;
import student.NormalStudent;
import student.Student;

import java.io.*;
import java.util.*;

public class Main {
    static public List<Student> studentList = new ArrayList<>();
    final static String REGEX_FULL_NAME = "[\\w|\\s]{10,50}";
    final static String REGEX_DATE_OF_BIRTH = "[\\d]{2}/[\\d]{2}/[\\d]{4}";
    final static String REGEX_PHONE = "^0(90|98|91|31|35)[\\d]{7}$";

    static private void readInput() {
        File file = new File("..\\Java-Practice2\\src\\com\\company\\List_student.txt");
        FileReader fileReader;

        try {
            fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while (true) {

                if ((line = bufferedReader.readLine()) != null) {
                    String[] parts = line.trim().split("-");
                    if (!parts[0].matches(REGEX_FULL_NAME)) throw new InvalidFullNameException();
                    if (!parts[1].matches(REGEX_DATE_OF_BIRTH)) throw new InvalidDOBException();
                    if (!parts[3].matches(REGEX_PHONE)) throw new InvalidPhoneNumberException();
                    if (parts[5].toLowerCase().equals("good")) {
                        GoodStudent goodStudent = new GoodStudent(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], Float.parseFloat(parts[6]), parts[7]);
                        studentList.add(goodStudent);
                    } else {
                        NormalStudent normalStudent = new NormalStudent(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], Integer.parseInt(parts[6]), Float.parseFloat(parts[7]));
                        studentList.add(normalStudent);
                    }
                } else break;
            }
        } catch (IOException e) {
            System.out.println("Input files have unknown errors !!!");
        }
    }


    public static void main(String[] args) {
        // write your code here
        readInput();

        Scanner scanner = new Scanner(System.in);
        String value = (scanner.nextLine());
        switch (value) {
            case "sort tang dan":
                studentList.sort(Comparator.comparing(Student::getPhoneNumber));
                for (Student student : studentList) {
                    student.showMyInfor();
                }
                break;
            case "sort giam dan":
                studentList.sort(new Comparator<Student>() {
                    @Override
                    public int compare(Student o1, Student o2) {
                        return o1.getFullName().compareTo(o2.getFullName());
                    }
                });
                for (Student student : studentList
                ) {
                    student.showMyInfor();
                }
                break;
            case "11":
            case "12":
            case "13":
            case "14":
            case "15":
                int count = Integer.parseInt(value);
                if (count >= studentList.size()) {
                    for (Student student : studentList) {
                        student.showMyInfor();
                    }
                } else {
                    List<Student> officialList = new ArrayList<>();
                    List<GoodStudent> subListGood = new ArrayList<>();
                    List<NormalStudent> subListNormal = new ArrayList<>();
                    for (Student student : studentList) {
                        if (student.getGradeLevel().toLowerCase().equals("good"))
                            subListGood.add((GoodStudent) student);
                        else subListNormal.add((NormalStudent) student);
                    }
                    if (count < subListGood.size()) {
                        subListGood.sort((o1, o2) -> {
                            if (o1.getGpa() < o2.getGpa()) {
                                return -1;
                            } else if (o1.getGpa() == o2.getGpa()) {
                                return (o2.getFullName().toLowerCase().compareTo(o1.getFullName().toLowerCase()));
                            } else {
                                return 1;
                            }
                        });
                        Collections.reverse(subListGood);
                        while (count != subListGood.size()) {
                            subListGood.remove(subListGood.size() - 1);
                        }
                        for (Student student : subListGood) {
                            student.showMyInfor();
                        }
                    } else if (count == subListGood.size()) {
                        for (Student student : subListGood) {
                            student.showMyInfor();
                        }
                    } else {
                        subListNormal.sort(new Comparator<NormalStudent>() {
                            @Override
                            public int compare(NormalStudent o1, NormalStudent o2) {
                                if (o1.getEnglishScore() < o2.getEnglishScore()) {
                                    return -1;
                                } else if (o1.getEnglishScore() == o2.getEnglishScore()) {
                                    return (o2.getFullName().toLowerCase().compareTo(o1.getFullName().toLowerCase()));
                                } else {
                                    return 1;
                                }
                            }
                        });
                        Collections.reverse(subListNormal);
                        for (Student student : subListGood) {
                            student.showMyInfor();
                        }
                        int sub = count - subListGood.size();
                        for (int i = 0; i < sub; i++) {
                            subListNormal.get(i).showMyInfor();
                        }
                    }
                }
                break;
            default:
                System.out.println("Wrong type value");
                break;
        }
    }
}
