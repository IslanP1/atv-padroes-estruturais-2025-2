package br.edu.ifpb.padroes.atv1.streamflix;

import br.edu.ifpb.padroes.atv1.streamflix.auth.AuthenticationService;
import br.edu.ifpb.padroes.atv1.streamflix.converter.VideoConverter;
import br.edu.ifpb.padroes.atv1.streamflix.decorators.IVideoComponent;
import br.edu.ifpb.padroes.atv1.streamflix.proxy.VideoProxy;
import br.edu.ifpb.padroes.atv1.streamflix.services.IStorageService;
import br.edu.ifpb.padroes.atv1.streamflix.stream.StreamingService;
import br.edu.ifpb.padroes.atv1.streamflix.subtitle.SubtitleService;

public class StreamFlixFacade {
    private final AuthenticationService authService;
    private final VideoConverter videoConverter;
    private final SubtitleService subtitleService;
    private final StreamingService streamingService;
    private final IStorageService storageService;

    public StreamFlixFacade(IStorageService storageService) {
        this.authService = new AuthenticationService();
        this.videoConverter = new VideoConverter();
        this.subtitleService = new SubtitleService();
        this.streamingService = new StreamingService();
        this.storageService = storageService;
    }

    public AuthenticationService getAuthService() {
        return authService;
    }

    public VideoConverter getVideoConverter() {
        return videoConverter;
    }

    public SubtitleService getSubtitleService() {
        return subtitleService;
    }

    public StreamingService getStreamingService() {
        return streamingService;
    }

    public IStorageService getStorageService() {
        return storageService;
    }

    public void watchVideo(String userId, String token, String videoId) {
        if (authService.authenticate(userId, token)) {
            // Usa VideoProxy para obter o vídeo (com cache)
            IVideoComponent video = getVideo(videoId);
            String subs = subtitleService.getSubtitles(videoId, "pt-BR");

            video.play();
            streamingService.startStream(video.getData());
        }
        else {
            System.out.println("Authentication failed. Cannot watch video.");
        }
    }

    // Sobrecarga para aceitar vídeo já decorado
    public void watchVideo(String userId, String token, IVideoComponent video) {
        if (authService.authenticate(userId, token)) {
            video.play();
            streamingService.startStream(video.getData());
        } else {
            System.out.println("Authentication failed. Cannot watch video.");
        }
    }

    public IVideoComponent getVideo(String videoId) {
        // Retorna um proxy que faz cache do vídeo
        return new VideoProxy(videoId, storageService, videoConverter);
    }
}
