package org.example.audiotranscript;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.assemblyai.api.AssemblyAI;
import com.assemblyai.api.resources.transcripts.types.Transcript;


// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        String pathToFile = "src/main/resources/subtitles.srt";
        String audio_file = "src/main/resources/Job Interview_ I Want to Learn (ESL).mp3";
        AssemblyAI aai = AssemblyAI.builder()
                .apiKey("API_KEY")
                .build();
        Transcript transcript = aai.transcripts().transcribe(new File(
                audio_file));
        SRTEntityGenerator srtEntityGenerator = new SRTEntityGenerator();

        if (transcript.getWords().isPresent()) {
            List<SRTEntity> subtitles = srtEntityGenerator.generateSubtitleEntities(transcript.getWords().get());
            writeSubtitlesToFile(subtitles, pathToFile);
        }

        /*URI uri = new URI("https://api.assemblyai.com/v2/transcript");
        Transcript transcript = new Transcript();
        transcript.setAudio_url(audio_url);

        Gson gson = new Gson();
        String jsonRequest = gson.toJson(transcript);
        System.out.println(jsonRequest);

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest postRequest = HttpRequest.newBuilder(uri)
                .POST(HttpRequest.BodyPublishers.ofString(jsonRequest))
                .headers("Authorization", "API_KEY")
                .build();

        HttpResponse<String> postResponse = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
        transcript = gson.fromJson(postResponse.body(), Transcript.class);
        HttpRequest getRequest = HttpRequest.newBuilder(new URI(uri + "/" + transcript.getId()))
                .headers("Authorization", "API_KEY")
                .build();
        do{
            HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
            transcript = gson.fromJson(getResponse.body(), Transcript.class);
            getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println(transcript.getStatus());
        }while(transcript.getStatus().equals("queued") || transcript.getStatus().equals("processing"));

        System.out.println(transcript.getText());*/

    }

    private static void writeSubtitlesToFile(List<SRTEntity> subtitles, String filePath) {
        Path path = Paths.get(filePath);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (SRTEntity subtitleEntity : subtitles) {
                writer.write(subtitleEntity.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}