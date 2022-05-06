package in.ashokit.repository;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import in.ashokit.entity.Contact;

@Repository

public interface ContactRepository extends JpaRepository<Contact, Integer> {

	public Page<Contact> findAll(Pageable pageable);
	
	//@Query("SELECT c FROM Contact c WHERE c.name LIKE %?1%")
	@Query("SELECT c FROM Contact c WHERE c.name =?1")
	public Page<Contact> search(String searchword,Pageable pageable);
}
