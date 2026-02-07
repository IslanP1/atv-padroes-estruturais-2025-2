package br.edu.ifpb.padroes.atv1.streamflix;

import br.edu.ifpb.padroes.atv1.streamflix.decorators.AnalyticsDecorator;
import br.edu.ifpb.padroes.atv1.streamflix.decorators.EncryptionDecorator;
import br.edu.ifpb.padroes.atv1.streamflix.decorators.IVideoComponent;
import br.edu.ifpb.padroes.atv1.streamflix.decorators.WatermarkDecorator;
import br.edu.ifpb.padroes.atv1.streamflix.proxy.VideoProxy;
import br.edu.ifpb.padroes.atv1.streamflix.services.AWSS3Service;
import br.edu.ifpb.padroes.atv1.streamflix.services.AWSS3StorageAdapter;
import br.edu.ifpb.padroes.atv1.streamflix.services.IStorageService;

public class StreamflixClientDemo {

    public static void main(String[] args) {
        // Configuração do serviço de storage
        IStorageService storageService = new AWSS3StorageAdapter(new AWSS3Service());

        // Criação da facade
        StreamFlixFacade facade = new StreamFlixFacade(storageService);

        System.out.println("=== DEMO: Video Proxy com Cache ===\n");

        // Primeira chamada - deve buscar do serviço externo
        System.out.println("--- Primeira visualização do vídeo 'video-001' ---");
        facade.watchVideo("user123", "valid-token", "video-001");

        System.out.println("\n--- Segunda visualização do vídeo 'video-001' ---");
        // Segunda chamada - deve usar o cache
        facade.watchVideo("user123", "valid-token", "video-001");

        System.out.println("\n--- Primeira visualização do vídeo 'video-002' ---");
        // Vídeo diferente - deve buscar do serviço externo
        facade.watchVideo("user123", "valid-token", "video-002");

        System.out.println("\n--- Terceira visualização do vídeo 'video-001' ---");
        // Terceira chamada do primeiro vídeo - deve usar o cache
        facade.watchVideo("user123", "valid-token", "video-001");

        System.out.println("\n\n=== DEMO: Video com Decorators ===\n");

        // Obtém vídeo usando proxy (com cache)
        System.out.println("--- Obtendo vídeo com decorators ---");
        IVideoComponent video = facade.getVideo("video-003");

        // Aplica decorators
        video = new WatermarkDecorator(video);
        video = new EncryptionDecorator(video);
        video = new AnalyticsDecorator(video);

        // Reproduz o vídeo decorado
        System.out.println("\n--- Reproduzindo vídeo decorado ---");
        facade.watchVideo("user123", "valid-token", video);

        System.out.println("\n--- Reproduzindo o mesmo vídeo decorado novamente ---");
        // Segunda reprodução do vídeo decorado - o proxy interno já tem cache
        facade.watchVideo("user123", "valid-token", video);

        System.out.println("\n=== Tamanho do cache: " + VideoProxy.getCacheSize() + " vídeos ===");
    }
}
