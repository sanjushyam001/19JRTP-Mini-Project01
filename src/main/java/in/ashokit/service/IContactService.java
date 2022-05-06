package in.ashokit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import in.ashokit.entity.Contact;

public interface IContactService {

	public boolean saveContact(Contact contact);
	public boolean deleteContactById(Integer id);
	public Contact getContactById(Integer id);
	public List<Contact> getAllContacts();
	public List<Contact> getContactsByUsingPagination(Integer page , Integer size);
	public Integer totalPages(Integer page,Integer size);
	public List<Contact> searchContactsByName(String searchWord,Integer page,Integer size);
	
	
}
