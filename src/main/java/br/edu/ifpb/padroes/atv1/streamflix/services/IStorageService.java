package br.edu.ifpb.padroes.atv1.streamflix.services;

public interface IStorageService {
    byte[] save(String fileLocation, String fileId);
    void upload(String fileName, String fileId, byte[] content);
}
