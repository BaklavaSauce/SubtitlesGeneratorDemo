package org.example.audiotranscript;

import com.assemblyai.api.resources.transcripts.types.TranscriptWord;
import rules.LineBreakOnCharacter;
import rules.MaxCharSubtitle;
import rules.Rule;
import rules.SubtitleEndPunctuation;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class SRTEntityGenerator {
    private PriorityQueue<Rule> rules;

    public SRTEntityGenerator() {
        defaultInit();
    }

    private void defaultInit() {
        this.rules = new PriorityQueue<>();
        this.rules.add(new MaxCharSubtitle(3, 84));
        this.rules.add(new SubtitleEndPunctuation(4, '.', '?', '!'));
    }

    public List<SRTEntity> generateSubtitleEntities(List<TranscriptWord> words){
        List<SRTEntity> subtitles = new ArrayList<>();
        int line = 1;
        SRTEntity srtEntity = new SRTEntity(line);
        for(TranscriptWord word : words){
            for(Rule rule : rules)
               word = rule.applyRule(srtEntity, word);
            srtEntity.addWord(word);
            if(srtEntity.isSubtitleTextCompleted()) {
                subtitles.add(srtEntity);
                line++;
                srtEntity = new SRTEntity(line);
            }
        }
        return subtitles;
    }

}
