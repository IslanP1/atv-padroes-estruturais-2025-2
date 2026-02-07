package br.edu.ifpb.padroes.atv1.streamflix.services;

public class AWSS3StorageAdapter implements IStorageService {
    private final AWSS3Service s3Service;

    public AWSS3StorageAdapter(AWSS3Service s3Service) {
        this.s3Service = s3Service;
    }

    @Override
    public byte[] save(String fileLocation, String fileId) {
        return s3Service.downloadFromS3(fileLocation, fileId);
    }

    @Override
    public void upload(String fileName, String fileId, byte[] content) {
        s3Service.uploadToS3(fileName, fileId, content);
    }
}
