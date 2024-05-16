package br.com.erudio.controllers

import br.com.erudio.data.vo.v1.UploadFileResponseVO
import br.com.erudio.services.FileStorageService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.logging.Logger

@Tag(name = "File Endpoint")
@RestController
@RequestMapping("/api/file/v1")
class FileController {

    private val logger = Logger.getLogger(FileController::class.java.name)

    @Autowired
    private lateinit var fileStorageService: FileStorageService

    @PostMapping("/upload-file")
    fun uplaodFile(@RequestParam("file") file:MultipartFile): UploadFileResponseVO{
        val fileName = fileStorageService.storeFile(file)
        val fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
            .path("/api/file/v1/upload-file/")
            .path(fileName)
            .toUriString()
        return UploadFileResponseVO(fileName, fileDownloadUri, file.contentType!!, file.size)
    }

    @PostMapping("/upload-multiple-files")
    fun uplaodMultipleFiles(@RequestParam("files") files:Array<MultipartFile>): List<UploadFileResponseVO>{
        val uploadFileResponseVOs = arrayListOf<UploadFileResponseVO>()
        for(file in files){
            var uploadFileResponseVO: UploadFileResponseVO = uplaodFile(file)
            uploadFileResponseVOs.add(uploadFileResponseVO)
        }
        return uploadFileResponseVOs
    }
}