package rules;

import com.assemblyai.api.resources.transcripts.types.TranscriptWord;
import org.example.audiotranscript.SRTEntity;

import java.util.ArrayList;
import java.util.List;

public class SubtitleEndPunctuation extends Rule{
    private List<Character> punctuation;
    public SubtitleEndPunctuation(int priority, char...punctuation) {
        super(priority);
        this.setPunctuation(punctuation);
    }

    private void setPunctuation(char[] punctuation) {
        this.punctuation = new ArrayList<>();
        for (char c : punctuation)
            this.punctuation.add(c);
    }


    @Override
    public TranscriptWord applyRule(SRTEntity srtEntity, TranscriptWord word) {
        char lastChar = word.getText().charAt(word.getText().length() - 1);
        for(Character c : punctuation){
            if(c == lastChar){
                if(srtEntity.getStartTime() == -1)
                    srtEntity.setStartTime(word.getStart());
                srtEntity.setEndTime(word.getEnd());
                return word;
            }
        }
        return word;
    }
}
