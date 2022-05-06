package in.ashokit.controller.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.constants.AppConstants;
import in.ashokit.entity.Contact;
import in.ashokit.props.AppProperties;
import in.ashokit.service.IContactService;

@RestController
@RequestMapping("contact-app")
@CrossOrigin("*")
public class ContactRestController {

	//Injected IContactService dependency 
	@Autowired
	private IContactService service;
	
	//Injected AppProperties Object
	@Autowired
	private AppProperties appProps;
	
	//save contact
	@PostMapping("/contact")
	public String saveContact(@RequestBody Contact contact){
	
		Map<String, String> messages = appProps.getMessages();
		
		String mesg="";
		boolean status=service.saveContact(contact);
		if(status) {
			System.out.println(AppConstants.CONTACT_SAVE_SUCC);
			mesg=messages.get(AppConstants.CONTACT_SAVE_SUCC);
			
		}else {
			mesg=messages.get(AppConstants.CONTACT_SAVE_FAIL);
		}
		return mesg;
	}
	
	//delete a contact
	@DeleteMapping("/contact/{id}")
	public String deleteContact(@PathVariable Integer id){
		
		Map<String, String> messages = appProps.getMessages();
		
		String mesg="";
		boolean status=service.deleteContactById(id);
		if(status) {
			mesg=messages.get(AppConstants.CONTACT_DEL_SUCC);
		}else {
			mesg=messages.get(AppConstants.CONTACT_DEL_FAIL);
		}
		return mesg;
		
	}
	
	//get a contact 
	@GetMapping("/contact/{id}")
	public Contact editContact(@PathVariable Integer id){
		return service.getContactById(id);
	}
	
	//get all contacts by using pagination technique--------------
	//per page 5 contacts
	//current page=0
	@GetMapping("/contacts/{page}")
	public List<Contact> getAll(@PathVariable Integer page){
	
		System.out.println("get all contacts using pagination..");
		
		Integer size=5;//no of contacts per pages
		return service.getContactsByUsingPagination(page, size);
		
	}
	
	//search contacts by name ....
	@GetMapping("/search/contacts/{searchWord}/{page}")
	public List<Contact> getAll(@PathVariable String searchWord,
			@PathVariable Integer page){
	
		
		System.out.println("CONTACTS ARE SEARCHING ....");
		
		Integer size=5;//no of contacts per pages
		return service.searchContactsByName(searchWord, page, size);
		
		
	}
	
	//returns total no of pages
	@GetMapping("/contacts/pages")
	public Integer getTotalPages() {
		Integer page=0;
		Integer size=5;
		System.out.println(service.totalPages(page, size));
		
		
		return service.totalPages(page, size);
	}
	
	//returns all contacts-----------------------------
	
	 @GetMapping("/contacts") 
	 public List<Contact> getAll(){
	 
	  
	  return service.getAllContacts();
	 }
	 
	 
}
