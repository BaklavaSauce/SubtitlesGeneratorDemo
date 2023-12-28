package rules;

import com.assemblyai.api.resources.transcripts.types.TranscriptWord;
import org.example.audiotranscript.SRTEntity;

public class MaxCharSubtitle extends Rule{
    private static final int DEFAULT_VALUE_MAX_CHARS = 84;
    private int numberOfCharsAllowed;
    public MaxCharSubtitle(int priority, int numberOfCharsAllowed) {
        super(priority);
        this.setNumberOfCharsAllowed(numberOfCharsAllowed);
    }

    public int getNumberOfCharsAllowed() {
        return numberOfCharsAllowed;
    }

    public void setNumberOfCharsAllowed(int numberOfCharsAllowed) {
        if(numberOfCharsAllowed <= 0 || numberOfCharsAllowed > DEFAULT_VALUE_MAX_CHARS)
            this.numberOfCharsAllowed = DEFAULT_VALUE_MAX_CHARS;
        else
            this.numberOfCharsAllowed = numberOfCharsAllowed;
    }

    @Override
    public TranscriptWord applyRule(SRTEntity srtEntity, TranscriptWord word) {
        if(srtEntity.getText().length() + word.getText().length() + 1 >= this.numberOfCharsAllowed)
            srtEntity.setEndTime(word.getEnd());
        if(srtEntity.getStartTime() == -1)
            srtEntity.setStartTime(word.getStart());
        return word;
    }
}
