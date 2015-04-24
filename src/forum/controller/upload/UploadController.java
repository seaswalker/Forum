package forum.controller.upload;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import forum.model.User;
import forum.util.DataUtil;
import forum.util.json.JSONObject;

/**
 * 图片上传
 * @author skywalker
 *
 */
@Controller
public class UploadController {

	/**
	 * 上传
	 * 约定上传文件名为file
	 */
	@RequestMapping("/upload")
	@ResponseBody
	public void upload(MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IllegalStateException, IOException {
		JSONObject json = new JSONObject();
		if(file == null) {
			json.addElement("result", "0").addElement("message", "请选择图片");
			DataUtil.writeJSON(json, response, false);
			return;
		}
		//验证文件类型
		String extension = DataUtil.getExtend(file.getOriginalFilename());
		if(!DataUtil.isImage(extension, file.getContentType())) {
			json.addElement("result", "0").addElement("message", "jpg/jpeg/png/bum/gif格式");
			DataUtil.writeJSON(json, response, false);
			return;
		}
		json.addElement("result", "1").addElement("message", moveFile(request, file, extension));
		DataUtil.writeJSON(json, response, false);
	}
	
	/**
	 * 移动文件
	 */
	private String moveFile(HttpServletRequest request, MultipartFile avatar, String extension) throws IllegalStateException, IOException {
		//移动到  avatar/用户名下(使用UUID作为文件名)
		User user = (User) request.getSession().getAttribute("user");
		String rootPath = request.getServletContext().getRealPath("/") + "/";
		StringBuffer path = new StringBuffer("avatar/");
		path.append(user.getUsername()).append("/");
		//文件夹不存在，建立
		File directory = new File(rootPath + path.toString());
		if(!directory.exists()) {
			directory.mkdirs();
		}
		path.append(UUID.randomUUID().toString()).append(".").append(extension);
		avatar.transferTo(new File(rootPath + path.toString()));
		return path.toString();
	}
	
}
