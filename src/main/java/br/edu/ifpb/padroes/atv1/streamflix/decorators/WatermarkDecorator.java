package br.edu.ifpb.padroes.atv1.streamflix.decorators;

public class WatermarkDecorator extends VideoDecorator {

    public WatermarkDecorator(IVideoComponent videoComponent) {
        super(videoComponent);
    }

    @Override
    public void play() {
        super.play();
        addWatermark();
    }

    private void addWatermark() {
        System.out.println("Adding watermark to the video...");
    }
}
