package cn.lhs.filesys.entity;

public class FileMsg {
    private String uploaderId;
    private String fileId;
    private String fileName;
    private String fileUrl;
    private String fileOriginName;
    private String fileFormats;//文件格式
    private String fileSort;//文件类别
    private long fileSize;
    private String uploadTime;
    private int downloadTimes;
    private String isShared;

    public FileMsg() {

    }

    public FileMsg(MyFile myFile){
        this.uploaderId = myFile.getUploaderId();
        this.fileId = myFile.getFileId();
        this.fileName = myFile.getFileName();
        this.fileUrl = myFile.getFileUrl();
        this.fileFormats = myFile.getFileFormats();
        this.fileSort = myFile.getFileSort();
        this.fileSize = myFile.getFileSize();
        this.uploadTime = myFile.dateToString(myFile.getUploadTime());
        this.downloadTimes = myFile.getDownloadTimes();
        this.isShared = myFile.getIsShared();
    }

    public FileMsg(String uploaderId, String fileId, String fileName, String fileUrl, String fileOriginName, String fileFormats, String fileSort, long fileSize, String uploadTime, int downloadTimes, String isShared) {
        this.uploaderId = uploaderId;
        this.fileId = fileId;
        this.fileName = fileName;
        this.fileUrl = fileUrl;
        this.fileOriginName = fileOriginName;
        this.fileFormats = fileFormats;
        this.fileSort = fileSort;
        this.fileSize = fileSize;
        this.uploadTime = uploadTime;
        this.downloadTimes = downloadTimes;
        this.isShared = isShared;
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

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
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

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    public int getDownloadTimes() {
        return downloadTimes;
    }

    public void setDownloadTimes(int downloadTimes) {
        this.downloadTimes = downloadTimes;
    }

    public String getIsShared() {
        return isShared;
    }

    public void setIsShared(String isShared) {
        this.isShared = isShared;
    }

    public String getFileOriginName() {
        return fileOriginName;
    }

    public void setFileOriginName(String fileOriginName) {
        this.fileOriginName = fileOriginName;
    }

    @Override
    public String toString() {
        return "FileMsg{" +
                "uploaderId='" + uploaderId + '\'' +
                ", fileId='" + fileId + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fileUrl='" + fileUrl + '\'' +
                ", fileOriginName='" + fileOriginName + '\'' +
                ", fileFormats='" + fileFormats + '\'' +
                ", fileSort='" + fileSort + '\'' +
                ", fileSize=" + fileSize +
                ", uploadTime='" + uploadTime + '\'' +
                ", downloadTimes=" + downloadTimes +
                ", isShared='" + isShared + '\'' +
                '}';
    }
}
