package com.chinasofti.lexian.mall.common.service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.chinasofti.lexian.mall.common.util.Constant;
import com.chinasofti.lexian.mall.common.util.ImageUtil;
import com.chinasofti.lexian.mall.user.po.User;

public class BaseService {
	

	protected Logger logger = Logger.getLogger(getClass());

	public String saveImage(String webpath, String uploadpath, MultipartFile multipartFile) {
		return saveImage(webpath, uploadpath, multipartFile, 0.5f);
	}

	// 保存图片
	public String saveImage(String webpath, String uploadpath, MultipartFile multipartFile, float quality) {
		String fileName = multipartFile.getOriginalFilename();
		String attrFix = fileName.substring(fileName.lastIndexOf(".") + 1);
		String newfileName = UUID.randomUUID() + "." + attrFix;
		File file = new File(uploadpath, newfileName);
		if (!file.exists() || !file.isFile()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				logger.error(e.getMessage(), e.getCause());
				return null;
			}
		}
		boolean isSuccess = ImageUtil.compressPic(multipartFile, uploadpath + File.separator + newfileName, attrFix,
				quality);
		if (!isSuccess) {
			return null;
		}
		String imageUrl = webpath + "/" + newfileName;
		return imageUrl;
	}

	public String saveImageRule(MultipartFile part, String webpath, String uploadpath, int maxWidth, int maxHeight) {
		File file = createSaveFile(part, uploadpath);
		if (!file.exists() || !file.isFile()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				logger.error(e.getMessage(), e.getCause());
				return null;
			}
		}
		try {
			ImageUtil.generateThumbnail(part, file, maxWidth, maxHeight);
		} catch (IOException e) {
			return null;
		}
		String imageUrl = webpath + "/" + file.getName();
		return imageUrl;
	}

	// 保存文件
	public String saveFile(String webpath, String uploadpath, MultipartFile multipartFile) {
		File file = createSaveFile(multipartFile, uploadpath);
		if (!file.exists() || !file.isFile()) {
			try {
				file.createNewFile();
				multipartFile.transferTo(file);
			} catch (IOException e) {
				return null;
			}
		}
		String imageUrl = webpath + "/" + file.getName();
		return imageUrl;
	}

	public File createSaveFile(MultipartFile multipartFile, String uploadpath) {
		String fileName = multipartFile.getOriginalFilename();
		String attrFix = fileName.substring(fileName.lastIndexOf(".") + 1);
		String newfileName = UUID.randomUUID() + "." + attrFix;
		File file = new File(uploadpath, newfileName);
		return file;
	}

	protected User getUser() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        return  (User)request.getAttribute(Constant.LEXIANUSERKEY);
	}
}
