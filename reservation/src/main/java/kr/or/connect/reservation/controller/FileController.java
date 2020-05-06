package kr.or.connect.reservation.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.or.connect.reservation.dto.ReviewData;
import kr.or.connect.reservation.service.ReviewWritepageService;

@Controller
public class FileController {
	@Autowired
	private ReviewWritepageService reviewWritepageService;

	@RequestMapping(value = "imgLoad.do")
	public void imgLoad(HttpServletRequest req, HttpServletResponse res) throws Exception {
		Integer imageId = Integer.parseInt(req.getParameter("imageId"));
		if (imageId.equals(0)) {
			// 요청 이미지가 없으면 load를 수행하지않고 종료
			return;
		}
		String[] splited = reviewWritepageService.selectImage(imageId).split("/");
		String imagePath = "C:\\tmp\\" + splited[0] + "\\";
		File file = new File(imagePath, splited[1]);
		res.setHeader("Content-Length", String.valueOf(file.length()));
		res.setHeader("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");
		Files.copy(file.toPath(), res.getOutputStream());
	}

	private ReviewData getFormattedReviewData(MultipartHttpServletRequest param, MultipartFile file, String uuid)
			throws Exception {
		ReviewData ret = new ReviewData.Builder().reservationId_(Integer.valueOf(param.getParameter("resrvId")))
				.productId_(Integer.valueOf(param.getParameter("productId")))
				.score_(Double.valueOf(param.getParameter("starGrade")))
				.comment_(String.valueOf(param.getParameter("reviewContent")))
				.fileName_((uuid + String.valueOf(file.getOriginalFilename())))
				.saveFileName_("img/" + uuid + String.valueOf(file.getOriginalFilename()))
				.conetentType_(String.valueOf(file.getContentType())).build();

		return ret;
	}

	@PostMapping("/reservations")
	public String upload(MultipartHttpServletRequest param) {
		MultipartFile file = param.getFile("imageFile");
		String uuid = UUID.randomUUID().toString();
		String fileName = uuid + file.getOriginalFilename();
		String redirectedURL = "redirect:/myreservation?resrv_email=%s";

		try (FileOutputStream fos = new FileOutputStream("c:/tmp/img/" + fileName);
				InputStream is = file.getInputStream();) {
			int readCount = 0;
			byte[] buffer = new byte[1024];
			while ((readCount = is.read(buffer)) != -1) {
				fos.write(buffer, 0, readCount);
			}
			reviewWritepageService.insertReviewData(getFormattedReviewData(param, file, uuid), file.isEmpty());
		} catch (Exception ex) {
			throw new RuntimeException("Error happened");
		}
		redirectedURL = redirectedURL.format(redirectedURL, String.valueOf(param.getParameter("resrvEmail")));
		return redirectedURL;
	}
}
