package br.edu.ifpb.padroes.atv1.streamflix.decorators;

public abstract class VideoDecorator implements IVideoComponent{
    protected IVideoComponent videoComponent;

    public VideoDecorator(IVideoComponent videoComponent) {
        this.videoComponent = videoComponent;
    }

    @Override
    public void play() {
        videoComponent.play();
    }

    @Override
    public byte[] getData() {
        return videoComponent.getData();
    }
}
