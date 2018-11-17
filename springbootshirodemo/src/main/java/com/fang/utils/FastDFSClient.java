package com.fang.utils;

import com.fang.entity.FastDFSFile;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: fwj
 * @Description:
 * @Date: Created in 2018/4/28 16:52
 * @Modified by:
 */
public class FastDFSClient {
    private static TrackerClient trackerClient;
    private static TrackerServer trackerServer;
    private static StorageClient storageClient;
    private static StorageServer storageServer;

    static {
        try {
            String filePath = new ClassPathResource("fdfs_client.conf").getFile().getAbsolutePath();
            ClientGlobal.init(filePath);
            trackerClient = new TrackerClient();
            trackerServer = trackerClient.getConnection();
            storageServer = trackerClient.getStoreStorage(trackerServer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String[] upload(FastDFSFile file) {
        System.out.println("File Name:" + file.getName() + " File Length:" + file.getContent().length);
        NameValuePair[] meta_list = new NameValuePair[1];
        meta_list[0] = new NameValuePair("author",file.getAuthor());
        long start = System.currentTimeMillis();
        String[] uploadResults = null;
        try {
            storageClient = new StorageClient(trackerServer, storageServer);
            uploadResults = storageClient.upload_file(file.getContent(), file.getExt(), meta_list);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
        System.out.println("upload file time used:" + (System.currentTimeMillis() - start) + "ms");
        if (uploadResults == null) {
            System.out.println(storageClient.getErrorCode());
        }
        String groupName = uploadResults[0];
        String remoteName = uploadResults[1];
        System.out.println("upload file successfully");
        return uploadResults;
    }

    public static FileInfo getFile(String groupName, String remoteFileName) {
        storageClient = new StorageClient(trackerServer, storageServer);
        try {
            return storageClient.get_file_info(groupName, remoteFileName);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static InputStream downFile(String groupName, String remoteFileName) {
        storageClient = new StorageClient(trackerServer, storageServer);
        try {
            byte[] bytes = storageClient.download_file(groupName, remoteFileName);
            InputStream inputStream = new ByteArrayInputStream(bytes);
            return inputStream;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void deleteFile(String groupName, String remoteFileName) {
        storageClient = new StorageClient(trackerServer, storageServer);
        try {
            storageClient.delete_file(groupName, remoteFileName);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

    public static String getTrackerUrl() {
        return "http://"+trackerServer.getInetSocketAddress().getHostString()+"/";
    }
}
