package br.edu.ifpb.padroes.atv1.streamflix;

import br.edu.ifpb.padroes.atv1.streamflix.decorators.IVideoComponent;

public class Video implements IVideoComponent {

    private final String id;
    private final String title;
    private final byte[] data;

    public Video(String id, String title, byte[] data) {
        this.id = id;
        this.title = title;
        this.data = data;
    }

    public void play() {
        System.out.println("Playing: " + title);
        // Reproduz o vídeo
    }

    public byte[] getData() {
        return data;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Video{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
