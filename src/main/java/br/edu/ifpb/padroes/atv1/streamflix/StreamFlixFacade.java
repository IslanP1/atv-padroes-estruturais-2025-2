package br.edu.ifpb.padroes.atv1.streamflix;

import br.edu.ifpb.padroes.atv1.streamflix.auth.AuthenticationService;
import br.edu.ifpb.padroes.atv1.streamflix.converter.VideoConverter;
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

    public void watchVideo(String userId, String token, String videoId) {
        if (authService.authenticate(userId, token)) {
            byte[] rawVideo = storageService.save("videos-bucket", videoId);
            byte[] convertedVideo = videoConverter.convert(rawVideo, "MP4");
            String subs = subtitleService.getSubtitles(videoId, "pt-BR");

            Video video = new Video(videoId, "Movie Title", convertedVideo);
            video.play();
            streamingService.startStream(convertedVideo);
        }
        else {
            System.out.println("Authentication failed. Cannot watch video.");
        }
    }
}
