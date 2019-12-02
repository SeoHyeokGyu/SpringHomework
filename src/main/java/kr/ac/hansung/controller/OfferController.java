package kr.ac.hansung.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.hansung.model.Offer;
import kr.ac.hansung.service.OfferService;

@Controller
public class OfferController {

	@Autowired
	private OfferService offerService;

	@RequestMapping("/offers")
	public String showOffers(Model model) {
		List<Offer> offers = offerService.getCurrent();
		model.addAttribute("offers", offers);

		return "offers";
	}

	@RequestMapping("/createoffer")
	public String createOffer(Model model) {
//		임의적으로 객체 생성하여 view 에서 사용할수 있게함 .
		model.addAttribute("offer", new Offer());

		return "createoffer";
	}

	@RequestMapping("/docreate")
//	데이터 바인딩. 자동으로 offer 들어간다.	
	public String docreate(Model model, @Valid Offer offer, BindingResult result) {

		if(result.hasErrors()) {
			System.out.println("=== Web Form data doen not validated ===");
			List<ObjectError> errors = result.getAllErrors();
//			메세지를 지정해줘야한다. 	
			for(ObjectError error: errors) {
				System.out.println(error.getDefaultMessage());
			}
			return "createoffer";
		}
//		컨트롤러 - 서비스 - dao
		offerService.insert(offer);

		return "offercreated";
	}
}
