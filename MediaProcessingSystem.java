// 播放能力介面
interface Playable {
    void play();
}

// 壓縮能力介面
interface Compressible {
    void compress();
}

// 抽象父類別 MediaFile
abstract class MediaFile {
    private String fileName;

    public MediaFile(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public abstract void showDetails();
}

// 圖片檔案（支援壓縮）
class ImageFile extends MediaFile implements Compressible {
    public ImageFile(String fileName) {
        super(fileName);
    }

    @Override
    public void showDetails() {
        System.out.println("圖片檔案: " + getFileName());
    }

    @Override
    public void compress() {
        System.out.println("[" + getFileName() + "] 執行 JPEG/PNG 圖片壓縮...");
    }
}

// 音訊檔案（支援播放與壓縮）
class AudioFile extends MediaFile implements Playable, Compressible {
    public AudioFile(String fileName) {
        super(fileName);
    }

    @Override
    public void showDetails() {
        System.out.println("音訊檔案: " + getFileName());
    }

    @Override
    public void play() {
        System.out.println("[" + getFileName() + "] 正在播放音訊串流...");
    }

    @Override
    public void compress() {
        System.out.println("[" + getFileName() + "] 執行 MP3 音訊壓縮...");
    }
}

// 影片檔案（支援播放與壓縮）
class VideoFile extends MediaFile implements Playable, Compressible {
    public VideoFile(String fileName) {
        super(fileName);
    }

    @Override
    public void showDetails() {
        System.out.println("影片檔案: " + getFileName());
    }

    @Override
    public void play() {
        System.out.println("[" + getFileName() + "] 正在播放高畫質影片...");
    }

    @Override
    public void compress() {
        System.out.println("[" + getFileName() + "] 執行 H.264 影片編碼壓縮...");
    }
}

public class MediaProcessingSystem {
    public static void main(String[] args) {
        MediaFile[] mediaFiles = new MediaFile[] {
            new ImageFile("vacation.png"),
            new AudioFile("song.mp3"),
            new VideoFile("movie.mp4")
        };

        System.out.println("=== 媒體檔案操作測試 ===");
        for (MediaFile file : mediaFiles) {
            file.showDetails();

            // 判斷並執行 Playable 操作
            if (file instanceof Playable playable) {
                playable.play();
            } else {
                System.out.println("[" + file.getFileName() + "] 不支援播放功能");
            }

            // 判斷並執行 Compressible 操作
            if (file instanceof Compressible compressible) {
                compressible.compress();
            }

            System.out.println("-----------------------------------");
        }
    }
}
