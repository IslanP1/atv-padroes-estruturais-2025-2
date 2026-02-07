package br.edu.ifpb.padroes.atv1.streamflix.decorators;

public class EncryptionDecorator extends VideoDecorator {

    public EncryptionDecorator(IVideoComponent videoComponent) {
        super(videoComponent);
    }

    @Override
    public void play() {
        super.play();
        encryptVideo();
    }

    private void encryptVideo() {
        System.out.println("Encrypting video stream...");
    }
}
