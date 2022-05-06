package in.ashokit.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;





@Data
@NoArgsConstructor
@Entity
@XmlRootElement
@Table(name = "contact_tabl")
public class Contact {

	@Id
	@GeneratedValue
	@Column(name="id")
	private Integer id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="email")
	private String email;
	
	@Column(name="phone")
	private String phone;
	
	@Column(name = "create_date",updatable = false)
	@CreationTimestamp
	private LocalDate createdDate;
	
	@Column(name = "update_date",insertable = false)
	@UpdateTimestamp
	private LocalDate updatedDate;
	
	@Column(name="active_sw")
	private String activeSw;
}
