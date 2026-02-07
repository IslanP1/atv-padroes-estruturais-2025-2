package br.edu.ifpb.padroes.atv1.streamflix.decorators;

public class AnalyticsDecorator extends VideoDecorator {

    public AnalyticsDecorator(IVideoComponent videoComponent) {
        super(videoComponent);
    }

    @Override
    public void play() {
        super.play();
        trackAnalytics();
    }

    private void trackAnalytics() {
        System.out.println("Tracking video analytics...");
    }
}
