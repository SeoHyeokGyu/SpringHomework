package kr.ac.hansung.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.ac.hansung.model.Lecture;
import kr.ac.hansung.service.LectureService;

@Controller
public class LectureController {

	@Autowired
	private LectureService offerService;

	@RequestMapping("/specifier")
	public String specifier(Model model, @RequestParam("year") int year,@RequestParam("semester") int semester) {
		List<Lecture> specifier = offerService.getOffer(year, semester);
		model.addAttribute("specifier",specifier);
		return "specifier";
	}
	
	@RequestMapping("/lectures")
	public String showOffers(Model model) {
		List<Lecture> lectures = offerService.getCurrent();
		model.addAttribute("lectures", lectures);

		return "lectures";
	}

	@RequestMapping("/createlecture")
	public String createOffer(Model model) {
//		임의적으로 객체 생성하여 view 에서 사용할수 있게함 .
		model.addAttribute("lecture", new Lecture());

		return "createlecture";
	}

	@RequestMapping("/docreate")
//	데이터 바인딩. 자동으로 offer 들어간다.	
	public String docreate(Model model, @Valid Lecture lecture, BindingResult result) {

		if(result.hasErrors()) {
			System.out.println("=== Web Form data doen not validated ===");
			List<ObjectError> errors = result.getAllErrors();
//			메세지를 지정해줘야한다. 	
			for(ObjectError error: errors) {
				System.out.println(error.getDefaultMessage());
			}
			return "createlecture";
		}
//		컨트롤러 - 서비스 - dao
		offerService.insert(lecture);

		return "lecturecreated";
	}
}
