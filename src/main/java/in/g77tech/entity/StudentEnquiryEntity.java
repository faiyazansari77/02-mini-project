package in.g77tech.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "G77_STUDENT_ENQUIRY")
public class StudentEnquiryEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer enqId;
	private String studentName;
	private String classMode;
	private String courseName;
	private String enqStatus;
	
	@CreationTimestamp
	private LocalDate dateCreate;
	
	@UpdateTimestamp
	private LocalDate lastUdate;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserDtlsEntity user;

}
