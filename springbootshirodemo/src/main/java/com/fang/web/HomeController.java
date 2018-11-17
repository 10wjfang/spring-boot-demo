package com.fang.web;

import com.fang.entity.FastDFSFile;
import com.fang.entity.UserInfo;
import com.fang.service.UserInfoService;
import com.fang.utils.FastDFSClient;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

/**
 * @Author: fwj
 * @Description:
 * @Date: Created in 2018/4/26 14:53
 * @Modified by:
 */
@Controller
public class HomeController {
    @Resource
    private UserInfoService userInfoService;

    @RequestMapping({"/","/index"})
    public String index() {
        return "/index";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request, Map<String, Object> map) throws Exception{
        String exception = (String)request.getAttribute("shiroLoginFailure");
        String msg = "";
        if (exception != null) {
            if (UnknownAccountException.class.getName().equals(exception)){
                System.out.println("UnknownAccountException -- > 账号不存在：");
                msg = "UnknownAccountException -- > 账号不存在：";
            } else if (IncorrectCredentialsException.class.getName().equals((exception))){
                System.out.println("IncorrectCredentialsException -- > 密码不正确：");
                msg = "IncorrectCredentialsException -- > 密码不正确：";
            } else {
                msg = "else >> "+exception;
                System.out.println("else -- >" + exception);
            }
        }
        map.put("msg", msg);
        return "/login";
    }

    @RequestMapping("/register")
    public String regster() {
        return "/register";
    }

    @RequestMapping("/regist")
    public String register(@RequestParam("username") String username,
                           @RequestParam("password") String password,
                           @RequestParam("name") String name) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(username);
        userInfo.setName(name);
        ByteSource salt = ByteSource.Util.bytes(username);
        userInfo.setSalt(salt.toHex());
        ByteSource pwd = ByteSource.Util.bytes(userInfo.getCredentialsSalt());
        String newPwd = new SimpleHash("MD5", password, pwd, 2).toHex();
        userInfo.setPassword(newPwd);
        userInfo.setState((byte)0);
        if (userInfoService.save(userInfo))
            return "redirect:login";
        return "/register";
    }

    @RequestMapping("403")
    public String unauthorizedRole() {
        return "/403";
    }

    @RequestMapping("/upload")
    public String upload(@RequestParam("file")MultipartFile file,
                         RedirectAttributes redirectAttributes) {
        if(file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }
        try {
//            byte[] bytes = file.getBytes();
//            Path path = Paths.get("D://tmp//" + file.getOriginalFilename());
//            Files.write(path, bytes);
            String path = saveFile(file);
            redirectAttributes.addFlashAttribute("message","You successfully uploaded '" + file.getOriginalFilename() + "'" + path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:uploadStatus";
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }

    public String saveFile(MultipartFile multipartFile) throws IOException {
        String[] fileAbsolutePath={};
        String fileName=multipartFile.getOriginalFilename();
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
        byte[] file_buff = null;
        InputStream inputStream=multipartFile.getInputStream();
        if(inputStream!=null){
            int len1 = inputStream.available();
            file_buff = new byte[len1];
            inputStream.read(file_buff);
        }
        inputStream.close();
        FastDFSFile file = new FastDFSFile(fileName, file_buff, ext);
        try {
            fileAbsolutePath = FastDFSClient.upload(file);  //upload to fastdfs
        } catch (Exception e) {
            System.out.println("upload file Exception!" + e);
        }
        if (fileAbsolutePath==null) {
            System.out.println("upload file failed,please upload again!");
        }
        String path=FastDFSClient.getTrackerUrl()+fileAbsolutePath[0]+ "/"+fileAbsolutePath[1];
        return path;
    }

}
