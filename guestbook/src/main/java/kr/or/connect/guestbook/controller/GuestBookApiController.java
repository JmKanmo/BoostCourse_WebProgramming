package kr.or.connect.guestbook.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.guestbook.dto.GuestBook;
import kr.or.connect.guestbook.service.GuestBookService;

@RestController
@RequestMapping(path = "/guestbooks")
public class GuestBookApiController {
	private GuestBookService guestBookService;

	public GuestBookApiController(GuestBookService guestBookService) {
		// TODO Auto-generated constructor stub
		this.guestBookService = guestBookService;
	}

	@GetMapping
	public Map<String, Object> list(@RequestParam(name = "start", required = false, defaultValue = "0") int start) {
		List<GuestBook> list = guestBookService.getGuestBooks(start);
		int count = guestBookService.getCount();
		int pageCount = count / GuestBookService.LIMIT;
		if (count % GuestBookService.LIMIT > 0)
			pageCount++;
		List<Integer> pageStartList = new ArrayList<>();
		for (int i = 0; i < pageCount; i++)
			pageStartList.add(i * GuestBookService.LIMIT);
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("count", count);
		map.put("pageStartList", pageStartList);
		return map;
	}

	@PostMapping
	public GuestBook write(@RequestBody GuestBook guestBook, HttpServletRequest request) {
		String clientIp = request.getRemoteAddr();
		GuestBook resultGuestBook = guestBookService.addGuestBook(guestBook, clientIp);
		return resultGuestBook;
	}

	@DeleteMapping("/{id}")
	public Map<String, String> delete(@PathVariable(name = "id") Long id, HttpServletRequest request) {
		String clientIp = request.getRemoteAddr();
		int delCount = guestBookService.deleteGuestBook(id, clientIp);
		return Collections.singletonMap("success", delCount > 0 ? "true" : "false");
	}
}
