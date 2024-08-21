package com.study.aws.s3;

import com.study.aws.s3.dto.FilesDto;
import com.study.aws.s3.service.FileService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/s3")
public class FileUploadController {
    private final FileService fileService;

    @PostMapping("")
    public String saveFile(@RequestParam("file") MultipartFile file){
        if(!file.isEmpty()){
            fileService.saveFile(file);
        }
        return "redirect:/s3";
    }

    @GetMapping("")
    public String getFile(Model model){
        List<FilesDto> files = fileService.getAllFiles();
        model.addAttribute("files", files);
        return "s3/upload";
    }
}
