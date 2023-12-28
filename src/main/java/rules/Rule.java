package rules;

import com.assemblyai.api.resources.transcripts.types.TranscriptWord;
import org.example.audiotranscript.SRTEntity;

public abstract class Rule implements Comparable<Rule>{
    private int priority;

    public Rule(int priority) {
       this.setPriority(priority);
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        if(priority < 0)
            throw new IllegalArgumentException("Priority cannot be negative");
        this.priority = priority;
    }

    @Override
    public int compareTo(Rule otherRule) {
        return Integer.compare(otherRule.priority, this.priority);
    }

    public abstract TranscriptWord applyRule(SRTEntity srtEntity, TranscriptWord word);


}
