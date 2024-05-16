package br.com.erudio.services

import br.com.erudio.config.FileStorageConfig
import br.com.erudio.exceptions.FileStorageException
import org.springframework.util.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardCopyOption

@Service
class FileStorageService @Autowired constructor(fileStorageConfig: FileStorageConfig) {

    private val fileStorageLocation:Path

    init {
        fileStorageLocation = Paths.get(fileStorageConfig.uploadDir).toAbsolutePath().normalize()

        try{
            Files.createDirectories(fileStorageLocation)
        } catch (e: Exception){
            throw FileStorageException("Could not create the directory where the uploaded files will be stored", e)
        }
    }

    fun storeFile(file: MultipartFile): String {
        val fileName = StringUtils.cleanPath(file.originalFilename!!)

        return try {
            if(fileName.contains(".."))
                throw FileStorageException("Sorry! FileName contais invalid path sequence $fileName")
            val targetLocation = fileStorageLocation.resolve(fileName)
            Files.copy(file.inputStream, targetLocation, StandardCopyOption.REPLACE_EXISTING)
            fileName
        }catch (e: Exception){
            throw FileStorageException("Could not store file $fileName. Please try again!", e)
        }
    }

}