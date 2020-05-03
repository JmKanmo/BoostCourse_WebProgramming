package kr.or.connect.mvcexam.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class UserController {
	private org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

	// get방식으로 요청이 올 경우 업로드 폼을 보여줍니다.
	@GetMapping("/userform")
	public String uploadform() {
		return "userform";
	}

	@PostMapping("/regist")
	public String upload(MultipartHttpServletRequest multi) {
		MultipartFile file = multi.getFile("file");

		try (
				// 윈도우일 경우
				FileOutputStream fos = new FileOutputStream("c:/tmp/" + file.getOriginalFilename());
				InputStream is = file.getInputStream();) {

			int readCount = 0;
			byte[] buffer = new byte[1024];
			while ((readCount = is.read(buffer)) != -1) {
				fos.write(buffer, 0, readCount);
			}
			logger.debug("file:{}, email:{}, age:{}", file.getOriginalFilename(), multi.getParameter("email"),
					multi.getParameter("age"));
		} catch (Exception ex) {
			throw new RuntimeException("file Save Error");
		}
		return "uploadok";
	}

	@GetMapping("/download")
	public void download(HttpServletResponse response) {

		// 직접 파일 정보를 변수에 저장해 놨지만, 이 부분이 db에서 읽어왔다고 가정한다.
		String fileName = "1_et_5.png";
		String saveFileName = "1_et_5.png"; // 맥일 경우 "/tmp/connect.png" 로 수정
		String contentType = "image/png";
		int fileLength = 1116731;

		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setHeader("Content-Type", contentType);
		response.setHeader("Content-Length", "" + fileLength);
		response.setHeader("Pragma", "no-cache;");
		response.setHeader("Expires", "-1;");
		File file = new File(saveFileName);
		System.out.println(file);
		try (FileInputStream fis = new FileInputStream(saveFileName); OutputStream out = response.getOutputStream();) {
			int readCount = 0;
			byte[] buffer = new byte[1024];
			while ((readCount = fis.read(buffer)) != -1) {
				out.write(buffer, 0, readCount);
			}
		} catch (Exception ex) {
			throw new RuntimeException("file Save Error");
		}
	}

	@RequestMapping(value = "imgLoad.do")
	public void imgLoad(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String fileDir = req.getParameter("fileDir");
		String fileName = req.getParameter("fileName");

		String path = "C:\\tmp\\download_img\\";
		File file = new File(path, "50_ma_129.png");

		res.setHeader("Content-Length", String.valueOf(file.length()));
		res.setHeader("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");
		Files.copy(file.toPath(), res.getOutputStream());
	}
}
