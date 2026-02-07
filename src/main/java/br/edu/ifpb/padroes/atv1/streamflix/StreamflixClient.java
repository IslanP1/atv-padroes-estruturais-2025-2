package br.edu.ifpb.padroes.atv1.streamflix;

import br.edu.ifpb.padroes.atv1.streamflix.services.AWSS3Service;
import br.edu.ifpb.padroes.atv1.streamflix.services.AWSS3StorageAdapter;
import br.edu.ifpb.padroes.atv1.streamflix.services.IStorageService;

public class StreamflixClient {
    public static void main(String[] args ) {
        IStorageService storageService = new AWSS3StorageAdapter(new AWSS3Service());
        // ou IStorageService storageService = new GCSStorageAdapter(new GoogleCloudStorage());

        StreamFlixFacade facade = new StreamFlixFacade(storageService);
        facade.watchVideo("user123", "valid-token", "video456");
    }
}
