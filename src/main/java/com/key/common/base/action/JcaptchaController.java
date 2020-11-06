package com.key.common.base.action;


import com.octo.captcha.service.image.ImageCaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

@Controller
public class JcaptchaController {
	
	@Autowired  
    private ImageCaptchaService imageCaptchaService;  


	@RequestMapping("/jcaptcha")
	public String execute(HttpServletRequest request,HttpServletResponse response) throws Exception {
        ByteArrayOutputStream out = null;
        byte[] captchaChallengeAsJpeg = null;  
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {  
            String captchaId = request.getSession().getId();
            BufferedImage challenge = imageCaptchaService.getImageChallengeForID(captchaId, request.getLocale());
            //新版图片api
            ImageIO.write(challenge, "jpg", jpegOutputStream);

        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();    
        // flush it in the response    
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream = response.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();
        return null;
	}
}
