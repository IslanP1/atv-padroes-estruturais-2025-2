package br.edu.ifpb.padroes.atv1.streamflix.proxy;

import br.edu.ifpb.padroes.atv1.streamflix.Video;
import br.edu.ifpb.padroes.atv1.streamflix.decorators.IVideoComponent;
import br.edu.ifpb.padroes.atv1.streamflix.services.IStorageService;
import br.edu.ifpb.padroes.atv1.streamflix.converter.VideoConverter;

import java.util.HashMap;
import java.util.Map;

public class VideoProxy implements IVideoComponent {
    private static final Map<String, Video> videoCache = new HashMap<>();

    private final String videoId;
    private final IStorageService storageService;
    private final VideoConverter videoConverter;
    private Video realVideo;

    public VideoProxy(String videoId, IStorageService storageService, VideoConverter videoConverter) {
        this.videoId = videoId;
        this.storageService = storageService;
        this.videoConverter = videoConverter;
    }

    private Video loadVideo() {
        // Verifica se o vídeo está no cache
        if (videoCache.containsKey(videoId)) {
            System.out.println("[CACHE HIT] Vídeo '" + videoId + "' recuperado do cache.");
            return videoCache.get(videoId);
        }

        // Se não está no cache, busca do serviço externo
        System.out.println("[CACHE MISS] Buscando vídeo '" + videoId + "' do serviço externo...");
        byte[] rawVideo = storageService.save("videos-bucket", videoId);
        byte[] convertedVideo = videoConverter.convert(rawVideo, "MP4");

        Video video = new Video(videoId, "Movie Title", convertedVideo);

        // Armazena no cache
        videoCache.put(videoId, video);
        System.out.println("[CACHE STORE] Vídeo '" + videoId + "' armazenado no cache.");

        return video;
    }

    @Override
    public void play() {
        if (realVideo == null) {
            realVideo = loadVideo();
        }
        realVideo.play();
    }

    public byte[] getData() {
        if (realVideo == null) {
            realVideo = loadVideo();
        }
        return realVideo.getData();
    }

    public String getId() {
        return videoId;
    }

    // Método utilitário para limpar o cache
    public static void clearCache() {
        videoCache.clear();
        System.out.println("[CACHE] Cache limpo.");
    }

    // Método utilitário para verificar o tamanho do cache
    public static int getCacheSize() {
        return videoCache.size();
    }
}
