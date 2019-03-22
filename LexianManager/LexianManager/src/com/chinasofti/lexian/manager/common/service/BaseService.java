package com.chinasofti.lexian.manager.common.service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.chinasofti.lexian.manager.common.util.Constant;
import com.chinasofti.lexian.manager.common.util.ImageUtil;
import com.chinasofti.lexian.manager.user.po.UserPo;

public class BaseService {
	protected Logger logger = Logger.getLogger(getClass());
	
	public void deleteImageFile(String uploadPath, String pictureUrl){
		File file = new File(uploadPath, pictureUrl);
		file.delete();
	}

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
		boolean isSuccess = ImageUtil.compressPic(multipartFile, 
				uploadpath + File.separator + newfileName, 
				attrFix,
				quality);
		if (!isSuccess) {
			return null;
		}
		String imageUrl = newfileName;
		int index = webpath.lastIndexOf("/");
		webpath = webpath.substring(index);
		if(webpath != null && webpath.length()>0){
			imageUrl = webpath + "/" + newfileName;
		}
		return imageUrl;
	}

	public String saveImageRule(MultipartFile part, String webpath, String uploadpath, int maxWidth) {
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
			ImageUtil.generateThumbnail(part, file, maxWidth);
		} catch (IOException e) {
			return null;
		}
		String imageUrl = webpath + "/" + file.getName();
		return imageUrl;
	}

	// 保存普通文件
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
		String fileUrl = webpath + "/" + file.getName();
		return fileUrl;
	}

	public File createSaveFile(MultipartFile multipartFile, String uploadpath) {
		String fileName = multipartFile.getOriginalFilename();
		String attrFix = fileName.substring(fileName.lastIndexOf(".") + 1);
		String newfileName = UUID.randomUUID() + "." + attrFix;
		File file = new File(uploadpath, newfileName);
		return file;
	}

	public Integer[] Arraysort(String arrayString) {
		String[] array = arrayString.split(",");
		Integer[] a = new Integer[array.length];
		for (int index = 0; index < array.length; index++) {
			a[index] = Integer.valueOf(array[index]);
		}
		int temp = 0;
		for (int i = 0; i < a.length - 1; i++) {
			for (int j = 0; j < a.length - 1 - i; j++) {
				if (a[j] > a[j + 1]) {
					temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
				}
			}
		}
		return a;
	}

	public long[] longSort(long[] longArray) {
		long temp = 0;
		for (int i = 0; i < longArray.length - 1; i++) {
			for (int j = 0; j < longArray.length - 1 - i; j++) {
				if (longArray[j] > longArray[j + 1]) {
					temp = longArray[j];
					longArray[j] = longArray[j + 1];
					longArray[j + 1] = temp;
				}
			}
		}
		return longArray;
	}

	protected UserPo getUser() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.
				getRequestAttributes()).getRequest();
        return  (UserPo)request.getAttribute(Constant.LEXIANUSERKEY);
	}
	
	
	protected String getServletRealPath(String path){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        if(logger.isDebugEnabled()){
        	logger.debug(request.getServletContext().getRealPath(path)+"++++");
        }
		return request.getServletContext().getRealPath(path);	
	}

}
