package br.edu.ifpb.padroes.atv1.streamflix.services;

public class GoogleCloudStorageAdapter implements IStorageService {
    private final GoogleCloudStorage googleCloudStorageService;

    public GoogleCloudStorageAdapter(GoogleCloudStorage googleCloudStorageService) {
        this.googleCloudStorageService = googleCloudStorageService;
    }

    @Override
    public byte[] save(String fileLocation, String fileId) {
        return googleCloudStorageService.retrieveFile(fileLocation, fileId);
    }

    @Override
    public void upload(String fileName, String fileId, byte[] content) {
        googleCloudStorageService.storeFile(fileName, fileId, content);
    }
}
