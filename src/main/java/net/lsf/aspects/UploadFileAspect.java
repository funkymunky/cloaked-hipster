package net.lsf.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Aspect
public class UploadFileAspect {
    private static final Logger log = Logger.getLogger(UploadFileAspect.class);

    @Value("${lsf.picture.tempFolder}")
    private String tempFileLocation;

    @Value("${lsf.picture.destinationFolder}")
    private String imageDestinationFolder;

        @After("execution(* net.lsf.service.*.updateProfilePicForStudent(..))")
        public void moveUploadedFile(JoinPoint joinPoint) throws IOException {
            String fileName = (String) joinPoint.getArgs()[1];
            String filePath = tempFileLocation + fileName;
            String newFilePath = imageDestinationFolder + fileName;

            Path source = null;
            Path target = null;
            try {
                source = Paths.get(filePath);
                target = Paths.get(newFilePath);
            } catch (Exception e) {
                log.warn("Having trouble with tempFileLocation and/or imageDestinationFolder");
            }
            if (!Files.exists(target)) {
                Files.copy(source, target);
            }

            Files.delete(source);
        }


}
