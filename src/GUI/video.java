package GUI;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class video
{

    private MediaPlayer mediaPlayer1;
    private MediaPlayer mediaPlayer2;

    public video()
    {
        System.out.println("video");
        video1get();
        video2get();
        play1();

    }

    public void video1get()
    {
        List<String> musicFiles = new ArrayList<>();
        AtomicInteger currentSongIndex = new AtomicInteger();
        // 添加音乐文件路径到列表
        musicFiles.add("path_to_your_music_file_A.mp3"); // 替换为音乐文件A的路径
        musicFiles.add("path_to_your_music_file_B.mp3"); // 替换为音乐文件B的路径
        musicFiles.add("path_to_your_music_file_C.mp3"); // 替换为音乐文件C的路径

        // 创建音乐播放器
        Media sound = new Media(new File(musicFiles.get(currentSongIndex.get())).toURI().toString());
        mediaPlayer1 = new MediaPlayer(sound);

        // 设置循环播放
        mediaPlayer1.setOnEndOfMedia(() -> {
            currentSongIndex.set((currentSongIndex.get() + 1) % musicFiles.size());
            mediaPlayer1.stop();
            mediaPlayer1 = new MediaPlayer(new Media(new File(musicFiles.get(currentSongIndex.get())).toURI().toString()));
            mediaPlayer1.play();
        });
    }

    public void video2get()
    {
        List<String> musicFiles = new ArrayList<>();
        AtomicInteger currentSongIndex = new AtomicInteger();
        // 添加音乐文件路径到列表
        musicFiles.add("path_to_your_music_file_A.mp3"); // 替换为音乐文件A的路径
        musicFiles.add("path_to_your_music_file_B.mp3"); // 替换为音乐文件B的路径
        musicFiles.add("path_to_your_music_file_C.mp3"); // 替换为音乐文件C的路径

        // 创建音乐播放器
        Media sound = new Media(new File(musicFiles.get(currentSongIndex.get())).toURI().toString());
        mediaPlayer2 = new MediaPlayer(sound);

        // 设置循环播放
        mediaPlayer2.setOnEndOfMedia(() -> {
            currentSongIndex.set((currentSongIndex.get() + 1) % musicFiles.size());
            mediaPlayer2.stop();
            mediaPlayer2 = new MediaPlayer(new Media(new File(musicFiles.get(currentSongIndex.get())).toURI().toString()));
            mediaPlayer2.play();
        });
    }

    public void play1()
    {
        mediaPlayer1.play();
        mediaPlayer2.stop();
    }

    public void play2()
    {
        mediaPlayer2.play();
        mediaPlayer1.stop();
    }
}
