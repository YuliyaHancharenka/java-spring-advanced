package com.epam.engx.cleancode.naming.task5;

import com.epam.engx.cleancode.naming.task5.thirdpartyjar.InvalidDirectoryException;
import com.epam.engx.cleancode.naming.task5.thirdpartyjar.InvalidFileTypeException;
import com.epam.engx.cleancode.naming.task5.thirdpartyjar.PropertyUtil;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public final class FileManager {

    private static final String[] IMAGE_EXTENSIONS = {"jpg", "png"};
    private static final String[] DOCUMENTS_EXTENSIONS = {"pdf", "doc"};

    private String basePath = PropertyUtil.loadProperty("basePath");

    File retrieveFile(String fileName) {
        validateFileType(fileName);
        final String dirPath = basePath + File.separator;
        return Paths.get(dirPath, fileName).toFile();
    }

    List<String> listAllImages() {
        return files(basePath, IMAGE_EXTENSIONS);
    }

    List<String> listAllDocumentFiles() {
        return files(basePath, DOCUMENTS_EXTENSIONS);
    }

    private void validateFileType(String fileName) {
        if (isInvalidFileType(fileName)) {
            throw new InvalidFileTypeException("File type not Supported: " + fileName);
        }
    }

    private boolean isInvalidFileType(String fileName) {
        return isInvalidImage(fileName) && isInvalidDocument(fileName);
    }

    private boolean isInvalidImage(String imageName) {
        FileExtensionPredicate imageExtensionsPredicate = new FileExtensionPredicate(IMAGE_EXTENSIONS);
        return !imageExtensionsPredicate.test(imageName);
    }

    private boolean isInvalidDocument(String documentName) {
        FileExtensionPredicate documentExtensionsPredicate = new FileExtensionPredicate(DOCUMENTS_EXTENSIONS);
        return !documentExtensionsPredicate.test(documentName);
    }

    private List<String> files(String directoryPath, String[] allowedExtensions) {
        final FileExtensionPredicate pred = new FileExtensionPredicate(allowedExtensions);
        return Arrays.asList(directory(directoryPath).list(getFilenameFilterByPredicate(pred)));
    }

    private FilenameFilter getFilenameFilterByPredicate(final FileExtensionPredicate pred) {
        return new FilenameFilter() {
            @Override
            public boolean accept(File dir, String str) {
                return pred.test(str);
            }
        };
    }

    private File directory(String directoryPath) {
        File directory = new File(directoryPath);
        validateDirectory(directory);
        return directory;
    }

    private void validateDirectory(File directoryInstance) {
        if (isNotDirectory(directoryInstance)) {
            throw new InvalidDirectoryException("Invalid directory found: " + directoryInstance.getAbsolutePath());
        }
    }

    private boolean isNotDirectory(File dir) {
        return !dir.isDirectory();
    }

}