package cn.lhs.filesys.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyFile {
    private String uploaderId;
    private String fileId;
    private String fileName;
    private String fileUrl;
    private String fileFormats;//文件格式
    private String fileSort;//文件类别:1-图片，2-视频
    private long fileSize;
    private Date uploadTime;
    private int downloadTimes;

    public MyFile() {

    }

    public String getUploaderId() {
        return uploaderId;
    }

    public void setUploaderId(String uploaderId) {
        this.uploaderId = uploaderId;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileFormats() {
        return fileFormats;
    }

    public void setFileFormats(String fileFormats) {
        this.fileFormats = fileFormats;
    }

    public String getFileSort() {
        return fileSort;
    }

    public void setFileSort(String fileSort) {
        this.fileSort = fileSort;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public int getDownloadTimes() {
        return downloadTimes;
    }

    public void setDownloadTimes(int downloadTimes) {
        this.downloadTimes = downloadTimes;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    @Override
    public String toString() {
        return "MyFile{" +
                "uploaderId='" + uploaderId + '\'' +
                ", fileId='" + fileId + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fileUrl='" + fileUrl + '\'' +
                ", fileFormats='" + fileFormats + '\'' +
                ", fileSort='" + fileSort + '\'' +
                ", fileSize=" + fileSize +
                ", uploadTime=" + dateToString(uploadTime) +
                ", downloadTimes=" + downloadTimes +
                '}';
    }

    public String dateToString(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }

}
