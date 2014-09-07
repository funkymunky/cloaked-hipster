package net.lsf.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Aspect
public class UploadFileAspect {

        @After("execution(* net.lsf.service.*.updateProfilePicForStudent(..))")
        public void moveUploadedFile(JoinPoint joinPoint) throws IOException {
            String fileName = (String) joinPoint.getArgs()[1];
            String filePath = "/tmp/images/" + fileName;
            String newFilePath = "/Users/ayeshaf/IdeaProjects/lsf/cloaked-hipster/src/main/webapp/images/" + fileName;

            Path source = Paths.get(filePath);
            Path target = Paths.get(newFilePath);
            if (!Files.exists(target)) {
                Files.copy(source, target);
            }

            Files.delete(source);
        }


}
