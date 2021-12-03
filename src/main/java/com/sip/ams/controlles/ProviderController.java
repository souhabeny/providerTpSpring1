package com.sip.ams.controlles;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sip.ams.entities.Provider;
import com.sip.ams.repositories.ProviderRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
@RequestMapping("/provider/")
public class ProviderController {
	private final ProviderRepository providerRepository;
	@Autowired
	 public ProviderController(ProviderRepository providerRepository) {
	 this.providerRepository = providerRepository;
	 }
	@GetMapping("list")
	 //@ResponseBody
	 public String listProviders(Model model) {
		

	 model.addAttribute("providers",providerRepository.findAll());
	 List<Provider> lp =(List<Provider>)providerRepository.findAll();
	
	
	 return "provider/listProvider";

	
	 //System.out.println(lp);

	 //return "Nombre de fournisseur = " + lp.size();
	 }
	@PostMapping("add")
	 public String addProvider(@Valid Provider provider, BindingResult result, Model model) {
	 if (result.hasErrors()) {
	 return "provider/addProvider";
	 }
	 providerRepository.save(provider);
	 return "redirect:list";
	 }
	@GetMapping("/add")
	public String getAddFormProvider(Model model) 
	{  model.addAttribute("provider",new Provider());
		return "provider/addProvider";
		
	}
	@GetMapping("delete/{id}")
	 public String deleteProvider(@PathVariable("id") long id, Model model) {
	 Provider provider = providerRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid provider Id:" + id));

	 System.out.println("suite du programme...");

	 providerRepository.delete(provider);

	 /*model.addAttribute("providers",
	providerRepository.findAll());
	 return "provider/listProviders";*/
	 return "redirect:../list";
	 }
	@GetMapping("edit/{id}")
	 public String showProviderFormToUpdate(@PathVariable("id") long id, Model model) {
	 Provider provider = providerRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid provider Id:" + id));
	 model.addAttribute("provider", provider);

	 return "provider/updateProvider";
	 }
	@PostMapping("update")
	 public String updateProvider(@Valid Provider provider, BindingResult result, Model model) {
	 providerRepository.save(provider);
	 return"redirect:list";

	 }
}
