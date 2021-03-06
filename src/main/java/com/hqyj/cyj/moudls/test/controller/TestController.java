package com.hqyj.cyj.moudls.test.controller;

import com.hqyj.cyj.moudls.test.entity.City;
import com.hqyj.cyj.moudls.test.entity.Country;
import com.hqyj.cyj.moudls.test.service.CityService;
import com.hqyj.cyj.moudls.test.service.CountryService;
import com.hqyj.cyj.moudls.test.vo.Test2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description TestController
 * @Author HymanHu
 * @Date 2020/8/10 10:39
 */
@Controller
@RequestMapping("/test")
public class TestController {

    private final static Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    @Value("${server.port}")
    private int port;
    @Value("${com.name}")
    private String name;
    @Value("${com.age}")
    private int age;

    @Autowired
    private Test2 applicationTest;
    @Autowired
    private CityService cityService;
    @Autowired
    private CountryService countryServcie;

    @GetMapping("/dFile")
    public ResponseEntity<Resource> downloadFile(String fileName){
        Resource resource = null;
        try {
            resource = new UrlResource(
                    Paths.get("D:\\upload\\"+fileName).toUri());
            if (resource.exists()  &&resource.isReadable()) {
                return  ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_TYPE,"application/octet-stream")
                        .header(HttpHeaders.CONTENT_DISPOSITION,
                                String.format("attachment;filename=\"%s\"",resource.getFilename()))
                        .body(resource);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping(value = "/files",consumes = "multipart/form-data")
    public  String uploadFiles(@RequestParam MultipartFile[] files,
                               RedirectAttributes redirectAttributes){

        boolean empty =true;
        try {

            for (MultipartFile file:files) {
                if (file.isEmpty()){
                    continue;
                }
                String destFilePath = "D:\\upload\\"+file.getOriginalFilename();
                File destFile = new File(destFilePath);
                file.transferTo(destFile);
                empty=false;
            }
            if (empty){
                redirectAttributes.addFlashAttribute("message","Please select file");
            }else {
                redirectAttributes.addFlashAttribute("message","Upload file success");
            }
        } catch (IOException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message","Upload file failed");
        }
        return "redirect:/test/index";
    }

    /**
     * 127.0.0.1/test/file
     */
    @PostMapping(value = "/file",consumes = "multipart/form-data")
    public String uploadFile(@RequestParam MultipartFile file,
                            RedirectAttributes redirectAttributes){
        if (file.isEmpty()){
            redirectAttributes.addFlashAttribute("message","Please select file");
            return "redirect:/test/index";
        }
        try {
            String destFilePath = "D:\\upload\\"+file.getOriginalFilename();
            File destFile = new File(destFilePath);
            file.transferTo(destFile);
        } catch (IOException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message","Upload file failed");
        }

        redirectAttributes.addFlashAttribute("message","Upload file success");

        return "redirect:/test/index";
    }

    /**
     * 127.0.0.1/test/index
     */
    @GetMapping("/index")
    public String testIndexPage(ModelMap modelMap) {
        int countryId = 522;
        List<City> cities = cityService.getCityByCountryId(countryId);
        cities = cities.stream().limit(10).collect(Collectors.toList());
        Country country = countryServcie.getCountryByCountryId(countryId);

        modelMap.addAttribute("thymeleafTitle", "scdscsadcsacd");
        modelMap.addAttribute("checked", true);
        modelMap.addAttribute("currentNumber", 99);
        modelMap.addAttribute("changeType", "checkbox");
        modelMap.addAttribute("baiduUrl", "/test/log");
        modelMap.addAttribute("city", cities.get(0));
		modelMap.addAttribute("shopLogo",
				"http://cdn.duitang.com/uploads/item/201308/13/20130813115619_EJCWm.thumb.700_0.jpeg");
        modelMap.addAttribute("shopLogo1",
                "/upload/1111.png");
        modelMap.addAttribute("country", country);
        modelMap.addAttribute("cities", cities);
        modelMap.addAttribute("updateCityUri", "/api/city");
//        modelMap.addAttribute("template", "test/index");
        // 返回外层的碎片组装器
        return "index";
    }

    /**
     * 127.0.0.1/test/index2
     */
    @GetMapping("/index2")
    public String testIndex2Page(ModelMap modelMap) {
        modelMap.addAttribute("template", "test/index2");
        return "index";
    }

    /**
     * 127.0.0.1:8085/test/logTest ---- get
     */
    @GetMapping("/logTest")
    @ResponseBody
    public String logTest() {
        LOGGER.trace("This is trace log");
        LOGGER.debug("This is debug log");
        LOGGER.info("This is info log");
        LOGGER.warn("This is warn log");
        LOGGER.error("This is error log");
        return "This is log test11111";
    }

    /**
     * 127.0.0.1:8085/test/config ---- get
     */
    @GetMapping("/config")
    @ResponseBody
    public String configTest() {
        StringBuffer sb = new StringBuffer();
        sb.append(port).append("----")
            .append(name).append("----")
            .append(age).append("----");
        sb.append(applicationTest.getPort()).append("----")
            .append(applicationTest.getName()).append("----")
            .append(applicationTest.getAge()).append("----");

        return sb.toString();

    }

    /**
     * 127.0.0.1:8080/test/testDesc?paramKey=fuck ---- get
     */
    @GetMapping("/testDesc")
    @ResponseBody
    public String testDesc(HttpServletRequest request,
                           @RequestParam(value = "paramKey") String paramValue) {
        String paramValue2 = request.getParameter("paramKey");
        return "This is test module desc."+paramValue+"=="+paramValue2;
    }

}
