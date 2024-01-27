package xyz.windows.file.explorer.model;

import java.io.File;
import xyz.windows.file.explorer.utils.Utils;

public class Data {
    
    private File fileObject;
    
    private String name;
    private String nameReadable;
    private long sizeInBytes;
    private String sizeInReadble;
    private long parentSizeInBytes;
    private String parentSizeInReadable;
    private long creationTimeStamp;
    private String creationTimeStampReadable;
    private double sizePercentRelativeToParent;
    private String sizePercentRelativeToParentReadable;
    
    private long numberOfFiles;
    private String numberOfFilesReadable;
    private long numberOfFolders;
    private String numberOfFoldersReadable;

    
    //GETTERS
    
    public String getName() {
        return name;
    }

    public String getNameReadable() {
        return nameReadable;
    }

    public String getSizeInReadble() {
        return sizeInReadble;
    }

    public long getSizeInBytes() {
        return sizeInBytes;
    }

    public long getParentSizeInBytes() {
        return parentSizeInBytes;
    }

    public String getParentSizeInReadable() {
        return parentSizeInReadable;
    }
    
    public double getSizePercentRelativeToParent() {
        return sizePercentRelativeToParent;
    }

    public String getSizePercentRelativeToParentReadable() {
        return sizePercentRelativeToParentReadable;
    }
    
    public String getCreationTimeStampReadable() {
        return creationTimeStampReadable;
    }

    public long getCreationTimeStamp() {
        return creationTimeStamp;
    }

    public long getNumberOfFiles() {
        return numberOfFiles;
    }

    public String getNumberOfFilesReadable() {
        return numberOfFilesReadable;
    }

    public long getNumberOfFolders() {
        return numberOfFolders;
    }

    public String getNumberOfFoldersReadable() {
        return numberOfFoldersReadable;
    }

    public File getFileObject() {
        return fileObject;
    }
    
    
    //SETTERS
    
    public void setName(String name) {
        this.name = name;
        //READABLE NAME
        this.nameReadable = Utils.getReadableString(this.name);
    }

    public void setSizeInBytes(long sizeInBytes) {
        this.sizeInBytes = sizeInBytes;
        //READABLE BYTES
        this.sizeInReadble = Utils.getReadableBytes(this.sizeInBytes);
    }
    
    public void setParentSizeInBytes(long parentSizeInBytes) {
        this.parentSizeInBytes = parentSizeInBytes;
        //READABLE BYTES
        this.parentSizeInReadable = Utils.getReadableBytes(this.parentSizeInBytes);
    }

    public void setNumberOfFiles(long numberOfFiles) {
        this.numberOfFiles = numberOfFiles;
        //READABLE NUMBER_OF_FILES
        this.numberOfFilesReadable = Utils.getReadableNumber(this.numberOfFiles);
    }

    public void setNumberOfFolders(long numberOfFolders) {
        this.numberOfFolders = numberOfFolders;
        //READABLE NUMBER_OF_FOLDERS
        this.numberOfFoldersReadable = Utils.getReadableNumber(this.numberOfFolders);
    }

    public void setCreationTimeStamp(long creationTimeStamp) {
        this.creationTimeStamp = creationTimeStamp;
        //READABLE CREATION_TIME_STAMP
        this.creationTimeStampReadable = Utils.getReadableTimestamp(this.creationTimeStamp);
    }
    
    public void setSizePercentRelativeToParent(double sizePercentRelativeToParent) {
        this.sizePercentRelativeToParent = sizePercentRelativeToParent;
        //READABLE PERCENT
        this.sizePercentRelativeToParentReadable = Utils.getReadablePercent(this.sizePercentRelativeToParent);
    }

    public void setFileObject(File fileObject) {
        this.fileObject = fileObject;
    }
    
}
