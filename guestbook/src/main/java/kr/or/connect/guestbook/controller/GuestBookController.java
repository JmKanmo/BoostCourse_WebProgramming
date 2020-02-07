package kr.or.connect.guestbook.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.connect.guestbook.dto.GuestBook;
import kr.or.connect.guestbook.service.GuestBookService;

@Controller
public class GuestBookController {

	private GuestBookService guestbookService;

	public GuestBookController(GuestBookService guestbookService) {
		this.guestbookService = guestbookService;
	}

	@GetMapping(path = "/list")
	public String list(@RequestParam(name = "start", required = false, defaultValue = "0") int start, ModelMap model) {
		List<GuestBook> list = guestbookService.getGuestBooks(start);
		int count = guestbookService.getCount();
		int pageCount = count / GuestBookService.LIMIT;
		if (count % GuestBookService.LIMIT > 0)
			pageCount++;
		List<Integer> pageStartList = new ArrayList<>();
		for (int i = 0; i < pageCount; i++) {
			pageStartList.add(i * GuestBookService.LIMIT);
		}
		model.addAttribute("list", list);
		model.addAttribute("count", count);
		model.addAttribute("pageStartList", pageStartList);
		return "list";
	}

	@PostMapping(path = "/write")
	public String write(@ModelAttribute GuestBook guestbook, HttpServletRequest request) {
		String clientIp = request.getRemoteAddr();
		System.out.println("clientIp : " + clientIp);
		guestbookService.addGuestBook(guestbook, clientIp);
		return "redirect:list";
	}
}