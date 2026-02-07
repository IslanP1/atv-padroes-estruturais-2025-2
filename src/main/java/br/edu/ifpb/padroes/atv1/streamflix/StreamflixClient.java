package br.edu.ifpb.padroes.atv1.streamflix;

import br.edu.ifpb.padroes.atv1.streamflix.decorators.*;
import br.edu.ifpb.padroes.atv1.streamflix.services.AWSS3Service;
import br.edu.ifpb.padroes.atv1.streamflix.services.AWSS3StorageAdapter;
import br.edu.ifpb.padroes.atv1.streamflix.services.IStorageService;

public class StreamflixClient {
    public static void main(String[] args ) {
        IStorageService storageService = new AWSS3StorageAdapter(new AWSS3Service());
        // ou IStorageService storageService = new GCSStorageAdapter(new GoogleCloudStorage());

        StreamFlixFacade facade = new StreamFlixFacade(storageService);
        facade.watchVideo("user123", "valid-token", "video456");

        IVideoComponent video = facade.getVideo("video456");
        // Adicionando funcionalidades usando Decorator
        video = new WatermarkDecorator(video);
        video = new AnalyticsDecorator(video);
        video = new EncryptionDecorator(video);
        video.play();
    }
}
