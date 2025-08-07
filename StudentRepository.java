package com.example.prj;
//
//import java.util.List;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
	
	 @Query("SELECT s.studentId as studentId,age as age,s.studentName as name,"
	 		+ "s.email as email,a.streetName as streetName,a.city as city,a.pincode as pincode,q.courseName as courseName,q.percentage as percentage,q.passOut as passOut,q.university as university"
	 		+ " FROM Student s join Address a on a.studentId = s.studentId join Qualification q on q.studentId=s.studentId where s.studentId = :id ")
	 List<AllDTO> getStudentDetailsById(@Param("id") int id);
	 
	 
	 @Query("select new com.example.prj.Student(s.studentId,s.studentName,s.email,s.dob,s.phoneNo,s.age) from Student s join Address a on a.studentId = s.studentId where a.pincode = :pincode")
	 List<Student> getDetails(@Param("pincode")int pincode);
}
