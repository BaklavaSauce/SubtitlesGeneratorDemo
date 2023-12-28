package org.example.audiotranscript;

import com.assemblyai.api.resources.transcripts.types.TranscriptWord;
import utils.TimeUtils;

public class SRTEntity {
    private int lineNumber;
    private int startTime;
    private int endTime;
    private StringBuilder text;

    public int getLineNumber() {
        return lineNumber;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public String getText() {
        return text.toString();
    }

    public SRTEntity(int lineNumber) {
        this.lineNumber = lineNumber;
        this.text = new StringBuilder();
        this.startTime = -1;
        this.endTime = -1;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public void addWord(TranscriptWord word){
        this.text.append(word.getText() + " ");
    }

    public boolean isSubtitleTextCompleted(){
       return this.getEndTime() != -1 && this.getStartTime() != -1;
    }

    @Override
    public String toString() {
        return String.format("%s%n%s --> %s%n%s%n", this.lineNumber,
                TimeUtils.getFormattedDuration(this.startTime),
                TimeUtils.getFormattedDuration(this.endTime),
                this.getText());
    }
}
